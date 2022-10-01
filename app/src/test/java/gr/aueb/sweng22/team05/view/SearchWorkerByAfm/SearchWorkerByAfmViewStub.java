package gr.aueb.sweng22.team05.view.SearchWorkerByAfm;

import gr.aueb.sweng22.team05.view.search_worker_by_afm.SearchWorkerByAfmView;

public class SearchWorkerByAfmViewStub implements SearchWorkerByAfmView {
    private String afm;
    private String errorMessage;
    private String errorTitle;

    @Override
    public String gettheafm() {return afm; }
    public void settheAfm(String afmm) { afm=afmm; }
    public String getErrorTitle() {
        return errorTitle;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    @Override
    public void showErrorMessage(String title, String message) {this.errorTitle = title;this.errorMessage = message; }
}
