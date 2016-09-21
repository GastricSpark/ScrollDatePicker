package com.harrywhewell.scrolldatepicker.holder;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;

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

    private TypedArray attrs;
    private Context context;
    private LocalDate value;

    private boolean selected = false;

    public MonthScrollDatePickerViewHolder(Context context, TypedArray attrs, View itemView) {
        super(itemView);
        monthTextView = (TextView) itemView.findViewById(R.id.month_list_item_name);
        this.attrs = attrs;
        this.context = context;
        init();
    }

    private void init(){
        selectedBackground = setDrawableBackgroundColor(
                context.getResources().getDrawable(R.drawable.bg_circle_drawable),
                attrs.getColor(R.styleable.ScrollDatePicker_selectedColor,
                        context.getResources().getColor(R.color.default_selected)));

        background = setDrawableBackgroundColor(
                context.getResources().getDrawable(R.drawable.bg_circle_drawable),
                attrs.getColor(R.styleable.ScrollDatePicker_baseColor,
                        context.getResources().getColor(R.color.default_base)));

        selectedTextColor =  attrs.getColor(R.styleable.ScrollDatePicker_selectedTextColor,
                context.getResources().getColor(R.color.default_selected_text));

        baseTextColor =  attrs.getColor(R.styleable.ScrollDatePicker_selectedTextColor,
                context.getResources().getColor(R.color.default_selected_text));
    }

    private Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private void handleViewSelection(boolean selected){
        monthTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        monthTextView.setBackground(selected ? selectedBackground : background);
    }

    public void onBind(LocalDate value){
        this.value = value;

        monthTextView.setTextColor(baseTextColor);
        monthTextView.setBackground(background);
        monthTextView.setText(value.toString("MMM"));

        onClick();
    }

    private void onClick(){
       monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected){
                    selected = false;
                    handleViewSelection(false);
                }else{
                    selected = true;
                    handleViewSelection(true);
                }


            }
        });
    }

}
