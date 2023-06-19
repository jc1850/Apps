// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.resultHeader;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ResultHeaderDetailFragment_ViewBinding implements Unbinder {
  private ResultHeaderDetailFragment target;

  @UiThread
  public ResultHeaderDetailFragment_ViewBinding(ResultHeaderDetailFragment target, View source) {
    this.target = target;

    target.dataUsage = Utils.findRequiredViewAsType(source, R.id.dataUsage, "field 'dataUsage'", LinearLayout.class);
    target.startTimeBox = Utils.findRequiredViewAsType(source, R.id.startTimeBox, "field 'startTimeBox'", LinearLayout.class);
    target.runtimeBox = Utils.findRequiredViewAsType(source, R.id.runtimeBox, "field 'runtimeBox'", LinearLayout.class);
    target.countryBox = Utils.findRequiredViewAsType(source, R.id.countryBox, "field 'countryBox'", LinearLayout.class);
    target.networkBox = Utils.findRequiredViewAsType(source, R.id.networkBox, "field 'networkBox'", LinearLayout.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
    target.upload = Utils.findRequiredViewAsType(source, R.id.upload, "field 'upload'", TextView.class);
    target.download = Utils.findRequiredViewAsType(source, R.id.download, "field 'download'", TextView.class);
    target.runtime = Utils.findRequiredViewAsType(source, R.id.runtime, "field 'runtime'", TextView.class);
    target.runtimeLabel = Utils.findRequiredViewAsType(source, R.id.runtimeLabel, "field 'runtimeLabel'", TextView.class);
    target.country = Utils.findRequiredViewAsType(source, R.id.country, "field 'country'", TextView.class);
    target.networkName = Utils.findRequiredViewAsType(source, R.id.networkName, "field 'networkName'", TextView.class);
    target.networkDetail = Utils.findRequiredViewAsType(source, R.id.networkDetail, "field 'networkDetail'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultHeaderDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dataUsage = null;
    target.startTimeBox = null;
    target.runtimeBox = null;
    target.countryBox = null;
    target.networkBox = null;
    target.startTime = null;
    target.upload = null;
    target.download = null;
    target.runtime = null;
    target.runtimeLabel = null;
    target.country = null;
    target.networkName = null;
    target.networkDetail = null;
  }
}
