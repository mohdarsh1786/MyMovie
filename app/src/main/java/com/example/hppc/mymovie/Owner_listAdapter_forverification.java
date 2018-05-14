package com.example.hppc.mymovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hppc.mymovie.User.Select_Seat;
import com.example.hppc.mymovie.User.Theater_info;
import com.example.hppc.mymovie.User.TicketCancelinfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class Owner_listAdapter_forverification extends BaseAdapter {


     private Context mlContext;
    private List<Owner_info> mOwnerList;

    Owner_listAdapter_forverification(Context activity, List<Owner_info> mowner)
    {
        this.mlContext = activity;
        this.mOwnerList = mowner;
    }

    @Override
    public int getCount() {
         return mOwnerList.size();
    }

    @Override
    public Object getItem(int position) {
       return mOwnerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        View v=View.inflate(mlContext,R.layout.activity_owner_info,null );
        final Owner_info item=mOwnerList.get(position);

        final TextView tvName=(TextView)v.findViewById(R.id.tvNameforVerification);
        final TextView email=(TextView)v.findViewById(R.id.tvemailforverification);
       final TextView Mobile=(TextView)v.findViewById(R.id.tvMobileforverification);
           Button Verify=v.findViewById(R.id.btnVerifyRequest  ) ;
           Button Reject=v.findViewById( R.id.btnRejectRequest ) ;
        // Toast.makeText( mlContext,mOwnerList.get(position).getEmail(),Toast.LENGTH_LONG ).show();
        //set text for textview
        tvName.setText(mOwnerList.get(position).getOwner_name());
        email.setText( mOwnerList.get(position).getEmail());
       Mobile.setText( mOwnerList.get( position ).getMobile() );
       final String Owneremail= mOwnerList.get(position).getEmail();
       Verify.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RequestQueue requestQueue = Volley.newRequestQueue(mlContext);
              // progressDialog.setMessage( "Please wait while Registering..." );
            //   progressDialog.show();
               // Toast.makeText(getApplicationContext(),""+name+" "+email+" "+password+" "+mobile+" ",Toast.LENGTH_SHORT).show();
               requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/AdminVerifyowner.php", new Response.Listener<String>() {
                   @Override

                   public void onResponse(String response) {

                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           Boolean result = jsonObject.getBoolean( "success" );
                           if(result)
                           {
                             //  progressDialog.dismiss();

                               Toast.makeText(mlContext, "Successfully Verified !", Toast.LENGTH_SHORT).show();
                               mOwnerList.remove(  item);
                               notifyDataSetChanged();
                           }
                           else
                           {
                             //  progressDialog.dismiss();
                               Toast.makeText(mlContext, "Error in verification !", Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                   }
               }, null) {
                   public HashMap<String, String> getParams() {
                       HashMap<String, String> parameters = new HashMap<>();

                       parameters.put("email", Owneremail);
                       return parameters;
                   }
               });
           }
       } );

       Reject.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RequestQueue requestQueue = Volley.newRequestQueue(mlContext);
               // progressDialog.setMessage( "Please wait while Registering..." );
               //   progressDialog.show();
               // Toast.makeText(getApplicationContext(),""+name+" "+email+" "+password+" "+mobile+" ",Toast.LENGTH_SHORT).show();
               requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/AdminRejectownerRequest.php", new Response.Listener<String>() {
                   @Override

                   public void onResponse(String response) {

                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           Boolean result = jsonObject.getBoolean( "success" );
                           if(result)
                           {
                               //  progressDialog.dismiss();
                               mOwnerList.remove(  item);
                               notifyDataSetChanged();

                               Toast.makeText(mlContext, "Successfully Deleted Request !", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               //  progressDialog.dismiss();
                               Toast.makeText(mlContext, "Error in Deletion !", Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                   }
               }, null) {
                   public HashMap<String, String> getParams() {
                       HashMap<String, String> parameters = new HashMap<>();

                       parameters.put("email", Owneremail);
                       return parameters;
                   }
               });
           }
       } );
//Save product id to tag
        v.setTag(mOwnerList.get(position).getMobile());
        return v;
    }

}
