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

public class DateItem$ViewHolder_ViewBinding implements Unbinder {
  private DateItem.ViewHolder target;

  @UiThread
  public DateItem$ViewHolder_ViewBinding(DateItem.ViewHolder target, View source) {
    this.target = target;

    target.textView = Utils.findRequiredViewAsType(source, R.id.textView, "field 'textView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DateItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView = null;
  }
}
