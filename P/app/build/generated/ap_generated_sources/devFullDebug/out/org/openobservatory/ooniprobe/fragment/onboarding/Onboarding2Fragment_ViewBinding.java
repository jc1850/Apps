// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.onboarding;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class Onboarding2Fragment_ViewBinding implements Unbinder {
  private Onboarding2Fragment target;

  private View view7f0a014c;

  private View view7f0a021b;

  @UiThread
  public Onboarding2Fragment_ViewBinding(final Onboarding2Fragment target, View source) {
    this.target = target;

    View view;
    target.bullet1 = Utils.findRequiredViewAsType(source, R.id.bullet1, "field 'bullet1'", TextView.class);
    target.bullet2 = Utils.findRequiredViewAsType(source, R.id.bullet2, "field 'bullet2'", TextView.class);
    target.bullet3 = Utils.findRequiredViewAsType(source, R.id.bullet3, "field 'bullet3'", TextView.class);
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
    Onboarding2Fragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bullet1 = null;
    target.bullet2 = null;
    target.bullet3 = null;

    view7f0a014c.setOnClickListener(null);
    view7f0a014c = null;
    view7f0a021b.setOnClickListener(null);
    view7f0a021b = null;
  }
}
