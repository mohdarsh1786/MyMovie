package com.example.hppc.mymovie;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hppc.mymovie.Theater_owner.OwnerAddShow;
import com.example.hppc.mymovie.User.SelectDate;

import java.util.List;

/**
 * Created by HP PC on 22-03-2018.
 */

public class movieListAdapter extends BaseAdapter {

    private Context mContext;
    private List<movies_info> mMoviesList;

    movieListAdapter(Context activity,List<movies_info> mmovies)
    {
        this.mContext = activity;
        this.mMoviesList = mmovies;
    }

    public int getCount() {
        return mMoviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMoviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v=View.inflate(mContext,R.layout.movie_info,null );
        final TextView tvName=(TextView)v.findViewById(R.id.tv_name);
      /*  TextView tvActorName=(TextView)v.findViewById( R.id.tvActorName );
        TextView tvActressName=(TextView)v.findViewById( R.id.tvActressName );
        final TextView tvcity=(TextView)v.findViewById( R.id.tv_Ac);*/
        Button lmovie = (Button) v.findViewById( R.id.btnBook ) ;
      TextView tvActorName=(TextView)v.findViewById( R.id.tvActorName );
        TextView tvActressName=(TextView)v.findViewById( R.id.tvActressName);
        TextView tvDescription=(TextView)v.findViewById( R.id.tv_description );
        //set text for textview
        tvName.setText(mMoviesList.get(position).getName());
        tvActorName.setText(mMoviesList.get(position).getActorName());
        tvActressName.setText( mMoviesList.get( position ).getActress() );
        tvDescription.setText( mMoviesList.get( position ).getMovieType() );
        final String city_name=mMoviesList.get(position).getCity();
        // Toast.makeText( mContext,""+city_name,Toast.LENGTH_LONG ).show();
        lmovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName=tvName.getText().toString();
                String city=city_name;
               /*Intent intent=new Intent(parent.getContext(), SelectDate.class);
               parent.getContext().startActivity(intent);*/
                Intent intent = new Intent(parent.getContext() , SelectDate.class );
                intent.putExtra( "movie_name" , movieName );
                intent.putExtra( "city_name" , city );
                parent.getContext().startActivity(intent);
             //  Toast.makeText(mContext,"Inside"+city,Toast.LENGTH_LONG).show();


            }
        } );

        //Save product id to tag
        v.setTag(mMoviesList.get(position).getActorName());
        return v;
    }


}
