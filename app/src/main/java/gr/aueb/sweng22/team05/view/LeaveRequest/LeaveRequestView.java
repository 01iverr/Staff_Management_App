package gr.aueb.sweng22.team05.view.LeaveRequest;

import gr.aueb.sweng22.team05.domain.Worker;

public interface LeaveRequestView {
    String getStartDateLR();
    String getEndDateLR();
    boolean isSingleDR();
    boolean isSickDR();
    void showErrorMessage(String title, String message);
    Long getIDofTheWorker();
    void successfullyFinishActivity(String message);
    void setisSingleDR(boolean a);
}

