package com.harrywhewell.scrolldatepicker.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;

import org.joda.time.LocalDate;
import org.joda.time.Months;

/**
 * Adapter to supply MonthScrollDatePicker with date data
 */
public class MonthScrollDatePickerAdapter extends RecyclerView.Adapter<MonthScrollDatePickerAdapter.MonthScrollDatePickerViewHolder>{

    private int selectedTextColor;
    private int baseTextColor;

    private Drawable background;
    private Drawable selectedBackground;

    private LocalDate startDate;
    private LocalDate endDate;

    private MonthScrollDatePickerListener listener;

    private TextView mSelectedView;
    private LocalDate dateTime;


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

    public MonthScrollDatePickerAdapter(Context context, int selectedColor, int selectedTextColor,
                                        int baseColor, int baseTextColor, MonthScrollDatePickerListener listener) {
        this.selectedTextColor = selectedTextColor;
        this.baseTextColor = baseTextColor;
        this.background = setDrawableBackgroundColor(context.getResources().getDrawable(R.drawable.bg_circle_drawable), baseColor);
        this.selectedBackground = setDrawableBackgroundColor(context.getResources().getDrawable(R.drawable.bg_circle_drawable), selectedColor);
        this.listener = listener;
        this.startDate = new LocalDate();
        listener.onFullDateInteraction(startDate);
    }

    public void setStartDate(int month, int year){
        this.startDate = startDate.withMonthOfYear(month).withYear(year);
        listener.onFullDateInteraction(startDate);
    }

    public void setStartMonth(int month){
        this.startDate = startDate.withMonthOfYear(month);
        listener.onFullDateInteraction(startDate);
    }

    public void setEndDate(int month, int year){
        this.endDate = startDate.withMonthOfYear(month).withYear(year);
    }

    public LocalDate getDate(){
        return dateTime;
    }

    private Drawable setDrawableBackgroundColor(Drawable drawable, int color){
        GradientDrawable gradientDrawable = (GradientDrawable) drawable.mutate();
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private void handleView(boolean selected){
        mSelectedView.setTextColor(selected ? selectedTextColor : baseTextColor);
        mSelectedView.setBackground(selected ? selectedBackground : background);
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
    public void onBindViewHolder(final MonthScrollDatePickerViewHolder holder,int position) {
        holder.monthTextView.setTextColor(baseTextColor);
        holder.monthTextView.setBackground(background);
        dateTime = startDate.plusMonths(position);
        holder.monthTextView.setText(dateTime.toString("MMM"));

        holder.monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSelectedView != null){
                    handleView(false);
                }
                mSelectedView = holder.monthTextView;
                handleView(true);
                listener.onFullDateInteraction(startDate.plusMonths(holder.getAdapterPosition()));
            }
        });
        listener.onYearInteraction(dateTime);
    }

    @Override
    public int getItemCount() {
        if(endDate != null){
            return Months.monthsBetween(startDate, endDate).getMonths() + 1;
        }else{
            return Integer.MAX_VALUE;
        }

    }

    public interface MonthScrollDatePickerListener{
        void onYearInteraction(LocalDate date);
        void onFullDateInteraction(LocalDate date);
    }
}
