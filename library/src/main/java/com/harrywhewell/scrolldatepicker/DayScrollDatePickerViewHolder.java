package com.harrywhewell.scrolldatepicker;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.joda.time.LocalDate;

 class DayScrollDatePickerViewHolder extends RecyclerView.ViewHolder{

    public TextView dayNameTextView;
    public TextView dayValueTextView;

    private int selectedTextColor;
    private int baseTextColor;
    private Drawable selectedBackground;
    private Drawable background;

    private OnChildClickedListener clickedListener;
    private static OnChildDateSelectedListener dateSelectedListener;

    public DayScrollDatePickerViewHolder(Style style, View itemView) {
        super(itemView);
        dayNameTextView = (TextView) itemView.findViewById(R.id.day_name);
        dayValueTextView = (TextView) itemView.findViewById(R.id.day_value);
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
        dayNameTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        dayValueTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        dayValueTextView.setBackground(selected ? selectedBackground : background);

    }

    public static void onDateSelected(OnChildDateSelectedListener listener){
        dateSelectedListener = listener;
    }

    public void onBind(final LocalDate value, OnChildClickedListener listener){
        this.clickedListener = listener;
        clickedListener.onChildClick(false);

        Log.d("LOG", value.toString());

        styleViewSection(false);
        dayNameTextView.setText(value.toString("EEE"));
        dayValueTextView.setText(value.toString("dd"));


        dayValueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                styleViewSection(true);
                clickedListener.onChildClick(true);
                dateSelectedListener.onDateSelectedChild(value);
            }
        });
    }
}
