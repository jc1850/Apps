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

public class TorFragment_ViewBinding implements Unbinder {
  private TorFragment target;

  @UiThread
  public TorFragment_ViewBinding(TorFragment target, View source) {
    this.target = target;

    target.bridges = Utils.findRequiredViewAsType(source, R.id.bridges, "field 'bridges'", TextView.class);
    target.authorities = Utils.findRequiredViewAsType(source, R.id.authorities, "field 'authorities'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TorFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bridges = null;
    target.authorities = null;
    target.desc = null;
  }
}
