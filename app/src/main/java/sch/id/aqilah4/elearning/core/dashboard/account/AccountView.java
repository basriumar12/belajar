package sch.id.aqilah4.elearning.core.dashboard.account;


import java.util.HashMap;

import sch.id.aqilah4.elearning.models.ResponseAuth;



public interface AccountView {
    void loadDetail(HashMap<String, String> user);
    void passwordChangged(ResponseAuth responseAuth);
    void passwordChanggedError(String message);
}
