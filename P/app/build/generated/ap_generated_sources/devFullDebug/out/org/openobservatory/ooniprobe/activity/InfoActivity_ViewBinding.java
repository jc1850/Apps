// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
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

public class InfoActivity_ViewBinding implements Unbinder {
  private InfoActivity target;

  private View view7f0a006e;

  private View view7f0a01df;

  private View view7f0a013b;

  private View view7f0a00bc;

  @UiThread
  public InfoActivity_ViewBinding(InfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InfoActivity_ViewBinding(final InfoActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.version = Utils.findRequiredViewAsType(source, R.id.version, "field 'version'", TextView.class);
    view = Utils.findRequiredView(source, R.id.blog, "method 'onBlogClick'");
    view7f0a006e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBlogClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.reports, "method 'onReportsClick'");
    view7f0a01df = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onReportsClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.learnMore, "method 'onLearnMoreClick'");
    view7f0a013b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLearnMoreClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.dataPolicy, "method 'onDataPolicyClick'");
    view7f0a00bc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDataPolicyClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    InfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.version = null;

    view7f0a006e.setOnClickListener(null);
    view7f0a006e = null;
    view7f0a01df.setOnClickListener(null);
    view7f0a01df = null;
    view7f0a013b.setOnClickListener(null);
    view7f0a013b = null;
    view7f0a00bc.setOnClickListener(null);
    view7f0a00bc = null;
  }
}
