package com.example.hppc.mymovie.Theater_owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Owner_delete_movie extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private List<String> names;

    private ListView lvDeletemovies;
    private Adapter_for_DeleteMovie adapter;
    private List<movies_delete_info> mdeleteMovieslist;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_owner_delete_movie );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String, String> details = sessionManagement.getUserDetails();
        final String email = details.get( SessionManagement.Key_UserName );
     //   Toast.makeText(Owner_delete_movie.this,email+"",Toast.LENGTH_LONG).show();

        lvDeletemovies = (ListView) findViewById( R.id.LvdeleteMovie );
        mdeleteMovieslist = new ArrayList<>();
        spinner = (Spinner) findViewById( R.id.spin_deleteMovie );
        spinner.setOnItemSelectedListener( this );

        final List<String> names = new ArrayList<>();
        names.add( "Select Theater" );
        RequestQueue queue = Volley.newRequestQueue( Owner_delete_movie.this );
        queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Select_Theater_for_deletion.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    int i = 0;
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.optJSONArray( "Theater_Name" );

                        for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        names.add( jsonObject1.getString( "name" ) );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, null ){
            public HashMap<String, String> getParams() {
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("Email", email);
                return parameters;
            }

        } );
        //creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, names );

        dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( dataAdapter );
    }
    @Override
    public void onItemSelected (AdapterView < ? > parent, View view, int position, long id){


        final String Theater_name = spinner.getSelectedItem().toString();
      //  Toast.makeText( Userdashboard2.this, "city" + "" + city, Toast.LENGTH_LONG ).show();
        RequestQueue queue1 = Volley.newRequestQueue( getApplicationContext() );
        queue1.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Owner_select_movie_for_delete.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.getJSONArray( "movie" );

                    mdeleteMovieslist.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        String name = jsonObject1.getString( "name" );

                         mdeleteMovieslist.add( new movies_delete_info( name) );
                        adapter = new Adapter_for_DeleteMovie( getApplicationContext(), mdeleteMovieslist,Theater_name );
                        lvDeletemovies.setAdapter( adapter );
                    }

                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            }
        }, null ) {
            public HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();

                params.put( "Theater_name", Theater_name );
                return params;
            }
        } );
    }
    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}
