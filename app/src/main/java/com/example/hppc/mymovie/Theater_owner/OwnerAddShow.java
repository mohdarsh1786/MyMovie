package com.example.hppc.mymovie.Theater_owner;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;


public class OwnerAddShow extends AppCompatActivity {
    private Button Addshows;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    private EditText Show_Time1,Show_Time2,Show_Time3,Show_Time4;
    String ShowTime1=" ",ShowTime2=" ",ShowTime3=" ",ShowTime4=" ";
    private EditText No_of_Seat1,No_of_Seat2,No_of_Seat3,No_of_Seat4;


    String NUmberofSeat1=" ",NUmberofSeat2=" ",NUmberofSeat3=" ",NUmberofSeat4=" ";
    String theater_name,movie_name;

    ProgressDialog progressDialog;
    private Button AddAllShow;

    static final int DILOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_owner_add_show );


        Bundle bundle = getIntent().getExtras();

         theater_name = bundle.getString( "theater_name" );
         movie_name = bundle.getString( "movie_name" );

       // Toast.makeText( getApplicationContext() , theater_name+" "+movie_name , Toast.LENGTH_LONG ).show();

        checkBox1=findViewById( R.id.checkBoxShow1 );
        checkBox2=findViewById( R.id.checkBoxShow2 );
        checkBox3=findViewById( R.id.checkBoxShow3 );
        checkBox4=findViewById( R.id.checkBoxShow4 );

        Show_Time1=findViewById(R.id.etTimeShow1);
        Show_Time2=findViewById(R.id.etTimeShow2);
        Show_Time3=findViewById(R.id.etTimeShow3);
        Show_Time4=findViewById(R.id.etTimeShow4);

        No_of_Seat1=findViewById(R.id.etNumberofSeat1 );
        No_of_Seat2=findViewById(R.id.etNumberofSeat2 );
        No_of_Seat3=findViewById(R.id.etNumberofSeat3 );
        No_of_Seat4=findViewById(R.id.etNumberofSeat4 );

        progressDialog =new ProgressDialog( this );
        AddAllShow=findViewById(R.id.btnAddAllshow);
        addListnerToCheckBox1();
       addListnerToCheckBox2();
        addListnerToCheckBox3();
       addListnerToCheckBox4();
        addListnerOnButton();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showDialogonButtonClick();
    }

     public void showDialogonButtonClick(){
        Addshows=findViewById(R.id.btnPickDate);

        Addshows.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        } );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }

    //*********************************For Checkboxses functionality**********************************************
    public void addListnerToCheckBox1(){
        checkBox1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked()){
                    Show_Time1.setVisibility( View.VISIBLE );
                    No_of_Seat1.setVisibility(View.VISIBLE  );
                     }
            }
        });
    }
    public void addListnerToCheckBox2(){
        checkBox2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked()){
                    Show_Time2.setVisibility( View.VISIBLE );
                    No_of_Seat2.setVisibility(View.VISIBLE  );
                  }
            }
        });
    }
    public void addListnerToCheckBox3(){
        checkBox3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked()){
                    Show_Time3.setVisibility( View.VISIBLE );
                    No_of_Seat3.setVisibility(View.VISIBLE  );
                     }
            }
        });
    }
    public void addListnerToCheckBox4(){
        checkBox4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox)v).isChecked()){
                    Show_Time4.setVisibility( View.VISIBLE );
                    No_of_Seat4.setVisibility(View.VISIBLE  );
                    // Toast.makeText(check_box.this,"Show1 is checked",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void addListnerOnButton(){

        AddAllShow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  if(checkBox1.isChecked())
                  {
                      ShowTime1=Show_Time1.getText().toString();
                      NUmberofSeat1=No_of_Seat1.getText().toString();}

                if(checkBox2.isChecked())
                {
                    ShowTime2=Show_Time2.getText().toString();
                    NUmberofSeat2=No_of_Seat2.getText().toString();
                }
               if(checkBox3.isChecked())
                {
                    ShowTime3=Show_Time3.getText().toString();
                    NUmberofSeat3=No_of_Seat3.getText().toString();
                }
                if(checkBox4.isChecked())
                {
                    ShowTime4=Show_Time4.getText().toString();
                    NUmberofSeat4=No_of_Seat4.getText().toString();
                }//Toast.makeText(OwnerAddShow.this,Show_Date,Toast.LENGTH_LONG).show();
               progressDialog.setMessage("wait...Adding show!");
              progressDialog.show();
             //   Toast.makeText(OwnerAddShow.this,movie_name+" "+theater_name+" "+ShowTime1+" "+NUmberofSeat1+" "+ShowTime2+" "+NUmberofSeat2+" "+ShowTime3+" "+NUmberofSeat3+" "+ShowTime4+" "+NUmberofSeat4,Toast.LENGTH_LONG ).show();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
              //  Toast.makeText(OwnerAddShow.this,"inside on click",Toast.LENGTH_LONG).show();
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Owner_Add_Shows.php", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(OwnerAddShow.this,"in Response",Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean( "success" );
                            if(result)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Show Successfully Added.", Toast.LENGTH_LONG).show();
                            }
                            else
                            {progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Error in Adding show ! Try Again...", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, null) {
                                        public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();

                        parameters.put("theater_name",theater_name);
                        parameters.put("movie_name",movie_name);
                        parameters.put("ShowTime1", ShowTime1);
                        parameters.put("NUmberofSeat1", NUmberofSeat1);
                        parameters.put("ShowTime2", ShowTime2);
                        parameters.put("NUmberofSeat2", NUmberofSeat2);
                        parameters.put("ShowTime3", ShowTime3);
                        parameters.put("NUmberofSeat3", NUmberofSeat3);
                        parameters.put("ShowTime4", ShowTime4);
                        parameters.put("NUmberofSeat4", NUmberofSeat4);

                        return parameters;
                    }
                });
            }
        } );
    }

}
