// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.resultHeader;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ResultHeaderTBAFragment_ViewBinding implements Unbinder {
  private ResultHeaderTBAFragment target;

  @UiThread
  public ResultHeaderTBAFragment_ViewBinding(ResultHeaderTBAFragment target, View source) {
    this.target = target;

    target.tested = Utils.findRequiredViewAsType(source, R.id.tested, "field 'tested'", TextView.class);
    target.blocked = Utils.findRequiredViewAsType(source, R.id.blocked, "field 'blocked'", TextView.class);
    target.available = Utils.findRequiredViewAsType(source, R.id.available, "field 'available'", TextView.class);
    target.testedTag = Utils.findRequiredViewAsType(source, R.id.testedTag, "field 'testedTag'", TextView.class);
    target.blockedTag = Utils.findRequiredViewAsType(source, R.id.blockedTag, "field 'blockedTag'", TextView.class);
    target.availableTag = Utils.findRequiredViewAsType(source, R.id.availableTag, "field 'availableTag'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultHeaderTBAFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tested = null;
    target.blocked = null;
    target.available = null;
    target.testedTag = null;
    target.blockedTag = null;
    target.availableTag = null;
  }
}
