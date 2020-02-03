
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("auth")
    private Auth mAuth;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;

    public Auth getAuth() {
        return mAuth;
    }

    public void setAuth(Auth auth) {
        mAuth = auth;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
