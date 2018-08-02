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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();

        btnSignIn=(Button) findViewById(R.id.login);
        btnSignUp=(Button) findViewById(R.id.register);
        inputEmail=(EditText) findViewById(R.id.emaill);
        inputPassword=(EditText) findViewById(R.id.password);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword=(Button) findViewById(R.id.resetpass);
    }

    public void reg(View view) {

        String email=inputEmail.getText().toString().trim();
        String password=inputPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(getApplicationContext(),"Enter email address!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter password!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(getApplicationContext(),"Password is too short, enter minimum 6 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);






        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(MainActivity.this,"Email id successfully registered",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Authentication Failed."+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(MainActivity.this,Success.class));
                        }

                    }
                });

    }

    public void reset(View view) {

        startActivity(new Intent(MainActivity.this,Resetpass.class));

    }

    public void loggingin(View view) {

        startActivity(new Intent(MainActivity.this,Login.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
