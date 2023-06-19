// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ResultListFragment_ViewBinding implements Unbinder {
  private ResultListFragment target;

  private View view7f0a0100;

  @UiThread
  public ResultListFragment_ViewBinding(final ResultListFragment target, View source) {
    this.target = target;

    View view;
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tests = Utils.findRequiredViewAsType(source, R.id.tests, "field 'tests'", TextView.class);
    target.networks = Utils.findRequiredViewAsType(source, R.id.networks, "field 'networks'", TextView.class);
    target.upload = Utils.findRequiredViewAsType(source, R.id.upload, "field 'upload'", TextView.class);
    target.download = Utils.findRequiredViewAsType(source, R.id.download, "field 'download'", TextView.class);
    view = Utils.findRequiredView(source, R.id.filterTests, "field 'filterTests' and method 'queryList'");
    target.filterTests = Utils.castView(view, R.id.filterTests, "field 'filterTests'", Spinner.class);
    view7f0a0100 = view;
    ((AdapterView<?>) view).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        target.queryList();
      }

      @Override
      public void onNothingSelected(AdapterView<?> p0) {
      }
    });
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    target.emptyState = Utils.findRequiredViewAsType(source, R.id.emptyState, "field 'emptyState'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.toolbar = null;
    target.tests = null;
    target.networks = null;
    target.upload = null;
    target.download = null;
    target.filterTests = null;
    target.recycler = null;
    target.emptyState = null;

    ((AdapterView<?>) view7f0a0100).setOnItemSelectedListener(null);
    view7f0a0100 = null;
  }
}
