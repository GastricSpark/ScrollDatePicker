package com.harrywhewell.scrolldatepicker.util;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public  class Util {
    public static Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
}
