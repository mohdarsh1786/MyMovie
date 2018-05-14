package com.example.hppc.mymovie;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.User.TheaterList_Adapter;
import com.example.hppc.mymovie.User.Theater_info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Owner_Verification extends AppCompatActivity {
    private ListView listView_for_verification;

    private Owner_listAdapter_forverification adapter;
    private List<Owner_info> mOwnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_owner__verification  );

        listView_for_verification=findViewById(R.id.listviewforverifications );

        mOwnerList =new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /********  theater owner info from database for verification ****/
        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        queue1.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/select_Theater_owner_info.php", new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try
                {
                    // Toast.makeText( )
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray( "owner_info" );

                    mOwnerList.clear();
                    for(int i=0;i<jsonArray.length();i++) {
                       // Toast.makeText(Owner_Verification.this,"inside loop"+jsonArray.length(),Toast.LENGTH_LONG).show();
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                      //  Toast.makeText(Owner_Verification.this,jsonObject1.toString(),Toast.LENGTH_LONG).show();
                        String name = jsonObject1.getString( "Name" );
                        String email = jsonObject1.getString( "Email" );
                        String Mobile = jsonObject1.getString( "MobileNumber" );
                       // Toast.makeText(Owner_Verification.this,name+ " "+email+""+Mobile,Toast.LENGTH_LONG).show();
                        mOwnerList.add(new Owner_info( name,email,Mobile ));
                        adapter=new Owner_listAdapter_forverification(getApplicationContext(),mOwnerList);
                        listView_for_verification.setAdapter( adapter );
                    }

                    //Intent intent=new Intent( Ownner_Verification.this, )
                }catch(Exception ex)
                {
                    ex.getStackTrace();
                }
            }
        }, null));
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
}
