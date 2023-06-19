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

public class MiddleboxesItem$ViewHolder_ViewBinding implements Unbinder {
  private MiddleboxesItem.ViewHolder target;

  @UiThread
  public MiddleboxesItem$ViewHolder_ViewBinding(MiddleboxesItem.ViewHolder target, View source) {
    this.target = target;

    target.asnName = Utils.findRequiredViewAsType(source, R.id.asnName, "field 'asnName'", TextView.class);
    target.startTime = Utils.findRequiredViewAsType(source, R.id.startTime, "field 'startTime'", TextView.class);
    target.status = Utils.findRequiredViewAsType(source, R.id.status, "field 'status'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MiddleboxesItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.asnName = null;
    target.startTime = null;
    target.status = null;
  }
}
