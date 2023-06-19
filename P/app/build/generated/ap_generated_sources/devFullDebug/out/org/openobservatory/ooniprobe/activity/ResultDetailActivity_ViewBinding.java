// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ResultDetailActivity_ViewBinding implements Unbinder {
  private ResultDetailActivity target;

  @UiThread
  public ResultDetailActivity_ViewBinding(ResultDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ResultDetailActivity_ViewBinding(ResultDetailActivity target, View source) {
    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.pager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'pager'", ViewPager2.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.toolbar = null;
    target.tabLayout = null;
    target.pager = null;
    target.recycler = null;
  }
}
