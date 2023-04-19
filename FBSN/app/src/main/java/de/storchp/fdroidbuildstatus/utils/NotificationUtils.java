package de.storchp.fdroidbuildstatus.utils;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import de.storchp.fdroidbuildstatus.MainActivity;
import de.storchp.fdroidbuildstatus.R;

public class NotificationUtils {

    private static final String CHANNEL_ID = "general";
    private static final String TAG = NotificationUtils.class.getSimpleName();

    public static void createNewBuildNotification(Context context, String text) {
        Log.d(TAG, "new notification: " + text);
        var notificationManager = getNotificationManager(context);
        if (!notificationManager.areNotificationsEnabled()) {
            Log.w(TAG, "notifications disabled");
            return;
        }

        var pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), new Intent(context, MainActivity.class), PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_fdroid_buildstatus)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());
    }

    public static void cancelNotification(Context context) {
        getNotificationManager(context).cancel(0);
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }

    public static boolean areNotificationsEnabled(Context context) {
        return getNotificationManager(context).areNotificationsEnabled();
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = new NotificationChannel(CHANNEL_ID, context.getString(R.string.channel_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(context.getString(R.string.channel_description));
            channel.enableVibration(true);
            getNotificationManager(context).createNotificationChannel(channel);
        }
    }

}
