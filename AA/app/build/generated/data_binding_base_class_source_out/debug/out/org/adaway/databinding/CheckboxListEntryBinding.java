// Generated by view binder compiler. Do not edit!
package org.adaway.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.adaway.R;

public final class CheckboxListEntryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CheckBox checkboxListCheckbox;

  @NonNull
  public final TextView checkboxListText;

  private CheckboxListEntryBinding(@NonNull ConstraintLayout rootView,
      @NonNull CheckBox checkboxListCheckbox, @NonNull TextView checkboxListText) {
    this.rootView = rootView;
    this.checkboxListCheckbox = checkboxListCheckbox;
    this.checkboxListText = checkboxListText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CheckboxListEntryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CheckboxListEntryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.checkbox_list_entry, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CheckboxListEntryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.checkbox_list_checkbox;
      CheckBox checkboxListCheckbox = ViewBindings.findChildViewById(rootView, id);
      if (checkboxListCheckbox == null) {
        break missingId;
      }

      id = R.id.checkbox_list_text;
      TextView checkboxListText = ViewBindings.findChildViewById(rootView, id);
      if (checkboxListText == null) {
        break missingId;
      }

      return new CheckboxListEntryBinding((ConstraintLayout) rootView, checkboxListCheckbox,
          checkboxListText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}