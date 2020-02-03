package sch.id.aqilah4.elearning.core.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import butterknife.ButterKnife;
import sch.id.aqilah4.elearning.R;
import sch.id.aqilah4.elearning.core.authentication.signin.SignInFragment;

public class AuthActivity extends AppCompatActivity implements AuthView {
    private AuthPresenter authPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        authPresenter   = new AuthPresenter(this);

        final SignInFragment signInFragment = new SignInFragment();
        loadFragmentSignIn(signInFragment);

    }
    @Override
    public void moveTo(Intent intent) {
        startActivity(intent);
    }

    private void loadFragmentSignIn(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_auth, fragment, "Load SignIn")
                .commit();
    }
}
