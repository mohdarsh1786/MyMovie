package com.example.hppc.mymovie.Theater_owner;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by HP PC on 23-04-2018.
 */

public class TimePickerFragment extends DialogFragment  {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int Hour=c.get(Calendar.HOUR_OF_DAY);
        int Minute=c.get(Calendar.MINUTE);
        return new TimePickerDialog( getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),Hour,Minute,DateFormat.is24HourFormat( getActivity() ));
    }

}
