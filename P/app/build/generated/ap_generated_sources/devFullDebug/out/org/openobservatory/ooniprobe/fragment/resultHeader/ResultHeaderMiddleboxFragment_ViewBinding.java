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

public class ResultHeaderMiddleboxFragment_ViewBinding implements Unbinder {
  private ResultHeaderMiddleboxFragment target;

  @UiThread
  public ResultHeaderMiddleboxFragment_ViewBinding(ResultHeaderMiddleboxFragment target,
      View source) {
    this.target = target;

    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultHeaderMiddleboxFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text = null;
  }
}
