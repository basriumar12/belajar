package sch.id.aqilah4.elearning.core.authentication.signin;


import sch.id.aqilah4.elearning.models.ResponseAuth;

public interface SignInView {
    void showLoading(String loadingMessages);
    void hideLoading();
    void dataFailed(String message);
    void dataSuccess(ResponseAuth responseAuth);
}
