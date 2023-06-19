// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class PreferenceGlobalFragment_ViewBinding implements Unbinder {
  private PreferenceGlobalFragment target;

  @UiThread
  public PreferenceGlobalFragment_ViewBinding(PreferenceGlobalFragment target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PreferenceGlobalFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
  }
}
