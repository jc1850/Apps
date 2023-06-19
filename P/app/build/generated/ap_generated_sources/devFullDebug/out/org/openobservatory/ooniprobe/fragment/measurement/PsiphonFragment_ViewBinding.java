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

public class PsiphonFragment_ViewBinding implements Unbinder {
  private PsiphonFragment target;

  @UiThread
  public PsiphonFragment_ViewBinding(PsiphonFragment target, View source) {
    this.target = target;

    target.bootstrap = Utils.findRequiredViewAsType(source, R.id.bootstrap, "field 'bootstrap'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PsiphonFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bootstrap = null;
    target.desc = null;
  }
}
