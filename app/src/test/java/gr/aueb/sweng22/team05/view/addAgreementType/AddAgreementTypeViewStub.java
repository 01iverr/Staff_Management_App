package gr.aueb.sweng22.team05.view.addAgreementType;

public class AddAgreementTypeViewStub implements AddAgreementTypeView{
    private String id;
    private String name;
    private String salary;
    private String restDays;
    private String sickDays;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;
    private Boolean fullTime;
    private Boolean partTime;
    private Boolean paidBySalary;
    private Boolean paidByWage;

    @Override
    public String getName() { return name; }

    @Override
    public String getSalary() { return salary; }

    @Override
    public String getRestDays() { return restDays; }

    @Override
    public String getSickDays() { return sickDays; }

    @Override
    public Boolean getFullTime() { return fullTime; }

    @Override
    public void setFullTime(boolean i) { fullTime=i;}

    @Override
    public Boolean getPartTime() { return partTime; }

    @Override
    public void setPartTime(boolean i) { partTime=i; }

    @Override
    public Boolean getPaidBySalary() { return paidBySalary; }

    @Override
    public void setPaidBySalary(boolean i) { paidBySalary=i; }

    @Override
    public Boolean getPaidByWage() { return paidByWage; }

    @Override
    public void setPaidByWage(boolean i) { paidByWage=i; }

    @Override
    public void setAgreementTypeResult(String value) {

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
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public void setSalary(String value) {
        this.salary = value;
    }

    @Override
    public void setRestDays(String value) {
        this.restDays = value;
    }

    @Override
    public void setSickDays(String value) {
        this.sickDays = value;
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
