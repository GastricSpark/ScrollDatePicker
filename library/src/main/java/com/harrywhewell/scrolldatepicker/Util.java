package com.harrywhewell.scrolldatepicker;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

 class Util {
    public static Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
}
