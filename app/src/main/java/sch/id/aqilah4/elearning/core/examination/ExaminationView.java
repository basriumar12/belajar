package sch.id.aqilah4.elearning.core.examination;


import sch.id.aqilah4.elearning.models.ResponsePackage;
import sch.id.aqilah4.elearning.models.ResponsePassedExam;

public interface ExaminationView {
    void showLoading();
    void hideLoading();
    void dataExamination(ResponsePackage responsePackage);
    void dataExaminationFailed(String message);
    void dataPassedExamination(ResponsePassedExam responsePassedExam);
    void dataPassedExaminationFailed(String message);
}
