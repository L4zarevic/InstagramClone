package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private EditText edtEmail, edtPass;
    private Button btnSignUp, btnLogIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        //Initialization
        edtEmail = findViewById(R.id.edtEmail_LogInActivity);
        edtPass = findViewById(R.id.edtPass_LogInActivity);
        edtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        btnSignUp = findViewById(R.id.btnSingUp_LogInActivity);
        btnLogIn = findViewById(R.id.btnLogIn_LogInActivity);

        btnLogIn.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        //Token session check
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    //OnClick method for sign up and login button
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogIn_LogInActivity:
                if (edtEmail.getText().toString().trim().equals("") ||
                        edtPass.getText().toString().trim().equals("")) {

                    FancyToast.makeText(LoginActivity.this, "Email,Password is required!", Toast.LENGTH_SHORT, FancyToast.INFO, true).show();

                } else {
                    ParseUser.logInInBackground(edtEmail.getText().toString().trim(), edtPass.getText().toString().trim(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged in successfully ", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            }
                        }
                    });
                }
                break;

            case R.id.btnSingUp_LogInActivity:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }

    //A method that allows us to hide the keyboard
    public void rootLoginLayout(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
