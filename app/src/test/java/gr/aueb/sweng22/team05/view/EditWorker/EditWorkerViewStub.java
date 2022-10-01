package gr.aueb.sweng22.team05.view.EditWorker;

import gr.aueb.sweng22.team05.domain.Agreement;

public class EditWorkerViewStub implements EditWorkerView{
    private static String WORKER_AFM = "afm";

    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private String department;
    private Agreement agreement;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;

    public EditWorkerViewStub(){
        firstName = lastName = email = department = errorTitle = errorMessage
                = finishMessage = "";
        agreement = null;
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

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Long getPhone() {
        return phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAFM() {
        return WORKER_AFM;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public Agreement getAgreement() {
        return agreement;
    }

    /**
     * Sets worker's first name
     * @param value worker's first name
     */
    @Override
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Sets worker's last name
     * @param value worker's last name
     */
    @Override
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    @Override
    public void setPhone(Long value) {
        this.phone = value;
    }

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    @Override
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Sets worker department name
     * @param value worker's department name
     */
    @Override
    public void setDepartment(String value) {
        this.department = value;
    }

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    @Override
    public void setAFM(String value) {

    }

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    public void setAfm(String value) {
        WORKER_AFM = value;
    }

    /**
     * Sets worker agreement
     * @param value worker's agreement
     */
    public void setAgreement(Agreement value){
        this.agreement = value;
    }

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishActivity(String message) {
        this.finishMessage = message;
    }

    /**
     * Message shown in case of error
     * @param title   message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        this.errorTitle = title;
        this.errorMessage = message;
    }
}
