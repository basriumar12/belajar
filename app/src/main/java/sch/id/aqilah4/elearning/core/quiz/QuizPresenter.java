package sch.id.aqilah4.elearning.core.quiz;

import android.util.Log;


import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import sch.id.aqilah4.elearning.BaseApps;
import sch.id.aqilah4.elearning.models.Examination;
import sch.id.aqilah4.elearning.models.Hasil;
import sch.id.aqilah4.elearning.models.RequestReport;
import sch.id.aqilah4.elearning.models.ResponsePackage;
import sch.id.aqilah4.elearning.models.ResponseReport;
import sch.id.aqilah4.elearning.network.NetworkClient;
import sch.id.aqilah4.elearning.network.RequestAPI;
import sch.id.aqilah4.elearning.utils.SessionManagement;

import static android.content.ContentValues.TAG;


public class QuizPresenter {
    private CompositeDisposable mCompositeDisposable;
    private QuizView view;
    private SessionManagement mSessionManagement;
    private HashMap<String, String> user;

    public QuizPresenter(QuizView view) {
        this.view = view;
        mSessionManagement  = new SessionManagement(BaseApps.getAppContext());
        loadToken();
    }

    public void loadExamination(String examId){
        view.showLoading();
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.examination(
                user.get(SessionManagement.key_token).toString(),
                examId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        responsePackage -> handleResponse(responsePackage),
                        throwable -> handleError(throwable)
                )
        );
    }

    // Get Available Token
    private void loadToken() {
        if (mSessionManagement != null){
            user = mSessionManagement.getUserDetails();
            Log.d(TAG, "loadToken: "+ user.get(SessionManagement.key_userid).toString());
        }
    }
    private void handleResponse(ResponsePackage responsePackage){
        view.loadExamination(responsePackage);
        view.hideLoading();
    }

    private void handleError(Throwable throwable){
        Log.d(TAG, "handleError: "+throwable.getMessage());
        view.loadExaminationError(throwable.getLocalizedMessage().toString());
        view.hideLoading();
    }

    public void changeQuiz(int position, List<Examination> examination){
        view.changeQuiz(examination.get(position));
    }

    public void submitExamination(String examId, List<Hasil> hasils){
        RequestReport requestReport;
        requestReport   = new RequestReport();
        /* Set Request Report Data */
        requestReport.setExamId(examId);
        requestReport.setUserId(user.get(SessionManagement.key_userkon).toString());
        requestReport.setHasil(hasils);

        view.submitShowLoading();
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.passreport(user.get(
                SessionManagement.key_token).toString(),
                requestReport)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        responseReport -> handleExamSubmit(responseReport),
                        throwable -> handleExamSubmitError(throwable)
                )
        );
    }
//ini
    private void handleExamSubmit(ResponseReport report){
        view.submitExamination(report);
        view.submitHideLoading();
    }
    private void handleExamSubmitError(Throwable t){
        Log.e("TAG","error submit exam "+t);
        view.submitExaminationError(t.getLocalizedMessage().toString());
        view.submitHideLoading();
    }
}
