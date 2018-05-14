package com.example.hppc.mymovie.Theater_owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hppc.mymovie.R;

public class movies_delete_info {
   private String Movie_Name;


    public movies_delete_info(String movie_Name) {
        this.Movie_Name = movie_Name;
    }

    public String getMovie_Name() {
        return Movie_Name;
    }

    public void setMovie_Name(String movie_Name) {
        Movie_Name = movie_Name;
    }
}
