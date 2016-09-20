package com.harrywhewell.scrolldatepicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.R;
import com.harrywhewell.scrolldatepicker.adapter.MonthScrollDatePickerAdapter;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * Date picker which displays the months of the year in a horizontal list.
 * If no end date is set the date picker will continue to infinity.
 */
public class MonthScrollDatePicker extends LinearLayout implements MonthScrollDatePickerAdapter.MonthScrollDatePickerListener{

    private TextView mYearTextView;
    private TextView mFullDateTextView;
    private RecyclerView mMonthRecyclerView;

    private MonthScrollDatePickerAdapter adapter;

    private int selectedColor;
    private int selectedTextColor;
    private int baseColor;
    private int baseTextColor;

    private boolean showTitle;
    private boolean showFullDate;

    public MonthScrollDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.date_picker_scroll_month, this, true);
        getViewElements();
        setAttributeValues(context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScrollDatePicker, 0, 0));
        initView();
    }

    /**
     * Get all the view elements from date_picker_scroll_month layout.
     */
    private void getViewElements(){
        mYearTextView = (TextView) findViewById(R.id.date_picker_scroll_month_year);
        mFullDateTextView = (TextView) findViewById(R.id.date_picker_scroll_month_full_date);
        mMonthRecyclerView = (RecyclerView) findViewById(R.id.date_picker_scroll_month_recycler_view);
    }

    /**
     * If attribute values have been set, get them.
     * If not get default values.
     * @param a styled attributes
     */
    private void setAttributeValues(TypedArray a){
        try{
            selectedColor = a.getColor(R.styleable.ScrollDatePicker_selectedColor, getResources().getColor(R.color.default_selected));
            selectedTextColor = a.getColor(R.styleable.ScrollDatePicker_selectedTextColor, getResources().getColor(R.color.default_selected_text));
            baseColor = a.getColor(R.styleable.ScrollDatePicker_baseColor, getResources().getColor(R.color.default_base));
            baseTextColor = a.getColor(R.styleable.ScrollDatePicker_baseTextColor, getResources().getColor(R.color.default_base_text));
            showTitle = a.getBoolean(R.styleable.ScrollDatePicker_showTitle, true);
            showFullDate = a.getBoolean(R.styleable.ScrollDatePicker_showFullDate, true);
        } finally {
            a.recycle();
        }
    }

    /**
     * Set values onto view elements.
     */
    private void initView(){
        setShowTitle(this.showTitle);
        setShowFullDate(this.showFullDate);
        setTextColor();
        initRecyclerView();
    }

    /**
     * set up Recycler view and its adapter
     */
    private void initRecyclerView(){
        adapter = new MonthScrollDatePickerAdapter(getContext(),
                selectedColor,
                selectedTextColor,
                baseColor,
                baseTextColor, this);
        mMonthRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mMonthRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Set values onto view elements.
     * @param show boolean, if true show title
     */
    private void setShowTitle(boolean show){
        if(show){
            mYearTextView.setVisibility(VISIBLE);
        }else{
            mYearTextView.setVisibility(GONE);
        }
    }

    /**
     * Set values onto view elements.
     * @param show boolean, if true show full date
     */
    private void setShowFullDate(boolean show){
        if(show){
            mFullDateTextView.setVisibility(VISIBLE);
        }else{
            mFullDateTextView.setVisibility(GONE);
        }
    }

    /**
     * Sets the text color of full date and title.
     */
    private void setTextColor(){
        mYearTextView.setTextColor(baseTextColor);
        mFullDateTextView.setTextColor(baseTextColor);
    }

    /**
     * Gets the current selected date as a Date.
     * @return selected Date
     * e.g. 00/01/2016
     */
    public Date getSelectedDate(){
        LocalDate selectedLocalDate =  adapter.getDate();
        return selectedLocalDate.toDate();

    }

    /**
     * Gets the current selected date value as a String.
     * @return selected date as a String
     * e.g. "00/01/2016"
     */
    public String getSelectedDateAsString(){
        LocalDate selectedLocalDate =  adapter.getDate();
        return selectedLocalDate.toString("dd/MM/yyyy");
    }

    /**
     * Gets the current selected month name as a String.
     * e.g. "January"
     * @return selected month name as String
     */
    public String getSelectedMonthName(){
        LocalDate selectedLocalDate =  adapter.getDate();
        return selectedLocalDate.toString("MMMM");
    }

    /**
     * Gets the current selected month short name as a String.
     * e.g. "Jan"
     * @return selected month short name as String
     */
    public String getSelectedMonthNameShort(){
        LocalDate selectedLocalDate =  adapter.getDate();
        return selectedLocalDate.toString("MMM");
    }

    /**
     * Sets the start month on the MonthScrollDatePicker.
     * Presumes year as local.
     * @param month start month
     */
    public void setStartMonth(int month){
        adapter.setStartMonth(month);
        adapter.notifyDataSetChanged();
    }

    /**
     * Sets the start date on the MonthScrollDatePicker
     * @param month start month
     * @param year start year
     */
    public void setStartDate(int month, int year){
        adapter.setStartDate(month, year);
        adapter.notifyDataSetChanged();
    }

    /**
     * Sets the end date of the MonthScrollDatePicker.
     * @param month end month
     * @param year end year
     */
    public void setEndDate(int month, int year){
        adapter.setEndDate(month, year);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onYearInteraction(LocalDate date) {
        mYearTextView.setText(date.toString("yyyy"));
    }

    @Override
    public void onFullDateInteraction(LocalDate date) {
        mFullDateTextView.setText((date.toString("MMMM") + " " + date.toString("yyyy")));
    }
}
