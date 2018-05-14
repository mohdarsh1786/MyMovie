package com.example.hppc.mymovie.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hppc.mymovie.MainActivity;
import com.example.hppc.mymovie.R;
import com.example.hppc.mymovie.SessionManagement;

public class BookingSuccessfull extends AppCompatActivity {

    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_booking_successfull );
        sessionManagement=new SessionManagement( getApplicationContext() );
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate( R.menu.menu3, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.CancelTicket: {
                Intent intent = new Intent( BookingSuccessfull.this, CancelTicket.class );
                startActivity( intent );
                finish();
                break;
            }
            case R.id.LogOutMenu3: {


                sessionManagement.logOutUser();
                Intent intent = new Intent( BookingSuccessfull.this, MainActivity.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
                finish();
                break;
            }

        }
        return super.onOptionsItemSelected( item );
    }
}
