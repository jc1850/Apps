// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.item;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class PerformanceItem$ViewHolder_ViewBinding implements Unbinder {
  private PerformanceItem.ViewHolder target;

  @UiThread
  public PerformanceItem$ViewHolder_ViewBinding(PerformanceItem.ViewHolder target, View source) {
    this.target = target;

    target.asnName = Utils.findRequiredViewAsType(source, R.id.asnName, "field 'asnName'", TextView.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
    target.upload = Utils.findRequiredViewAsType(source, R.id.upload, "field 'upload'", TextView.class);
    target.download = Utils.findRequiredViewAsType(source, R.id.download, "field 'download'", TextView.class);
    target.quality = Utils.findRequiredViewAsType(source, R.id.quality, "field 'quality'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PerformanceItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.asnName = null;
    target.startTime = null;
    target.upload = null;
    target.download = null;
    target.quality = null;
  }
}
