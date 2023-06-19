// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ProgressFragment_ViewBinding implements Unbinder {
  private ProgressFragment target;

  @UiThread
  public ProgressFragment_ViewBinding(ProgressFragment target, View source) {
    this.target = target;

    target.progress_layout = Utils.findRequiredViewAsType(source, R.id.progress_layout, "field 'progress_layout'", FrameLayout.class);
    target.progress = Utils.findRequiredViewAsType(source, R.id.progress, "field 'progress'", ProgressBar.class);
    target.running = Utils.findRequiredViewAsType(source, R.id.running, "field 'running'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProgressFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progress_layout = null;
    target.progress = null;
    target.running = null;
    target.name = null;
  }
}
