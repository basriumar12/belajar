
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class ListPackage {

    @SerializedName("exam_detail")
    private String mExamDetail;
    @SerializedName("exam_id")
    private String mExamId;
    @SerializedName("exam_time")
    private String mExamTime;
    @SerializedName("exam_title")
    private String mExamTitle;
    @SerializedName("exam_total")
    private String mExamTotal;

    public String getExamDetail() {
        return mExamDetail;
    }

    public void setExamDetail(String examDetail) {
        mExamDetail = examDetail;
    }

    public String getExamId() {
        return mExamId;
    }

    public void setExamId(String examId) {
        mExamId = examId;
    }

    public String getExamTime() {
        return mExamTime;
    }

    public void setExamTime(String examTime) {
        mExamTime = examTime;
    }

    public String getExamTitle() {
        return mExamTitle;
    }

    public void setExamTitle(String examTitle) {
        mExamTitle = examTitle;
    }

    public String getExamTotal() {
        return mExamTotal;
    }

    public void setExamTotal(String examTotal) {
        mExamTotal = examTotal;
    }

}
