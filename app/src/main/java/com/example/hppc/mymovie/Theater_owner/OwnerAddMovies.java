package com.example.hppc.mymovie.Theater_owner;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerAddMovies extends AppCompatActivity implements  TimePickerDialog.OnTimeSetListener {
           private EditText price;
           private Button Addshow;
           private TextView show1,show2,show3,show4;
         private ProgressDialog progressDialog;
         public String format;
         SessionManagement sessionManagement;
         String Time1,Time2,Time3,Time4;
         String TM1,TM2,TM3,TM4;
    Boolean btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        SessionManagement sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();
        btn1 = btn2 = btn3 = btn4 = false;
        final String email = details.get( SessionManagement.Key_UserName );
        final String password=details.get(SessionManagement.Key_Password);
         progressDialog= new ProgressDialog(this);
        setContentView( R.layout.activity_owner_add_movies );
        price=findViewById( R.id.etMoviePrice);
        Addshow=findViewById(R.id.btnAddShow);
        show1=findViewById( R.id.tvshow1);
        show2=findViewById( R.id.tvshow2 );
        show3=findViewById( R.id.tvshow3 );
        show4=findViewById( R.id.tvshow4 );

           getTime();


           /********************code for Time set**********************************/

           show1.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   btn1 = true;
                   android.support.v4.app.DialogFragment timepicker=new TimePickerFragment();
                   timepicker.show( getSupportFragmentManager(),"time picker" );
               }
           } );

        show2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2 = true;
                android.support.v4.app.DialogFragment timepicker=new TimePickerFragment();
                timepicker.show( getSupportFragmentManager(),"time picker2" );

            }
        } );
        show3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3 = true;
                android.support.v4.app.DialogFragment timepicker=new TimePickerFragment();
                timepicker.show( getSupportFragmentManager(),"time picker3" );

            }
        } );
        show4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn4 = true;
                android.support.v4.app.DialogFragment timepicker=new TimePickerFragment();
                timepicker.show( getSupportFragmentManager(),"time picker4" );

            }
        } );


        /********************code for Time set**********************************/


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Spinner spinnerTheater = (Spinner) findViewById( R.id.spinnerTheater );
        final Spinner spinnerMovies = (Spinner) findViewById( R.id.spinnerMovies );

        final List<String> theater_name = new ArrayList<>();
        theater_name.add( "Select Theater" );

        final List<String> Film_name = new ArrayList<>();
        Film_name.add( "Select Film" );

        RequestQueue queue = Volley.newRequestQueue( OwnerAddMovies.this );
        queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/OwnerAddMovies.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    int i=0,j=0;
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.optJSONArray( "Theater_Name" );
                    for(i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        if(jsonObject1.get( "name" ).equals( "Film_Name" ))
                        {
                            break;
                        }
                        theater_name.add( jsonObject1.getString( "name" ) );
                    }

                    for(i = i+1;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        Film_name.add( jsonObject1.getString( "name" ) );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, null){
            public HashMap<String, String> getParams() {
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("email", email);
            return parameters;
        }

        } );

        ArrayAdapter<String> theater_name_data = new ArrayAdapter<String>( OwnerAddMovies.this,android.R.layout.simple_list_item_1,theater_name);
        theater_name_data.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerTheater.setAdapter( theater_name_data );

        ArrayAdapter<String> film_name_data = new ArrayAdapter<String>( OwnerAddMovies.this,android.R.layout.simple_list_item_1,Film_name);
        film_name_data.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerMovies.setAdapter( film_name_data );


        Addshow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String theater_Name = spinnerTheater.getSelectedItem().toString();
                final String movie_Name = spinnerMovies.getSelectedItem().toString();
                final String prices = price.getText().toString();
                progressDialog.setTitle("Adding Movies");
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Time1=show1.getText().toString();
                Time2=show2.getText().toString();
                Time3=show3.getText().toString();
                Time4=show4.getText().toString();
             //   Toast.makeText(OwnerAddMovies.this,Time1 +""+ Time2+" "+Time3+" "+Time4,Toast.LENGTH_LONG ).show();

                RequestQueue queue = Volley.newRequestQueue( OwnerAddMovies.this );
                queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/OwnerInsertMovies.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                           // int i=0,j=0;
                            JSONObject jsonObject = new JSONObject( response.toString() );
                           // Boolean success = jsonObject.getBoolean( "success" );
                            String re=jsonObject.getString( "success" );
                            if(re.equals( "success" ))
                            { progressDialog.dismiss();
                                Toast.makeText( getApplicationContext(),"Movie Added successfully..",Toast.LENGTH_SHORT).show();
                             }
                            else if(re.equals( "already" ))
                            {
                                progressDialog.dismiss();
                                Toast.makeText( getApplicationContext(),"Movie Already exist Please add show detils !",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText( getApplicationContext(),"Error Movie not Added !",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, null){
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("email", email);
                        parameters.put( "movie_name",movie_Name );
                        parameters.put( "theater_name",theater_Name );
                        parameters.put( "price",prices );
                        parameters.put( "time1",Time1 );
                        parameters.put( "time2",Time2 );
                        parameters.put( "time3",Time3 );
                        parameters.put( "time4",Time4 );
                        return parameters;
                    }

                } );

            }
        } );
    }

    private void getTime() {
        Calendar c = Calendar.getInstance();
        final int hours = c.get( Calendar.HOUR_OF_DAY );
        final int minute = c.get( Calendar.MINUTE );
        int OURS = hours;
        //selectTimeFormat(hour);
        if (hours == 0) {
            OURS = hours + 12;
            format = "AM";
        } else if (hours == 12) {
            format = "PM";
        } else if (hours > 12) {
            OURS = hours - 12;
            format = "PM";
        } else {
            format = "AM";
        }
        String o, m;
        if (OURS < 10)
            o = "0" + OURS;
        else
            o = "" + OURS;
        if (minute < 10)
            m = "0" + minute;
        else
            m = "" + minute;
        String CurrentTime = "" + o + ":" + m + " " + format;


        show1.setText( CurrentTime );
        show2.setText( CurrentTime );
        show3.setText( CurrentTime );
        show4.setText( CurrentTime );
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(btn1)
        {
            int OURSTime = hourOfDay;
            //selectTimeFormat(hourOfDay);
            if (hourOfDay == 0) {
                OURSTime = hourOfDay + 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                OURSTime = hourOfDay - 12;
                format = "PM";
            } else {
                format = "AM";
            }
            String o1, m1;
            if (OURSTime < 10)
                o1 = "0" + OURSTime;
            else
                o1 = "" + OURSTime;
            if (minute < 10)
                m1 = "0" + minute;
            else
                m1 = "" + minute;
            TM1= "" + o1 + ":" + m1 + " " + format;

            show1.setText( TM1 );
            btn1 = false;
        }
        if(btn2)
        {
            int OURSTime = hourOfDay;
            //selectTimeFormat(hourOfDay);
            if (hourOfDay == 0) {
                OURSTime = hourOfDay + 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                OURSTime = hourOfDay - 12;
                format = "PM";
            } else {
                format = "AM";
            }
            String o1, m1;
            if (OURSTime < 10)
                o1 = "0" + OURSTime;
            else
                o1 = "" + OURSTime;
            if (minute < 10)
                m1 = "0" + minute;
            else
                m1 = "" + minute;
            TM2= "" + o1 + ":" + m1 + " " + format;

            show2.setText( TM2 );
            btn2 = false;
        }
        if(btn3)
        {
            // TextView textView2=(TextView)findViewById( R.id.textView2 );
            int OURSTime = hourOfDay;
            //selectTimeFormat(hourOfDay);
            if (hourOfDay == 0) {
                OURSTime = hourOfDay + 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                OURSTime = hourOfDay - 12;
                format = "PM";
            } else {
                format = "AM";
            }
            String o1, m1;
            if (OURSTime < 10)
                o1 = "0" + OURSTime;
            else
                o1 = "" + OURSTime;
            if (minute < 10)
                m1 = "0" + minute;
            else
                m1 = "" + minute;
            TM3= "" + o1 + ":" + m1 + " " + format;


            show3.setText(TM3);
            btn3 = false;
        }
        if(btn4)
        {
            // TextView textView2=(TextView)findViewById( R.id.textView2 );
            int OURSTime = hourOfDay;
            //selectTimeFormat(hourOfDay);
            if (hourOfDay == 0) {
                OURSTime = hourOfDay + 12;
                format = "AM";
            } else if (hourOfDay == 12) {
                format = "PM";
            } else if (hourOfDay > 12) {
                OURSTime = hourOfDay - 12;
                format = "PM";
            } else {
                format = "AM";
            }
            String o1, m1;
            if (OURSTime < 10)
                o1 = "0" + OURSTime;
            else
                o1 = "" + OURSTime;
            if (minute < 10)
                m1 = "0" + minute;
            else
                m1 = "" + minute;
            TM4= "" + o1 + ":" + m1 + " " + format;
           show4.setText(TM4 );
            btn4 = false;
        }
    }
}
