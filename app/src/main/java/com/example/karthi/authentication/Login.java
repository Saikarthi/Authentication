package com.example.karthi.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword,edittext;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button signup, login, reset,button2;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){

            startActivity(new Intent(Login.this, Success.class));

        }


        inputEmail=(EditText) findViewById(R.id.email1);
        inputPassword=(EditText) findViewById(R.id.password1);
        progressBar=(ProgressBar) findViewById(R.id.progressBar2);
        signup=(Button) findViewById(R.id.signup);
        login=(Button) findViewById(R.id.loginbut);
        reset=(Button) findViewById(R.id.resetpass1);
        edittext=(EditText) findViewById(R.id.editText);
        button2=(Button) findViewById(R.id.button2);

        auth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

    }

    public void log(View view) {

        String email=inputEmail.getText().toString();
        final String password=inputPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter email address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                inputPassword.setError("Password too short, enter minimum 6 characters");
                            } else {
                                Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Intent intent = new Intent(Login.this, Success.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }


    public void res(View view) {
        startActivity(new Intent(Login.this,Resetpass.class));
    }

    public void sign(View view) {
        startActivity(new Intent(Login.this,MainActivity.class));
    }

    public void sendotp(View view) {

        String phonenumber = edittext.getText().toString();
        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber,60, TimeUnit.SECONDS,this,verificationCallbacks);

    }

    private void setUpVerificationCallbacks() {



    }
}
