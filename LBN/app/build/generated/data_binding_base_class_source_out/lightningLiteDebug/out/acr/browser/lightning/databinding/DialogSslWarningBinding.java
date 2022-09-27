// Generated by view binder compiler. Do not edit!
package acr.browser.lightning.databinding;

import acr.browser.lightning.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogSslWarningBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CheckBox checkBoxDontAskAgain;

  private DialogSslWarningBinding(@NonNull LinearLayout rootView,
      @NonNull CheckBox checkBoxDontAskAgain) {
    this.rootView = rootView;
    this.checkBoxDontAskAgain = checkBoxDontAskAgain;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSslWarningBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSslWarningBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_ssl_warning, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSslWarningBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.checkBoxDontAskAgain;
      CheckBox checkBoxDontAskAgain = ViewBindings.findChildViewById(rootView, id);
      if (checkBoxDontAskAgain == null) {
        break missingId;
      }

      return new DialogSslWarningBinding((LinearLayout) rootView, checkBoxDontAskAgain);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
