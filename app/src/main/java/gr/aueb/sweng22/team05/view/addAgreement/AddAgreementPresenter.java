package gr.aueb.sweng22.team05.view.addAgreement;

import android.os.Build;
import androidx.annotation.RequiresApi;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import java.time.LocalDate;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.Worker;


public class AddAgreementPresenter {
    private AddAgreementView view;
    private AgreementDAO agreements;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param agreements an instance of agreement
     */
    public AddAgreementPresenter(AddAgreementView view, AgreementDAO agreements) {
        this.view = view;
        this.agreements = agreements;
    }

    /**
     * Saves the new Agreement with the user's input
     * and checks that dates are valid
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSaveAgreement() {
        String
                hireD = view.getHireDate(),
                startD = view.getStartDate(),
                endD = view.getEndDate();
        LocalDate
                hireDate,
                startDate,
                endDate;

        String[] hireDSplit = hireD.split("/");
        String[] startDSplit = startD.split("/");
        String[] endDSplit = endD.split("/");

        if (hireD.length() < 8 || hireD.contains(".") || hireDSplit.length > 3){
            view.showErrorMessage("Error!", "Hire Date must be like 25/10/2022.");
        }
        else if(startD.length() < 8 || startD.contains(".") || startDSplit.length > 3){
            view.showErrorMessage("Error!", "Start Date must be like 25/10/2022.");
        }
        else if (endD.length() < 8 || endD.contains(".") || endDSplit.length > 3) {
            view.showErrorMessage("Error!", "End Date must be like 25/10/2022.");
        }
        else if(checkDate(hireDSplit).length() > 0){
            view.showErrorMessage("Error!", checkDate(hireDSplit));
        }
        else if(checkDate(startDSplit).length() > 0){
            view.showErrorMessage("Error!", checkDate(startDSplit));
        }
        else if(checkDate(endDSplit).length() > 0){
            view.showErrorMessage("Error!", checkDate(endDSplit));
        }
        else{
            hireDate = convertToLocalDate(hireDSplit);
            startDate = convertToLocalDate(startDSplit);
            endDate = convertToLocalDate(endDSplit);

            if (hireDate.isAfter(startDate)) {
                view.showErrorMessage("Error!", "Hire Date must be before Start Date.");
            }
            else if (hireDate.isAfter(endDate)) {
                view.showErrorMessage("Error!", "Hire Date must be before End Date.");
            }
            else if (startDate.isAfter(endDate)) {
                view.showErrorMessage("Error!", "Start Date must be before End Date.");
            }
            else {
                Worker worker = new Worker();

                // Get Agreement object
                AgreementType agreementType = view.getAgreementType();
                //AgreementType agreementType = new AgreementType();

                Agreement n_agreement = new Agreement(agreements.nextId(), hireDate, startDate, endDate, agreementType, true);
                agreements.save(n_agreement, worker);

                view.setAgreementResult(String.valueOf(n_agreement.getId())); // to inform other activity of id
                view.successfullyFinishActivity("Agreement " + n_agreement.getId() + " successfully created!");
            }
        }
    }

    /**
     * Converts a string array to a LocalDate object
     * @param value string array with the input date
     * @return the LocalDate object from the input
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate convertToLocalDate(String[] value){
        return LocalDate.of(Integer.parseInt(value[2]), Integer.parseInt(value[1]), Integer.parseInt(value[0]));
    }

    /**
     * Checks a string array if it is a valid date
     * @param dateInput string array with the input date where
     *                  dateInput[0] -> day
     *                  dateInput[1] -> month
     *                  dateInput[2] -> year
     * @return the error message if it isn't valid else empty string
     */
    private String checkDate(String[] dateInput){
        if(Integer.parseInt(dateInput[0]) > 31 || Integer.parseInt(dateInput[0]) < 1){
            return "Day must be between 1 and 31.";
        }
        else if(Integer.parseInt(dateInput[1]) > 12 || Integer.parseInt(dateInput[1]) < 1){
            return "Month must be between 1 and 12.";
        }
        else if(dateInput[2].length() < 3){
            return "Year must be 4 digits.";
        }
        else{
            return "";
        }
    }
}
