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

public class HeaderNdtFragment_ViewBinding implements Unbinder {
  private HeaderNdtFragment target;

  @UiThread
  public HeaderNdtFragment_ViewBinding(HeaderNdtFragment target, View source) {
    this.target = target;

    target.download = Utils.findRequiredViewAsType(source, R.id.download, "field 'download'", TextView.class);
    target.upload = Utils.findRequiredViewAsType(source, R.id.upload, "field 'upload'", TextView.class);
    target.ping = Utils.findRequiredViewAsType(source, R.id.ping, "field 'ping'", TextView.class);
    target.server = Utils.findRequiredViewAsType(source, R.id.server, "field 'server'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HeaderNdtFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.download = null;
    target.upload = null;
    target.ping = null;
    target.server = null;
  }
}
