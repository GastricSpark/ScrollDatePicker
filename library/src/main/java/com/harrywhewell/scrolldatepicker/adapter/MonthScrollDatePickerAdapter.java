package com.harrywhewell.scrolldatepicker.adapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;

/**
 * Adapter to supply MonthScrollDatePicker with date data
 */
public class MonthScrollDatePickerAdapter extends RecyclerView.Adapter<MonthScrollDatePickerAdapter.MonthScrollDatePickerViewHolder>{

    private int selectedColor;
    private int selectedTextColor;
    private int baseColor;
    private int baseTextColor;

    private Drawable background;

    private boolean selected = false;

    /**
     *  ViewHolder for month_list_item layout
     */
    class MonthScrollDatePickerViewHolder extends RecyclerView.ViewHolder{

        public TextView monthTextView;

        public MonthScrollDatePickerViewHolder(View itemView) {
            super(itemView);
            monthTextView = (TextView) itemView.findViewById(R.id.month_list_item_name);
        }
    }

    public MonthScrollDatePickerAdapter(int selectedColor, int selectedTextColor, int baseColor, int baseTextColor, Drawable background) {
        this.selectedColor = selectedColor;
        this.selectedTextColor = selectedTextColor;
        this.baseColor = baseColor;
        this.baseTextColor = baseTextColor;
        this.background = background;
    }


    public void setStartDate(){

    }

    public void setStartMonth(){

    }

    public void setEndDate(){

    }

    private Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    @Override
    public MonthScrollDatePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.month_list_item,
                parent,
                false);
        return new MonthScrollDatePickerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MonthScrollDatePickerViewHolder holder, int position) {
        holder.monthTextView.setTextColor(selected ? selectedTextColor : baseTextColor);
        holder.monthTextView.setBackground(selected ?
                setDrawableBackgroundColor(background, selectedColor) :
                setDrawableBackgroundColor(background, baseColor));

        holder.monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
