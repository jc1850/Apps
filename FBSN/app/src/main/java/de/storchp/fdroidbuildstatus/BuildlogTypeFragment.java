package de.storchp.fdroidbuildstatus;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import de.storchp.fdroidbuildstatus.model.BuildlogType;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class BuildlogTypeFragment extends DialogFragment {

    public static final String BUILDLOG_TYPE_REQUEST = "buildlogTypeRequest";
    public static final String BUILDLOG_TYPE_RESULT = "buildlogTypeResult";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        var fragmentActivity = requireActivity();
        var view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_buildlog_type, null);
        CheckBox always = view.findViewById(R.id.always);

        ImageButton log = view.findViewById(R.id.log);
        log.setOnClickListener(view1 -> sendResult(fragmentActivity, always, BuildlogType.LOG));
        log.setImageDrawable(DrawableUtils.getTintedDrawable(fragmentActivity, R.drawable.ic_log, always.getCurrentTextColor()));

        ImageButton gz = view.findViewById(R.id.gz);
        gz.setOnClickListener(view1 -> sendResult(fragmentActivity, always, BuildlogType.GZ));
        gz.setImageDrawable(DrawableUtils.getTintedDrawable(fragmentActivity, R.drawable.ic_gz, always.getCurrentTextColor()));

        return new AlertDialog.Builder(fragmentActivity)
                .setView(view)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.buildlog_type)
                .create();
    }

    private void sendResult(FragmentActivity fragmentActivity, CheckBox always, BuildlogType buildlogType) {
        if (always.isChecked()) {
            PreferenceUtils.setBuildlogType(fragmentActivity, buildlogType);
        }
        var result = new Bundle();
        result.putString(BUILDLOG_TYPE_RESULT, buildlogType.name());
        getParentFragmentManager().setFragmentResult(BUILDLOG_TYPE_REQUEST, result);
        dismiss();
    }

}
