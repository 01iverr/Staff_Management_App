package gr.aueb.sweng22.team05.view.LeaveRequest;

import gr.aueb.sweng22.team05.view.LeaveRequest.LeaveRequestView;


public class LeaveRequestViewStub implements LeaveRequestView {
    private String StartDateLR;
    private String EndDateLR;
    private boolean isSingleDR;
    private boolean isSickDR;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;
    Long IDofTheWorker;
    @Override
    public String getStartDateLR() {
        return StartDateLR;
    }
    public void setStartDateLR(String a) {
        StartDateLR=a;
    }
    @Override
    public String getEndDateLR() {
        return EndDateLR;
    }
    public void setEndDateLR(String a) {
         EndDateLR = a;
    }

    @Override
    public boolean isSingleDR() {
        return isSingleDR;
    }

    @Override
    public void setisSingleDR(boolean a) {
        this.isSingleDR=a;
    }

    @Override
    public boolean isSickDR() {
        return isSickDR;
    }


    public void setifIsSickDR(Boolean a) {
        isSickDR=a;
    }

    @Override
    public void showErrorMessage(String title, String message)
    {this.errorTitle = title;this.errorMessage = message;}

    @Override
    public Long getIDofTheWorker() { return IDofTheWorker; }

    public void setIDofTheWorker(Long a) { IDofTheWorker=a;}
    @Override
    public void successfullyFinishActivity(String message) {
        this.finishMessage = message;
    }
}