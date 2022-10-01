package gr.aueb.sweng22.team05.view.addDepartment;

public class AddDepartmentViewStub implements AddDepartmentView {
    private String name;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;

    @Override
    public String getName() { return name; }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFinishMessage() {
        return finishMessage;
    }

    @Override
    public void setName(String value) {
        this.name = value;
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
}
