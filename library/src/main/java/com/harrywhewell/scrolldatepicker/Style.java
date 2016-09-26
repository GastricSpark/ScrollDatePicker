package com.harrywhewell.scrolldatepicker;

import android.graphics.drawable.Drawable;

 class Style {
    private int selectedColor;
    private int baseColor;
    private int selectedTextColor;
    private int baseTextColor;
    private Drawable background;
    private Drawable selectedBackground;

    public Style(int selectedColor, int baseColor, int selectedTextColor, int baseTextColor, Drawable background, Drawable selectedBackground) {
        this.selectedColor = selectedColor;
        this.baseColor = baseColor;
        this.selectedTextColor = selectedTextColor;
        this.baseTextColor = baseTextColor;
        this.background = background;
        this.selectedBackground = selectedBackground;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public int getBaseColor() {
        return baseColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public int getBaseTextColor() {
        return baseTextColor;
    }

    public Drawable getBackground() {
        return background;
    }

    public Drawable getSelectedBackground() {
        return selectedBackground;
    }
}
