package gr.aueb.sweng22.team05.view.hrPage;

public class HrPageViewStub implements HrPageView{
    private String month;
    private String year;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;

    @Override
    public String getMonth() {
        return month;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public void setMonth(String value) {
        this.month = value;
    }

    @Override
    public void setYear(String value) {
        this.year = value;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFinishMessage() {
        return finishMessage;
    }

    /**
     * Message shown when activity finishes successfully
     *
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishActivity(String message) {
        this.finishMessage = message;
    }

    /**
     * Message shown in case of error
     *
     * @param title   message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        this.errorTitle = title;
        this.errorMessage = message;
    }

    /**
     * Message shown when a task of an activity finishes successfully
     * @param message message to be shown
     */
    public void successfullyFinishTask(String title, String message) {
        this.errorTitle = title;
        this.errorMessage = message;
    }
}
