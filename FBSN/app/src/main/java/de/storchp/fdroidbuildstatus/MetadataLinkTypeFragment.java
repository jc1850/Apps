package de.storchp.fdroidbuildstatus;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import de.storchp.fdroidbuildstatus.model.MetadataLinkType;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class MetadataLinkTypeFragment extends DialogFragment {

    public static final String METADATA_LINK_TYPE_REQUEST = "metadataLinkTypeRequest";
    public static final String METADATA_LINK_TYPE_RESULT = "metadataLinkTypeResult";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        var fragmentActivity = requireActivity();
        var view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_metadata_link_type, null);
        CheckBox always = view.findViewById(R.id.always);
        ImageButton yaml = view.findViewById(R.id.yaml);
        yaml.setOnClickListener(view1 -> sendResult(fragmentActivity, always, MetadataLinkType.YAML));
        yaml.setImageDrawable(DrawableUtils.getTintedDrawable(fragmentActivity, R.drawable.ic_yaml, always.getCurrentTextColor()));
        ImageButton gitlab = view.findViewById(R.id.gitlab);
        gitlab.setOnClickListener(view1 -> sendResult(fragmentActivity, always, MetadataLinkType.GITLAB));
        gitlab.setImageDrawable(DrawableUtils.getTintedDrawable(fragmentActivity, R.drawable.ic_gitlab, always.getCurrentTextColor()));

        return new AlertDialog.Builder(fragmentActivity)
                .setView(view)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.metadata_link_type)
                .create();
    }

    private void sendResult(FragmentActivity fragmentActivity, CheckBox always, MetadataLinkType metadataLinkType) {
        if (always.isChecked()) {
            PreferenceUtils.setMetadataLinkType(fragmentActivity, metadataLinkType);
        }
        var result = new Bundle();
        result.putString(METADATA_LINK_TYPE_RESULT, metadataLinkType.name());
        getParentFragmentManager().setFragmentResult(METADATA_LINK_TYPE_REQUEST, result);
        dismiss();
    }

}
