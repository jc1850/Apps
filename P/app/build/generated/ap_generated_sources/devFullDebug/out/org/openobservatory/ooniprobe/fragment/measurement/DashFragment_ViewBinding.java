// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.measurement;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class DashFragment_ViewBinding implements Unbinder {
  private DashFragment target;

  @UiThread
  public DashFragment_ViewBinding(DashFragment target, View source) {
    this.target = target;

    target.medianBitrate = Utils.findRequiredViewAsType(source, R.id.medianBitrate, "field 'medianBitrate'", TextView.class);
    target.playoutDelay = Utils.findRequiredViewAsType(source, R.id.playoutDelay, "field 'playoutDelay'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DashFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.medianBitrate = null;
    target.playoutDelay = null;
  }
}
