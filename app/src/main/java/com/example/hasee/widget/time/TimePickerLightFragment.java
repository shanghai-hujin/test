package com.example.hasee.widget.time;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.hasee.R;
import com.tr4android.support.extension.picker.time.AppCompatTimePicker;
import com.tr4android.support.extension.picker.time.AppCompatTimePickerDialog;

import java.util.Calendar;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/24 16:52
 */

@SuppressLint("ValidFragment")
public class TimePickerLightFragment extends DialogFragment
        implements AppCompatTimePickerDialog.OnTimeSetListener {

    private OnTimeSetLinstener mOnTimeSetLinstener;

    public TimePickerLightFragment(OnTimeSetLinstener onTimeSetLinstener) {
        this.mOnTimeSetLinstener = onTimeSetLinstener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new AppCompatTimePickerDialog(getActivity(), R.style.Theme_AppCompat_TimeBluePickerDialog,
                this, hour, minute, false);
    }

    @Override
    public void onTimeSet(AppCompatTimePicker view, int hourOfDay, int minute) {
        mOnTimeSetLinstener.onTimeSet(hourOfDay,minute);
    }
}
