// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class CustomWebsiteActivity_ViewBinding implements Unbinder {
  private CustomWebsiteActivity target;

  private View view7f0a0049;

  @UiThread
  public CustomWebsiteActivity_ViewBinding(CustomWebsiteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomWebsiteActivity_ViewBinding(final CustomWebsiteActivity target, View source) {
    this.target = target;

    View view;
    target.urlContainer = Utils.findRequiredViewAsType(source, R.id.urlContainer, "field 'urlContainer'", LinearLayout.class);
    target.bottomBar = Utils.findRequiredViewAsType(source, R.id.bottomBar, "field 'bottomBar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.add, "method 'add'");
    view7f0a0049 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.add();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomWebsiteActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.urlContainer = null;
    target.bottomBar = null;

    view7f0a0049.setOnClickListener(null);
    view7f0a0049 = null;
  }
}
