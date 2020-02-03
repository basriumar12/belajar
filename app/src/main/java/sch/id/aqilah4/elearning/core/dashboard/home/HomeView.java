package sch.id.aqilah4.elearning.core.dashboard.home;

import android.content.Intent;

import sch.id.aqilah4.elearning.models.ResponseCategory;
import sch.id.aqilah4.elearning.models.ResponseLatest;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void categorySuccess(ResponseCategory responseCategory);
    void categoryFailed(String failed);
    void latestSuccess(ResponseLatest latest);
    void latestFailed(String failed);
    void moveToCategory(Intent intent);
    void moveToPackage(Intent intent);
}
