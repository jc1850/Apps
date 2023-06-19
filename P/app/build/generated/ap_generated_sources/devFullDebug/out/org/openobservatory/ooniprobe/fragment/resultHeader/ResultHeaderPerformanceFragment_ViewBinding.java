// Generated code from Butter Knife. Do not modify!
package org.openobservatory.ooniprobe.fragment.resultHeader;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public class ResultHeaderPerformanceFragment_ViewBinding implements Unbinder {
  private ResultHeaderPerformanceFragment target;

  @UiThread
  public ResultHeaderPerformanceFragment_ViewBinding(ResultHeaderPerformanceFragment target,
      View source) {
    this.target = target;

    target.video = Utils.findRequiredViewAsType(source, R.id.video, "field 'video'", TextView.class);
    target.upload = Utils.findRequiredViewAsType(source, R.id.upload, "field 'upload'", TextView.class);
    target.download = Utils.findRequiredViewAsType(source, R.id.download, "field 'download'", TextView.class);
    target.ping = Utils.findRequiredViewAsType(source, R.id.ping, "field 'ping'", TextView.class);
    target.videoLabel = Utils.findRequiredViewAsType(source, R.id.videoLabel, "field 'videoLabel'", TextView.class);
    target.downloadLabel = Utils.findRequiredViewAsType(source, R.id.downloadLabel, "field 'downloadLabel'", TextView.class);
    target.uploadLabel = Utils.findRequiredViewAsType(source, R.id.uploadLabel, "field 'uploadLabel'", TextView.class);
    target.pingLabel = Utils.findRequiredViewAsType(source, R.id.pingLabel, "field 'pingLabel'", TextView.class);
    target.videoUnit = Utils.findRequiredViewAsType(source, R.id.videoUnit, "field 'videoUnit'", TextView.class);
    target.downloadUnit = Utils.findRequiredViewAsType(source, R.id.downloadUnit, "field 'downloadUnit'", TextView.class);
    target.uploadUnit = Utils.findRequiredViewAsType(source, R.id.uploadUnit, "field 'uploadUnit'", TextView.class);
    target.pingUnit = Utils.findRequiredViewAsType(source, R.id.pingUnit, "field 'pingUnit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResultHeaderPerformanceFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.video = null;
    target.upload = null;
    target.download = null;
    target.ping = null;
    target.videoLabel = null;
    target.downloadLabel = null;
    target.uploadLabel = null;
    target.pingLabel = null;
    target.videoUnit = null;
    target.downloadUnit = null;
    target.uploadUnit = null;
    target.pingUnit = null;
  }
}
