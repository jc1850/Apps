// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.onboarding;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class OnboardingDialogWarningFragment_ViewBinding implements Unbinder {
  private OnboardingDialogWarningFragment target;

  private View view7f0a01c2;

  private View view7f0a0194;

  @UiThread
  public OnboardingDialogWarningFragment_ViewBinding(final OnboardingDialogWarningFragment target,
      View source) {
    this.target = target;

    View view;
    target.title = Utils.findOptionalViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.question = Utils.findRequiredViewAsType(source, R.id.question, "field 'question'", TextView.class);
    target.dialog = Utils.findRequiredViewAsType(source, R.id.dialog, "field 'dialog'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.positive, "method 'positiveClick'");
    view7f0a01c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.positiveClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.negative, "method 'negativeClick'");
    view7f0a0194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.negativeClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OnboardingDialogWarningFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.question = null;
    target.dialog = null;

    view7f0a01c2.setOnClickListener(null);
    view7f0a01c2 = null;
    view7f0a0194.setOnClickListener(null);
    view7f0a0194 = null;
  }
}
