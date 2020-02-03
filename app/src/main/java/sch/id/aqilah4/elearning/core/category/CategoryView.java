package sch.id.aqilah4.elearning.core.category;

import android.content.Intent;

import sch.id.aqilah4.elearning.models.ResponseDetailCategory;



public interface CategoryView {
    void showLoading();
    void hideLoading();
    void categoryDetailSuccess(ResponseDetailCategory responseDetailCategory);
    void categoryDetailFailed(String message);
    void moveToPackage(Intent intent);
}
