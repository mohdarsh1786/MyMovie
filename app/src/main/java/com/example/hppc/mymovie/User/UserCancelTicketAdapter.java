package com.example.hppc.mymovie.User;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class UserCancelTicketAdapter extends BaseAdapter {
 private Context mCancelTicket;
 //ProgressDialog progressDialog;

  private List<TicketCancelinfo> mTicketList;

      UserCancelTicketAdapter(Context activity, List<TicketCancelinfo> mTicket) {
        this.mCancelTicket = activity;
        this.mTicketList = mTicket;
    }

    @Override
    public int getCount() {
        return mTicketList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTicketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          final TicketCancelinfo item =mTicketList.get(position);
         // progressDialog = new ProgressDialog( mCancelTicket.getApplicationContext() );
        View v=View.inflate(mCancelTicket,R.layout.activity_ticket_cancelinfo,null );
        final TextView Tid=(TextView)v.findViewById(R.id.tvBookTicketid);
        final TextView TheaterName=(TextView)v.findViewById(R.id.tvBookTheaterName);
        final TextView MovieName=(TextView)v.findViewById(R.id.tvBookMovieName);
        final TextView Date=(TextView)v.findViewById(R.id.tvBookShowDate);
       final TextView Show_Time=(TextView)v.findViewById(R.id.tvBookShowTime);
       final TextView SeatNo=(TextView)v.findViewById(R.id.tvBookSeatNo);
        final Button CancelTicket=v.findViewById( R.id.btnCancelTicketdetails );
      // Toast.makeText( v.getContext(), "hdhhdh", Toast.LENGTH_SHORT ).show();
        Tid.setText( mTicketList.get(position).getTid());
        TheaterName.setText(mTicketList.get( position ).getTheaterName());
        MovieName.setText( mTicketList.get(position) .getMovieName());
       Date.setText( mTicketList.get( position ).getDate() );
        Show_Time.setText( mTicketList.get( position ).getShowTime() );
        SeatNo.setText( mTicketList.get( position ).getSeatNo() );
         final String tid= mTicketList.get(position).getTid();
         final String seatno=mTicketList.get( position ).getSeatNo();
         //final int seats =Integer.parseInt( seatno );
        CancelTicket.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(v.getContext(),"in button",Toast.LENGTH_LONG ).show();
               /* progressDialog.setMessage( "Wait while .....Deleting" );
                progressDialog.show();*/
                RequestQueue requestQueue = Volley.newRequestQueue(mCancelTicket);
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/UserDeleteTicket.php", new Response.Listener<String>() {
                    @Override
                 public void onResponse(String response) {
                          try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean( "success" );
                            if(result)
                            {
                                Toast.makeText(mCancelTicket, "Successfully Deleted !", Toast.LENGTH_SHORT).show();
                               //Intent intent = new Intent( (Context) mCancelTicket, CancelTicket.class );
                             //  mCancelTicket.startActivity( intent );
                             ///   notifyDataSetChanged();
                               mTicketList.remove(  item);
                                notifyDataSetChanged();
                            }
                            else
                            {
                                 // progressDialog.dismiss();
                                Toast.makeText(mCancelTicket, "Error in deletion !", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, null) {
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();

                        parameters.put("tid", tid);
                        parameters.put( "seatno",seatno );
                        return parameters;
                    }
                });
            }
        } );

        v.setTag(mTicketList.get(position).getTheaterName());
        return v;
    }
}
