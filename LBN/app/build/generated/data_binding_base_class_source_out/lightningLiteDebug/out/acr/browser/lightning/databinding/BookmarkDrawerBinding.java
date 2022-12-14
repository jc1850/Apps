// Generated by view binder compiler. Do not edit!
package acr.browser.lightning.databinding;

import acr.browser.lightning.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BookmarkDrawerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView actionAddBookmark;

  @NonNull
  public final ImageView actionPageTools;

  @NonNull
  public final ImageView actionReading;

  @NonNull
  public final ImageView bookmarkBackButton;

  @NonNull
  public final RecyclerView bookmarkListView;

  @NonNull
  public final LinearLayout bookmarkTitleLayout;

  private BookmarkDrawerBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView actionAddBookmark, @NonNull ImageView actionPageTools,
      @NonNull ImageView actionReading, @NonNull ImageView bookmarkBackButton,
      @NonNull RecyclerView bookmarkListView, @NonNull LinearLayout bookmarkTitleLayout) {
    this.rootView = rootView;
    this.actionAddBookmark = actionAddBookmark;
    this.actionPageTools = actionPageTools;
    this.actionReading = actionReading;
    this.bookmarkBackButton = bookmarkBackButton;
    this.bookmarkListView = bookmarkListView;
    this.bookmarkTitleLayout = bookmarkTitleLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BookmarkDrawerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BookmarkDrawerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.bookmark_drawer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BookmarkDrawerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_add_bookmark;
      ImageView actionAddBookmark = ViewBindings.findChildViewById(rootView, id);
      if (actionAddBookmark == null) {
        break missingId;
      }

      id = R.id.action_page_tools;
      ImageView actionPageTools = ViewBindings.findChildViewById(rootView, id);
      if (actionPageTools == null) {
        break missingId;
      }

      id = R.id.action_reading;
      ImageView actionReading = ViewBindings.findChildViewById(rootView, id);
      if (actionReading == null) {
        break missingId;
      }

      id = R.id.bookmark_back_button;
      ImageView bookmarkBackButton = ViewBindings.findChildViewById(rootView, id);
      if (bookmarkBackButton == null) {
        break missingId;
      }

      id = R.id.bookmark_list_view;
      RecyclerView bookmarkListView = ViewBindings.findChildViewById(rootView, id);
      if (bookmarkListView == null) {
        break missingId;
      }

      id = R.id.bookmark_title_layout;
      LinearLayout bookmarkTitleLayout = ViewBindings.findChildViewById(rootView, id);
      if (bookmarkTitleLayout == null) {
        break missingId;
      }

      return new BookmarkDrawerBinding((LinearLayout) rootView, actionAddBookmark, actionPageTools,
          actionReading, bookmarkBackButton, bookmarkListView, bookmarkTitleLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
