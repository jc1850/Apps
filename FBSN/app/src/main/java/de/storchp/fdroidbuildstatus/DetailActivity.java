package de.storchp.fdroidbuildstatus;

import static de.storchp.fdroidbuildstatus.MainActivity.EXTRA_BUILD_ITEM_ID;
import static de.storchp.fdroidbuildstatus.MainActivity.EXTRA_BUILD_ITEM_VERSION_CODE;
import static de.storchp.fdroidbuildstatus.utils.DrawableUtils.setMenuIconTint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import de.storchp.fdroidbuildstatus.adapter.gitlab.Metadata;
import de.storchp.fdroidbuildstatus.databinding.ActivityDetailBinding;
import de.storchp.fdroidbuildstatus.model.App;
import de.storchp.fdroidbuildstatus.model.AppBuild;
import de.storchp.fdroidbuildstatus.model.MetadataLinkType;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;
import de.storchp.fdroidbuildstatus.utils.FormatUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    public static final String BUNDLE_PUBLISHED_VERSION = "PUBLISHED_VERSION";
    public static final String BUNDLE_METADATA_VERSION = "METADATA_VERSION";

    private ActivityDetailBinding binding;
    private App app;
    private BaseApplication baseApplication;
    private final AtomicInteger runningFetches = new AtomicInteger(0);
    private String publishedVersionText;
    private String metadataVersionText;
    private DetailAppBuildListAdapter detailAppBuildListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        baseApplication = (BaseApplication) getApplication();
        var actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        binding.favouriteIcon.setOnClickListener(this::toggleFavourite);

        getSupportFragmentManager().setFragmentResultListener(MetadataLinkTypeFragment.METADATA_LINK_TYPE_REQUEST, this, (requestKey, bundle) -> {
            var metadataLinkType = MetadataLinkType.valueOf(bundle.getString(MetadataLinkTypeFragment.METADATA_LINK_TYPE_RESULT));
            if (metadataLinkType.getMetadataBaseUri() != null) {
                openMetadataLink(metadataLinkType);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(BUNDLE_PUBLISHED_VERSION, publishedVersionText);
        outState.putString(BUNDLE_METADATA_VERSION, metadataVersionText);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        publishedVersionText = savedInstanceState.getString(BUNDLE_PUBLISHED_VERSION);
        metadataVersionText = savedInstanceState.getString(BUNDLE_METADATA_VERSION);
    }

    @Override
    protected void onResume() {
        super.onResume();

        var intent = getIntent();
        if (intent != null) {
            var id = intent.getStringExtra(EXTRA_BUILD_ITEM_ID);
            if (id != null) {
                app = baseApplication.getDbAdapter().loadAppBuilds(id);

                if (app == null) {
                    app = baseApplication.getDbAdapter().loadApp(id);
                    if (app == null) {
                        app = new App(id, getString(R.string.not_found_build_item_name));
                    }
                }
            }

            if (app != null) {
                binding.appName.setText(app.getName());
                binding.appId.setText(app.getId());

                DrawableUtils.setIconWithTint(this, binding.disabledIcon, R.drawable.ic_disabled_24dp, binding.appName.getCurrentTextColor());
                DrawableUtils.setIconWithTint(this, binding.archivedIcon, R.drawable.ic_archive_24, binding.appName.getCurrentTextColor());
                DrawableUtils.setIconWithTint(this, binding.noPackagesIcon, R.drawable.ic_no_packages_24, binding.appName.getCurrentTextColor());
                DrawableUtils.setIconWithTint(this, binding.noUpdateCheckIcon, R.drawable.ic_no_update_check_24, binding.appName.getCurrentTextColor());
                DrawableUtils.setIconWithTint(this, binding.needsUpdateIcon, R.drawable.ic_upgrade_black_24dp, binding.appName.getCurrentTextColor());
                DrawableUtils.setIconWithTint(this, binding.favouriteIcon, app.getFavouriteIcon(), binding.appName.getCurrentTextColor());

                binding.disabledIcon.setVisibility(app.getDisabled() ? View.VISIBLE : View.GONE);
                binding.archivedIcon.setVisibility(app.getArchived() ? View.VISIBLE : View.GONE);
                binding.noPackagesIcon.setVisibility(app.getNoPackages() ? View.VISIBLE : View.GONE);
                binding.noUpdateCheckIcon.setVisibility(app.getNoUpdateCheck() ? View.VISIBLE : View.GONE);
                binding.needsUpdateIcon.setVisibility(app.getNeedsUpdate() ? View.VISIBLE : View.GONE);

                detailAppBuildListAdapter = new DetailAppBuildListAdapter(this, app.getAppBuildsByVersionCodeAndStatus());
                binding.builds.setAdapter(detailAppBuildListAdapter);
                binding.builds.setOnItemClickListener((parent, view, position, itemId) -> onBuildSelected(detailAppBuildListAdapter.getItemForPosition(position)));

                fetchPublishedVersions(app.getId());
                fetchMetadata(app.getId());
            }
        }
    }

    private void fetchMetadata(String id) {
        if (metadataVersionText != null) {
            binding.metadataVersion.setText(metadataVersionText);
            return;
        }
        startProgress();
        baseApplication.getGitlabClient().getFdroidDataMetadata(id, response -> {
            stopProgress();
            if (response.status() == ApiResponse.Status.SUCCESS) {
                var metadata = response.value();
                metadata.getHighestVersion().ifPresent(
                        highestVersion -> {
                            metadataVersionText = getString(R.string.metadata_version, FormatUtils.formatVersion(highestVersion.getVersionCode(), highestVersion.getVersionName()));
                            binding.metadataVersion.setText(metadataVersionText);
                        });

                app.setName(metadata.getAppName());
                binding.appName.setText(app.getName());
                app.setSourceCode(metadata.getSourceCode());

                baseApplication.getDbAdapter().updateApp(id, metadata);

                for (int i = 0; i < detailAppBuildListAdapter.getCount(); i++) {
                    var appBuild = detailAppBuildListAdapter.getItemForPosition(i);
                    if (appBuild.getVersionName() == null) {
                        appBuild.setVersionName(metadata.getBuilds().stream()
                                .filter(build -> build.getVersionCode().equals(appBuild.getVersionCode())).findAny()
                                .map(Metadata.Build::getVersionName).orElse(null));
                    }
                }
                detailAppBuildListAdapter.notifyDataSetChanged();
            } else if (response.status() == ApiResponse.Status.NOT_FOUND) {
                metadataVersionText = getString(R.string.metadata_version_none);
                binding.metadataVersion.setText(metadataVersionText);
            } else {
                Toast.makeText(DetailActivity.this, getResources().getString(R.string.loading_metadata_versions_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onBuildSelected(AppBuild selectedAppBuild) {
        if (selectedAppBuild.getBuildCycle().isUpdatable()) {
            var buildlogIntent = new Intent(this, BuildlogActivity.class);
            buildlogIntent.putExtra(EXTRA_BUILD_ITEM_ID, app.getId());
            buildlogIntent.putExtra(EXTRA_BUILD_ITEM_VERSION_CODE, selectedAppBuild.getVersionCode());
            startActivity(buildlogIntent);
        }
    }

    private void fetchPublishedVersions(String id) {
        if (publishedVersionText != null) {
            binding.publishedVersions.setText(publishedVersionText);
            return;
        }
        startProgress();
        baseApplication.getFdroidClient().getPublishedVersions(id, response -> {
            stopProgress();
            if (response.isSuccess()) {
                var publishedVersions = response.value();
                publishedVersionText = getResources().getString(R.string.published_versions) +
                        publishedVersions.getPackages().stream()
                                .map(version -> "\n\u00B7 " + FormatUtils.formatVersion(version.getVersionCode(), version.getVersionName()))
                                .collect(Collectors.joining());
                binding.publishedVersions.setText(publishedVersionText);
            } else if (response.status() == ApiResponse.Status.NOT_FOUND) {
                publishedVersionText = getString(R.string.published_versions_none);
                binding.publishedVersions.setText(publishedVersionText);
            } else {
                Toast.makeText(DetailActivity.this, getResources().getString(R.string.loading_published_versions_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        setMenuIconTint(this, menu, R.id.action_open, R.color.design_default_color_on_primary);
        setMenuIconTint(this, menu, R.id.action_metadata, R.color.design_default_color_on_primary);
        setMenuIconTint(this, menu, R.id.action_source, R.color.design_default_color_on_primary);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app.getFdroidUri())));
            return true;
        } else if (id == R.id.action_metadata) {
            var metadataLinkType = PreferenceUtils.getMetadataLinkType(this);
            if (metadataLinkType.getMetadataBaseUri() != null) {
                openMetadataLink(metadataLinkType);
            } else {
                var metadataLinkTypeFragment = new MetadataLinkTypeFragment();
                metadataLinkTypeFragment.show(getSupportFragmentManager(), MetadataLinkTypeFragment.METADATA_LINK_TYPE_REQUEST);
            }
            return true;
        } else if (id == R.id.action_source) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app.getSourceCode())));
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Failed to start activity for SourceCode: " + app.getSourceCode());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void openMetadataLink(MetadataLinkType metadataLinkType) {
        var metadataUri = app.getMetadataUri(metadataLinkType);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(metadataUri)));
    }

    public void toggleFavourite(View view) {
        app.setFavourite(!app.getFavourite());
        baseApplication.getDbAdapter().toggleFavourite(app.getId());
        DrawableUtils.setIconWithTint(this, binding.favouriteIcon, app.getFavouriteIcon(), binding.appName.getCurrentTextColor());
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

}
