package gr.aueb.sweng22.team05.view.addWorker;

import gr.aueb.sweng22.team05.domain.Agreement;

public interface AddWorkerView {
    String getFirstName();
    String getLastName();
    Long getPhone();
    String getEmail();
    String getAFM();
    String getDepartment();
    String getManagerEmployee();
    Agreement getAgreement();

    /**
     * Sets worker's first name
     * @param value worker's first name
     */
    void setFirstName(String value);

    /**
     * Sets worker's last name
     * @param value worker's last name
     */
    void setLastName(String value);

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
     * Sets worker department name
     * @param value worker's department name
     */
    void setDepartment(String value);

    /**
     * Sets worker type
     * @param value worker's type (Manager or Employee)
     */
    void setWorkerType(String value);

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    void setWorkerAFM(String value);

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
