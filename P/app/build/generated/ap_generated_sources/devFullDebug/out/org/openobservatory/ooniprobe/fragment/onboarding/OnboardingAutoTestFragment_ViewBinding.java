// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.onboarding;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class OnboardingAutoTestFragment_ViewBinding implements Unbinder {
  private OnboardingAutoTestFragment target;

  private View view7f0a014c;

  private View view7f0a021b;

  @UiThread
  public OnboardingAutoTestFragment_ViewBinding(final OnboardingAutoTestFragment target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.master, "method 'masterClick'");
    view7f0a014c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.masterClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.slave, "method 'slaveClick'");
    view7f0a021b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.slaveClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a014c.setOnClickListener(null);
    view7f0a014c = null;
    view7f0a021b.setOnClickListener(null);
    view7f0a021b = null;
  }
}
