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

public class HeaderOutcomeFragment_ViewBinding implements Unbinder {
  private HeaderOutcomeFragment target;

  @UiThread
  public HeaderOutcomeFragment_ViewBinding(HeaderOutcomeFragment target, View source) {
    this.target = target;

    target.outcome = Utils.findRequiredViewAsType(source, R.id.outcome, "field 'outcome'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HeaderOutcomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.outcome = null;
  }
}
