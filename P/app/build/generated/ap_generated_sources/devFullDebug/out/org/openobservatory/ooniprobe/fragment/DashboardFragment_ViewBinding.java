// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class DashboardFragment_ViewBinding implements Unbinder {
  private DashboardFragment target;

  @UiThread
  public DashboardFragment_ViewBinding(DashboardFragment target, View source) {
    this.target = target;

    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.lastTested = Utils.findRequiredViewAsType(source, R.id.last_tested, "field 'lastTested'", TextView.class);
    target.runAll = Utils.findRequiredViewAsType(source, R.id.run_all, "field 'runAll'", TextView.class);
    target.vpn = Utils.findRequiredViewAsType(source, R.id.vpn, "field 'vpn'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DashboardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycler = null;
    target.toolbar = null;
    target.lastTested = null;
    target.runAll = null;
    target.vpn = null;
  }
}
