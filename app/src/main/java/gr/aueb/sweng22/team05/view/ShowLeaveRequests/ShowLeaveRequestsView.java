package gr.aueb.sweng22.team05.view.ShowLeaveRequests;

public interface ShowLeaveRequestsView {
    String getID();
    String getType();
    String getName();
    String getStartDate();
    String getEndDate();

    /**
     * Sets id value of leave request to be handled
     * @param value id of leave request
     */
    void setLeaveRequestID(String value);

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    void successfullyFinishActivity(String message);

}
