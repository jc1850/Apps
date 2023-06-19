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

public class ExperimentalItem$ViewHolder_ViewBinding implements Unbinder {
  private ExperimentalItem.ViewHolder target;

  @UiThread
  public ExperimentalItem$ViewHolder_ViewBinding(ExperimentalItem.ViewHolder target, View source) {
    this.target = target;

    target.asnName = Utils.findRequiredViewAsType(source, R.id.asnName, "field 'asnName'", TextView.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExperimentalItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.asnName = null;
    target.startTime = null;
  }
}
