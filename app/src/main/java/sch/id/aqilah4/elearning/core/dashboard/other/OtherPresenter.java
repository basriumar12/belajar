package sch.id.aqilah4.elearning.core.dashboard.other;


import sch.id.aqilah4.elearning.BaseApps;
import sch.id.aqilah4.elearning.utils.SessionManagement;

public class OtherPresenter {
    OtherView otherView;
    private SessionManagement mSessionManagement;
    public OtherPresenter(OtherView otherView) {
        this.otherView = otherView;
        mSessionManagement  = new SessionManagement(BaseApps.getAppContext());
    }

    public void signoutSystem(){
        mSessionManagement.logout();
        otherView.signout();
    }
}
