package com.example.hppc.mymovie.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class SeatSelect extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    private String TheaterName;
    private String MovieName,Time;
    int intprice,Total_Money;
    private String Show_Date;
    String price =null;
    private String Show_id;
    public List<Integer> l;
    List<AbstractItem> items;
   // final ArrayList<Integer> bookedList = new ArrayList<>();
    RecyclerView recyclerView;
    public static List<Integer> b;
    AirplaneAdapter adapter,adapter1;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seat_select );
        l = new ArrayList<>();

        b = new ArrayList<>();
       Intent bundle = getIntent();
        TheaterName = bundle.getStringExtra( "TheaterName" );
        MovieName = bundle.getStringExtra( "MovieName" );
        Show_Date = bundle.getStringExtra( "Date" );
        Show_id = bundle.getStringExtra( "showName" );
        l = (ArrayList<Integer>) getIntent().getSerializableExtra( "seat_no" );
      //  Toast.makeText(getApplicationContext(),"Booked listseat" + l.toString(),Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );


        progressDialog = new ProgressDialog( this );
        //Toast.makeText( SeatSelect.this,""+TheaterName+""+MovieName+""+Show_Date+""+Show_id,Toast.LENGTH_LONG ).show();
        txtSeatSelected = (TextView) findViewById( R.id.txt_seat_selected );
        getSupportActionBar().setTitle( "  " + TheaterName + " " );
        List<AbstractItem> items = new ArrayList<>();


        for (int i = 0; i < 40; i++) {
            if (i % COLUMNS == 0 || i % COLUMNS == 4) {
                items.add( new EdgeItem( String.valueOf( i ) ) );
            } else if (i % COLUMNS == 1 || i % COLUMNS == 3) {
                items.add( new CenterItem( String.valueOf( i ) ) );
            } else {
                items.add( new EmptyItem( String.valueOf( i ) ) );
            }
        }


        GridLayoutManager manager = new GridLayoutManager( SeatSelect.this, COLUMNS );
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.lst_items );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( manager );

        adapter = new AirplaneAdapter(SeatSelect.this, items,l);
       recyclerView.setAdapter(adapter);
     //  Toast.makeText( this,"sunmklo" + l.toString(),Toast.LENGTH_SHORT ).show();
       /* l.add(0);
        l.add(1);
        l.add(5);
        l.add(15);
        /*int i=0;
        while(i < 30){
            if( i%5 != 2){
                l.add(i);
            }
            i++;
        }*/   /*******************************************My Code*****************************************/

       loadSeatSelect();

        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getApplicationContext(),"textView clicked",Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(),"" + b.toString(),Toast.LENGTH_SHORT).show();
                b = getSelectedSeat();
                if(b.size()==0)
                {
                    Toast.makeText( SeatSelect.this,"Please Select at least one Seat..!",Toast.LENGTH_SHORT ).show();
                }
                if(b.size()>0) {
                    Intent intent = new Intent( SeatSelect.this, Book_show.class );
                    intent.putExtra( "TheaterName", TheaterName );
                    intent.putExtra( "Movie_name", MovieName );
                    intent.putExtra( "Date", Show_Date );
                    intent.putExtra( "show_Time", Time );
                    intent.putExtra( "seat_no", (Serializable) b );
                    intent.putExtra( "price", intprice );
                    intent.putExtra( "show_id", Show_id );
                    startActivity( intent );
                   // Toast.makeText( getApplicationContext(), "" + b.toString(), Toast.LENGTH_LONG ).show();
                }
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

    private void loadSeatSelect() {
        RequestQueue queue1 = Volley.newRequestQueue( getApplicationContext() );
        queue1.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/select_seat.php", new Response.Listener<String>() {
            public void onResponse(String Response) {
                try {
                    JSONObject jsonobject = new JSONObject( Response.toString() );
                    JSONArray jsonArray = jsonobject.getJSONArray( "seat" );

                    JSONObject showvalue = jsonArray.getJSONObject( 0 );
                    Time = showvalue.getString( "Time" );
                    price = showvalue.getString( "price" );

                    intprice = Integer.parseInt( price );
              // Toast.makeText( SeatSelect.this,""+Time+" "+price, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }, null ) {
            public HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put( "theater_name", TheaterName );
                params.put( "movie_name", MovieName );
                params.put( "show", Show_id );
                return params;
            }
        } );
    }

        /*******************************************My Code*****************************************/

    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setText("Book "+count+" seats");


    }

    @Override
    public List<Integer> getSelectedSeat() {
        return adapter.listSeatSelected();
    }
}
