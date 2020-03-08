package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Xuw7fcSXurA2wYjmjLRhWRE3Y1Aclu3amZ9fdz9U")
                // if defined
                .clientKey("I5ijmagSfNxKcj9L8PCs0GZmpSrtybs7UKQyQaLG")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
