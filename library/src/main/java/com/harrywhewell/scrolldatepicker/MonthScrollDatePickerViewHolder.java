package com.harrywhewell.scrolldatepicker;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.joda.time.LocalDate;

/**
 *  ViewHolder for month_list_item layout
 */
 class MonthScrollDatePickerViewHolder extends RecyclerView.ViewHolder{

    public TextView monthTextView;

    private int selectedTextColor;
    private int baseTextColor;
    private Drawable selectedBackground;
    private Drawable background;

    private OnChildClickedListener clickedListener;
    private static OnChildDateSelectedListener dateSelectedListener;


    public MonthScrollDatePickerViewHolder(Style style, View itemView) {
        super(itemView);
        monthTextView = (TextView) itemView.findViewById(R.id.month_list_item_name);
        init(style);
        dateSelectedListener.onDateSelectedChild(null);

    }

    private void init(Style style){
        selectedTextColor = style.getSelectedTextColor();
        baseTextColor = style.getBaseTextColor();
        selectedBackground = style.getSelectedBackground();
        background = style.getBackground();
    }

    public void styleViewSection(boolean selected){
        monthTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        monthTextView.setBackground(selected ? selectedBackground : background);

    }

    public static void onDateSelected(OnChildDateSelectedListener listener){
        dateSelectedListener = listener;
    }

    public void onBind(final LocalDate value, OnChildClickedListener listener){
        this.clickedListener = listener;
        clickedListener.onChildClick(false);

        Log.d("LOG", value.toString());

        styleViewSection(false);
        monthTextView.setText(value.toString("MMM"));


        monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                styleViewSection(true);
                clickedListener.onChildClick(true);
                dateSelectedListener.onDateSelectedChild(value);
            }
        });
    }

}
