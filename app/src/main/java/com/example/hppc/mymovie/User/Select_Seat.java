package com.example.hppc.mymovie.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.movieListAdapter;
import com.example.hppc.mymovie.movies_info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Select_Seat extends AppCompatActivity  {

    private TextView Number_of_vacant_seat,ticketPrice;
    private Spinner spinnerNoOfTicket;
    String price = null;
    String itemselect;
    String total1;
    int intprice,total;
    private Button book_show;
    String TheaterName,show_name,Date,movie_name;
    private TextView Total_Price;
    private List<String> seat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Bundle bundle=getIntent().getExtras();
         TheaterName=bundle.getString( "Theater_name" );
         show_name=bundle.getString( "show");
         Date=bundle.getString( "Date" );
         movie_name=bundle.getString( "movie_name" );
        setContentView( R.layout.activity_select__seat );

        Number_of_vacant_seat=findViewById(R.id.tvNoOfSeat);
        ticketPrice=findViewById(R.id.tvTicketPrice );
        book_show=findViewById( R.id.btn_book );
        spinnerNoOfTicket=findViewById(R.id.spinner_NumberofSeat );
        Total_Price=findViewById(R.id.tvTotalPrice );
        seat=new ArrayList<>();


        RequestQueue queue1= Volley.newRequestQueue(getApplicationContext());
        queue1.add(new StringRequest(Request.Method.POST,"https://mdarshnitc.000webhostapp.com/select_seat.php",new Response.Listener<String>()
        {
            public void onResponse(String Response)
            {
                try{
                    JSONObject jsonobject=new JSONObject(Response.toString());
                    JSONArray jsonArray=jsonobject.getJSONArray("seat" );

                    JSONObject showvalue = jsonArray.getJSONObject( 0 );

                    String seats = showvalue.getString( "seats" );
                    price = showvalue.getString( "price" );
                    intprice = Integer.parseInt( price );

                   // Toast.makeText( Select_Seat.this,"No of seat"+seats+" "+price,Toast.LENGTH_LONG ).show();
                    Number_of_vacant_seat.setText( seats );
                    ticketPrice.setText( price );
                }catch (Exception e)
                {
                    e.getStackTrace();
                }
            }
        },null){
            public HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put( "theater_name", TheaterName);
                params.put("movie_name",movie_name);
                params.put( "date",Date );
                params.put( "show",show_name );
                return params;
            }
        });


        spinnerNoOfTicket.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 itemselect = spinnerNoOfTicket.getSelectedItem().toString();
                Integer selectseats = Integer.parseInt( itemselect );
                 total = intprice * selectseats;

                if(total != 0)
                {
                   // Toast.makeText( getApplicationContext(), itemselect+" "+selectseats+" "+intprice+" "+total , Toast.LENGTH_LONG ).show();
                }
               total1=String.valueOf( total );
                Total_Price.setText( total1 );
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
     book_show.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Select_Seat.this,Book_show.class);
             intent.putExtra("TheaterName", TheaterName);
             intent.putExtra( "MovieName",movie_name );
             intent.putExtra( "Total_Money",total1 );
             intent.putExtra("date",Date);
             intent.putExtra( "Itemselect",itemselect );
             startActivity( intent );

         }
     } );
    }
}
