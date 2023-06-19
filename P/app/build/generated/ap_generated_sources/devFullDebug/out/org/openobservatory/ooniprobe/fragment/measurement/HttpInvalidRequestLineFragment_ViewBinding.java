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

public class HttpInvalidRequestLineFragment_ViewBinding implements Unbinder {
  private HttpInvalidRequestLineFragment target;

  @UiThread
  public HttpInvalidRequestLineFragment_ViewBinding(HttpInvalidRequestLineFragment target,
      View source) {
    this.target = target;

    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HttpInvalidRequestLineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.desc = null;
  }
}
