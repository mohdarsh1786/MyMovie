package com.example.hppc.mymovie.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.MainActivity;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;
import com.example.hppc.mymovie.movieListAdapter;
import com.example.hppc.mymovie.movies_info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User_TheaterDetails extends AppCompatActivity {

    private ListView listView;

    private TheaterList_Adapter adapter;
    private List<Theater_info> mTheaterlist;
    SessionManagement sessionManagement;
    String moviename,cityname,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user__theater_details );
          sessionManagement =new SessionManagement( getApplicationContext() );
        Bundle bundle = getIntent().getExtras();
        moviename = bundle.get( "movie_name" ).toString();
         cityname = bundle.get( "city_name" ).toString();
         date = bundle.get( "date" ).toString();
        listView=findViewById(R.id.lvTheaterdetails );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setTitle( "   " + moviename + "   " );
         // Toast.makeText( getApplicationContext(),moviename+cityname+date,Toast.LENGTH_LONG ).show();
        mTheaterlist =new ArrayList<>();

        /*  theater list info from database  ****/
        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        queue1.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/select_Theater.php", new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try
                {
                  //  Toast.makeText( getApplicationContext(),"in response",Toast.LENGTH_LONG ).show();
                         JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray( "Theater" );
                     mTheaterlist.clear();
                     for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        String name = jsonObject1.getString( "name" );
                        String show1 = jsonObject1.getString( "show1" );
                        String show2 = jsonObject1.getString( "show2" );
                        String show3 = jsonObject1.getString( "show3" );
                        String show4 = jsonObject1.getString( "show4" );
                        mTheaterlist.add(new Theater_info(name,show1,show2,show3,show4,date,moviename));
                        adapter=new TheaterList_Adapter(getApplicationContext(),mTheaterlist);
                        listView.setAdapter( adapter );
                       }
                  }catch(Exception ex)
                {
                    Toast.makeText( getApplicationContext(),ex.toString(),Toast.LENGTH_LONG ).show();

                    ex.getStackTrace();
                }
            }
        }, null){
            public HashMap<String,String> getParams()
            {
                HashMap<String,String> params = new HashMap<>();
                params.put("movie_name",moviename);
                params.put("city_name",cityname);

                return params;
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate( R.menu.menu3, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.CancelTicket: {
                Intent intent = new Intent( User_TheaterDetails.this, CancelTicket.class );
                startActivity( intent );
                finish();
                break;
            }
            case R.id.LogOutMenu3: {


                sessionManagement.logOutUser();
                Intent intent = new Intent( User_TheaterDetails.this, MainActivity.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }
            case android.R.id.home:
                onBackPressed();

        }
        return super.onOptionsItemSelected( item );
    }
}
