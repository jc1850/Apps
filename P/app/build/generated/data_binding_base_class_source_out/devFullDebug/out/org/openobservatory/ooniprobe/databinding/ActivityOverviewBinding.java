// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.openobservatory.ooniprobe.R;

public final class ActivityOverviewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button customUrl;

  @NonNull
  public final TextView desc;

  @NonNull
  public final ImageView icon;

  @NonNull
  public final TextView lastTime;

  @NonNull
  public final Button run;

  @NonNull
  public final TextView runtime;

  @NonNull
  public final Toolbar toolbar;

  private ActivityOverviewBinding(@NonNull LinearLayout rootView, @NonNull Button customUrl,
      @NonNull TextView desc, @NonNull ImageView icon, @NonNull TextView lastTime,
      @NonNull Button run, @NonNull TextView runtime, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.customUrl = customUrl;
    this.desc = desc;
    this.icon = icon;
    this.lastTime = lastTime;
    this.run = run;
    this.runtime = runtime;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOverviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOverviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_overview, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOverviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.customUrl;
      Button customUrl = ViewBindings.findChildViewById(rootView, id);
      if (customUrl == null) {
        break missingId;
      }

      id = R.id.desc;
      TextView desc = ViewBindings.findChildViewById(rootView, id);
      if (desc == null) {
        break missingId;
      }

      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.lastTime;
      TextView lastTime = ViewBindings.findChildViewById(rootView, id);
      if (lastTime == null) {
        break missingId;
      }

      id = R.id.run;
      Button run = ViewBindings.findChildViewById(rootView, id);
      if (run == null) {
        break missingId;
      }

      id = R.id.runtime;
      TextView runtime = ViewBindings.findChildViewById(rootView, id);
      if (runtime == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityOverviewBinding((LinearLayout) rootView, customUrl, desc, icon, lastTime,
          run, runtime, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}