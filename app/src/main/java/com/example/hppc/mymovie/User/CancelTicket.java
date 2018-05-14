package com.example.hppc.mymovie.User;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.Owner_info;
import com.example.hppc.mymovie.Owner_listAdapter_forverification;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CancelTicket extends AppCompatActivity {

    private ListView ListforCancelTicket;
    private UserCancelTicketAdapter adapter;
    private List<TicketCancelinfo> mTicketList;
   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cancel_ticket );
        ListforCancelTicket=findViewById( R.id.lvForCancelTicket );
        mTicketList=new ArrayList<>();

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );


        SessionManagement sessionManagement = new SessionManagement( getApplicationContext() );
        HashMap<String,String> details = sessionManagement.getUserDetails();
        final String UserEmail = details.get( SessionManagement.Key_UserName );
        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        queue1.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Cancel_Ticket.php", new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
              //  Toast.makeText( CancelTicket.this,"In responce",Toast.LENGTH_LONG  ).show();
                try
                {
                    // Toast.makeText( )
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray( "Ticket_info" );

                    mTicketList.clear();
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        String Tid = jsonObject1.getString( "Tid" );
                        String TheaterName = jsonObject1.getString( "TheaterName" );
                        String MovieName = jsonObject1.getString( "MovieName" );
                        String Date = jsonObject1.getString( "Date" );
                        String ShowTime=jsonObject1.getString( "ShowTime" );
                        String SeatNo = jsonObject1.getString( "SeatNo" );
                        mTicketList.add(new TicketCancelinfo( Tid,TheaterName,MovieName,Date,ShowTime,SeatNo ));
                        adapter=new UserCancelTicketAdapter(getApplicationContext(),mTicketList);
                        ListforCancelTicket.setAdapter( adapter );
                        }


                }catch(Exception ex)
                {
                    ex.getStackTrace();
                    Toast.makeText( CancelTicket.this,"In Catch"+ex.toString(),Toast.LENGTH_LONG ).show();
                }
            }
        }, null){
            public HashMap<String,String> getParams(){
                HashMap<String,String> parameters =new HashMap<>();
                parameters.put( "email",UserEmail);
                return parameters;
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
