
package sch.id.aqilah4.elearning.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLatest {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("package_latest")
    private List<PackageLatest> mPackageLatest;
    @SerializedName("status")
    private Boolean mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<PackageLatest> getPackageLatest() {
        return mPackageLatest;
    }

    public void setPackageLatest(List<PackageLatest> packageLatest) {
        mPackageLatest = packageLatest;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
