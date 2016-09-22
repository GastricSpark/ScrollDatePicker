package com.harrywhewell.scrolldatepicker.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.harrywhewell.scrolldatepicker.R;
import com.harrywhewell.scrolldatepicker.holder.MonthScrollDatePickerViewHolder;
import com.harrywhewell.scrolldatepicker.model.Style;

import org.joda.time.LocalDate;
import org.joda.time.Months;

/**
 * Adapter to supply MonthScrollDatePicker with date data
 */
public class MonthScrollDatePickerAdapter extends RecyclerView.Adapter<MonthScrollDatePickerViewHolder>{

    private LocalDate startDate;
    private LocalDate endDate;

    private OnYearInteractionListener listener;

    private Style style;

    private Integer selectedPosition = null;


    public MonthScrollDatePickerAdapter(Style style, OnYearInteractionListener listener) {
        this.listener = listener;
        this.startDate = new LocalDate();
        this.style = style;
        listener.onYearInteraction(startDate);
    }

    public void setStartDate(int month, int year){
        this.startDate = startDate.withMonthOfYear(month).withYear(year);
        listener.onYearInteraction(startDate);
    }

    public void setStartMonth(int month){
        this.startDate = startDate.withMonthOfYear(month);
        listener.onYearInteraction(startDate);
    }

    public void setEndDate(int month, int year){
        this.endDate = startDate.withMonthOfYear(month).withYear(year);
    }
    @Override
    public MonthScrollDatePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.month_list_item,
                parent,
                false);
        return new MonthScrollDatePickerViewHolder(style, itemView);
    }

    @Override
    public void onBindViewHolder(final MonthScrollDatePickerViewHolder holder, final int position) {
        LocalDate dateTime = startDate.plusMonths(position);
        holder.onBind(dateTime);
        listener.onYearInteraction(dateTime);
        holder.handleViewSelection(false);

        holder.monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPosition != null){
                    notifyItemChanged(selectedPosition);
                }
                holder.handleViewSelection(true);
                selectedPosition = holder.getAdapterPosition();


            }
        });
    }

    @Override
    public int getItemCount() {
        if(endDate != null){
            return Months.monthsBetween(startDate, endDate).getMonths() + 1;
        }else{
            return Integer.MAX_VALUE;
        }

    }

    public interface OnYearInteractionListener{
        void onYearInteraction(LocalDate date);
    }

    //TODO implement onFullDateInteraction
    public interface OnFullDateInteractionListener{
        void onFullDateInteraction(LocalDate date);
    }

}
