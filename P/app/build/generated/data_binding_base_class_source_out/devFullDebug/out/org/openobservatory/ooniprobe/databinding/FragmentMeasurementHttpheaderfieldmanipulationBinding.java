// Generated by view binder compiler. Do not edit!
package org.openobservatory.ooniprobe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import org.openobservatory.ooniprobe.R;

public final class FragmentMeasurementHttpheaderfieldmanipulationBinding implements ViewBinding {
  @NonNull
  private final TextView rootView;

  @NonNull
  public final TextView desc;

  private FragmentMeasurementHttpheaderfieldmanipulationBinding(@NonNull TextView rootView,
      @NonNull TextView desc) {
    this.rootView = rootView;
    this.desc = desc;
  }

  @Override
  @NonNull
  public TextView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMeasurementHttpheaderfieldmanipulationBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMeasurementHttpheaderfieldmanipulationBinding inflate(
      @NonNull LayoutInflater inflater, @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_measurement_httpheaderfieldmanipulation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMeasurementHttpheaderfieldmanipulationBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    TextView desc = (TextView) rootView;

    return new FragmentMeasurementHttpheaderfieldmanipulationBinding((TextView) rootView, desc);
  }
}