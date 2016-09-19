package com.harrywhewell.scrolldatepicker.adapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;

import org.joda.time.LocalDate;

/**
 * Adapter to supply MonthScrollDatePicker with date data
 */
public class MonthScrollDatePickerAdapter extends RecyclerView.Adapter<MonthScrollDatePickerAdapter.MonthScrollDatePickerViewHolder>{

    private int selectedColor;
    private int selectedTextColor;
    private int baseColor;
    private int baseTextColor;

    private Drawable background;

    private LocalDate dateTime;
    private LocalDate endDate;

    private TextView mSelectedView;
    private LocalDate selectedDate;

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

        this.dateTime = new LocalDate();
    }


    public void setStartDate(int month, int year){
        this.dateTime = dateTime.withMonthOfYear(month).withYear(year);
    }

    public void setStartMonth(int month){
        this.dateTime = dateTime.withMonthOfYear(month);
    }

    public void setEndDate(int month, int year){
        this.endDate = endDate.withMonthOfYear(month).withYear(year);
    }

    public LocalDate getDate(){
        return selectedDate;
    }

    private Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private void deselectView(){
        mSelectedView.setTextColor(baseTextColor);
        mSelectedView.setBackground(setDrawableBackgroundColor(background, baseColor));
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
    public void onBindViewHolder(final MonthScrollDatePickerViewHolder holder, int position) {
        holder.monthTextView.setTextColor(baseTextColor);
        holder.monthTextView.setBackground(setDrawableBackgroundColor(background, baseColor));

        holder.monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectView();
                holder.monthTextView.setTextColor(selectedTextColor);
                holder.monthTextView.setBackground(setDrawableBackgroundColor(background, selectedColor));
                mSelectedView = holder.monthTextView;
                selectedDate = dateTime;
            }
        });
        this.dateTime = dateTime.plusMonths(position);
        holder.monthTextView.setText(dateTime.toString("MMM"));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
