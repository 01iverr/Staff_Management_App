package gr.aueb.sweng22.team05.view.addDepartment;

public interface AddDepartmentView {
    String getName();

    /**
     * set the department's name
     * @param value
     */
    void setName(String value);

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    void successfullyFinishActivity(String message);

    /**
     * Message shown in case of error
     * @param title message title
     * @param message message content
     */
    void showErrorMessage(String title, String message);
}
