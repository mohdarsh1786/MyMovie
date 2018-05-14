package com.example.hppc.mymovie.Theater_owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.hppc.mymovie.User.SelectDate;
import com.example.hppc.mymovie.User.TicketCancelinfo;
import com.example.hppc.mymovie.movies_info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HP PC on 22-04-2018.
 */
public class Adapter_for_DeleteMovie extends BaseAdapter {

    private Context mdeleteContext;
    private List<movies_delete_info> mdeleteMovieslist;
    private String TheaterName;
   // private  String Movie_Name;
   // ProgressDialog progressDialog;
    Adapter_for_DeleteMovie(Context activity, List<movies_delete_info> mdeletemovies,String Theater_name) {
        this.mdeleteContext = activity;
        this.mdeleteMovieslist = mdeletemovies;
        this.TheaterName=Theater_name;
    }
    public int getCount() {
        return mdeleteMovieslist.size();
    }
  @Override
    public Object getItem(int position) {
        return mdeleteMovieslist.get( position );
    }

   @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v = View.inflate( mdeleteContext, R.layout.activity_movies_delete_info, null );
        final movies_delete_info item=mdeleteMovieslist.get(position);

        final TextView mvName = (TextView) v.findViewById( R.id.tvdeleteMovie );
       Button ldeletemovie =v.findViewById( R.id.btnownerdeleteMovie );
        mvName.setText( mdeleteMovieslist.get( position ).getMovie_Name() );
        final String Movie_name=mdeleteMovieslist.get( position ).getMovie_Name();
        ldeletemovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               RequestQueue requestQueue = Volley.newRequestQueue(mdeleteContext);
                requestQueue.add(new StringRequest( Request.Method.POST, "https://mdarshnitc.000webhostapp.com/Ownerdelete_Moviefromtheater.php", new Response.Listener<String>() {
                    @Override
                     public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean result = jsonObject.getBoolean( "success" );
                            if(result)
                            {
                                Toast.makeText(mdeleteContext, "Movie Successfully Deleted !", Toast.LENGTH_SHORT).show();
                                mdeleteMovieslist.remove(  item);
                                notifyDataSetChanged();
                              //  mdeleteMovieslist.clear();
                                //mdeleteMovieslist.addAll(mdeleteMovieslist);
                                //notifyDataSetChanged();
                            }
                            else
                            {
                                // progressDialog.dismiss();
                                Toast.makeText(mdeleteContext, "Error in Deletion !", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, null) {
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> parameters = new HashMap<>();
                       // Toast.makeText( mdeleteContext,TheaterName+" "+Movie_name,Toast.LENGTH_LONG ).show();
                        parameters.put("Theatername",TheaterName);
                        parameters.put("movie_name", Movie_name);
                        return parameters;
                    }
                });

            }
        } );

        //Save product id to tag
        v.setTag( mdeleteMovieslist.get( position ).getMovie_Name() );
        return v;
    }
}

