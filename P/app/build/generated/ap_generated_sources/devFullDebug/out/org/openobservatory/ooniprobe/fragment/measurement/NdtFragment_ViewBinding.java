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

public class NdtFragment_ViewBinding implements Unbinder {
  private NdtFragment target;

  @UiThread
  public NdtFragment_ViewBinding(NdtFragment target, View source) {
    this.target = target;

    target.packetLoss = Utils.findRequiredViewAsType(source, R.id.packetLoss, "field 'packetLoss'", TextView.class);
    target.averagePing = Utils.findRequiredViewAsType(source, R.id.averagePing, "field 'averagePing'", TextView.class);
    target.maxPing = Utils.findRequiredViewAsType(source, R.id.maxPing, "field 'maxPing'", TextView.class);
    target.mss = Utils.findRequiredViewAsType(source, R.id.mss, "field 'mss'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NdtFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.packetLoss = null;
    target.averagePing = null;
    target.maxPing = null;
    target.mss = null;
  }
}
