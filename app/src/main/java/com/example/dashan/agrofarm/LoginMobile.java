package com.example.dashan.agrofarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginMobile extends AppCompatActivity implements View.OnClickListener{
EditText mPhoneNumberField,mVerificationField;
Button mStartButton,mVerifyButton,mresendbutton;
private FirebaseAuth mAuth;
private PhoneAuthProvider.ForceResendingToken mResendToken;
private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
String mverificationId;
private static final String TAG="PhoneAuthActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mobile);


        mPhoneNumberField=(EditText) findViewById(R.id.field_phone_number);
        mVerificationField=(EditText) findViewById(R.id.field_verification_code);


        mStartButton=(Button) findViewById(R.id.button_start_verification);
        mVerifyButton = (Button) findViewById(R.id.button_verify_phone);
        mresendbutton = (Button) findViewById(R.id.button_resend);

        mStartButton.setOnClickListener(this);
        mVerifyButton.setOnClickListener(this);
        mresendbutton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        mcallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                Toast.makeText(LoginMobile.this,"Verifaication Completed",Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
             Log.w(TAG,"OnVerficationFailed",e);
             if(e instanceof FirebaseAuthInvalidCredentialsException){
                 mPhoneNumberField.setError("Invalid Phone No");
             }
             else if(e instanceof FirebaseTooManyRequestsException){



             }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Log.d(TAG,"onCodeSent"+s);
                mverificationId=s;
                mResendToken=forceResendingToken;
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(LoginMobile.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(LoginMobile.this, HomePage.class));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginMobile.this,"Login Failure",Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                mVerificationField.setError("Invalid code.");
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60,  TimeUnit.SECONDS,   this,  mcallbacks);

    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60,   TimeUnit.SECONDS,  this,  mcallbacks, token);

    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }
    @Override    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginMobile.this, HomePage.class));
            finish();
        }
    }
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.button_start_verification:
                if (!validatePhoneNumber()) {
                    return;
                }
                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
                break;
            case R.id.button_verify_phone:
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mverificationId, code);
                break;
            case R.id.button_resend:
                //resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
                String text=mPhoneNumberField.getText().toString().trim();
                if(text.isEmpty()){
                    mPhoneNumberField.setError("should not empty");
                    mPhoneNumberField.requestFocus();
                    return;
                }
                resendVerificationCode(text,mResendToken);
                break;
        }

    }
}
