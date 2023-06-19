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

public class TelegramFragment_ViewBinding implements Unbinder {
  private TelegramFragment target;

  @UiThread
  public TelegramFragment_ViewBinding(TelegramFragment target, View source) {
    this.target = target;

    target.application = Utils.findRequiredViewAsType(source, R.id.application, "field 'application'", TextView.class);
    target.webApp = Utils.findRequiredViewAsType(source, R.id.webApp, "field 'webApp'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TelegramFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.application = null;
    target.webApp = null;
    target.desc = null;
  }
}
