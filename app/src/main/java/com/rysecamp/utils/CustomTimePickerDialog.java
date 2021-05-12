package com.rysecamp.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sahil Sharma on 10/10/18.
 */

class CustomTimePickerDialog extends TimePickerDialog {
    private static Date cDate;
    private static Date mDate;
    private int minHour = -1;
    private int minMinute = -1;
    private int maxHour = 25;
    private int maxMinute = 25;
    private int currentHour = 0;
    private int currentMinute = 0;

    CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView, Date date) {
        super(context, callBack, hourOfDay, minute, is24HourView);
        currentHour = hourOfDay;
        currentMinute = minute;
        mDate = date;

        try {
            Class<?> superclass = getClass().getSuperclass();
            Field mTimePickerField = superclass.getDeclaredField("mTimePicker");
            mTimePickerField.setAccessible(true);
            TimePicker mTimePicker = (TimePicker) mTimePickerField.get(this);
            mTimePicker.setOnTimeChangedListener(this);
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException ignored) {
        }
    }


    void setMin(int hour, int minute) {
        minHour = hour;
        minMinute = minute;
    }

    public void setMax(int hour, int minute) {
        maxHour = hour;
        maxMinute = minute;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        boolean validTime = true;
        Calendar calendar = Calendar.getInstance();
        cDate = calendar.getTime();
        if ((hourOfDay < minHour || (hourOfDay == minHour && minute < minMinute))) {
            validTime = false;
        }

        /*if (hourOfDay > maxHour || (hourOfDay == maxHour && minute > maxMinute) && mDate.after(cDate)) {
            validTime = false;
        }*/


        if (validTime) {
            currentHour = hourOfDay;
            currentMinute = minute;
            getButton(TimePickerDialog.BUTTON_POSITIVE).setEnabled(true);
        } else
            getButton(TimePickerDialog.BUTTON_POSITIVE).setEnabled(false);

        updateTime(currentHour, currentMinute);
    }

}