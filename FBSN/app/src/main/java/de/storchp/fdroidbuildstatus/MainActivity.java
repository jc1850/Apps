package de.storchp.fdroidbuildstatus;

import static de.storchp.fdroidbuildstatus.utils.DrawableUtils.setMenuIconTint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.chip.Chip;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import de.storchp.fdroidbuildstatus.adapter.fdroid.BuildResult;
import de.storchp.fdroidbuildstatus.adapter.fdroid.UpdateResult;
import de.storchp.fdroidbuildstatus.databinding.ActivityMainBinding;
import de.storchp.fdroidbuildstatus.databinding.MainListHeaderBinding;
import de.storchp.fdroidbuildstatus.model.App;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.model.BuildRun;
import de.storchp.fdroidbuildstatus.model.StatusFilter;
import de.storchp.fdroidbuildstatus.monitor.MonitorJobService;
import de.storchp.fdroidbuildstatus.utils.Constants;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;
import de.storchp.fdroidbuildstatus.utils.FormatUtils;
import de.storchp.fdroidbuildstatus.utils.NotificationUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_BUILD_ITEM_ID = "EXTRA_BUILD_ITEM_ID";
    public static final String EXTRA_BUILD_ITEM_VERSION_CODE = "EXTRA_BUILD_ITEM_VERSION_CODE";

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String F_DROID_ORG_PACKAGES = "://f-droid.org/packages/";
    private static final String LIST_INSTANCE_STATE = "LIST_INSTANCE_STATE";

    private ActivityMainBinding binding;
    private MainListHeaderBinding headerBinding;
    private MainAppListAdapter adapter;
    private BaseApplication baseApplication;

    private final AtomicInteger runningFetches = new AtomicInteger(0);

    private String lastSearch;
    private Parcelable listInstanceState;

    final ActivityResultLauncher<Intent> detailActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        headerBinding = MainListHeaderBinding.inflate((getLayoutInflater()));
        binding.buildList.addHeaderView(headerBinding.getRoot());

        baseApplication = (BaseApplication) getApplication();

        binding.buildList.setOnItemClickListener((parent, view, position, id) -> {
            var app = adapter.getItemForPosition(position);
            if (app != null) {
                var detailIntent = new Intent(this, DetailActivity.class);
                detailIntent.putExtra(EXTRA_BUILD_ITEM_ID, app.getId());
                detailActivityResultLauncher.launch(detailIntent);
            }
        });

        binding.buildList.setOnItemLongClickListener((parent, view, position, id) -> {
            var app = adapter.getItemForPosition(position);
            if (app != null) {
                if (getString(R.string.not_found_build_item_name).equals(app.getName())) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle(R.string.app_name)
                            .setMessage(getApplicationContext().getString(R.string.add_not_found_app_as_favourite, app.getId()))
                            .setNeutralButton(android.R.string.ok, (dialog, which) -> toggleFavourite(app)).create().show();
                } else {
                    toggleFavourite(app);
                }
            }
            return true;
        });

        NotificationUtils.cancelNotification(this);

        if (getIntent() != null) {
            onNewIntent(getIntent());
        }

        if (!PreferenceUtils.isRepoIndexLoaded(this)) {
            loadIndex();
        }

        if (savedInstanceState != null) {
            listInstanceState = savedInstanceState.getParcelable(LIST_INSTANCE_STATE);
        }

        binding.chipCycle.setOnClickListener(this::showBuildCycleMenu);
        binding.chipStatus.setOnClickListener(this::showBuildStatusMenu);
        binding.chipFavourite.setCheckedIcon(DrawableUtils.getTintedDrawable(this, R.drawable.ic_favourite_24px, binding.chipStatus.getCurrentTextColor()));

        binding.pullToRefresh.setOnRefreshListener(() -> {
            refreshList();
            binding.pullToRefresh.setRefreshing(false);
        });
    }

    private void showBuildCycleMenu(View v) {
        var popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.menu_buildcycle, popup.getMenu());

        popup.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.build_cycle_running) {
                PreferenceUtils.setBuildCycleFilter(this, BuildCycle.RUNNING);
            } else if (menuItem.getItemId() == R.id.build_cycle_build) {
                PreferenceUtils.setBuildCycleFilter(this, BuildCycle.BUILD);
            } else if (menuItem.getItemId() == R.id.build_cycle_update) {
                PreferenceUtils.setBuildCycleFilter(this, BuildCycle.UPDATE);
            } else {
                PreferenceUtils.setBuildCycleFilter(this, BuildCycle.NONE);
            }
            setChipCycle();
            refreshList();
            return false;
        });

        setPopupMenuIcons(popup);
        popup.setOnDismissListener(menu -> setCloseIcon(binding.chipCycle, R.drawable.ic_baseline_arrow_drop_up_24));
        popup.show();
        setCloseIcon(binding.chipCycle, R.drawable.ic_baseline_arrow_drop_down_24);
    }

    private void setCloseIcon(final Chip chip, final int icon) {
        chip.setCloseIcon(AppCompatResources.getDrawable(this, icon));
    }

    private void showBuildStatusMenu(View v) {
        var popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.menu_buildstatus, popup.getMenu());

        popup.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.build_status_builds) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.SUCCESSFUL_BUILD);
            } else if (menuItem.getItemId() == R.id.build_status_failed) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.FAILED_BUILD);
            } else if (menuItem.getItemId() == R.id.build_status_missing) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.MISSING_BUILD);
            } else if (menuItem.getItemId() == R.id.status_disabled) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.DISABLED);
            } else if (menuItem.getItemId() == R.id.status_archived) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.ARCHIVED);
            } else if (menuItem.getItemId() == R.id.status_needs_update) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.NEEDS_UPDATE);
            } else if (menuItem.getItemId() == R.id.status_no_packages) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.NO_PACKAGES);
            } else if (menuItem.getItemId() == R.id.status_no_update_check) {
                PreferenceUtils.setStatusFilter(this, StatusFilter.NO_UPDATE_CHECK);
            } else {
                PreferenceUtils.setStatusFilter(this, StatusFilter.NONE);
            }
            setChipStatus();
            refreshList();
            return false;
        });

        setPopupMenuIcons(popup);
        popup.setOnDismissListener(menu -> setCloseIcon(binding.chipStatus, R.drawable.ic_baseline_arrow_drop_up_24));
        popup.show();
        setCloseIcon(binding.chipStatus, R.drawable.ic_baseline_arrow_drop_down_24);
    }

    @SuppressLint("RestrictedApi")
    private void setPopupMenuIcons(final PopupMenu popup) {
        try {
            if (popup.getMenu() instanceof MenuBuilder) {
                var menuBuilder = (MenuBuilder) popup.getMenu();
                menuBuilder.setOptionalIconsVisible(true);
                for (var item : menuBuilder.getVisibleItems()) {
                    var iconMarginPx =
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics());
                    if (item.getIcon() != null) {
                        InsetDrawable icon;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            icon = new InsetDrawable(item.getIcon(), iconMarginPx, 0, iconMarginPx, 0);
                        } else {
                            icon = new InsetDrawable(item.getIcon(), iconMarginPx, 0, iconMarginPx, 0) {
                                @Override
                                public int getIntrinsicWidth() {
                                    return (int) (getIntrinsicHeight() + iconMarginPx + iconMarginPx);
                                }
                            };
                        }
                        icon.setTint(getResources().getColor(R.color.colorSurface, null));
                        item.setIcon(icon);
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "Error setting popupMenuIcons: ", e);
        }
    }

    private void loadIndex() {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage(R.string.load_index_question)
                .setNegativeButton(R.string.not_now, ((dialog, which) -> PreferenceUtils.setRepoIndexLoaded(MainActivity.this)))
                .setNeutralButton(R.string.download, (dialog, which) -> fetchIndex())
                .create()
                .show();
    }

    private void fetchIndex() {
        startProgress();
        baseApplication.getFdroidClient().getIndex(response -> {
            stopProgress();
            if (response.status() == ApiResponse.Status.SUCCESS) {
                var index = response.value();
                baseApplication.getDbAdapter().updateApps(index.getApps());
                PreferenceUtils.setRepoIndexLoaded(MainActivity.this);
                refreshList();
                Toast.makeText(getBaseContext(), getString(R.string.load_index_success, index.getApps().size()), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.load_index_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setChipCycle();
        setChipStatus();

        boolean checked = PreferenceUtils.isShowFavouritesWithoutBuildStatus(this);
        binding.chipFavourite.setChecked(checked);
        binding.chipFavourite.setOnCheckedChangeListener((buttonView, isChecked) -> toggleFavouriteFilter());
        binding.chipFavourite.setTextColor(getChipForegroundColor(checked));
        binding.chipFavourite.setChipStrokeColorResource(checked ? R.color.colorPrimary : R.color.colorForeground);

        refreshList();
        if (listInstanceState != null) {
            binding.buildList.onRestoreInstanceState(listInstanceState);
        }
    }

    private int getChipForegroundColor(final boolean active) {
        return getColor(getChipForegroundColorRes(active));
    }

    private int getChipForegroundColorRes(final boolean active) {
        return active ? R.color.colorOnPrimary : R.color.colorForeground;
    }

    private void setChipStatus() {
        var statusFilter = PreferenceUtils.getStatusFilter(this);
        setChipColorWithIcon(binding.chipStatus, statusFilter.getIconRes(), statusFilter.isActive());
        binding.chipStatus.setText(statusFilter.getTextRes());
    }

    private void setChipColorWithIcon(Chip binding, int iconRes, boolean active) {
        if (iconRes != 0) {
            binding.setChipIcon(DrawableUtils.getTintedDrawable(this, iconRes, getChipForegroundColor(active)));
        } else {
            binding.setChipIcon(null);
        }
        binding.setChipBackgroundColorResource(active ? R.color.colorPrimary : R.color.fullTransparent);
        binding.setTextColor(getChipForegroundColor(active));
        binding.setCloseIconTintResource(getChipForegroundColorRes(active));
        binding.setChipStrokeColorResource(active ? R.color.colorPrimary : R.color.colorForeground);
    }

    private void setChipCycle() {
        var buildCycleFilter = PreferenceUtils.getBuildCycleFilter(this);
        var cycleText = "";
        switch (buildCycleFilter) {
            case RUNNING:
                cycleText = getString(R.string.bottom_bar_running);
                setChipColorWithIcon(binding.chipCycle, R.drawable.ic_directions_run_24px, true);
                break;
            case BUILD:
                cycleText = getString(R.string.bottom_bar_build);
                setChipColorWithIcon(binding.chipCycle, R.drawable.ic_build_24, true);
                break;
            case UPDATE:
                cycleText = getString(R.string.bottom_bar_update);
                setChipColorWithIcon(binding.chipCycle, R.drawable.ic_upgrade_black_24dp, true);
                break;
            default:
                cycleText = getString(R.string.build_cycle);
                setChipColorWithIcon(binding.chipCycle, 0, false);
        }
        binding.chipCycle.setText(cycleText);
    }

    public void toggleFavouriteFilter() {
        var active = !PreferenceUtils.isShowFavouritesWithoutBuildStatus(this);
        PreferenceUtils.setShowFavouritesWithoutBuildStatus(this, active);
        binding.chipFavourite.setTextColor(getChipForegroundColor(active));
        binding.chipFavourite.setChipStrokeColorResource(active ? R.color.colorPrimary : R.color.colorForeground);
        refreshList();
    }

    private void toggleFavourite(App item) {
        item.setFavourite(!item.getFavourite());
        baseApplication.getDbAdapter().toggleFavourite(item.getId());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            lastSearch = intent.getStringExtra(SearchManager.QUERY);
        } else if (Intent.ACTION_SEND.equals(intent.getAction())) {
            var text = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (text != null) {
                int pos = text.indexOf(F_DROID_ORG_PACKAGES);
                if (pos > 0) {
                    lastSearch = text.substring(pos + F_DROID_ORG_PACKAGES.length());
                }
            }
        } else if (intent.getData() != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
            var data = intent.getData();
            var path = data.getPath();
            String packageName = null;
            if (data.isHierarchical()) {
                var host = data.getHost();
                if (host == null) {
                    return;
                }
                switch (host) {
                    case "f-droid.org":
                    case "www.f-droid.org":
                    case "staging.f-droid.org":
                        if (path.startsWith("/app/") || path.startsWith("/packages/")
                                || path.matches("^/[a-z][a-z][a-zA-Z_-]*/packages/.*")) {
                            // http://f-droid.org/app/packageName
                            packageName = data.getLastPathSegment();
                        }
                        break;
                    case "details":
                        // market://details?id=app.id
                        packageName = data.getQueryParameter("id");
                        break;
                }
            } else if ("fdroid.app".equals(data.getScheme())) {
                // fdroid.app:app.id
                packageName = data.getSchemeSpecificPart();
            }

            if (!TextUtils.isEmpty(packageName)) {
                Log.d(TAG, "launched via app link for '" + packageName + "'");
                lastSearch = packageName;
            }
        }
    }

    private void loadData(boolean forceUpdate) {
        var buildCycle = PreferenceUtils.getBuildCycleFilter(this);
        var buildRuns = baseApplication.getDbAdapter().loadBuildRuns();
        reloadList(buildCycle, buildRuns);

        if (!forceUpdate && (System.currentTimeMillis() - baseApplication.getLastUpdate()) < Constants.TIME.ONE_HOUR) {
            return;
        }

        startProgress();
        baseApplication.getFdroidClient().getUpdate(response -> {
            var result = response.value();
            if (response.status() == ApiResponse.Status.SUCCESS) {
                baseApplication.getDbAdapter().saveUpdate(result, false);
                reloadList(PreferenceUtils.getBuildCycleFilter(MainActivity.this), null);
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.update_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
            stopProgress();
        });

        startProgress();
        baseApplication.getFdroidClient().getBuild(response -> {
            var buildRun = response.value();
            if (response.status() == ApiResponse.Status.SUCCESS) {
                baseApplication.getDbAdapter().saveBuildRun(buildRun, BuildCycle.BUILD);
                reloadList(buildCycle, null);
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.update_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
            stopProgress();
        });

        startProgress();
        baseApplication.getFdroidClient().getRunning(response -> {
            if (response.status() == ApiResponse.Status.SUCCESS) {
                var result = response.value();
                if (result instanceof BuildResult) {
                    var buildRun = (BuildResult) result;
                    baseApplication.getDbAdapter().saveBuildRun(buildRun, BuildCycle.RUNNING);
                    reloadList(PreferenceUtils.getBuildCycleFilter(MainActivity.this), null);
                } else if (result instanceof UpdateResult) {
                    var updateResult = (UpdateResult) result;
                    baseApplication.getDbAdapter().saveUpdate(updateResult, true);
                    reloadList(PreferenceUtils.getBuildCycleFilter(MainActivity.this), null);
                }
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.update_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
            stopProgress();
        });

        baseApplication.setLastUpdate();
    }

    private void reloadList(BuildCycle buildCycle, Map<BuildCycle, BuildRun> cachedBuildRuns) {
        setBuildStatusText(cachedBuildRuns != null ? cachedBuildRuns : baseApplication.getDbAdapter().loadBuildRuns());

        adapter = new MainAppListAdapter(MainActivity.this, baseApplication.getDbAdapter(), buildCycle);
        binding.buildList.setAdapter(adapter);

        if (!TextUtils.isEmpty(lastSearch)) {
            searchList(lastSearch);
        }
    }

    private void startProgress() {
        runningFetches.incrementAndGet();
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void stopProgress() {
        if (runningFetches.decrementAndGet() == 0) {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void setBuildStatusText(Map<BuildCycle, BuildRun> buildRuns) {
        var buildRunRunning = buildRuns.get(BuildCycle.RUNNING);
        if (buildRunRunning != null) {
            headerBinding.buildStatusHeadRunning.setText(getString(R.string.build_status_head_running_command, buildRunRunning.getSubcommand()));
            headerBinding.buildStatusStartRunning.setText(FormatUtils.formatShortDateTime(buildRunRunning.getStartTimestamp()));
            headerBinding.buildStatusEndRunning.setText(buildRunRunning.getEndTimestamp() > 0 ? FormatUtils.formatShortDateTime(buildRunRunning.getEndTimestamp()) : "");
            headerBinding.buildStatusLastModifiedRunning.setText(FormatUtils.formatShortDateTime(buildRunRunning.getLastModified().getTime()));
            headerBinding.buildStatusBuildsRunning.setText(buildRunRunning.getNumberOfBuildsText());
        } else {
            headerBinding.buildStatusHeadRunning.setText(R.string.build_status_head_running);
            headerBinding.buildStatusStartRunning.setText("");
            headerBinding.buildStatusEndRunning.setText("");
            headerBinding.buildStatusLastModifiedRunning.setText("");
            headerBinding.buildStatusBuildsRunning.setText("");
        }
        var buildRunFinished = buildRuns.get(BuildCycle.BUILD);
        if (buildRunFinished != null) {
            headerBinding.buildStatusHeadBuild.setText(R.string.build_status_head_build_command);
            headerBinding.buildStatusStartBuild.setText(FormatUtils.formatShortDateTime(buildRunFinished.getStartTimestamp()));
            headerBinding.buildStatusEndBuild.setText(FormatUtils.formatShortDateTime(buildRunFinished.getEndTimestamp()));
            headerBinding.buildStatusLastModifiedBuild.setText(FormatUtils.formatShortDateTime(buildRunFinished.getLastModified().getTime()));
            headerBinding.buildStatusBuildsBuild.setText(buildRunFinished.getNumberOfBuildsText());
            if (buildRunFinished.isMaxBuildTimeReached()) {
                DrawableUtils.setCompoundDrawablesRight(this, headerBinding.buildStatusEndBuild,
                        R.drawable.ic_error_outline_24px, headerBinding.buildStatusEnd.getCurrentTextColor());
                headerBinding.buildStatusEndBuild.setCompoundDrawablePadding(5);
            } else {
                headerBinding.buildStatusEndBuild.setCompoundDrawables(null, null, null, null);
            }
        } else {
            headerBinding.buildStatusStartBuild.setText("");
            headerBinding.buildStatusEndBuild.setText("");
            headerBinding.buildStatusEndBuild.setCompoundDrawables(null, null, null, null);
            headerBinding.buildStatusLastModifiedBuild.setText("");
            headerBinding.buildStatusBuildsBuild.setText("");
        }

        var buildRunUpdate = buildRuns.get(BuildCycle.UPDATE);
        if (buildRunUpdate != null) {
            headerBinding.buildStatusStartUpdate.setText(FormatUtils.formatShortDateTime(buildRunUpdate.getStartTimestamp()));
            headerBinding.buildStatusEndUpdate.setText(FormatUtils.formatShortDateTime(buildRunUpdate.getEndTimestamp()));
            headerBinding.buildStatusLastModifiedUpdate.setText(FormatUtils.formatShortDateTime(buildRunUpdate.getLastModified().getTime()));
            headerBinding.buildStatusBuildsUpdate.setText(buildRunUpdate.getNumberOfBuildsText());
        } else {
            headerBinding.buildStatusStartUpdate.setText("");
            headerBinding.buildStatusEndUpdate.setText("");
            headerBinding.buildStatusLastModifiedUpdate.setText("");
            headerBinding.buildStatusBuildsUpdate.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        setMenuIconTint(this, menu, R.id.action_search, R.color.design_default_color_on_primary);
        setMenuIconTint(this, menu, R.id.action_refresh, R.color.design_default_color_on_primary);

        var searchMenu = menu.findItem(R.id.action_search);
        searchMenu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem arg0) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem arg0) {
                // fix #17
                invalidateOptionsMenu();
                return true;
            }
        });

        var searchView = (SearchView) searchMenu.getActionView();
        searchView.setSearchableInfo(((SearchManager) getSystemService(Context.SEARCH_SERVICE)).getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: " + s);
                searchList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
                searchList(s);
                return false;
            }

        });

        return true;
    }

    private void searchList(String search) {
        this.lastSearch = search;
        adapter.getFilter().filter(search);
    }

    ActivityResultLauncher<Intent> settingsActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    refreshList();
                    // update monitoring service after setting changes
                    MonitorJobService.schedule(MainActivity.this);
                }
            });

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            settingsActivityResultLauncher.launch(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_refresh) {
            loadData(true);
            return true;
        } else if (id == R.id.action_website_build_status) {
            var websiteBuildStatusFragment = new WebsiteBuildStatusFragment();
            websiteBuildStatusFragment.show(getSupportFragmentManager(), "WEBSITE_BUILD_STATUS");
            return true;
        } else if (id == R.id.action_legend) {
            var legendFragment = new LegendFragment();
            legendFragment.show(getSupportFragmentManager(), "LEGEND");
            return true;
        } else if (id == R.id.action_about) {
            var appInfoFragment = new AppInfoFragment();
            appInfoFragment.show(getSupportFragmentManager(), "APP_INFO");
            return true;
        } else if (id == R.id.action_load_index) {
            loadIndex();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() {
        loadData(false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LIST_INSTANCE_STATE, binding.buildList.onSaveInstanceState());
    }

    @Override
    protected void onPause() {
        super.onPause();
        listInstanceState = binding.buildList.onSaveInstanceState();
    }

}