
package sch.id.aqilah4.elearning.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RequestReport {

    @SerializedName("exam_id")
    private String mExamId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("hasil")
    private List<Hasil> mHasil;

    public String getExamId() {
        return mExamId;
    }

    public void setExamId(String examId) {
        mExamId = examId;
    }

    public List<Hasil> getHasil() {
        return mHasil;
    }

    public void setHasil(List<Hasil> hasil) {
        mHasil = hasil;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
