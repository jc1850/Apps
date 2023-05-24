// Generated by view binder compiler. Do not edit!
package de.storchp.fdroidbuildstatus.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import de.storchp.fdroidbuildstatus.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ListView buildList;

  @NonNull
  public final Chip chipCycle;

  @NonNull
  public final Chip chipFavourite;

  @NonNull
  public final ChipGroup chipGroup;

  @NonNull
  public final HorizontalScrollView chipScrollView;

  @NonNull
  public final Chip chipStatus;

  @NonNull
  public final RelativeLayout contentMain;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final SwipeRefreshLayout pullToRefresh;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull ListView buildList,
      @NonNull Chip chipCycle, @NonNull Chip chipFavourite, @NonNull ChipGroup chipGroup,
      @NonNull HorizontalScrollView chipScrollView, @NonNull Chip chipStatus,
      @NonNull RelativeLayout contentMain, @NonNull ProgressBar progressBar,
      @NonNull SwipeRefreshLayout pullToRefresh) {
    this.rootView = rootView;
    this.buildList = buildList;
    this.chipCycle = chipCycle;
    this.chipFavourite = chipFavourite;
    this.chipGroup = chipGroup;
    this.chipScrollView = chipScrollView;
    this.chipStatus = chipStatus;
    this.contentMain = contentMain;
    this.progressBar = progressBar;
    this.pullToRefresh = pullToRefresh;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.build_list;
      ListView buildList = ViewBindings.findChildViewById(rootView, id);
      if (buildList == null) {
        break missingId;
      }

      id = R.id.chip_cycle;
      Chip chipCycle = ViewBindings.findChildViewById(rootView, id);
      if (chipCycle == null) {
        break missingId;
      }

      id = R.id.chip_favourite;
      Chip chipFavourite = ViewBindings.findChildViewById(rootView, id);
      if (chipFavourite == null) {
        break missingId;
      }

      id = R.id.chipGroup;
      ChipGroup chipGroup = ViewBindings.findChildViewById(rootView, id);
      if (chipGroup == null) {
        break missingId;
      }

      id = R.id.chipScrollView;
      HorizontalScrollView chipScrollView = ViewBindings.findChildViewById(rootView, id);
      if (chipScrollView == null) {
        break missingId;
      }

      id = R.id.chip_status;
      Chip chipStatus = ViewBindings.findChildViewById(rootView, id);
      if (chipStatus == null) {
        break missingId;
      }

      RelativeLayout contentMain = (RelativeLayout) rootView;

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.pullToRefresh;
      SwipeRefreshLayout pullToRefresh = ViewBindings.findChildViewById(rootView, id);
      if (pullToRefresh == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, buildList, chipCycle, chipFavourite,
          chipGroup, chipScrollView, chipStatus, contentMain, progressBar, pullToRefresh);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}