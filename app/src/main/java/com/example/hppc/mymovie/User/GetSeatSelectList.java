package com.example.hppc.mymovie.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hppc.mymovie.MySingleton;
import com.example.hppc.mymovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSeatSelectList extends AppCompatActivity {

    private String TheaterName;
    private String MovieName,Time;
    private TextView TheaterNamefromdata,Movienamefromdata,Datefromdata,Seatfromdata;
    private String Show_Date;
    private Button seatselection;
    String price =null;
    private String Show_id;
    ProgressDialog progressDialog;
    public List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_seat_select_list );
        TheaterNamefromdata=findViewById( R.id.tvTheaterName );
        Movienamefromdata=findViewById( R.id.tvMovieName );
        Datefromdata=findViewById( R.id.tvDate);
       Seatfromdata=findViewById( R.id.tvVacantSeat);
        seatselection=findViewById( R.id.btnseatselectionpre );
        Intent bundle = getIntent();
        TheaterName = bundle.getStringExtra( "Theater_name" );
        MovieName = bundle.getStringExtra( "movie_name" );
        Show_Date = bundle.getStringExtra( "Date" );
        Show_id = bundle.getStringExtra( "show" );
        getSupportActionBar().setTitle( "  " + TheaterName + " " );
        progressDialog = new ProgressDialog( this );
        TheaterNamefromdata.setText( TheaterName );
        Movienamefromdata.setText( MovieName );
        Datefromdata.setText( Show_Date );
        list = new ArrayList<>( );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        final String url = "https://mdarshnitc.000webhostapp.com/getbooked_seat_list.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText( getApplicationContext(), "in response", Toast.LENGTH_LONG ).show();

                        try {

                            JSONArray j = new JSONArray( response );
                            for (int i = 0; i < j.length(); i++) {
                                progressDialog.dismiss();
                                JSONObject jsonObject = j.getJSONObject( i );
                                String seat_nom = jsonObject.getString( "SeatNo" );
                                int no = Integer.parseInt( seat_nom );
                                list.add( no );

                            }
                            int p=list.size();
                            int q=40-p;
                            Seatfromdata.setText( String.valueOf( q ) );
                        } catch (JSONException e) {
                            Toast.makeText( getApplicationContext(), "catch response", Toast.LENGTH_LONG ).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText( getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put( "TheaterName", TheaterName );
                params.put( "MovieName", MovieName );
                params.put( "showName", Show_id );
                params.put("show_date",Show_Date);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);

      //  Seatfromdata.setText( p );
      seatselection.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              //Toast.makeText( GetSeatSelectList.this,"Buttonclicked",Toast.LENGTH_LONG ).show();
              //Toast.makeText(getApplicationContext(),"Geted Seats here" + list.toString(),Toast.LENGTH_SHORT).show();

              Intent intent = new Intent( GetSeatSelectList.this,SeatSelect.class );
              intent.putExtra( "TheaterName", TheaterName );
              intent.putExtra( "MovieName", MovieName );
              intent.putExtra( "showName", Show_id );
              intent.putExtra( "Date",Show_Date );
              intent.putExtra( "seat_no", (Serializable) list );
              startActivity( intent );
          }
      } );

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}
