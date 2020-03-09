package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    //Variables
    private EditText edtUserSignUp, edtPassSignUp, edtUserLogIn, edtPassLogIn;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        //Initialization
        edtUserSignUp = findViewById(R.id.edtUserSignUp);
        edtPassSignUp = findViewById(R.id.edtPassSignUp);
        edtUserLogIn = findViewById(R.id.edtUserLogIn);
        edtPassLogIn = findViewById(R.id.edtPassLogIn);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserSignUp.getText().toString());
                appUser.setPassword(edtPassSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,  appUser.get("username")+" is sign up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else {
                            FancyToast.makeText(SignUpLoginActivity.this,  e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserLogIn.getText().toString(), edtPassLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,  user.get("username")+" is log in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,  e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });


    }
}
