package com.harrywhewell.scrolldatepicker;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.LocalDate;

/**
 * Date picker which displays the days of the month in a horizontal list.
 * If no end date is set the date picker will continue to infinity.
 */
public class DayScrollDatePicker  extends LinearLayout implements TitleValueCallback, OnChildDateSelectedListener {

    private TextView mMonthTextView;
    private TextView mFullDateTextView;
    private RecyclerView mDayRecyclerView;

    private int mBaseTextColor;

    private DayScrollDatePickerAdapter mAdapter;

    private boolean mShowTitle;
    private boolean mShowFullDate;

    private OnDateSelectedListener mListener;

    private Style mStyle;

    public DayScrollDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.date_picker_scroll_day, this, true);
        getViewElements();
        setAttributeValues(context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScrollDatePicker, 0, 0));
        DayScrollDatePickerViewHolder.onDateSelected(this);
        initView();
    }

    /**
     * Get all the view elements from date_picker_scroll_day layout.
     */
    private void getViewElements(){
        mMonthTextView = (TextView) findViewById(R.id.date_picker_scroll_day_month);
        mFullDateTextView = (TextView) findViewById(R.id.date_picker_scroll_day_full_date);
        mDayRecyclerView = (RecyclerView) findViewById(R.id.date_picker_scroll_day_recycler_view);
    }

    /**
     * If attribute values have been set, get them.
     * If not get default values.
     * @param a styled attributes
     */
    private void setAttributeValues(TypedArray a){
        int baseColor;
        int selectedTextColor;
        int selectedColor;
        try{
            mBaseTextColor = a.getColor(R.styleable.ScrollDatePicker_baseTextColor,
                    getResources().getColor(R.color.default_base_text));
            baseColor = a.getColor(R.styleable.ScrollDatePicker_baseColor,
                    getResources().getColor(R.color.default_base));
            selectedTextColor = a.getColor(R.styleable.ScrollDatePicker_selectedTextColor,
                    getResources().getColor(R.color.default_selected_text));
            selectedColor = a.getColor(R.styleable.ScrollDatePicker_selectedColor,
                    getResources().getColor(R.color.default_selected));

            mShowTitle = a.getBoolean(R.styleable.ScrollDatePicker_showTitle, true);
            mShowFullDate = a.getBoolean(R.styleable.ScrollDatePicker_showFullDate, true);

        } finally {
            a.recycle();
        }

        Drawable background = Util.setDrawableBackgroundColor(
                getResources().getDrawable(R.drawable.bg_circle_drawable), baseColor);
        Drawable selectedBackground = Util.setDrawableBackgroundColor(
                getResources().getDrawable(R.drawable.bg_circle_drawable), selectedColor);
        mStyle = new Style(selectedColor, baseColor, selectedTextColor, mBaseTextColor, background, selectedBackground);
    }

    /**
     * Set values onto view elements.
     */
    private void initView(){
        setShowTitle(this.mShowTitle);
        setShowFullDate(this.mShowFullDate);
        setTextColor();
        initRecyclerView();
    }

    /**
     * set up Recycler view and its adapter
     */
    private void initRecyclerView(){
        mAdapter = new DayScrollDatePickerAdapter(mStyle, this);
        mDayRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDayRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Set values onto view elements.
     * @param show boolean, if true show title
     */
    private void setShowTitle(boolean show){
        if(show){
            mMonthTextView.setVisibility(VISIBLE);
        }else{
            mMonthTextView.setVisibility(GONE);
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
        mMonthTextView.setTextColor(mBaseTextColor);
        mFullDateTextView.setTextColor(mBaseTextColor);
    }

    /**
     * Sets the start date on the DayScrollPicker
     * @param day start day e.g. 28
     * @param month start month e.g. 10
     * @param year start year e.g. 2010
     */
    public void setStartDate(int day, int month, int year){
        mAdapter.setStartDate(day, month, year);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Sets the end date on the DayScrollPicker
     * @param day start day e.g. 28
     * @param month start month e.g. 10
     * @param year start year e.g. 2010
     */
    public void setEndDate(int day, int month, int year){
        mAdapter.setEndDate(day, month, year);
        mAdapter.notifyDataSetChanged();;
    }

    /**
     * Gets the current selected date as a Date.
     */
    public void getSelectedDate(OnDateSelectedListener listener){
        this.mListener = listener;
    }


    @Override
    public void onDateSelectedChild(@Nullable LocalDate date) {
        if(date != null) {
            mFullDateTextView.setText(String.format("%s %s %s", date.toString("dd"),
                    date.toString("MMMM"), date.toString("yyyy")));
            mListener.onDateSelected(date.toDate());
        }
    }

    @Override
    public void onTitleValueReturned(LocalDate date) {
        mMonthTextView.setText(date.toString("MMMM"));
    }
}
