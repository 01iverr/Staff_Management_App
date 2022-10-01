package gr.aueb.sweng22.team05.view.addAgreementType;

public interface AddAgreementTypeView {
    /**
     * Gets AgreementType's name
     * @return AgreementType's name
     */
    String getName();

    /**
     * Gets AgreementType's salary
     * @return AgreementType's salary
     */
    String getSalary();

    /**
     * Gets AgreementType's rest days
     * @return AgreementType's rest days
     */
    String getRestDays();

    /**
     * Gets AgreementType's sick days
     * @return AgreementType's sick days
     */
    String getSickDays();

    /**
     * Gets AgreementType's FullTime
     * @return True if AgreementType is FullTime else False
     */
    Boolean getFullTime();
    
    /**
     * Gets AgreementType's PartTime
     * @return True if AgreementType is PartTime else False
     */
    Boolean getPartTime();
    
    /**
     * Gets AgreementType's PaidBySalary
     * @return True if AgreementType is PaidBySalary else False
     */
    Boolean getPaidBySalary();
    
    /**
     * Gets AgreementType's PaidByWage
     * @return True if AgreementType is PaidByWage else False
     */
    Boolean getPaidByWage();
    
    /**
     * Sets AGREEMENT_TYPE_RESULT
     * @param value string with the id
     */
    void setAgreementTypeResult(String value);

    /**
     * Sets AgreementType's name
     * @param value AgreementType's name
     */
    void setName(String value);

    /**
     * Sets AgreementType's salary
     * @param value AgreementType's salary
     */
    void setSalary(String value);

    /**
     * Sets AgreementType's rest days
     * @param value AgreementType's rest days
     */
    void setRestDays(String value);

    /**
     * Sets AgreementType's sick days
     * @param value AgreementType's sick days
     */
    void setSickDays(String value);

    /**
     * Sets AgreementType's FullTime
     * @param i AgreementType's FullTime
     */
    void setFullTime(boolean i);

    /**
     * Sets AgreementType's PartTime
     * @param i AgreementType's PartTime
     */
    void setPartTime(boolean i);

    /**
     * Sets AgreementType's PaidBySalary
     * @param i AgreementType's PaidBySalary
     */
    void setPaidBySalary(boolean i);

    /**
     * Sets AgreementType's PaidByWage
     * @param i AgreementType's PaidByWage
     */
    void setPaidByWage(boolean i);

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
