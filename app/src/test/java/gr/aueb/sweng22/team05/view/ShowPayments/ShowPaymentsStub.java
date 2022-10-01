package gr.aueb.sweng22.team05.view.ShowPayments;

import java.util.List;

public class ShowPaymentsStub implements ShowPaymentsView{

    static String MONTH = "month";
    static String YEAR = "year";

    List<String> allAFM;
    List<String> allPayments;

    String afm;
    String payment;
    String date;
//    String finishMessage;

    public ShowPaymentsStub(){
        afm = payment /*= finishMessage*/ = "";
    }

//    public String getFinishMessage() {
//        return finishMessage;
//    }

    public String getMONTH(){
        return MONTH;
    }

    public String getYEAR(){
        return YEAR;
    }

    @Override
    public String getAFM() {
        return afm;
    }

    @Override
    public String getPayment() {
        return afm;
    }

    public void setMONTH(String value){
        MONTH = value;
    }

    public void setYEAR(String value){
        YEAR = value;
    }

    /**
     * Sets date value
     * @param value date (month) for which
     */
    @Override
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Get month and year required
     */
    @Override
    public void getMonthYear() {
        setMONTH("2");
        setYEAR("2022");
    }

//    /**
//     * Message shown when activity finishes successfully
//     * @param message message to be shown
//     */
//    @Override
//    public void successfullyFinishActivity(String message) {
//        this.finishMessage = message;
//    }
}
