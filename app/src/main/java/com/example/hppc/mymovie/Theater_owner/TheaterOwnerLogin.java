package com.example.hppc.mymovie.Theater_owner;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.MainActivity;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;
import com.example.hppc.mymovie.TheaterOwnerSignup;
import com.example.hppc.mymovie.TheaterOwnerdashboard;
import com.example.hppc.mymovie.User.Forget_Password;


import org.json.JSONObject;

import java.util.HashMap;

public class TheaterOwnerLogin extends AppCompatActivity {

    private EditText ownerLoginEmail,ownerLoginPassword;
    private Button ownerLoginButton;
    private TextView ownerSignup,ForgetPassword;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_theater_owner_login );
        ownerLoginEmail=findViewById(R.id.etOwnerEmail);
        ownerLoginPassword=findViewById(R.id.etOwnerPassword);
        ownerLoginButton=findViewById(R.id.btnOwnerLogin);
        ownerSignup=findViewById(R.id.tvOwnerSignup );
        ForgetPassword=findViewById( R.id.tvOwnerForgetPassword );
        progressDialog=new ProgressDialog( this );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       ownerLoginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(ownerLoginEmail.getText().toString(),ownerLoginPassword.getText().toString());
            }
        } );
      ownerSignup.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplication(),TheaterOwnerSignup.class ) );
          }
      } );
        ForgetPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( TheaterOwnerLogin.this,Forget_Password.class );
                intent.putExtra( "User","Theater_Owner" );
                startActivity( intent );
                //   startActivity( new Intent(MainActivity.this, Forget_Password.class) );
            }
        } );

    }
    private void check(final String email, final String password)
    {
        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(TheaterOwnerLogin.this,"Please enter all detail",Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage( "Please wait while Login..." );
        progressDialog.show();
       // Toast.makeText(getApplication(),"in function",Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/TheaterOwnerLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplication(),"in response",Toast.LENGTH_SHORT).show();
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean exist = jsonObject.getBoolean("exist");
                    if(exist)
                    {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplication(),"success",Toast.LENGTH_SHORT).show();
                        com.example.hppc.mymovie.SessionManagement sessionManagement;
                        sessionManagement= new com.example.hppc.mymovie.SessionManagement(getApplicationContext());
                        String lastlogin = "TheaterOwner";
                        sessionManagement.creatLoginSesson(ownerLoginEmail.getText().toString(),ownerLoginPassword.getText().toString(),lastlogin);
                        startActivity(new Intent(TheaterOwnerLogin.this,TheaterOwnerdashboard.class) );
                    }
                    else
                    {progressDialog.dismiss();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}
