package com.example.hppc.mymovie.Theater_owner;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class Delete_Movie_from_Theater extends AppCompatActivity {


    private Button DeleteMovie;
    private Spinner spinnerdeleteMovies,spinnerdeleteTheater;
    private ProgressDialog progressDialog;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete__movie_from__theater );
        SessionManagement sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();

        final String email = details.get( SessionManagement.Key_UserName );
        final String password=details.get(SessionManagement.Key_Password);
        Toast.makeText(Delete_Movie_from_Theater.this,email+"",Toast.LENGTH_LONG).show();

        DeleteMovie=findViewById(R.id.btnDeleteMovie);
        spinnerdeleteMovies=findViewById(R.id.spMoviename);
        spinnerdeleteTheater=findViewById(R.id.spTheaterName);

        progressDialog=new ProgressDialog( this );

        final List<String> Theater_Name = new ArrayList<>();
        Theater_Name.add( "Select Theater" );

        final List<String> Film_Name = new ArrayList<>();
        Film_Name.add( "Select Film" );

        RequestQueue queue = Volley.newRequestQueue( Delete_Movie_from_Theater.this );
        queue.add( new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Delete_Movies_From_Theater.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    int i=0,j=0;
                    JSONObject jsonObject = new JSONObject( response.toString() );
                    JSONArray jsonArray = jsonObject.optJSONArray( "theater_Name" );
                    for(i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        if(jsonObject1.get( "name" ).equals( "Film_Name" ))
                        {
                            break;
                        }
                        Theater_Name.add( jsonObject1.getString( "name" ) );
                    }

                    for(i = i+1;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        Film_Name.add( jsonObject1.getString( "name" ) );
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
        ArrayAdapter<String> Theater_name_data = new ArrayAdapter<String>( Delete_Movie_from_Theater.this,android.R.layout.simple_list_item_1,Theater_Name);
        Theater_name_data.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
       spinnerdeleteTheater.setAdapter( Theater_name_data );

        ArrayAdapter<String> Film_name_data = new ArrayAdapter<String>( Delete_Movie_from_Theater.this,android.R.layout.simple_list_item_1,Film_Name);
        Film_name_data.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerdeleteMovies.setAdapter( Film_name_data );

        DeleteMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Theatername=spinnerdeleteTheater.getSelectedItem().toString();
                final String Moviename=spinnerdeleteMovies.getSelectedItem().toString();

                progressDialog.setTitle("Please wait");
                progressDialog.setMessage( "Removing in Progress..." );
                progressDialog.show();
                Toast.makeText( Delete_Movie_from_Theater.this,Theatername+" "+ Moviename+" "+ email ,Toast.LENGTH_LONG ).show();
                RequestQueue requestQueue=Volley.newRequestQueue(Delete_Movie_from_Theater.this );
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Delete_Movies.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText( UpdateMovies.this,"In hashmap",Toast.LENGTH_LONG ).show();

                            JSONObject jsonObject = new JSONObject( response.toString() );
                            Boolean success = jsonObject.getBoolean( "success" );
                            if(success)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Delete_Movie_from_Theater.this,"Movie successfully Deleted !",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText( Delete_Movie_from_Theater.this,"ERROR Movie Not Deleted",Toast.LENGTH_LONG ).show();
                            }
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, null){
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();

                        parameters.put( "theater_name",Theatername );
                        parameters.put( "movie_name",Moviename );
                        parameters.put("email",email);
                        return parameters;

                    }
                });
            }
        } );

    }
}
