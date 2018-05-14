package com.example.hppc.mymovie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class StuRegistration extends StringRequest {

    private static  final String url = "https://mdarshnitc.000webhostapp.com/auth.php";
    Map<String,String> params;
    public StuRegistration(String email,String password,Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super( Method.POST, url, listener, errorListener);
        params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
    }

    public Map<String,String> getParams()
    {
        return params;
    }
}
