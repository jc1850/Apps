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

public class InstantMessagingItem$ViewHolder_ViewBinding implements Unbinder {
  private InstantMessagingItem.ViewHolder target;

  @UiThread
  public InstantMessagingItem$ViewHolder_ViewBinding(InstantMessagingItem.ViewHolder target,
      View source) {
    this.target = target;

    target.asnName = Utils.findRequiredViewAsType(source, R.id.asnName, "field 'asnName'", TextView.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
    target.failedMeasurements = Utils.findRequiredViewAsType(source, R.id.failedMeasurements, "field 'failedMeasurements'", TextView.class);
    target.okMeasurements = Utils.findRequiredViewAsType(source, R.id.okMeasurements, "field 'okMeasurements'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InstantMessagingItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.asnName = null;
    target.startTime = null;
    target.failedMeasurements = null;
    target.okMeasurements = null;
  }
}
