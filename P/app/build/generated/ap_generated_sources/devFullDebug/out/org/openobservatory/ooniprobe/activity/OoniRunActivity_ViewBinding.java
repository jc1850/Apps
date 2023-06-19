// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class OoniRunActivity_ViewBinding implements Unbinder {
  private OoniRunActivity target;

  @UiThread
  public OoniRunActivity_ViewBinding(OoniRunActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OoniRunActivity_ViewBinding(OoniRunActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
    target.iconBig = Utils.findRequiredViewAsType(source, R.id.iconBig, "field 'iconBig'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
    target.run = Utils.findRequiredViewAsType(source, R.id.run, "field 'run'", Button.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OoniRunActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.icon = null;
    target.iconBig = null;
    target.title = null;
    target.desc = null;
    target.run = null;
    target.recycler = null;
  }
}
