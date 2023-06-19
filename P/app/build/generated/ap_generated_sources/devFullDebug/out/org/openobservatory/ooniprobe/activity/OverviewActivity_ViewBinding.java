// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class OverviewActivity_ViewBinding implements Unbinder {
  private OverviewActivity target;

  private View view7f0a00b5;

  private View view7f0a01eb;

  @UiThread
  public OverviewActivity_ViewBinding(OverviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OverviewActivity_ViewBinding(final OverviewActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
    target.runtime = Utils.findRequiredViewAsType(source, R.id.runtime, "field 'runtime'", TextView.class);
    target.lastTime = Utils.findRequiredViewAsType(source, R.id.lastTime, "field 'lastTime'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
    view = Utils.findRequiredView(source, R.id.customUrl, "field 'customUrl' and method 'customUrlClick'");
    target.customUrl = Utils.castView(view, R.id.customUrl, "field 'customUrl'", Button.class);
    view7f0a00b5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.customUrlClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.run, "field 'run' and method 'onRunClick'");
    target.run = Utils.castView(view, R.id.run, "field 'run'", Button.class);
    view7f0a01eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRunClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OverviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.icon = null;
    target.runtime = null;
    target.lastTime = null;
    target.desc = null;
    target.customUrl = null;
    target.run = null;

    view7f0a00b5.setOnClickListener(null);
    view7f0a00b5 = null;
    view7f0a01eb.setOnClickListener(null);
    view7f0a01eb = null;
  }
}
