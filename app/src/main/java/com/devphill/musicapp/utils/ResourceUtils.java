package com.devphill.musicapp.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ResourceUtils {

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int toPixels(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / 160f));
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public static float toDips(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

}
