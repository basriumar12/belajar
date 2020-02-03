package sch.id.aqilah4.elearning.core.quiz;


import sch.id.aqilah4.elearning.models.Examination;
import sch.id.aqilah4.elearning.models.ResponsePackage;
import sch.id.aqilah4.elearning.models.ResponseReport;

public interface QuizView {
    void loadExamination(ResponsePackage responsePackage);
    void loadExaminationError(String messages);
    void showLoading();
    void hideLoading();
    void changeQuiz(Examination examination);
    void changeQuizError(String messages);
    void submitExamination(ResponseReport responseReport);
    void submitExaminationError(String messages);
    void submitShowLoading();
    void submitHideLoading();
}
