package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
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
    private Button btnSingUp, btnLogIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialization
        edtEmail = findViewById(R.id.edtEmail_LogInActivity);
        edtPass = findViewById(R.id.edtPass_LogInActivity);
        btnSingUp = findViewById(R.id.btnSingUp_LogInActivity);
        btnLogIn = findViewById(R.id.btnLogIn_LogInActivity);

        btnLogIn.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        //Token session check
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogIn_LogInActivity:
                ParseUser.logInInBackground(edtEmail.getText().toString().trim(), edtPass.getText().toString().trim(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged in successfully ", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        }else{

                        }
                    }
                });
                break;

            case R.id.btnSingUp_LogInActivity:
                break;
        }
    }
}
