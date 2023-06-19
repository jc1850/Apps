// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class FailedItem$ViewHolder_ViewBinding implements Unbinder {
  private FailedItem.ViewHolder target;

  @UiThread
  public FailedItem$ViewHolder_ViewBinding(FailedItem.ViewHolder target, View source) {
    this.target = target;

    target.testName = Utils.findRequiredViewAsType(source, R.id.testName, "field 'testName'", TextView.class);
    target.subtitle = Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitle'", TextView.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FailedItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.testName = null;
    target.subtitle = null;
    target.startTime = null;
    target.icon = null;
  }
}
