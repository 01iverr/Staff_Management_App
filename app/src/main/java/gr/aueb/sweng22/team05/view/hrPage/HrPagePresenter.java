package gr.aueb.sweng22.team05.view.hrPage;

import java.util.HashMap;

import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Worker;

public class HrPagePresenter {
    private HrPageView view;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param workers an instance of workers
     */
    public HrPagePresenter(HrPageView view) {
        this.view = view;
    }

    /**
     * checks that month and year are valid
     * and calls calculatePay to calculate payments for all workers
     */
    public Boolean calcPayments(){
        String month = view.getMonth(),
                year = view.getYear();

        if(month.length() > 2 || month.length() < 1){
            view.showErrorMessage("Error!", "Month must be at most two digits.");
            return false;
        }
        else if (year.length() != 4){
            view.showErrorMessage("Error!", "Year must be four digits.");
            return false;
        }
        else if(Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1){
            view.showErrorMessage("Error!", "Month must be between 1 and 12.");
            return false;
        }
        else{
            return true;
        }
    }
}
