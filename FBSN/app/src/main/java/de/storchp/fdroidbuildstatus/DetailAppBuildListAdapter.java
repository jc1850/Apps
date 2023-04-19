package de.storchp.fdroidbuildstatus;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.storchp.fdroidbuildstatus.databinding.ItemDetailAppbuildBinding;
import de.storchp.fdroidbuildstatus.model.AppBuild;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.utils.DrawableUtils;

public class DetailAppBuildListAdapter extends BaseAdapter {
    private final Activity context;
    private final List<List<AppBuild>> appBuildsByVersionCodeAndStatus;

    public DetailAppBuildListAdapter(Activity context, Map<String, List<AppBuild>> appBuildsByVersionCodeAndStatus) {
        super();
        this.appBuildsByVersionCodeAndStatus = new ArrayList<>(appBuildsByVersionCodeAndStatus.values());
        this.context = context;
    }

    public AppBuild getItemForPosition(int position) {
        if (appBuildsByVersionCodeAndStatus == null) {
            return null;
        }

        return appBuildsByVersionCodeAndStatus.get(position).get(0);
    }

    public int getCount() {
        return appBuildsByVersionCodeAndStatus != null ? appBuildsByVersionCodeAndStatus.size() : 0;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        var rowView = convertView;
        // reuse views
        ItemDetailAppbuildBinding binding;
        if (rowView == null) {
            binding = ItemDetailAppbuildBinding.inflate(context.getLayoutInflater(), parent, false);
            rowView = binding.getRoot();
            rowView.setTag(binding);
        } else {
            binding = (ItemDetailAppbuildBinding) rowView.getTag();
        }

        var appBuilds = appBuildsByVersionCodeAndStatus.get(position);
        var item = appBuilds.get(0);
        binding.version.setText(item.getFullVersion());
        DrawableUtils.setIconWithTint(context, binding.statusIcon, item.getStatus().getIconRes(), binding.version.getCurrentTextColor());
        setBuildTypIcon(binding.buildRunTypeRunning, appBuilds, BuildCycle.RUNNING, binding.version.getCurrentTextColor());
        setBuildTypIcon(binding.buildRunTypeFinished, appBuilds, BuildCycle.BUILD, binding.version.getCurrentTextColor());
        setBuildTypIcon(binding.buildRunTypeUpdate, appBuilds, BuildCycle.UPDATE, binding.version.getCurrentTextColor());

        return rowView;
    }

    private void setBuildTypIcon(ImageView bindingBuild, List<AppBuild> builds, BuildCycle running, int color) {
        bindingBuild.setVisibility(builds.stream().anyMatch(a -> a.getBuildCycle() == running) ? View.VISIBLE : View.GONE);
        DrawableUtils.setIconWithTint(context, bindingBuild, running.getIconRes(), color);
    }

}