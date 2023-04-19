package de.storchp.fdroidbuildstatus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.TwoStatePreference;

import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        var actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        private TwoStatePreference updateCheckPref;

        private final ActivityResultLauncher<String> requestNotificationPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (!isGranted) {
                        updateCheckPref.setChecked(false);
                        Toast.makeText(requireContext(), R.string.notification_permission_needed, Toast.LENGTH_SHORT).show();
                    }
                });

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                updateCheckPref = findPreference(getString(R.string.PREF_UPDATE_CHECK));

                assert updateCheckPref != null;
                updateCheckPref.setOnPreferenceChangeListener((preference, newValue) -> {
                    if (Boolean.TRUE.equals(newValue) && isNotificationPermissionDenied()) {
                        requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                    }
                    return true;
                });
                if (isNotificationPermissionDenied()) {
                    updateCheckPref.setChecked(false);
                }
            }

            var nightModePref = findPreference(getString(R.string.night_mode_key));
            assert nightModePref != null;
            nightModePref.setOnPreferenceChangeListener(((preference, newValue) -> {
                requireActivity().runOnUiThread(() -> PreferenceUtils.applyNightMode(Integer.parseInt((String) newValue)));
                return true;
            }));
        }

        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        private boolean isNotificationPermissionDenied() {
            return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED;
        }

    }

}