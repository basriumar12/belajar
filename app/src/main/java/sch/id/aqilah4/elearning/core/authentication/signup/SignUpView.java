package sch.id.aqilah4.elearning.core.authentication.signup;


import sch.id.aqilah4.elearning.models.ResponseAuth;


public interface SignUpView {
    void showLoading(String loadingMessages);
    void hideLoading();
    void dataFailed(String message);
    void dataSuccess(ResponseAuth responseAuth);
}
