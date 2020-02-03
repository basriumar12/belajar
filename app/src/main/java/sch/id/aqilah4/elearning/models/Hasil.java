
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class Hasil {

    @SerializedName("jawab")
    private String mJawab;
    @SerializedName("soal_id")
    private String mSoalId;

    public String getJawab() {
        return mJawab;
    }

    public void setJawab(String jawab) {
        mJawab = jawab;
    }

    public String getSoalId() {
        return mSoalId;
    }

    public void setSoalId(String soalId) {
        mSoalId = soalId;
    }

}
