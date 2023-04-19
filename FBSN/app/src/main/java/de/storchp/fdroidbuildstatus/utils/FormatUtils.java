package de.storchp.fdroidbuildstatus.utils;

import android.net.Uri;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {

    public static String formatShortDateTime(long millis) {
        if (millis > 0 && millis < 10000000000L) {
            // for some files we get seconds instead of millis
            millis *= 1000;
        }
        return new SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()).format(new Date(millis));
    }

    public static String formatVersion(String versionCode, String versionName) {
        return (isEmpty(versionCode) ? "" : versionCode)
                + (isEmpty(versionName) ? "" : " - " + versionName);
    }

    public static boolean isEmpty(final String value) {
        return value == null || value.trim().length() == 0;
    }

    @NonNull
    public static String getNameFromSource(String sourceCode) {
        try {
            return Uri.parse(sourceCode).getLastPathSegment();
        } catch (Exception ignored) {
        }
        return "";
    }

}
