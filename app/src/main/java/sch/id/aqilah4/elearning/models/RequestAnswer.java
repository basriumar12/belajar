
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class RequestAnswer {

    @SerializedName("jawaban")
    private String mJawaban;
    @SerializedName("soal_id")
    private String mSoalId;

    public String getJawaban() {
        return mJawaban;
    }

    public void setJawaban(String jawaban) {
        mJawaban = jawaban;
    }

    public String getSoalId() {
        return mSoalId;
    }

    public void setSoalId(String soalId) {
        mSoalId = soalId;
    }

}
