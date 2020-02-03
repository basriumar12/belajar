package sch.id.aqilah4.elearning.core.authentication;

import android.app.Activity;
import android.content.Intent;

import sch.id.aqilah4.elearning.core.dashboard.DashboardActivity;


public class AuthPresenter {
    private AuthView view;

    public AuthPresenter(AuthView view){
        this.view   = view;
    }

    public void moveTo(Activity activity){
        Intent intent   = new Intent(activity, DashboardActivity.class);
        view.moveTo(intent);
    }
}
