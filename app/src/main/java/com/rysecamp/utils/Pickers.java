package com.rysecamp.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sahil Sharma on 10/10/18.
 */

public class Pickers {

    public static Date date;

    public static void datePicker(Context context, final Long datePickerDate, Long minDate, Long maxDate, final ClickListener clickListener) {

        Calendar c = Calendar.getInstance();
        if (datePickerDate != null)
            c.setTimeInMillis(datePickerDate);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date = (parseDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year + " " + mHour + ":" + mMinute));
                        clickListener.selectedDate(date);
                    }
                }, mYear, mMonth, mDay);
        if (minDate != null)
            datePickerDialog.getDatePicker().setMinDate(minDate);
        if (maxDate != null)
            datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
    }

    public static void timePicker(Context context, Long timePickerDate, final ClickListener clickListener) {

        final Calendar c = Calendar.getInstance();
        if (timePickerDate != null)
            c.setTimeInMillis(timePickerDate);
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH) + 1;
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        /*int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);*/

        final Calendar mcurrentTime = Calendar.getInstance();
        final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minute = mcurrentTime.get(Calendar.MINUTE);
        final CustomTimePickerDialog timePickerDialog;

        timePickerDialog = new CustomTimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        clickListener.selectedDate(parseDate(mDay + "/" + mMonth + "/" + mYear + " " + hourOfDay + ":" + minute));
                    }
                }, hour, minute, false, date);

        timePickerDialog.setMin(hour, minute);
        timePickerDialog.show();
        timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE)
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            clickListener.selectedDate(null);
                                            timePickerDialog.dismiss();
                                        }
                                    }
                );
    }

    public static void timePicker(Context context, final ClickListener clickListener) {

        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH) + 1;
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final CustomTimePickerDialog timePickerDialog;

        timePickerDialog = new CustomTimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        clickListener.selectedDate(parseDate(mDay + "/" + mMonth + "/" + mYear + " " + hourOfDay + ":" + minute));
                    }
                }, hour, minute, false, date);

        timePickerDialog.show();
        timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE)
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            clickListener.selectedDate(null);
                                            timePickerDialog.dismiss();
                                        }
                                    }
                );
    }

    public static void dateTimePicker(final Context context, Long datePickerDate, Long minDate, Long maxDate, final ClickListener clickListener) {
        datePicker(context, datePickerDate, minDate, maxDate, new ClickListener() {
            @Override
            public void selectedDate(Date value) {
                clickListener.selectedDate(value);
                Log.e("StartDate_Pickers", value.toString());
                timePicker(context, value.getTime(), new ClickListener() {
                    @Override
                    public void selectedDate(Date value) {
                        clickListener.selectedDate(value);
                    }
                });
            }
        });
    }

    private static Date parseDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HH:mm", Locale.getDefault());
        try {
            return dateFormat.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public interface ClickListener {
        void selectedDate(Date value);
    }

}
