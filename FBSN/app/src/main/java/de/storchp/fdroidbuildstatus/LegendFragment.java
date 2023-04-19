package de.storchp.fdroidbuildstatus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import de.storchp.fdroidbuildstatus.databinding.ActivityLegendBinding;
import de.storchp.fdroidbuildstatus.databinding.ItemLegendBinding;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;

public class LegendFragment extends DialogFragment {

    private ActivityLegendBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = ActivityLegendBinding.inflate(getLayoutInflater(), null, false);
        binding.iconsList.setAdapter(new LegendAdapter(
                getContext(), getResources().getStringArray(R.array.icons), getResources().getStringArray(R.array.icon_labels)));

        return new AlertDialog.Builder(requireActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.legend_title)
                .setPositiveButton(android.R.string.ok, null)
                .setView(binding.getRoot())
                .create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class LegendAdapter extends BaseAdapter {

        private final String[] icons;
        private final String[] labels;
        private final Context context;

        public LegendAdapter(Context context, String[] icons, String[] labels) {
            super();
            this.icons = icons;
            this.labels = labels;
            this.context = context;
        }

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public Object getItem(int position) {
            return icons[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            var rowView = convertView;
            // reuse views
            ItemLegendBinding binding;
            if (rowView == null) {
                binding = ItemLegendBinding.inflate(LayoutInflater.from(context), parent, false);
                rowView = binding.getRoot();
                rowView.setTag(binding);
            } else {
                binding = (ItemLegendBinding) rowView.getTag();
            }

            binding.legendItem.setText(labels[position]);
            DrawableUtils.setCompoundDrawablesLeft(context,
                    binding.legendItem,
                    context.getResources().getIdentifier(icons[position], "drawable", context.getPackageName()),
                    binding.legendItem.getCurrentTextColor());

            return rowView;
        }

    }

}
