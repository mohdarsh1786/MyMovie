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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Theater_owner.Delete_Movie_from_Theater;
import com.example.hppc.mymovie.Theater_owner.TheaterOwnerLogin;
import com.example.hppc.mymovie.Theater_owner.UpdateMovies;
import com.example.hppc.mymovie.User.Forget_Password;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
   // DataBaseHelper helper=new DataBaseHelper(this);
    private EditText editEmail,editPassword;
    private Button Login;
    private TextView Registration,ForgetPassword;
    private ProgressDialog progressDialog;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement( getApplicationContext() );
        //Toast.makeText( getApplicationContext(),""+sessionManagement.isLoggedIn(),Toast.LENGTH_LONG ).show();
        if(sessionManagement.isLoggedIn())
        {

            HashMap<String,String> userdetails = sessionManagement.getUserDetails();
            String lastLogin = userdetails.get( SessionManagement.LastLogin);

           // Toast.makeText( getApplicationContext() , lastLogin , Toast.LENGTH_LONG ).show();

            if(lastLogin.equals( "User" ))
            {
                Intent intent = new Intent( MainActivity.this,Userdashboard2.class);
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
            }else if(lastLogin.equals( "TheaterOwner" ))
            {
                Intent intent = new Intent( MainActivity.this,TheaterOwnerdashboard.class);
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();

            }else if(lastLogin.equals( "Admin" ))
            {
                Intent intent = new Intent( MainActivity.this,AdminDashboard.class);
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();

            }

        }


       /* getSupportActionBar().setTitle("Passenger Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        ForgetPassword=findViewById( R.id.tvUserforgetPassword );
        editEmail = findViewById(R.id.etEmail);
        editPassword = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        Registration = findViewById(R.id.tvSignup);
        progressDialog=new ProgressDialog( this );
       ForgetPassword.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                                Intent intent =new Intent( MainActivity.this,Forget_Password.class );
                                intent.putExtra( "User","user" );
                                startActivity( intent );
            //   startActivity( new Intent(MainActivity.this, Forget_Password.class) );
           }
       } );
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(editEmail.getText().toString(),editPassword.getText().toString());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.adminMenu: {
                startActivity( new Intent( MainActivity.this, AdminLogin.class ) );
                break;
            }
            case R.id.theaterownerMenu: {
                startActivity( new Intent( MainActivity.this, TheaterOwnerLogin.class ) );
                break;
            }
        }

        return super.onOptionsItemSelected( item );
    }

    private void check(final String email, final String password)
    {
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }
        progressDialog.setTitle("Login");
        progressDialog.setMessage( "Please wait..." );
        progressDialog.show();
       // Toast.makeText(getApplication(),"in function",Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(new StringRequest(Request.Method.POST, "https://mdarshnitc.000webhostapp.com/auth.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplication(),"in response",Toast.LENGTH_SHORT).show();
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean exist = jsonObject.getBoolean("exist");
                    if(exist)
                    {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplication(),"success",Toast.LENGTH_SHORT).show();
                        sessionManagement= new SessionManagement(getApplicationContext());
                        String lastlogin = "User";
                        sessionManagement.creatLoginSesson(email,password,lastlogin);
                        HashMap<String,String> details = new HashMap<>(  );
                        details = sessionManagement.getUserDetails();
                       // Toast.makeText( getApplicationContext(),details.get( "ownerLoginEmail" )+details.get( "lastlogin" ),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,Userdashboard2.class) );
                    }
                    else
                    {    progressDialog.dismiss();
                        Toast.makeText(getApplication(),"Wrong id password",Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception ex)
                {
                    ex.getStackTrace();
                }
            }
        }, null){
            public HashMap<String,String> getParams()
            {
                HashMap<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        });
    }

}
