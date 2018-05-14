package com.example.hppc.mymovie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Theater_owner.Delete_Movie_from_Theater;
import com.example.hppc.mymovie.Theater_owner.OwnerAddMovies;
import com.example.hppc.mymovie.Theater_owner.UpdateMovies;
import com.example.hppc.mymovie.User.CancelTicket;
import com.example.hppc.mymovie.User.SelectDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Userdashboard2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private List<String> names;

    private ListView lvmovies;
    private movieListAdapter adapter;
    private List<movies_info> mMovieslist;
    SessionManagement sessionManagement;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_userdashboard2 );
        sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String, String> details = sessionManagement.getUserDetails();
        lvmovies = (ListView) findViewById( R.id.listview_product );
        mMovieslist = new ArrayList<>();
        spinner = (Spinner) findViewById( R.id.sp1 );
        spinner.setOnItemSelectedListener( this );

        final List<String> names = new ArrayList<>();
        names.add( "Select city" );
      /*  names.add("Calicut");
        names.add("Mumbai");
        names.add("Delhi");*/

        RequestQueue queue = Volley.newRequestQueue( Userdashboard2.this );
        queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Select_city.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    int i = 0;
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.optJSONArray( "City_Name" );


                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        names.add( jsonObject1.getString( "name" ) );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, null ) );
        //creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, names );

        dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( dataAdapter );
    }

    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){


        final String city = spinner.getSelectedItem().toString();
      //  Toast.makeText( Userdashboard2.this, "city" + "" + city, Toast.LENGTH_LONG ).show();
        RequestQueue queue1 = Volley.newRequestQueue( getApplicationContext() );
        queue1.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/select_movie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.getJSONArray( "movie" );

                    mMovieslist.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        String name = jsonObject1.getString( "name" );
                        String Actorname = jsonObject1.getString( "ActorName" );
                        String ActressName = jsonObject1.getString( "ActressName" );
                        String MovieType = jsonObject1.getString( "description" );
                        mMovieslist.add( new movies_info( name, ActressName, MovieType, Actorname,city ) );
                        adapter = new movieListAdapter( getApplicationContext(), mMovieslist );
                        lvmovies.setAdapter( adapter );
                    }

                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            }
        }, null ) {
            public HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();

                params.put( "city", city );
                return params;
            }
        } );
    }
    @Override
    public void onNothingSelected (AdapterView < ? > parent){

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
                Intent intent = new Intent( Userdashboard2.this, CancelTicket.class );
                startActivity( intent );

                break;
            }
            case R.id.LogOutMenu3: {


                sessionManagement.logOutUser();
                Intent intent = new Intent( Userdashboard2.this, MainActivity.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }

 }
        return super.onOptionsItemSelected( item );
    }

}

