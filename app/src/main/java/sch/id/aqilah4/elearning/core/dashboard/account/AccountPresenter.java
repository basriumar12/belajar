package sch.id.aqilah4.elearning.core.dashboard.account;


import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import sch.id.aqilah4.elearning.BaseApps;
import sch.id.aqilah4.elearning.models.ResponseAuth;
import sch.id.aqilah4.elearning.network.NetworkClient;
import sch.id.aqilah4.elearning.network.RequestAPI;
import sch.id.aqilah4.elearning.utils.SessionManagement;



public class AccountPresenter {
    private SessionManagement mSessionManagement;
    private HashMap<String, String> user;
    private CompositeDisposable mCompositeDisposable;

    AccountView view;
    public AccountPresenter(AccountView view) {
        this.view = view;
        mSessionManagement  = new SessionManagement(BaseApps.getAppContext());
        loadUserData();
    }
    private void loadUserData(){
        if (mSessionManagement != null)
            user    = mSessionManagement.getUserDetails();
    }
    public void loadMyProfile(){
        view.loadDetail(user);
    }
    public void changePassword(String newpassword, String oldpassword){
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(requestAPI.changepassword(
                user.get(SessionManagement.key_token).toString().trim(),
                user.get(SessionManagement.key_username).toString().trim(),
                oldpassword,
                newpassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        responseAuth -> handleResponse(responseAuth),
                        throwable -> handleError(throwable)
                )
        );
    }
    private void handleResponse(ResponseAuth responseAuth){
        view.passwordChangged(responseAuth);
    }
    private void handleError(Throwable throwable){
        view.passwordChanggedError(throwable.getLocalizedMessage().toString());
    }
    public void destroyData(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }
}
