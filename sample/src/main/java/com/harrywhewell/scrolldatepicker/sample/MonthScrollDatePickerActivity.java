package com.harrywhewell.scrolldatepicker.sample;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.harrywhewell.scrolldatepicker.MonthScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;

public class MonthScrollDatePickerActivity extends AppCompatActivity {

    private MonthScrollDatePicker mPicker;
    private TextView mValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_scroll_date_picker);
        setUpActionBar();
        initViews();
        getValue();
    }

    private void setUpActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.month_scroll_date_picker);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    // Initialise views
    private void initViews (){
        mPicker = (MonthScrollDatePicker) findViewById(R.id.month_date_picker);
        mValueTextView = (TextView) findViewById(R.id.value_text_view);
        setUpPicker();
    }

    private void setUpPicker(){
        mPicker.setStartDate(11, 2016); // set start date for the picker
        mPicker.setEndDate(3, 2018); // set end date for the picker
    }

    private void getValue(){
        mPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if(date != null){
                    mValueTextView.setText(date.toString()); // set picker selected value onto textView
                }
            }
        });
    }
}
