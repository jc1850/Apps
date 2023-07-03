// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.openobservatory.ooniprobe.R;

public final class ActivityInfoBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final Button blog;

  @NonNull
  public final Button dataPolicy;

  @NonNull
  public final Button learnMore;

  @NonNull
  public final Button reports;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView version;

  private ActivityInfoBinding(@NonNull CoordinatorLayout rootView, @NonNull Button blog,
      @NonNull Button dataPolicy, @NonNull Button learnMore, @NonNull Button reports,
      @NonNull Toolbar toolbar, @NonNull TextView version) {
    this.rootView = rootView;
    this.blog = blog;
    this.dataPolicy = dataPolicy;
    this.learnMore = learnMore;
    this.reports = reports;
    this.toolbar = toolbar;
    this.version = version;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_info, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInfoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.blog;
      Button blog = ViewBindings.findChildViewById(rootView, id);
      if (blog == null) {
        break missingId;
      }

      id = R.id.dataPolicy;
      Button dataPolicy = ViewBindings.findChildViewById(rootView, id);
      if (dataPolicy == null) {
        break missingId;
      }

      id = R.id.learnMore;
      Button learnMore = ViewBindings.findChildViewById(rootView, id);
      if (learnMore == null) {
        break missingId;
      }

      id = R.id.reports;
      Button reports = ViewBindings.findChildViewById(rootView, id);
      if (reports == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.version;
      TextView version = ViewBindings.findChildViewById(rootView, id);
      if (version == null) {
        break missingId;
      }

      return new ActivityInfoBinding((CoordinatorLayout) rootView, blog, dataPolicy, learnMore,
          reports, toolbar, version);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}