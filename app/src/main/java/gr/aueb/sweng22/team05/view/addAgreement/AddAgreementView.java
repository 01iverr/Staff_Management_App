package gr.aueb.sweng22.team05.view.addAgreement;

import gr.aueb.sweng22.team05.domain.AgreementType;

public interface AddAgreementView {
    /**
     * Gets worker's Hire Date
     * @return Hire date in String
     */
    String getHireDate();

    /**
     * Gets worker's Start Date
     * @return Start date in String
     */
    String getStartDate();

    /**
     * Gets worker's End Date
     * @return End date in String
     */
    String getEndDate();

    /**
     * Gets the Agreement Type with the id given by AddAgreementTypeActivity
     * @return Agreement Type object
     */
    AgreementType getAgreementType();

    /**
     * Sets worker's Hire Date
     * @param value worker's Hire Date
     */
    void setHireDate(String value);

    /**
     * Sets worker's Start Date
     * @param value worker's Start Date
     */
    void setStartDate(String value);

    /**
     * Sets worker's End Date
     * @param value worker's End Date
     */
    void setEndDate(String value);

    /**
     * Set new value to AGREEMENT_RESULT
     * @param value new value
     */
    void setAgreementResult(String value);

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
