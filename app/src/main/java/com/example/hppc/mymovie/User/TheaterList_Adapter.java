package com.example.hppc.mymovie.User;

import android.app.Activity;
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

import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.movies_info;

import java.util.List;

public class TheaterList_Adapter extends BaseAdapter {

    private Context mlContext;
    private List<Theater_info> mTheaterList;

    TheaterList_Adapter(Context activity,List<Theater_info> mTheater)
    {
        this.mlContext = activity;
        this.mTheaterList = mTheater;
    }
       @Override
    public int getCount() {
        return mTheaterList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTheaterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v=View.inflate(mlContext,R.layout.activity_theater_info,null );

        final TextView tvName=(TextView)v.findViewById(R.id.tv_name);
        Button show1=(Button) v.findViewById(R.id.show1);
        Button show2=(Button) v.findViewById(R.id.show2);
        Button show3=(Button) v.findViewById(R.id.show3);
        Button show4=(Button) v.findViewById(R.id.show4);
      //Toast.makeText( mlContext,""+show1+" "+show2+"",Toast.LENGTH_LONG );
      //set text for textview
        tvName.setText(mTheaterList.get(position).getName());
            if(mTheaterList.get( position ).getShow1().equals(" "))
            {show1.setVisibility( View.INVISIBLE );}
            else
            { show1.setText(String.valueOf( mTheaterList.get(position).getShow1()));}
        Toast.makeText( mlContext,""+show1+" "+show2+"",Toast.LENGTH_LONG );
        if(mTheaterList.get( position ).getShow2().equals(" "))
        {show2.setVisibility( View.INVISIBLE );}
        else
        { show2.setText(String.valueOf( mTheaterList.get(position).getShow2()));}

        if(mTheaterList.get( position ).getShow3().equals(" "))
        {show3.setVisibility( View.INVISIBLE );}
        else
        { show3.setText(String.valueOf( mTheaterList.get(position).getShow3()));}

        if(mTheaterList.get( position ).getShow4().equals(" "))
        {show4.setVisibility( View.INVISIBLE );}
        else
        { show4.setText(String.valueOf( mTheaterList.get(position).getShow4()));}

        final String date = mTheaterList.get( position ).getDate();
        final String Movie_name=mTheaterList.get(position).getMovie();
       show1.setOnClickListener( new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                final String Theater_name=tvName.getText().toString();
               Intent intent=new Intent(parent.getContext(), GetSeatSelectList.class);
                     intent.putExtra( "Theater_name",Theater_name );
                     intent.putExtra( "Date",date );
                     intent.putExtra( "show", "show1");
                     intent.putExtra( "movie_name",Movie_name );
                parent.getContext().startActivity(intent);
             }} );

        show2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Theater_name=tvName.getText().toString();
                Intent intent=new Intent(parent.getContext(), GetSeatSelectList.class);
                intent.putExtra( "Theater_name",Theater_name );
                intent.putExtra( "Date",date );
                intent.putExtra( "show", "show2");
                intent.putExtra( "movie_name",Movie_name );
                parent.getContext().startActivity(intent);
            }} );

        show3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Theater_name=tvName.getText().toString();
                Intent intent=new Intent(parent.getContext(),GetSeatSelectList.class);
                intent.putExtra( "Theater_name",Theater_name );
                intent.putExtra( "Date",date );
                intent.putExtra( "show", "show3");
                intent.putExtra( "movie_name",Movie_name );
                parent.getContext().startActivity(intent);
            }} );

        show4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Theater_name=tvName.getText().toString();
                Intent intent=new Intent(parent.getContext(), GetSeatSelectList.class);
                intent.putExtra( "Theater_name",Theater_name );
                intent.putExtra( "Date",date );
                intent.putExtra( "show", "show4");
                intent.putExtra( "movie_name",Movie_name );
                parent.getContext().startActivity(intent);
            }} );
//Save product id to tag
        v.setTag(mTheaterList.get(position).getShow1());
        return v;
    }

}

