package gr.aueb.sweng22.team05.view.hrPage;

public interface HrPageView {
    /**
     * Gets month for calc pay
     * @return month for calc pay
     */
    String getMonth();

    /**
     * Gets year for calc pay
     * @return year for calc pay
     */
    String getYear();

    /**
     * Sets month for calc pay
     * @param value month for calc pay
     */
    void setMonth(String value);

    /**
     * Sets year for calc pay
     * @param value year for calc pay
     */
    void setYear(String value);

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

    /**
     * Message shown when a task of an activity finishes successfully
     * @param message message to be shown
     */
    void successfullyFinishTask(String title, String message);
}
