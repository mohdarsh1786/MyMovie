package com.example.hppc.mymovie.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hppc.mymovie.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;



public class SelectDate extends AppCompatActivity {

    String movie_name,city_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_select_date );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        Bundle bundle = getIntent().getExtras();
        city_name = bundle.getString( "city_name" );
        movie_name = bundle.getString( "movie_name" );
        getSupportActionBar().setTitle( "  " + movie_name + " " );
          // Toast.makeText(SelectDate.this,city_name +""+ movie_name,Toast.LENGTH_LONG ).show();
          Date today = new Date();
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.add(Calendar.MONTH, 1);

        CalendarPickerView datePicker = findViewById(R.id.calendar);
        datePicker.init(today, nextMonth.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);

                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);



                String selectedDate = "" + calSelected.get(Calendar.YEAR)
                        + "-" + (calSelected.get(Calendar.MONTH) + 1)
                        + "-" + calSelected.get(Calendar.DAY_OF_MONTH);
           //    Toast.makeText(SelectDate.this, selectedDate, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SelectDate.this , User_TheaterDetails.class );
                intent.putExtra( "movie_name" , movie_name );
                intent.putExtra( "city_name" , city_name );
                intent.putExtra( "date",selectedDate );
                startActivity(intent);

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}