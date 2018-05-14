package com.example.hppc.mymovie.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.HashMap;

public class NewPassword extends AppCompatActivity {

    private EditText newPassword,confirmpassword;
    private Button Submitnewpassword;
    private String Email,Person;
    ProgressDialog progressDialog;
    String checknewPassword,checkconfirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_password );
        Bundle bundle=getIntent().getExtras();

        Email=bundle.getString( "Email" );
        Person=bundle.getString( "user1" );
       // Toast.makeText( NewPassword.this,""+Person,Toast.LENGTH_LONG ).show();
        newPassword=findViewById( R.id.etNewPassword );
        progressDialog=new ProgressDialog( this );
        confirmpassword=findViewById( R.id.etConfirmPassword );
        Submitnewpassword=findViewById( R.id.btnsubmitPassword );
        Submitnewpassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage( "Processing..." );
                progressDialog.show();
                checknewPassword=newPassword.getText().toString();
                checkconfirmpassword=confirmpassword.getText().toString();
                if(checkconfirmpassword.equals(  checknewPassword))
                {

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/ForgetNewPassword.php",new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           // Toast.makeText(getApplication(),"in response",Toast.LENGTH_SHORT).show();
                            try
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                Boolean result = jsonObject.getBoolean("success");
                                if(result)
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText( NewPassword.this, "Password Successfully Change",Toast.LENGTH_LONG).show();
                                    startActivity( new Intent(NewPassword.this, MainActivity.class) );
                                }
                                else
                                { progressDialog.dismiss();
                                    Toast.makeText( NewPassword.this, "Sorry Password does not change",Toast.LENGTH_LONG).show();
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
                            params.put("email",Email);
                            params.put("NewPassword",checkconfirmpassword);
                            params.put("user",Person);
                            return params;
                        }
                    });

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(NewPassword.this,"Password does not match",Toast.LENGTH_LONG).show();
                }
            }
        } );

    }
}
