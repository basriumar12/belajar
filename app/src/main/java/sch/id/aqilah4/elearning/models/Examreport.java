
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class Examreport {

    @SerializedName("correct")
    private Double mCorrect;
    @SerializedName("grade")
    private Double mGrade;
    @SerializedName("grade_weight")
    private Double mGradeWeight;
    @SerializedName("total_exam")
    private Double mTotalExam;

    public Double getCorrect() {
        return mCorrect;
    }

    public void setCorrect(Double correct) {
        mCorrect = correct;
    }

    public Double getGrade() {
        return mGrade;
    }

    public void setGrade(Double grade) {
        mGrade = grade;
    }

    public Double getGradeWeight() {
        return mGradeWeight;
    }

    public void setGradeWeight(Double gradeWeight) {
        mGradeWeight = gradeWeight;
    }

    public Double getTotalExam() {
        return mTotalExam;
    }

    public void setTotalExam(Double totalExam) {
        mTotalExam = totalExam;
    }

}
