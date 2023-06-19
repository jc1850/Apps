// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class RunningActivity_ViewBinding implements Unbinder {
  private RunningActivity target;

  @UiThread
  public RunningActivity_ViewBinding(RunningActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RunningActivity_ViewBinding(RunningActivity target, View source) {
    this.target = target;

    target.running = Utils.findRequiredViewAsType(source, R.id.running, "field 'running'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.log = Utils.findRequiredViewAsType(source, R.id.log, "field 'log'", TextView.class);
    target.eta = Utils.findRequiredViewAsType(source, R.id.eta, "field 'eta'", TextView.class);
    target.progress = Utils.findRequiredViewAsType(source, R.id.progress, "field 'progress'", ProgressBar.class);
    target.close = Utils.findRequiredViewAsType(source, R.id.close, "field 'close'", ImageButton.class);
    target.stop = Utils.findRequiredViewAsType(source, R.id.stop, "field 'stop'", Button.class);
    target.animation = Utils.findRequiredViewAsType(source, R.id.animation, "field 'animation'", LottieAnimationView.class);
    target.proxy_icon = Utils.findRequiredViewAsType(source, R.id.proxy_icon, "field 'proxy_icon'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RunningActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.running = null;
    target.name = null;
    target.log = null;
    target.eta = null;
    target.progress = null;
    target.close = null;
    target.stop = null;
    target.animation = null;
    target.proxy_icon = null;
  }
}
