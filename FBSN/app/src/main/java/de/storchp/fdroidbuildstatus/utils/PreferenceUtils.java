package de.storchp.fdroidbuildstatus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import de.storchp.fdroidbuildstatus.R;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.model.BuildlogType;
import de.storchp.fdroidbuildstatus.model.MetadataLinkType;
import de.storchp.fdroidbuildstatus.model.StatusFilter;

public class PreferenceUtils {

    private static final String TAG = PreferenceUtils.class.getSimpleName();

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isUpdateCheckEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(context.getString(R.string.PREF_UPDATE_CHECK), true);
    }

    public static void setUpdateCheckEnabled(Context context, boolean enabled) {
        putBoolean(context, R.string.PREF_UPDATE_CHECK, true);
    }

    public static boolean isNotifyFavouritesOnly(Context context) {
        return getSharedPreferences(context).getBoolean(context.getString(R.string.PREF_NOTIFY_FAVOURITES_ONLY), false);
    }

    public static boolean isShowFavouritesWithoutBuildStatus(Context context) {
        return getSharedPreferences(context).getBoolean(context.getString(R.string.PREF_SHOW_UNKNWON_FAVOURITE_BUILDS), true);
    }

    public static void setShowFavouritesWithoutBuildStatus(Context context, boolean show) {
        putBoolean(context, R.string.PREF_SHOW_UNKNWON_FAVOURITE_BUILDS, show);
    }

    public static BuildCycle getBuildCycleFilter(Context context) {
        try {
            return BuildCycle.valueOf(getSharedPreferences(context).getString(context.getString(R.string.PREF_BUILD_CYCLE_FILTER), BuildCycle.NONE.toString()));
        } catch (Exception ignored) {
            return BuildCycle.NONE;
        }
    }

    public static void setBuildCycleFilter(Context context, BuildCycle buildCycle) {
        putString(context, R.string.PREF_BUILD_CYCLE_FILTER, buildCycle.name());
    }

    public static MetadataLinkType getMetadataLinkType(Context context) {
        try {
            return MetadataLinkType.valueOf(getSharedPreferences(context).getString(context.getString(R.string.PREF_METADATA_LINK_TYPE), MetadataLinkType.ASK.toString()));
        } catch (Exception ignored) {
            return MetadataLinkType.ASK;
        }
    }

    public static void setMetadataLinkType(Context context, MetadataLinkType metadataLinkType) {
        putString(context, R.string.PREF_METADATA_LINK_TYPE, metadataLinkType.name());
    }

    public static BuildlogType getBuildlogType(Context context) {
        try {
            return BuildlogType.valueOf(getSharedPreferences(context).getString(context.getString(R.string.PREF_BUILDLOG_TYPE), BuildlogType.ASK.toString()));
        } catch (Exception ignored) {
            return BuildlogType.ASK;
        }
    }

    public static void setBuildlogType(Context context, BuildlogType buildlogType) {
        putString(context, R.string.PREF_BUILDLOG_TYPE, buildlogType.name());
    }

    public static long getUpdateInterval(Context context) {
        try {
            return Long.parseLong(getSharedPreferences(context).getString(context.getString(R.string.PREF_UPDATE_INTERVAL), String.valueOf(Constants.TIME.TWO_HOURS)));
        } catch (NumberFormatException ignored) {
            return Constants.TIME.TWO_HOURS;
        }
    }

    public static BuildCycle getBuildCycleCheck(Context context) {
        try {
            return BuildCycle.valueOf(getSharedPreferences(context).getString(context.getString(R.string.PREF_BUILD_CYCLE_CHECK), BuildCycle.BUILD.toString()));
        } catch (Exception ignored) {
            return BuildCycle.BUILD;
        }
    }

    /**
     * @return {@link androidx.appcompat.app.AppCompatDelegate}.MODE_*
     */
    public static int getDefaultNightMode(Context context) {
        var defaultValue = context.getString(R.string.night_mode_default);
        var value = getSharedPreferences(context).getString(context.getString(R.string.night_mode_key), defaultValue);
        return Integer.parseInt(value);
    }

    public static void applyDefaultNightMode(Context context) {
        applyNightMode(getDefaultNightMode(context));
    }

    public static void applyNightMode(int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    private static void putBoolean(Context context, int key, boolean value) {
        var editor = getSharedPreferences(context).edit();
        editor.putBoolean(context.getString(key), value);
        editor.apply();
    }

    private static void putString(Context context, int key, String value) {
        var editor = getSharedPreferences(context).edit();
        if (value == null) {
            editor.remove(context.getString(key));
        } else {
            editor.putString(context.getString(key), value.trim());
        }
        editor.apply();
    }

    private static void putLong(Context context, int key, long value) {
        var editor = getSharedPreferences(context).edit();
        editor.putLong(context.getString(key), value);
        editor.apply();
    }

    public static long getLastUpdateLoaded(Context context) {
        return getSharedPreferences(context).getLong(context.getString(R.string.PREF_LAST_UPDATE_LOADED), 0L);
    }

    public static void setLastUpdateLoaded(Context context, long lastUpdateLoaded) {
        putLong(context, R.string.PREF_LAST_UPDATE_LOADED, lastUpdateLoaded);
    }

    public static void setRepoIndexLoaded(Context context) {
        putBoolean(context, R.string.PREF_REPO_INDEX_LOADED, true);
    }

    public static boolean isRepoIndexLoaded(Context context) {
        return getSharedPreferences(context).getBoolean(context.getString(R.string.PREF_REPO_INDEX_LOADED), false);
    }

    public static int getMaxLogLines(Context context) {
        return Integer.parseInt(getSharedPreferences(context).getString(context.getString(R.string.PREF_MAX_LOG_LINES), "1000"));
    }

    public static StatusFilter getStatusFilter(Context context) {
        try {
            return StatusFilter.valueOf(getSharedPreferences(context).getString(context.getString(R.string.PREF_STATUS_FILTER), StatusFilter.NONE.toString()));
        } catch (Exception ignored) {
            return StatusFilter.NONE;
        }
    }

    public static void setStatusFilter(Context context, StatusFilter statusFilter) {
        putString(context, R.string.PREF_STATUS_FILTER, statusFilter.name());
    }

}
