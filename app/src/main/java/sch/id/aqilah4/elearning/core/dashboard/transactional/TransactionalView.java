package sch.id.aqilah4.elearning.core.dashboard.transactional;


import sch.id.aqilah4.elearning.models.ResponseHistory;


public interface TransactionalView {
    void loadHistory(ResponseHistory history);
    void loadHistoryError(String message);
    void showLoading();
    void hideLoading();
}
