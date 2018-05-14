package com.example.hppc.mymovie.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hppc.mymovie.R;

public class TicketCancelinfo  {
    private String Tid,TheaterName,MovieName,Date,ShowTime,seatNo;

    public TicketCancelinfo(String tid, String theaterName, String movieName, String date, String showTime, String seatNo) {
        this.Tid = tid;
        this.TheaterName = theaterName;
        this.MovieName = movieName;
        this.Date = date;
        this.ShowTime = showTime;
        this.seatNo = seatNo;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTheaterName() {
        return TheaterName;
    }

    public void setTheaterName(String theaterName) {
        TheaterName = theaterName;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getShowTime() {
        return ShowTime;
    }

    public void setShowTime(String showTime) {
        ShowTime = showTime;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}
