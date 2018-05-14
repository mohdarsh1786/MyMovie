package com.example.hppc.mymovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.HashMap;

public class AdminLogin extends AppCompatActivity {
       private EditText AdminName,AdminEmail,AdminPassword;
       private Button AdminLogin;
       private ProgressDialog progressDialog;
       SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_login );
       getSupportActionBar().setDisplayHomeAsUpEnabled( true );

       AdminName=findViewById(R.id.etAdminName);
        AdminEmail=findViewById(R.id.etAdminemail);
        AdminPassword=findViewById(R.id.etAdminPassword);
        AdminLogin=findViewById(R.id.btnAdminLogin);
        progressDialog= new ProgressDialog(this);
        AdminLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAdmin(AdminName.getText().toString(),AdminEmail.getText().toString(),AdminPassword.getText().toString());
            }
        } );
    }
    private void CheckAdmin(final String name,final String email,final String password)
    {
        if(name.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(getApplication(),"Please enter all details",Toast.LENGTH_SHORT).show();
        }

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        progressDialog.setMessage( "Login..." );
        progressDialog.show();
        queue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/AdminLogin.php", new Response.Listener<String>() {
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
                        sessionManagement= new com.example.hppc.mymovie.SessionManagement(getApplicationContext());
                        String lastlogin = "Admin";
                        sessionManagement.creatLoginSesson(email,password,lastlogin);
                        startActivity(new Intent(AdminLogin.this,AdminDashboard.class) );
                    }
                    else
                    { progressDialog.dismiss();
                        Toast.makeText(getApplication(),"Wrong Details ",Toast.LENGTH_SHORT).show();
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
                params.put("name",name);
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
