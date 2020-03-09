package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private EditText edtEmail, edtUsername, edtPass;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up");

        //Initialization
        edtEmail = findViewById(R.id.edtEmail_SignUpActivity);
        edtUsername = findViewById(R.id.edtUsername_SignUpActivity);
        edtPass = findViewById(R.id.edtPass_SignUpActivity);
        btnSignUp = findViewById(R.id.btnSignUp_SignUpActivity);
        btnLogin = findViewById(R.id.btnLogIn_SignUpActivity);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //Token session check
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }


    }


    //OnClick method for signup and login button
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignUp_SignUpActivity:

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPass.getText().toString());

                //Creating progress dialog which showing when tap sing up button
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up "+edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;

            case R.id.btnLogIn_SignUpActivity:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }

    }
}
