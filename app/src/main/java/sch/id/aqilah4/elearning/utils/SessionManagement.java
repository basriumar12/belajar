package sch.id.aqilah4.elearning.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

import sch.id.aqilah4.elearning.core.dashboard.DashboardActivity;


public class SessionManagement {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    Context mContext;
    int mode    = 1;

    public static final String session_name    = "elearning";
    private static final String is_login   = "islogin";
    public static final String key_username = "keyusername";
    public static final String key_token    = "keytoken";
    public static final String key_fullname = "keyfullname";
    public static final String key_userid   = "keyuserid";
    public static final String key_userkon  = "keyuserkon";

    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences  = mContext.getSharedPreferences(session_name, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    public void createSession(String username, String token, String fullname, String userid, String userkon){
        mEditor.putBoolean(is_login, true);
        mEditor.putString(key_username, username);
        mEditor.putString(key_token, token);
        mEditor.putString(key_fullname, fullname);
        mEditor.putString(key_userid, userid);
        mEditor.putString(key_userkon, userkon);
        mEditor.commit();
    }

    public void checkAuth(){
        if (this.is_login()){
            // Isset Authentication Data
            Intent intent   = new Intent(mContext, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
        }
    }

    public boolean is_login(){
        return mSharedPreferences.getBoolean(is_login, false);
    }

    public void logout(){
        mEditor.clear();
        mEditor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user    = new HashMap<String, String>();
        user.put(key_fullname, mSharedPreferences.getString(key_fullname, null));
        user.put(key_token, mSharedPreferences.getString(key_token, null));
        user.put(key_username, mSharedPreferences.getString(key_username, null));
        user.put(key_userid, mSharedPreferences.getString(key_userid, null));
        user.put(key_userkon, mSharedPreferences.getString(key_userkon, null));
        return user;
    }
}
