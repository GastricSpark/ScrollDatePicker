package com.harrywhewell.scrolldatepicker.Interfaces;

import android.support.annotation.Nullable;

import org.joda.time.LocalDate;

public interface OnChildDateSelectedListener {
    void onDateSelectedChild(@Nullable LocalDate date);
}
