package com.example.hasee.widget.time;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.hasee.R;
import com.tr4android.support.extension.picker.date.AppCompatDatePicker;
import com.tr4android.support.extension.picker.date.AppCompatDatePickerDialog;

import java.util.Calendar;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/24 16:02
 */

@SuppressLint("ValidFragment")
public class DatePickerDarkFragment extends DialogFragment
        implements AppCompatDatePickerDialog.OnDateSetListener {

    private OnDataSetLinstener onDataSetLinstener;

    public DatePickerDarkFragment(OnDataSetLinstener onDataSetLinstener) {
        this.onDataSetLinstener = onDataSetLinstener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of AppCompatDatePickerDialog and return it
        return new AppCompatDatePickerDialog(getActivity(),
                R.style.Theme_AppCompat_DatePickerDialog, this, year, month, day);
    }


    @Override
    public void onDateSet(AppCompatDatePicker view, int year, int month, int day, String firstWeek) {
        onDataSetLinstener.onDataSet(year,month,day,firstWeek);
    }
}
