// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.openobservatory.ooniprobe.R;

public final class ActivityProxyBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout customProxyHostname;

  @NonNull
  public final TextInputLayout customProxyPort;

  @NonNull
  public final RadioGroup customProxyRadioGroup;

  @NonNull
  public final RadioButton customProxySOCKS5;

  @NonNull
  public final RadioButton proxyCustom;

  @NonNull
  public final TextView proxyFooter;

  @NonNull
  public final RadioButton proxyNone;

  @NonNull
  public final RadioButton proxyPsiphon;

  @NonNull
  public final RadioGroup proxyRadioGroup;

  private ActivityProxyBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputLayout customProxyHostname, @NonNull TextInputLayout customProxyPort,
      @NonNull RadioGroup customProxyRadioGroup, @NonNull RadioButton customProxySOCKS5,
      @NonNull RadioButton proxyCustom, @NonNull TextView proxyFooter,
      @NonNull RadioButton proxyNone, @NonNull RadioButton proxyPsiphon,
      @NonNull RadioGroup proxyRadioGroup) {
    this.rootView = rootView;
    this.customProxyHostname = customProxyHostname;
    this.customProxyPort = customProxyPort;
    this.customProxyRadioGroup = customProxyRadioGroup;
    this.customProxySOCKS5 = customProxySOCKS5;
    this.proxyCustom = proxyCustom;
    this.proxyFooter = proxyFooter;
    this.proxyNone = proxyNone;
    this.proxyPsiphon = proxyPsiphon;
    this.proxyRadioGroup = proxyRadioGroup;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProxyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProxyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_proxy, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProxyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.customProxyHostname;
      TextInputLayout customProxyHostname = ViewBindings.findChildViewById(rootView, id);
      if (customProxyHostname == null) {
        break missingId;
      }

      id = R.id.customProxyPort;
      TextInputLayout customProxyPort = ViewBindings.findChildViewById(rootView, id);
      if (customProxyPort == null) {
        break missingId;
      }

      id = R.id.customProxyRadioGroup;
      RadioGroup customProxyRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (customProxyRadioGroup == null) {
        break missingId;
      }

      id = R.id.customProxySOCKS5;
      RadioButton customProxySOCKS5 = ViewBindings.findChildViewById(rootView, id);
      if (customProxySOCKS5 == null) {
        break missingId;
      }

      id = R.id.proxyCustom;
      RadioButton proxyCustom = ViewBindings.findChildViewById(rootView, id);
      if (proxyCustom == null) {
        break missingId;
      }

      id = R.id.proxyFooter;
      TextView proxyFooter = ViewBindings.findChildViewById(rootView, id);
      if (proxyFooter == null) {
        break missingId;
      }

      id = R.id.proxyNone;
      RadioButton proxyNone = ViewBindings.findChildViewById(rootView, id);
      if (proxyNone == null) {
        break missingId;
      }

      id = R.id.proxyPsiphon;
      RadioButton proxyPsiphon = ViewBindings.findChildViewById(rootView, id);
      if (proxyPsiphon == null) {
        break missingId;
      }

      id = R.id.proxyRadioGroup;
      RadioGroup proxyRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (proxyRadioGroup == null) {
        break missingId;
      }

      return new ActivityProxyBinding((ConstraintLayout) rootView, customProxyHostname,
          customProxyPort, customProxyRadioGroup, customProxySOCKS5, proxyCustom, proxyFooter,
          proxyNone, proxyPsiphon, proxyRadioGroup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}