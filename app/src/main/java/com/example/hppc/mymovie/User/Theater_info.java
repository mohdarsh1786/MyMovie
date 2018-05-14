package com.example.hppc.mymovie.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hppc.mymovie.R;

public class Theater_info  {
    private String name;
    private String show1,show2,show3,show4,date,movie;

    public Theater_info(String name, String show1, String show2, String show3, String show4, String date,String movie) {
        this.name = name;
        this.show1 = show1;
        this.show2 = show2;
        this.show3 = show3;
        this.show4 = show4;
        this.date = date;
        this.movie=movie;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShow1() { return show1;}

    public void setShow1(String show1) {
        show1 = show1;
    }

    public String getShow2() {
        return show2;
    }

    public void setShow2(String show2) {
        this.show2 = show2;
    }

    public String getShow3() {
        return show3;
    }

    public void setShow3(String show3) {
        this.show3 = show3;
    }

    public String getShow4() {
        return show4;
    }

    public void setShow4(String show4) {
        this.show4 = show4;
    }
}
