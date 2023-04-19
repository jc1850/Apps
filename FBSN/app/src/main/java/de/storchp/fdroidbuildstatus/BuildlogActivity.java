package de.storchp.fdroidbuildstatus;

import static de.storchp.fdroidbuildstatus.utils.DrawableUtils.setMenuIconTint;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import de.storchp.fdroidbuildstatus.databinding.ActivityBuildlogBinding;
import de.storchp.fdroidbuildstatus.model.BuildlogType;
import de.storchp.fdroidbuildstatus.utils.LoglinesBuffer;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class BuildlogActivity extends AppCompatActivity {

    private static final String TAG = BuildlogActivity.class.getSimpleName();

    private ActivityBuildlogBinding binding;
    private BaseApplication baseApplication;
    private String id;
    private String versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuildlogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        baseApplication = (BaseApplication) getApplication();
        var actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getSupportFragmentManager().setFragmentResultListener(BuildlogTypeFragment.BUILDLOG_TYPE_REQUEST, this, (requestKey, bundle) -> {
            var buildlogType = BuildlogType.valueOf(bundle.getString(BuildlogTypeFragment.BUILDLOG_TYPE_RESULT));
            if (buildlogType.getExtension() != null) {
                onBuildlogDownload(buildlogType);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        var intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra(MainActivity.EXTRA_BUILD_ITEM_ID);
            versionCode = intent.getStringExtra(MainActivity.EXTRA_BUILD_ITEM_VERSION_CODE);
            setTitle(getString(R.string.buildlog_title, versionCode));
            fetchBuildLog(id, versionCode, new UILogConsumer());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buildlog, menu);
        setMenuIconTint(this, menu, R.id.action_download_buildlog, R.color.design_default_color_on_primary);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_download_buildlog) {
            onBuildlogDownload(PreferenceUtils.getBuildlogType(this));
            return true;
        } else if (itemId == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<String> downloadLogResultLauncher = registerForActivityResult(
            new ActivityResultContracts.CreateDocument("text/plain"),
            result -> fetchBuildLog(id, versionCode, (id, versionCode, reader) -> {
                try (var parcelableFileDescriptor = getContentResolver().openFileDescriptor(result, "w");
                     var writer = new BufferedWriter(new FileWriter(parcelableFileDescriptor.getFileDescriptor()))) {
                    reader.lines().forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Error downloading logfile to Document URI", e);
                    Toast.makeText(BuildlogActivity.this, getResources().getString(R.string.loading_build_log_failed, e.getMessage()), Toast.LENGTH_LONG).show();
                }
            }));


    private void onBuildlogDownload(BuildlogType buildlogType) {
        switch (buildlogType) {
            case GZ:
                startActivity(new Intent(Intent.ACTION_VIEW, baseApplication.getFdroidClient().logUri(id, versionCode)));
                break;
            case LOG:
                downloadLogResultLauncher.launch(String.format("%s_%s.log", id, versionCode));
                break;
            default:
                BuildlogTypeFragment buildlogTypeFragment = new BuildlogTypeFragment();
                buildlogTypeFragment.show(getSupportFragmentManager(), BuildlogTypeFragment.BUILDLOG_TYPE_REQUEST);
        }
    }

    private interface LogConsumer {
        void consume(String id, String versionCode, BufferedReader reader) throws IOException;
    }

    private class UILogConsumer implements LogConsumer {

        @Override
        public void consume(String id, String versionCode, BufferedReader reader) {
            var buffer = new LoglinesBuffer(PreferenceUtils.getMaxLogLines(BuildlogActivity.this));
            reader.lines().forEach(buffer::add);
            var log = buffer.toString();
            setBuildLog(log, id, versionCode, new UILogConsumer());
        }
    }

    private void fetchBuildLog(String id, String versionCode, LogConsumer consumer) {
        startProgress();
        baseApplication.getFdroidClient().getBuildLog(id, versionCode, response -> {
            stopProgress();
            if (response.isSuccess()) {
                try {
                    consumer.consume(id, versionCode, new BufferedReader(response.value()));
                } catch (Exception e) {
                    Log.d(TAG, "reading logfile failed", e);
                    Toast.makeText(BuildlogActivity.this, getResources().getString(R.string.loading_build_log_failed, e.getMessage()), Toast.LENGTH_LONG).show();
                }
            } else if (response.status() == ApiResponse.Status.NOT_FOUND) {
                Toast.makeText(BuildlogActivity.this, getResources().getString(R.string.build_log_not_found), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BuildlogActivity.this, getResources().getString(R.string.loading_build_log_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setBuildLog(String log, String id, String versionCode, LogConsumer logConsumer) {
        Log.d(TAG, "Log.size: " + log.length());
        var lines = log.split("\n");
        binding.buildLog.setAdapter(new ArrayAdapter<>(this, R.layout.item_logline, lines));
        if (lines.length == 1 && id != null && versionCode != null) {
            // we might have an incomplete build log with only 1 line, fetch again
            fetchBuildLog(id, versionCode, logConsumer);
        }
    }

    private void startProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void stopProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

}
