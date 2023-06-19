// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.openobservatory.ooniprobe.R;

public final class FragmentMeasurementHeaderNdtBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView download;

  @NonNull
  public final TextView ping;

  @NonNull
  public final TextView server;

  @NonNull
  public final TextView upload;

  private FragmentMeasurementHeaderNdtBinding(@NonNull LinearLayout rootView,
      @NonNull TextView download, @NonNull TextView ping, @NonNull TextView server,
      @NonNull TextView upload) {
    this.rootView = rootView;
    this.download = download;
    this.ping = ping;
    this.server = server;
    this.upload = upload;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMeasurementHeaderNdtBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMeasurementHeaderNdtBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_measurement_header_ndt, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMeasurementHeaderNdtBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.download;
      TextView download = ViewBindings.findChildViewById(rootView, id);
      if (download == null) {
        break missingId;
      }

      id = R.id.ping;
      TextView ping = ViewBindings.findChildViewById(rootView, id);
      if (ping == null) {
        break missingId;
      }

      id = R.id.server;
      TextView server = ViewBindings.findChildViewById(rootView, id);
      if (server == null) {
        break missingId;
      }

      id = R.id.upload;
      TextView upload = ViewBindings.findChildViewById(rootView, id);
      if (upload == null) {
        break missingId;
      }

      return new FragmentMeasurementHeaderNdtBinding((LinearLayout) rootView, download, ping,
          server, upload);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
