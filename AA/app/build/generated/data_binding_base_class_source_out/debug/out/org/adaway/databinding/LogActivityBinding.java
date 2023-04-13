// Generated by view binder compiler. Do not edit!
package org.adaway.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.adaway.R;

public final class LogActivityBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final CoordinatorLayout coordinator;

  @NonNull
  public final TextView emptyTextView;

  @NonNull
  public final RecyclerView logList;

  @NonNull
  public final SwipeRefreshLayout swipeRefresh;

  @NonNull
  public final FloatingActionButton toggleLogRecording;

  private LogActivityBinding(@NonNull CoordinatorLayout rootView,
      @NonNull CoordinatorLayout coordinator, @NonNull TextView emptyTextView,
      @NonNull RecyclerView logList, @NonNull SwipeRefreshLayout swipeRefresh,
      @NonNull FloatingActionButton toggleLogRecording) {
    this.rootView = rootView;
    this.coordinator = coordinator;
    this.emptyTextView = emptyTextView;
    this.logList = logList;
    this.swipeRefresh = swipeRefresh;
    this.toggleLogRecording = toggleLogRecording;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LogActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LogActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.log_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LogActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CoordinatorLayout coordinator = (CoordinatorLayout) rootView;

      id = R.id.emptyTextView;
      TextView emptyTextView = ViewBindings.findChildViewById(rootView, id);
      if (emptyTextView == null) {
        break missingId;
      }

      id = R.id.log_list;
      RecyclerView logList = ViewBindings.findChildViewById(rootView, id);
      if (logList == null) {
        break missingId;
      }

      id = R.id.swipeRefresh;
      SwipeRefreshLayout swipeRefresh = ViewBindings.findChildViewById(rootView, id);
      if (swipeRefresh == null) {
        break missingId;
      }

      id = R.id.toggle_log_recording;
      FloatingActionButton toggleLogRecording = ViewBindings.findChildViewById(rootView, id);
      if (toggleLogRecording == null) {
        break missingId;
      }

      return new LogActivityBinding((CoordinatorLayout) rootView, coordinator, emptyTextView,
          logList, swipeRefresh, toggleLogRecording);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
