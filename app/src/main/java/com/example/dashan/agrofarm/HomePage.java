package com.example.dashan.agrofarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
private static final String TAG="PhoneAuthActivity";
private FirebaseAuth mAuth;
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
