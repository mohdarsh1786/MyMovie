package com.example.hppc.mymovie.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.MainActivity;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;
import com.example.hppc.mymovie.Userdashboard2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Book_show extends AppCompatActivity {
     private TextView theater_namefinal,movie_namefinal,datefinal,numberofseatfinal,total_pay,show_Time;
    String TheaterName,MovieName,Date,numberofseat,Show_Time,Show_Id;
    String Total_money;
   int price,no_of_seat;
     ArrayList<Integer> arrayList;
   private Button payfinalamount;
    SessionManagement sessionManagement;
   int TotalCost;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_book_show );
        sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        final String email = details.get( SessionManagement.Key_UserName );
        Bundle bundle=getIntent().getExtras();
        TheaterName=bundle.getString( "TheaterName" );
        MovieName=bundle.getString( "Movie_name" );
        Date=bundle.getString( "Date" );
       Show_Time=bundle.getString( "show_Time" );
        Show_Id=bundle.getString( "show_id" );
        price=bundle.getInt( "price" ) ;
         //Toast.makeText( Book_show.this,"time " +Show_Time+ price,Toast.LENGTH_LONG).show();
        Intent i = getIntent();
        getSupportActionBar().setTitle( "  " + TheaterName + "-"+MovieName+" " );
        arrayList = (ArrayList<Integer>) getIntent().getSerializableExtra( "seat_no" );
         no_of_seat=arrayList.size();
         numberofseat=String.valueOf( no_of_seat );

        TotalCost=no_of_seat*price;
        Total_money=String.valueOf( TotalCost );
        progressDialog=new ProgressDialog( this );

        //Toast.makeText(Book_show.this,TheaterName+" "+MovieName+" "+Date+" " +String.valueOf( no_of_seat )+" "+Show_Id+" "+Show_Time,Toast.LENGTH_LONG).show();
        theater_namefinal=findViewById( R.id.tvthname );
        show_Time=findViewById( R.id.tvShowTimeFinal );
        movie_namefinal=findViewById( R.id.tvMovieName );
        datefinal=findViewById( R.id.tvshowdate );
        numberofseatfinal=findViewById( R.id.tvNumberofseat);
        total_pay=findViewById( R.id.tvTotal_Money );
        payfinalamount=findViewById( R.id.btnPay);
         theater_namefinal.setText( TheaterName );
         movie_namefinal.setText( MovieName );
         datefinal.setText( Date );
         numberofseatfinal.setText( numberofseat );
         show_Time.setText(  Show_Time);
         total_pay.setText( Total_money );
        payfinalamount.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage( "Booking Ticket..." );
                progressDialog.show();
                RequestQueue requestQueue= Volley.newRequestQueue( getApplicationContext() );
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/BookTicket.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject( response);
                            Boolean Result=jsonObject.getBoolean( "success" );
                            if(Result)
                            {
                                progressDialog.dismiss();
                               // Toast.makeText(Book_show.this,"Your Booking is Successful !",Toast.LENGTH_LONG ).show();
                                startActivity( new Intent( Book_show.this,BookingSuccessfull.class ) );
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(Book_show.this,"Sorry Booking Not Successful Try Again !",Toast.LENGTH_LONG ).show();
                            }
                        }
                        catch(Exception e)
                        {
                            e.getStackTrace();
                        }
                     }
                },null ){
                    public HashMap<String,String> getParams(){
                        HashMap<String,String> params=new HashMap<>();

                        params.put("TheaterName",TheaterName);
                        params.put("MovieName",MovieName);
                        params.put("Date",Date);
                        params.put("showName",Show_Id );
                        params.put("email",email);
                        //Seat id..........
                        params.put("numberOfSeat",String.valueOf(arrayList.size()));
                        for(int i =0 ;i <arrayList.size();i++){
                            params.put("s"+i,String.valueOf(arrayList.get(i)));
                        }
                        return params;
                    }
            } );
    }
});
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate( R.menu.menu3, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.CancelTicket: {
                Intent intent = new Intent( Book_show.this, CancelTicket.class );
                startActivity( intent );
                finish();
                break;
            }
            case R.id.LogOutMenu3: {


                sessionManagement.logOutUser();
                Intent intent = new Intent( Book_show.this, MainActivity.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }
            case android.R.id.home: {
                onBackPressed();

            }


        }
        return super.onOptionsItemSelected( item );
    }

}
