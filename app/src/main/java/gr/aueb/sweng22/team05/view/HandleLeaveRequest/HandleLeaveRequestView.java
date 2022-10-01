package gr.aueb.sweng22.team05.view.HandleLeaveRequest;

public interface HandleLeaveRequestView {
    String getID();
    String getLeaveType();
    String getStartDate();
    String getEndDate();
    String getName();
    String getAFM();
    String getEmail();
    String getPhone();
    String getRestTaken();
    String getSickTaken();
    String getLeaveRequestID();

    /**
     * Sets leave's id
     * @param value leave's id
     */
    void setID(String value);

    /**
     * Sets leave's type
     * @param value leave's type
     */
    void setLeaveType(String value);

    /**
     * Sets leave's start date
     * @param value leave's start date
     */
    void setStart(String value);

    /**
     * Sets leave's end date
     * @param value leave's end date
     */
    void setEnd(String value);

    /**
     * Sets worker's name
     * @param value worker's name
     */
    void setName(String value);

    /**
     * Sets worker's afm
     * @param value worker's afm
     */
    void setAFM(String value);

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    void setPhone(Long value);

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    void setEmail(String value);

    /**
     * Sets worker's total sick days taken
     * @param value worker's total sick days
     */
    void setSickTaken(String value);

    /**
     * Sets worker's total rest days taken
     * @param value worker's total rest days
     */
    void setRestTaken(String value);

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    void successfullyFinishActivity(String message);

    /**
     * Sets leave request id from previous action
     */
    void setLeaveRequestId();
}
