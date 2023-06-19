// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class ItemFailedBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView icon;

  @NonNull
  public final TextView startTime;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView testName;

  private ItemFailedBinding(@NonNull LinearLayout rootView, @NonNull ImageView icon,
      @NonNull TextView startTime, @NonNull TextView subtitle, @NonNull TextView testName) {
    this.rootView = rootView;
    this.icon = icon;
    this.startTime = startTime;
    this.subtitle = subtitle;
    this.testName = testName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemFailedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemFailedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_failed, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemFailedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.startTime;
      TextView startTime = ViewBindings.findChildViewById(rootView, id);
      if (startTime == null) {
        break missingId;
      }

      id = R.id.subtitle;
      TextView subtitle = ViewBindings.findChildViewById(rootView, id);
      if (subtitle == null) {
        break missingId;
      }

      id = R.id.testName;
      TextView testName = ViewBindings.findChildViewById(rootView, id);
      if (testName == null) {
        break missingId;
      }

      return new ItemFailedBinding((LinearLayout) rootView, icon, startTime, subtitle, testName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
