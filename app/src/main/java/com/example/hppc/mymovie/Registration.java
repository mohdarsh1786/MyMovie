package com.example.hppc.mymovie;


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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Theater_owner.OwnerAddShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText Name, Email, Password, Mobile;
    private Button Registration;
    private TextView signin;
    RequestQueue requestQueue;
    //DataBaseHelper Mydb;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Mydb = new DataBaseHelper(this);
        Name = (EditText) findViewById(R.id.etRegName);
        Email = (EditText) findViewById(R.id.etRegEmail);
        Password =(EditText) findViewById(R.id.etRegPassword);
        Mobile = (EditText) findViewById(R.id.etRegMobile);
        Registration =(Button) findViewById(R.id.btnRegistration);
        signin = findViewById(R.id.tvLogin);
        progressDialog=new ProgressDialog( this );
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final String name = Name.getText().toString();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                final String mobile = Mobile.getText().toString();
                progressDialog.setTitle( "Registering" );
                progressDialog.setMessage( "Please Wait..." );
                progressDialog.show();
                requestQueue = Volley.newRequestQueue(getApplicationContext());
               // Toast.makeText(getApplicationContext(),""+name+" "+email+" "+password+" "+mobile+" ",Toast.LENGTH_SHORT).show();
                requestQueue.add(new StringRequest(Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Registration.php", new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean( "success" );
                            if(result)
                            {
                                progressDialog.dismiss();
                              Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                              startActivity( new Intent( Registration.this,MainActivity.class ) );
                            }
                            else
                            {progressDialog.dismiss();
                              Toast.makeText(getApplicationContext(), "Error in Registering! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, null) {
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("name", name);
                        parameters.put("email", email);
                        parameters.put("password", password);
                        parameters.put("mobile", mobile);
                        return parameters;
                    }
                });
            }
        });

        signin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        } );

    }


}
