package gr.aueb.sweng22.team05.view.HandleLeaveRequest;

public class HandleLeaveRequestViewStub implements HandleLeaveRequestView{

    private String id;
    private String type;
    private String start;
    private String end;
    private String name;
    private String afm;
    private String email;
    private Long phone;
    private String restTaken;
    private String sickTaken;
    private static String LEAVE_REQUEST_ID = "leave_request_id";

    private String finishMessage = "";

    public String getFinishMessage(){
        return finishMessage;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getLeaveType() {
        return type;
    }

    @Override
    public String getStartDate() {
        return start;
    }

    @Override
    public String getEndDate() {
        return end;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAFM() {
        return afm;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return String.valueOf(phone);
    }

    @Override
    public String getRestTaken() {
        return restTaken;
    }

    @Override
    public String getSickTaken() {
        return sickTaken;
    }

    @Override
    public String getLeaveRequestID() {
        return LEAVE_REQUEST_ID;
    }

    /**
     * Sets leave's id
     * @param value leave's id
     */
    @Override
    public void setID(String value) {
        id = value;
    }

    /**
     * Sets leave's type
     * @param value leave's type
     */
    @Override
    public void setLeaveType(String value) {
        type = value;
    }

    /**
     * Sets leave's start date
     * @param value leave's start date
     */
    @Override
    public void setStart(String value) {
        start = value;
    }

    /**
     * Sets leave's end date
     * @param value leave's end date
     */
    @Override
    public void setEnd(String value) {
        end = value;
    }

    /**
     * Sets worker's name
     * @param value worker's name
     */
    @Override
    public void setName(String value) {
        name = value;
    }

    /**
     * Sets worker's afm
     * @param value worker's afm
     */
    @Override
    public void setAFM(String value) {
        afm = value;
    }

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    @Override
    public void setPhone(Long value) {
        phone = value;
    }

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    @Override
    public void setEmail(String value) {
        email = value;
    }

    /**
     * Sets worker's total sick days taken
     * @param value worker's total sick days
     */
    @Override
    public void setSickTaken(String value) {
        sickTaken = value;
    }

    /**
     * Sets worker's total rest days taken
     * @param value worker's total rest days
     */
    @Override
    public void setRestTaken(String value) {
        restTaken = value;
    }

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishActivity(String message) {
        finishMessage = message;
    }

    /**
     * Sets leave request id from previous action
     */
    @Override
    public void setLeaveRequestId() {
        LEAVE_REQUEST_ID = "13";
    }
}
