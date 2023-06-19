// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class MeasurementDetailActivity_ViewBinding implements Unbinder {
  private MeasurementDetailActivity target;

  private View view7f0a0146;

  private View view7f0a00f9;

  private View view7f0a00b9;

  @UiThread
  public MeasurementDetailActivity_ViewBinding(MeasurementDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeasurementDetailActivity_ViewBinding(final MeasurementDetailActivity target,
      View source) {
    this.target = target;

    View view;
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.log, "field 'log' and method 'logClick'");
    target.log = Utils.castView(view, R.id.log, "field 'log'", Button.class);
    view7f0a0146 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.logClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.explorer, "field 'explorer' and method 'explorerClick'");
    target.explorer = Utils.castView(view, R.id.explorer, "field 'explorer'", Button.class);
    view7f0a00f9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.explorerClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.data, "field 'data' and method 'dataClick'");
    target.data = Utils.castView(view, R.id.data, "field 'data'", Button.class);
    view7f0a00b9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dataClick();
      }
    });
    target.methodology = Utils.findRequiredViewAsType(source, R.id.methodology, "field 'methodology'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasurementDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.toolbar = null;
    target.log = null;
    target.explorer = null;
    target.data = null;
    target.methodology = null;

    view7f0a0146.setOnClickListener(null);
    view7f0a0146 = null;
    view7f0a00f9.setOnClickListener(null);
    view7f0a00f9 = null;
    view7f0a00b9.setOnClickListener(null);
    view7f0a00b9 = null;
  }
}
