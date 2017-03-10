package com.example.daniel.feedtest;

import android.content.Context;

/**
 * Created by Daniel on 17/5/2016.
 */
public class Utils {

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

}
