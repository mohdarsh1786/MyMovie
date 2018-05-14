package com.example.hppc.mymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Owner_info {
   private String Owner_name,email;
   private String mobile;
    public Owner_info(String Owner_name,String email,String mobile) {
        this.Owner_name=Owner_name;
        this.email=email;
        this.mobile=mobile;
    }

    public String getOwner_name() {
        return Owner_name;
    }

    public void setOwner_name(String owner_name) {
        Owner_name = owner_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
