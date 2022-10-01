package gr.aueb.sweng22.team05.view.addAgreement;

import gr.aueb.sweng22.team05.domain.AgreementType;

public class AddAgreementViewStub implements AddAgreementView {
    public static String AGREEMENT_RESULT = "agreement_id";

    private String hireDate;
    private String startDate;
    private String endDate;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;

    public AddAgreementViewStub(){
        hireDate = startDate = endDate = "";
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
    public String getHireDate() {
        return hireDate;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public AgreementType getAgreementType() {
        return new AgreementType();
    }

    /**
     * Sets worker's Hire Date
     * @param value worker's Hire Date
     */
    @Override
    public void setHireDate(String value) {
        this.hireDate = value;
    }

    /**
     * Sets worker's Start Date
     * @param value worker's Start Date
     */
    @Override
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Sets worker's End Date
     * @param value worker's End Date
     */
    @Override
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Set new value to AGREEMENT_RESULT
     *
     * @param value new value
     */
    @Override
    public void setAgreementResult(String value) {
        AGREEMENT_RESULT = value;
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
