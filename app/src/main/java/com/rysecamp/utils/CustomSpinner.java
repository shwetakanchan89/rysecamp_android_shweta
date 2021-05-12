package com.rysecamp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by Sahil Sharma on 10/10/18.
 */

public class CustomSpinner extends Spinner {
    private int prevPos = -1;
    private String itemText;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        try {
            if (position == getSelectedItemPosition() && prevPos == position) {
                if (getOnItemSelectedListener() != null) {
                    getOnItemSelectedListener().onItemSelected(null, null, position, 0);
                }
            }
        } catch (Exception ignored) {

        }

        prevPos = position;
    }
}