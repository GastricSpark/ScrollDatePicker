package com.harrywhewell.scrolldatepicker.holder;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;
import com.harrywhewell.scrolldatepicker.model.Style;

import org.joda.time.LocalDate;

/**
 *  ViewHolder for month_list_item layout
 */
public class MonthScrollDatePickerViewHolder extends RecyclerView.ViewHolder{

    public TextView monthTextView;

    private int selectedTextColor;
    private int baseTextColor;
    private Drawable selectedBackground;
    private Drawable background;

    private LocalDate value;
    public int positon = getAdapterPosition();

    public MonthScrollDatePickerViewHolder(Style style, View itemView) {
        super(itemView);
        monthTextView = (TextView) itemView.findViewById(R.id.month_list_item_name);
        init(style);
    }

    private void init(Style style){
        selectedTextColor = style.getSelectedTextColor();
        baseTextColor = style.getBaseTextColor();
        selectedBackground = style.getSelectedBackground();
        background = style.getBackground();
    }

    public void handleViewSelection(boolean selected){
        monthTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        monthTextView.setBackground(selected ? selectedBackground : background);

    }

    public void onBind(LocalDate value){
        this.value = value;
        Log.d("LOG", value.toString());

        monthTextView.setTextColor(baseTextColor);
        monthTextView.setBackground(background);
        monthTextView.setText(value.toString("MMM"));

    }

}
