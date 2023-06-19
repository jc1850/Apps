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

public class RiseupVPNFragment_ViewBinding implements Unbinder {
  private RiseupVPNFragment target;

  @UiThread
  public RiseupVPNFragment_ViewBinding(RiseupVPNFragment target, View source) {
    this.target = target;

    target.bootstrap_value = Utils.findRequiredViewAsType(source, R.id.bootstrap_value, "field 'bootstrap_value'", TextView.class);
    target.openvpn_value = Utils.findRequiredViewAsType(source, R.id.openvpn_value, "field 'openvpn_value'", TextView.class);
    target.bridges_value = Utils.findRequiredViewAsType(source, R.id.bridges_value, "field 'bridges_value'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RiseupVPNFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bootstrap_value = null;
    target.openvpn_value = null;
    target.bridges_value = null;
    target.desc = null;
  }
}
