package com.example.karthi.authentication;

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
import com.google.firebase.auth.FirebaseAuth;

public class Resetpass extends AppCompatActivity {

    private EditText inputEmail;
    private Button reset, back;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        inputEmail=(EditText) findViewById(R.id.emailr);
        reset=(Button) findViewById(R.id.resetbt);
        back=(Button) findViewById(R.id.back);
        progressBar =(ProgressBar) findViewById(R.id.progressBar3);

        auth =FirebaseAuth.getInstance();

    }

    public void rest(View view) {

        String email=inputEmail.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter your email address",Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Resetpass.this,"We have sent you a mail for reset password",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Resetpass.this,"Failed to send email!!!",Toast.LENGTH_SHORT) .show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    public void back(View view) {

        finish();

    }
}
