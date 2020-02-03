package sch.id.aqilah4.elearning.core.category;

import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import sch.id.aqilah4.elearning.BaseApps;
import sch.id.aqilah4.elearning.core.examination.ExaminationActivity;
import sch.id.aqilah4.elearning.models.ListPackage;
import sch.id.aqilah4.elearning.models.ResponseDetailCategory;
import sch.id.aqilah4.elearning.network.NetworkClient;
import sch.id.aqilah4.elearning.network.RequestAPI;
import sch.id.aqilah4.elearning.utils.SessionManagement;


public class CategoryPresenter {
    private CategoryView view;
    private CompositeDisposable mCompositeDisposable;
    private SessionManagement mSessionManagement;
    private HashMap<String, String> user;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
        this.mSessionManagement = new SessionManagement(BaseApps.getAppContext());
        loadToken();
    }
    // Load Token
    private void loadToken() {
        if (mSessionManagement != null){
            user = mSessionManagement.getUserDetails();
        }
    }
    // Load Data Detail
    public void loadDataDetail(String id){
        view.showLoading();
        RequestAPI requestAPI   = NetworkClient.getRetrofit().create(RequestAPI.class);
        if (mCompositeDisposable == null)
            mCompositeDisposable    = new CompositeDisposable();

        mCompositeDisposable.add(
                requestAPI.detailcategory(user.get(SessionManagement.key_token).toString(), id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        responseDetailCategory -> handleCategory(responseDetailCategory),
                        throwable -> handleCategoryError(throwable)
                )
        );
    }
    private void handleCategory(ResponseDetailCategory category){
        view.categoryDetailSuccess(category);
        view.hideLoading();
    }
    private void handleCategoryError(Throwable throwable){
        view.categoryDetailFailed(throwable.getLocalizedMessage().toString());
        view.hideLoading();
    }
    public void destroyData(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
    }
    public void getItemExamination(ListPackage listPackage, Activity activity){
        Intent intent   = new Intent(activity, ExaminationActivity.class);
        intent.putExtra("title", listPackage.getExamTitle());
        intent.putExtra("id", listPackage.getExamId());
        view.moveToPackage(intent);
    }
}
