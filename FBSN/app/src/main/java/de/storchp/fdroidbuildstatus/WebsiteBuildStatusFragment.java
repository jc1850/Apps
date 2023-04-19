package de.storchp.fdroidbuildstatus;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import de.storchp.fdroidbuildstatus.databinding.ActivityWebsiteBuildStatusBinding;
import de.storchp.fdroidbuildstatus.utils.FormatUtils;

public class WebsiteBuildStatusFragment extends DialogFragment {

    private ActivityWebsiteBuildStatusBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        var fragmentActivity = requireActivity();
        var builder = new AlertDialog.Builder(fragmentActivity);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.action_website_build_status)
                .setPositiveButton(android.R.string.ok, null);

        binding = ActivityWebsiteBuildStatusBinding.inflate(getLayoutInflater(), null, false);

        ((BaseApplication) fragmentActivity.getApplication()).getFdroidClient().getWebsiteBuildStatus((response) -> {
            var websiteBuildStatus = response.value();
            if (response.isSuccess()) {
                binding.websiteBuildStatusStartTimestampText.setText(FormatUtils.formatShortDateTime(websiteBuildStatus.getStartTimestamp()));
                if (websiteBuildStatus.getEndTimestamp() == 0L) {
                    binding.websiteBuildStatusEndTimestampText.setText(R.string.website_build_status_running);
                } else {
                    binding.websiteBuildStatusEndTimestampText.setText(FormatUtils.formatShortDateTime(websiteBuildStatus.getEndTimestamp()));
                }
            } else {
                var context = getContext();
                if (context != null) {
                    Toast.makeText(context, getString(R.string.load_website_build_status_failed, response.errorMessage()), Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setView(binding.getRoot());

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
