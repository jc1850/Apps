package de.storchp.fdroidbuildstatus.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableUtils {

    public static void setMenuIconTint(Context context, Menu menu, int itemId, int color) {
        var drawable = menu.findItem(itemId).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, color));
        menu.findItem(itemId).setIcon(drawable);
    }

    public static void setIconWithTint(Context context, ImageView imageView, int imageId, int color) {
        imageView.setImageDrawable(getTintedDrawable(context, imageId, color));
    }

    public static void setCompoundDrawablesLeft(Context context, TextView textView, int imageId, int color) {
        textView.setCompoundDrawablesWithIntrinsicBounds(getTintedDrawable(context, imageId, color), null, null, null);
    }

    public static void setCompoundDrawablesRight(Context context, TextView textView, int imageId, int color) {
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, getTintedDrawable(context, imageId, color), null);
    }

    public static Drawable getTintedDrawable(Context context, int imageId, int color) {
        if (imageId > 0) {
            var unwrappedDrawable = ContextCompat.getDrawable(context, imageId);
            Drawable wrappedDrawable;
            if (unwrappedDrawable != null) {
                wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, color);
                return wrappedDrawable;
            }
        }
        return null;
    }

}
