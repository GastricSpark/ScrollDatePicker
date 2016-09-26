package com.harrywhewell.scrolldatepicker.sample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mDayPickerBtn;
    private Button mMonthPickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBar();
        initViews();
    }

    private void setUpActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.sample);
    }

    // Initialise button views
    private void initViews(){
        mDayPickerBtn = (Button) findViewById(R.id.day_date_picker_btn);
        mMonthPickerBtn = (Button) findViewById(R.id.month_date_picker_btn);
        setOnClickListeners();
    }

    // Set button views onClickListeners
    private void setOnClickListeners(){
        mDayPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start DayScrollDatePickerActivity
                startActivity( new Intent(MainActivity.this, DayScrollDatePickerActivity.class));
            }
        });

        mMonthPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start MonthScrollDatePickerActivity
                startActivity( new Intent(MainActivity.this, MonthScrollDatePickerActivity.class));
            }
        });
    }
}
