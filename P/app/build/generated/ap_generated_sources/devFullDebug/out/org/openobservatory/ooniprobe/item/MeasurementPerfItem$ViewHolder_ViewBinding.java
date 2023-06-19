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

public class MeasurementPerfItem$ViewHolder_ViewBinding implements Unbinder {
  private MeasurementPerfItem.ViewHolder target;

  @UiThread
  public MeasurementPerfItem$ViewHolder_ViewBinding(MeasurementPerfItem.ViewHolder target,
      View source) {
    this.target = target;

    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
    target.data1 = Utils.findRequiredViewAsType(source, R.id.data1, "field 'data1'", TextView.class);
    target.data2 = Utils.findRequiredViewAsType(source, R.id.data2, "field 'data2'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasurementPerfItem.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text = null;
    target.data1 = null;
    target.data2 = null;
  }
}
