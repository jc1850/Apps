// Generated by view binder compiler. Do not edit!
package org.adaway.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.adaway.R;

public final class HostsContentFragmentBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView hostsContentHeader;

  @NonNull
  public final TextView hostsContentText;

  @NonNull
  public final TextView hostsDescriptionText;

  @NonNull
  public final MaterialButton hostsOpenFile;

  @NonNull
  public final View separatorHeader;

  private HostsContentFragmentBinding(@NonNull ScrollView rootView,
      @NonNull TextView hostsContentHeader, @NonNull TextView hostsContentText,
      @NonNull TextView hostsDescriptionText, @NonNull MaterialButton hostsOpenFile,
      @NonNull View separatorHeader) {
    this.rootView = rootView;
    this.hostsContentHeader = hostsContentHeader;
    this.hostsContentText = hostsContentText;
    this.hostsDescriptionText = hostsDescriptionText;
    this.hostsOpenFile = hostsOpenFile;
    this.separatorHeader = separatorHeader;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static HostsContentFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HostsContentFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.hosts_content_fragment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HostsContentFragmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hosts_content_header;
      TextView hostsContentHeader = ViewBindings.findChildViewById(rootView, id);
      if (hostsContentHeader == null) {
        break missingId;
      }

      id = R.id.hosts_content_text;
      TextView hostsContentText = ViewBindings.findChildViewById(rootView, id);
      if (hostsContentText == null) {
        break missingId;
      }

      id = R.id.hosts_description_text;
      TextView hostsDescriptionText = ViewBindings.findChildViewById(rootView, id);
      if (hostsDescriptionText == null) {
        break missingId;
      }

      id = R.id.hosts_open_file;
      MaterialButton hostsOpenFile = ViewBindings.findChildViewById(rootView, id);
      if (hostsOpenFile == null) {
        break missingId;
      }

      id = R.id.separator_header;
      View separatorHeader = ViewBindings.findChildViewById(rootView, id);
      if (separatorHeader == null) {
        break missingId;
      }

      return new HostsContentFragmentBinding((ScrollView) rootView, hostsContentHeader,
          hostsContentText, hostsDescriptionText, hostsOpenFile, separatorHeader);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}