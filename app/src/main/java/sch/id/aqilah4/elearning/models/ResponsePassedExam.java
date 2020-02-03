
package sch.id.aqilah4.elearning.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePassedExam {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("pass_exam")
    private List<PassExam> mPassExam;
    @SerializedName("status")
    private Boolean mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<PassExam> getPassExam() {
        return mPassExam;
    }

    public void setPassExam(List<PassExam> passExam) {
        mPassExam = passExam;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
