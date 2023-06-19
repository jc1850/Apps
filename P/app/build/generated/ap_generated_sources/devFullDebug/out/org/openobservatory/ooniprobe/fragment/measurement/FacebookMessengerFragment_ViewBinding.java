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

public class FacebookMessengerFragment_ViewBinding implements Unbinder {
  private FacebookMessengerFragment target;

  @UiThread
  public FacebookMessengerFragment_ViewBinding(FacebookMessengerFragment target, View source) {
    this.target = target;

    target.tcp = Utils.findRequiredViewAsType(source, R.id.tcp, "field 'tcp'", TextView.class);
    target.dns = Utils.findRequiredViewAsType(source, R.id.dns, "field 'dns'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FacebookMessengerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tcp = null;
    target.dns = null;
    target.desc = null;
  }
}
