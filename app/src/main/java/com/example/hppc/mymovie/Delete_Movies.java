package com.example.hppc.mymovie;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Theater_owner.OwnerAddMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Delete_Movies extends AppCompatActivity {
     private Spinner deleteMovies;
     private Button DeleteMovie;
     ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete__movies );
        deleteMovies = findViewById( R.id.spDeleteMovie );
        DeleteMovie = findViewById( R.id.btnDeleteMoviefomList );
       progressDialog=new ProgressDialog( this );

        final List<String>  Film_Name=new ArrayList<>();
        Film_Name.add("Select Film");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RequestQueue queue = Volley.newRequestQueue( Delete_Movies.this );
        queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/AdminDeleteMovie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    int i=0,j=0;
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.optJSONArray( "Movie_Name" );
                    for(i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );

                        Film_Name.add( jsonObject1.getString( "name" ) );
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
             }
        }, null) );




        ArrayAdapter<String> film_name_data = new ArrayAdapter<String>( Delete_Movies.this,android.R.layout.simple_list_item_1,Film_Name);
        film_name_data.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        deleteMovies.setAdapter( film_name_data);


        DeleteMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Movie_names=deleteMovies.getSelectedItem().toString();
                progressDialog.setTitle( "Please wait..." );
                progressDialog.setMessage( "Remove in Progress !" );
                progressDialog.show();
                RequestQueue queue = Volley.newRequestQueue( Delete_Movies.this );
                queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/AdminDeleteMoviefromList.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {JSONObject jsonObject = new JSONObject( response);
                           Boolean result=jsonObject.getBoolean("success");
                           if(result)
                           {
                               progressDialog.dismiss();
                               Toast.makeText( Delete_Movies.this,"Movie Successfully Deleted",Toast.LENGTH_LONG ).show();
                           }
                           else
                           {
                               progressDialog.dismiss();
                               Toast.makeText( Delete_Movies.this,"Error in deleting please try Again !",Toast.LENGTH_LONG ).show();
                           }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, null){
                   public HashMap<String,String> getParams(){
                        HashMap<String,String> parameters =new HashMap<>();
                        parameters.put( "Moviename",Movie_names);
                        return parameters;
                    }
                } );
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
