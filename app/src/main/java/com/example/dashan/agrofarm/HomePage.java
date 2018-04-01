package com.example.dashan.agrofarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
private static final String TAG="PhoneAuthActivity";
private FirebaseAuth mAuth;
private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button SignOut=(Button) findViewById(R.id.log_out);


        TextView fireBaseId = (TextView) findViewById(R.id.detail);

        mAuth=FirebaseAuth.getInstance();

        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }

        SignOut.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.action_map:{
                break;
            }
            case R.id.settings_menu:{
                break;
            }
            case R.id.logout_menu:{
                mAuth.signOut();
                startActivity(new Intent(HomePage.this,LoginMobile.class));

                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_out:{
                mAuth.signOut();
                startActivity(new Intent(HomePage.this,LoginMobile.class));

                finish();
                break;
            }
        }

    }
}
