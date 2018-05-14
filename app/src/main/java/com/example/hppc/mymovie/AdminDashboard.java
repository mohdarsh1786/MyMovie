package com.example.hppc.mymovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class AdminDashboard extends AppCompatActivity {
 private EditText upcomingMovieName,upcomingMovieActor,upcomingMovieActress,upcomingMovieDescription;

 private Button AddupcomingMovies,upcomingMoviePoster;
 private ProgressDialog progressDialog;

 SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_dashboard );
        sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();
        upcomingMovieName=findViewById(R.id.etUpcomingMoviesName);
       // upcomingMoviePoster=findViewById( R.id.btnaddposter );
        upcomingMovieActress=findViewById( R.id.etUpcomingMoviesActress );
        upcomingMovieActor=findViewById(R.id.etUpcomingMoviesActor);
        upcomingMovieDescription=findViewById( R.id.etUpcomingMoviesDesription );
        AddupcomingMovies=findViewById(R.id.btnUpcomingMovies);

        progressDialog=new ProgressDialog( this );

        AddupcomingMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=upcomingMovieName.getText().toString();
                //final String poster=upcomingMoviePoster.getText().toString();
                final String Actor=upcomingMovieActor.getText().toString();
                final String Actress=upcomingMovieActress.getText().toString();
                final String Description=upcomingMovieDescription.getText().toString();
                progressDialog.setMessage("Movie Adding...");
                progressDialog.show();
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/UpcomingMovies.php", new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            Boolean result=jsonObject.getBoolean("Success");
                            if(result)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(AdminDashboard.this,"Movie successfully Added",Toast.LENGTH_SHORT ).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(AdminDashboard.this,"Error in Adding Movie",Toast.LENGTH_SHORT ).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();

                        }
                    }
                },null)
                {
                    public HashMap<String,String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put( "name", name );
                        parameters.put( "description", Description);
                        parameters.put( "Actress", Actress );
                        parameters.put( "actor", Actor );
                        return parameters;
                    }
                });
                }

        } );
       /* Removemovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( AdminDashboard.this,Delete_Movies.class ));
            }
        } );*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.LogOutMenu:{
                sessionManagement.logOutUser();
                Intent intent = new Intent( AdminDashboard.this,AdminLogin.class);
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }
            case R.id.RemoveMenu:{
                startActivity(new Intent( AdminDashboard.this,Delete_Movies.class ));

                break;
            }
            case R.id.VerifyMenu:{
                startActivity(new Intent( AdminDashboard.this,Owner_Verification.class ));

                break;
            }

        }
        return super.onOptionsItemSelected( item );
    }
}
