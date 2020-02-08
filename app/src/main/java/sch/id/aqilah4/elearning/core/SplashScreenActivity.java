package sch.id.aqilah4.elearning.core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.authentication.AuthActivity;
import sch.id.aqilah4.elearning.core.authentication.signin.SignInFragment;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed (new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (SplashScreenActivity.this, AuthActivity.class);
                startActivity(i);

                finish ();
            }
        }, 4000);
    }
}
