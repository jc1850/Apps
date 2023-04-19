package de.storchp.fdroidbuildstatus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import de.storchp.fdroidbuildstatus.adapter.db.DbAdapter;
import de.storchp.fdroidbuildstatus.databinding.ItemMainAppBinding;
import de.storchp.fdroidbuildstatus.databinding.ItemMainAppbuildBinding;
import de.storchp.fdroidbuildstatus.model.App;
import de.storchp.fdroidbuildstatus.model.AppBuild;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class MainAppListAdapter extends BaseAdapter implements Filterable {

    private final Activity context;
    private final Object lock = new Object();
    private final List<App> allAppList;
    private final DbAdapter dbAdapter;
    private List<App> appList;
    private ArrayFilter filter;

    public MainAppListAdapter(MainActivity context, DbAdapter dbAdapter, BuildCycle buildCycle) {
        super();
        this.allAppList = dbAdapter.loadAppBuilds(buildCycle,
                PreferenceUtils.isShowFavouritesWithoutBuildStatus(context),
                PreferenceUtils.getStatusFilter(context));
        this.appList = new ArrayList<>(allAppList);
        this.context = context;
        this.dbAdapter = dbAdapter;
        sort();
    }

    public App getItemForPosition(int position) {
        if (appList == null || position == 0) {
            return null;
        }

        return appList.get(position - 1);
    }

    public int getCount() {
        return appList != null ? appList.size() : 0;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        ItemMainAppBinding binding;
        var inflater = context.getLayoutInflater();
        if (rowView == null) {
            binding = ItemMainAppBinding.inflate(inflater, parent, false);
            rowView = binding.getRoot();
            rowView.setTag(binding);
        } else {
            binding = (ItemMainAppBinding) rowView.getTag();
        }

        var app = appList.get(position);
        binding.appName.setText(app.getName());
        binding.appId.setText(app.getId());
        DrawableUtils.setIconWithTint(context, binding.disabledIcon, R.drawable.ic_disabled_24dp, binding.appName.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, binding.archivedIcon, R.drawable.ic_archive_24, binding.appName.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, binding.noPackagesIcon, R.drawable.ic_no_packages_24, binding.appName.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, binding.noUpdateCheckIcon, R.drawable.ic_no_update_check_24, binding.appName.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, binding.needsUpdateIcon, R.drawable.ic_upgrade_black_24dp, binding.appName.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, binding.favouriteIcon, app.getFavouriteIcon(), binding.appName.getCurrentTextColor());
        binding.disabledIcon.setVisibility(app.getDisabled() ? View.VISIBLE : View.GONE);
        binding.archivedIcon.setVisibility(app.getArchived() ? View.VISIBLE : View.GONE);
        binding.noPackagesIcon.setVisibility(app.getNoPackages() ? View.VISIBLE : View.GONE);
        binding.noUpdateCheckIcon.setVisibility(app.getNoUpdateCheck() ? View.VISIBLE : View.GONE);

        binding.needsUpdateIcon.setVisibility(app.getNeedsUpdate() ? View.VISIBLE : View.GONE);

        binding.appBuilds.removeAllViews();
        app.getAppBuildsByVersionCodeAndStatus()
                .forEach((key, builds) -> addAppBuild(binding, inflater, builds));

        return rowView;
    }

    private void addAppBuild(ItemMainAppBinding binding, LayoutInflater inflater, List<AppBuild> builds) {
        @NonNull var bindingBuild = ItemMainAppbuildBinding.inflate(inflater, binding.appBuilds, false);
        AppBuild appBuild = builds.get(0);
        bindingBuild.version.setText(appBuild.getFullVersion());
        setBuildTypIcon(bindingBuild.buildRunTypeRunning, builds, BuildCycle.RUNNING, bindingBuild.version.getCurrentTextColor());
        setBuildTypIcon(bindingBuild.buildRunTypeFinished, builds, BuildCycle.BUILD, bindingBuild.version.getCurrentTextColor());
        setBuildTypIcon(bindingBuild.buildRunTypeUpdate, builds, BuildCycle.UPDATE, bindingBuild.version.getCurrentTextColor());
        DrawableUtils.setIconWithTint(context, bindingBuild.statusIcon, appBuild.getStatus().getIconRes(), context.getColor(appBuild.getStatus().getIconColorRes(bindingBuild.version.getCurrentTextColor())));
        binding.appBuilds.addView(bindingBuild.getRoot());
    }

    private void setBuildTypIcon(ImageView bindingBuild, List<AppBuild> builds, BuildCycle buildCycle, int color) {
        bindingBuild.setVisibility(builds.stream().anyMatch(a -> a.getBuildCycle() == buildCycle) ? View.VISIBLE : View.GONE);
        DrawableUtils.setIconWithTint(context, bindingBuild, buildCycle.getIconRes(), color);
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new ArrayFilter();
        }
        return filter;
    }

    /**
     * An array filters constrains the content of the array adapter with a prefix. Each
     * item that does not start with the supplied prefix is removed from the list.
     */
    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence search) {
            var results = new FilterResults();

            if (search == null || search.length() == 0) {
                synchronized (lock) {
                    results.values = new ArrayList<>(allAppList);
                    results.count = allAppList.size();
                }
            } else {
                var searchString = search.toString().toLowerCase(Locale.getDefault());
                var newValues = new ArrayList<>(allAppList.size());

                allAppList.stream()
                        .filter(app -> {
                            if ((app.getName() != null && app.getName().toLowerCase(Locale.getDefault()).contains(searchString)))
                                return true;
                            app.getId();
                            return app.getId().toLowerCase(Locale.getDefault()).contains(searchString);
                        })
                        .forEach(newValues::add);

                if (newValues.isEmpty()) {
                    var apps = dbAdapter.findApps(search.toString());
                    if (apps.isEmpty()) {
                        newValues.add(new App(search.toString(), context.getString(R.string.not_found_build_item_name)));
                    } else {
                        newValues.addAll(apps);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            appList = (List<App>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        sort();
        super.notifyDataSetChanged();
    }

    /**
     * Sort favourites over running build over failed build
     */
    private void sort() {
        appList.sort(Comparator.comparing(App::getFavourite).thenComparing(app -> app.getAppBuilds().size()).reversed().thenComparing(App::getId));
    }

}