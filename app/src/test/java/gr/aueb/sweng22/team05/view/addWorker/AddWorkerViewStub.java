package gr.aueb.sweng22.team05.view.addWorker;

import gr.aueb.sweng22.team05.domain.Agreement;

public class AddWorkerViewStub implements AddWorkerView{
    private static String WORKER_AFM = "afm";

    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String workerType;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;
    private Long phone;
    private Agreement agreement;

    public AddWorkerViewStub(){
        firstName = lastName = email = department = workerType =
                errorTitle = errorMessage = finishMessage = "";
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
    public String getManagerEmployee() {
        return workerType;
    }

    @Override
    public Agreement getAgreement(){
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
     * Sets Worker type (Manager or Employee)
     * @param value worker's type (Manager or Employee)
     */
    public void setWorkerType(String value){
        this.workerType = value;
    }

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    @Override
    public void setWorkerAFM(String value) {
        WORKER_AFM = value;
    }

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
