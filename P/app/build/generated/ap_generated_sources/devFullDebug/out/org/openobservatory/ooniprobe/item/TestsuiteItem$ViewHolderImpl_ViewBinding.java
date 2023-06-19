// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class TestsuiteItem$ViewHolderImpl_ViewBinding implements Unbinder {
  private TestsuiteItem.ViewHolderImpl target;

  @UiThread
  public TestsuiteItem$ViewHolderImpl_ViewBinding(TestsuiteItem.ViewHolderImpl target,
      View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.desc, "field 'desc'", TextView.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestsuiteItem.ViewHolderImpl target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.desc = null;
    target.icon = null;
  }
}
