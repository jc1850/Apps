package org.openobservatory.ooniprobe.common;

import android.app.Activity;

import org.jetbrains.annotations.NotNull;

public class ThirdPartyServices {
    public static void reloadConsents(Application app) {
    }

    public static void logException(Exception e){
        // does nothing
    }

    public static boolean shouldShowOnboardingCrash() {
        return false;
    }

    public static void acceptCrash(Application app) {
        //no need to set false, it's already the default value
    }
    
    public static void checkUpdates(Activity activity){
        // does nothing
    }

    public static void addLogExtra(final @NotNull String key, final @NotNull String value){
        // does nothing
    }
}
