package com.example.hppc.mymovie.User;

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
import com.example.hppc.mymovie.MainActivity;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.Userdashboard2;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class Forget_Password extends AppCompatActivity {
    private EditText Email,enterOTP;
    private Button genrateotp,sendemail;
    String Email_for_verification,person;
    ProgressDialog progressDialog;
     String Otp;
    Integer otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Bundle bundle=getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        progressDialog =new ProgressDialog( this );
        person=bundle.getString( "User" );
       // Toast.makeText( Forget_Password.this,""+person,Toast.LENGTH_LONG ).show();
        setContentView( R.layout.activity_forget__password );
        Email=findViewById( R.id.forgetemail );
        enterOTP = (EditText) findViewById( R.id.enterotp );
        enterOTP.setVisibility( View.INVISIBLE );
        genrateotp=findViewById(R.id.btnforgetpass);
        sendemail=findViewById( R.id.btnsubmitotp );
        genrateotp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Email_for_verification=Email.getText().toString();
                Integer Min=1000000;
                Integer Max=9999999;
                Random rand=new Random();
             progressDialog.setMessage( "Sending Otp Wait..." );
             progressDialog.show();
                otp=rand.nextInt((Max-Min)+1)+Min;
                 Otp=String.valueOf( otp );
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/ForgetPassword.php",new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplication(),"in response",Toast.LENGTH_SHORT).show();
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean("success");
                            if(result)
                            {progressDialog.dismiss();

                                Toast.makeText( Forget_Password.this,"Your Otp Has been send",Toast.LENGTH_LONG ).show();
                                Email.setVisibility( View.INVISIBLE );
                                enterOTP.setVisibility( View.VISIBLE );
                                sendemail.setVisibility( View.VISIBLE );
                                genrateotp.setVisibility( View.INVISIBLE );
                              }
                            else
                            {progressDialog.dismiss();
                                Toast.makeText( Forget_Password.this, "Email not match",Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception ex)
                        {
                            ex.getStackTrace();
                        }
                    }
                } , null){
                    public HashMap<String,String> getParams()
                    {
                        HashMap<String,String> params = new HashMap<>();
                        params.put("email",Email_for_verification);
                        params.put("OTP",Otp);
                        params.put( "user",person );
                        return params;
                    }
                });


            }

        } );
        sendemail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userotp = enterOTP.getText().toString();
                Toast.makeText( getApplicationContext(),userotp+" "+Otp,Toast.LENGTH_LONG ).show();
                if(userotp.equals( Otp ))
                {
                   // Toast.makeText( getApplicationContext(),"match",Toast.LENGTH_LONG ).show();
                    Intent intent=new Intent( Forget_Password.this,NewPassword.class );
                    intent.putExtra( "Email",Email_for_verification );
                    intent.putExtra( "user1",person );
                    startActivity( intent );


                }
                else
                {
                    Toast.makeText( getApplicationContext(),"Sorry OTP Not match",Toast.LENGTH_LONG ).show();
                }


            }
        } );
    }

    public void mailverification(View view) {
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}
