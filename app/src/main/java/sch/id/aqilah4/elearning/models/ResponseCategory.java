
package sch.id.aqilah4.elearning.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCategory {

    @SerializedName("category")
    private List<Category> mCategory;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Category> getCategory() {
        return mCategory;
    }

    public void setCategory(List<Category> category) {
        mCategory = category;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
