package com.example.hppc.mymovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int Private_Mode = 0;

    private static final String Pref_Name = "Theater Booking System";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String Key_UserName = "ownerLoginEmail";
    public  static final  String Key_Password = "ownerLoginPassword";
    public  static final  String LastLogin = "lastlogin";

    public SessionManagement(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(Pref_Name,Private_Mode);
        editor = pref.edit();
    }


    //create login session

    public void creatLoginSesson(String username, String password, String lastLogin)
    {
        editor.putString(Key_UserName,username);
        editor.putString(Key_Password,password);
        editor.putString(LastLogin,lastLogin);
        editor.putBoolean(IS_LOGIN,true);
        editor.commit();
    }

    //in order to get stored preference data

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String, String>();
        user.put(Key_UserName,pref.getString(Key_UserName,null));
        user.put(Key_Password,pref.getString(Key_Password,null));
        user.put(LastLogin,pref.getString(LastLogin,null));
        return user;
    }

    //checklogin method will user login status

    public void checkLogin(){
        if(!this.isLoggedIn())
        {
            Intent i = new Intent(_context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        }
    }

    //add a function for logout clear

    public  void logOutUser(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }




}