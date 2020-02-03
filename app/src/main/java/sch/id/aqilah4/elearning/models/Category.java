
package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("category_detail")
    private String mCategoryDetail;
    @SerializedName("category_id")
    private String mCategoryId;
    @SerializedName("category_name")
    private String mCategoryName;
    @SerializedName("category_thumbnail")
    private String mCategoryThumbnail;

    public String getCategoryDetail() {
        return mCategoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        mCategoryDetail = categoryDetail;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryThumbnail() {
        return mCategoryThumbnail;
    }

    public void setCategoryThumbnail(String categoryThumbnail) {
        mCategoryThumbnail = categoryThumbnail;
    }

}
