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
import com.example.hppc.mymovie.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Theater_owner.Delete_Movie_from_Theater;
import com.example.hppc.mymovie.Theater_owner.OwnerAddMovies;
import com.example.hppc.mymovie.Theater_owner.Owner_delete_movie;
import com.example.hppc.mymovie.Theater_owner.TheaterOwnerLogin;
import com.example.hppc.mymovie.Theater_owner.UpdateMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TheaterOwnerdashboard extends AppCompatActivity {

    private EditText TheaterName,Theatercity;
    private Button AddTheater;
    private Button AddMovies;
    private ProgressDialog progressDialog;
        SessionManagement sessionManagement;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

       sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();

        final String email = details.get( SessionManagement.Key_UserName );
        String password=details.get(SessionManagement.Key_Password);
      // Toast.makeText( getApplicationContext(),email,Toast.LENGTH_SHORT ).show();
     setContentView( R.layout.activity_theater_ownerdashboard );
        TheaterName=findViewById(R.id.etTheaterName);
        Theatercity=findViewById(R.id.etTheaterCity);
        AddTheater=findViewById(R.id.btnAddTheater);
        AddMovies=findViewById(R.id.btnAddMovies);
        progressDialog=new ProgressDialog( this );
        AddMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheaterOwnerdashboard.this, OwnerAddMovies.class));
            }
        } );

        AddTheater.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = TheaterName.getText().toString();
                final String city = Theatercity.getText().toString();

              RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                  progressDialog.setMessage("Wait While Adding...");
                  progressDialog.show();
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Theater_details.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean( "success" );
                            if(result)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Theater Successfully Registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Error in Registering", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, null) {
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("email",email);
                        parameters.put("TheaterName", name);
                        parameters.put("TheaterCity", city);

                        return parameters;
                    }
                });
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.LogOutMenu2:{

                sessionManagement.logOutUser();
                Intent intent = new Intent( TheaterOwnerdashboard.this,TheaterOwnerLogin.class);
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }
            case R.id.UpdateMenu:{
                startActivity(new Intent(TheaterOwnerdashboard.this,UpdateMovies.class) );
                break;
            }
            case R.id.DeleteMenu:{
                startActivity(new Intent( TheaterOwnerdashboard.this, Owner_delete_movie.class ) );
                break;
            }
        }
        return super.onOptionsItemSelected( item );
    }
}
