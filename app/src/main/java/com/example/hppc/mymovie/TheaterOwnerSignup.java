package com.example.hppc.mymovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.hppc.mymovie.Theater_owner.TheaterOwnerLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TheaterOwnerSignup extends AppCompatActivity {
    private EditText OwnerSignupName,OwnerSignupMobile,OwnerSignupEmail,OwnerSignupPassword;
    private Button btnownerRegister;
    private TextView btnAlreadysignup;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_theater_owner_signup );
       OwnerSignupName=findViewById( R.id.etSignupOwnerName);
       OwnerSignupMobile=findViewById(R.id.etSignupTheaterownerMobile);
       OwnerSignupEmail=findViewById( R.id.etSignupTheaterownerEmail);
       OwnerSignupPassword=findViewById(R.id.etSignupTheaterownerPassword);
       btnownerRegister=findViewById(R.id.btnTheaterownerSignUp);
       btnAlreadysignup=findViewById(R.id.tvSignupTheaterownerAlredysignup);
       progressDialog=new ProgressDialog( this );
       btnAlreadysignup.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(TheaterOwnerSignup.this,TheaterOwnerLogin.class));
           }
       } );

       btnownerRegister.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String name = OwnerSignupName.getText().toString();
               final String mobile = OwnerSignupMobile.getText().toString();
               final String email = OwnerSignupEmail.getText().toString();
               final String password = OwnerSignupPassword.getText().toString();

             RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
             progressDialog.setMessage( "Please wait while Registering..." );
             progressDialog.show();
               // Toast.makeText(getApplicationContext(),""+name+" "+email+" "+password+" "+mobile+" ",Toast.LENGTH_SHORT).show();
               requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/TheaterOwnerRegistration.php", new Response.Listener<String>() {
                   @Override

                   public void onResponse(String response) {

                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           Boolean result = jsonObject.getBoolean( "success" );
                           if(result)
                           {
                               progressDialog.dismiss();

                               Toast.makeText(getApplicationContext(), "Successfully Register !", Toast.LENGTH_SHORT).show();
                               startActivity( new Intent( TheaterOwnerSignup.this,TheaterOwnerLogin.class ) );
                           }
                           else
                           {
                               progressDialog.dismiss();
                               Toast.makeText(getApplicationContext(), "Error Registration failed", Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                   }
               }, null) {
                   public HashMap<String, String> getParams() {
                       HashMap<String, String> parameters = new HashMap<>();
                       parameters.put("name", name);
                       parameters.put("mobile", mobile);
                       parameters.put("email", email);
                       parameters.put("password", password);
                       return parameters;
                   }
               });
           }
       } );

    }
}
