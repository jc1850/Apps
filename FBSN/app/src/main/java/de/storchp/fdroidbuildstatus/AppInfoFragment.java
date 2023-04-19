package de.storchp.fdroidbuildstatus;

import android.app.Dialog;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

import de.storchp.fdroidbuildstatus.databinding.ActivityAboutBinding;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.model.BuildRun;
import de.storchp.fdroidbuildstatus.utils.FormatUtils;

public class AppInfoFragment extends DialogFragment {

    private ActivityAboutBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        var fragmentActivity = requireActivity();
        var builder = new AlertDialog.Builder(fragmentActivity);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_info_title)
                .setPositiveButton(android.R.string.ok, null);

        binding = ActivityAboutBinding.inflate(getLayoutInflater(), null, false);

        binding.aboutVersionText.setText(String.format("%s (%s)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        binding.aboutInfo.setMovementMethod(LinkMovementMethod.getInstance());

        var buildRuns = ((BaseApplication) fragmentActivity.getApplication()).getDbAdapter().loadBuildRuns();
        binding.aboutLastUpdateBuildText.setText(getLastUpdate(buildRuns, BuildCycle.BUILD));
        binding.aboutLastUpdateRunningText.setText(getLastUpdate(buildRuns, BuildCycle.RUNNING));

        builder.setView(binding.getRoot());

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @NotNull
    private String getLastUpdate(Map<BuildCycle, BuildRun> buildRuns, BuildCycle runType) {
        return buildRuns.containsKey(runType) ? FormatUtils.formatShortDateTime(Objects.requireNonNull(buildRuns.get(runType)).getLastUpdated().getTime()) : "";
    }

}
