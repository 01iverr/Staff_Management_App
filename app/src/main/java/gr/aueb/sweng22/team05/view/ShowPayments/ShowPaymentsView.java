package gr.aueb.sweng22.team05.view.ShowPayments;

public interface ShowPaymentsView {
    String getAFM();
    String getPayment();

    /**
     * Sets date value
     * @param value date (month) for which
     */
    void setDate(String value);

    /**
     * Get month and year required
     */
    void getMonthYear();

//    /**
//     * Message shown when activity finishes successfully
//     * @param message message to be shown
//     */
//    void successfullyFinishActivity(String message);
}
