package com.example.telcoapp.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.telcoapp.R;

public class DrawableUtils {

    private DrawableUtils() {

    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.black
                    ), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
