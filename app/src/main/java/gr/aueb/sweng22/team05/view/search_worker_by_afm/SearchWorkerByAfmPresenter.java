package gr.aueb.sweng22.team05.view.search_worker_by_afm;

import java.util.List;

import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Worker;

public class SearchWorkerByAfmPresenter  {
    private final SearchWorkerByAfmView view;
    private final WorkerDAO workers;
    public SearchWorkerByAfmPresenter(SearchWorkerByAfmView view, WorkerDAO workers) {
        this.view = view;
        this.workers = workers;
    }
    protected Boolean thisWorkerExists() {
    List<Worker> allWorker= workers.findAll();
    for (int i =0;i<allWorker.size();i++){
        if (allWorker.get(i).getAFM().equals(view.gettheafm())){
            return true;
        }
    }
        return false;
    }
    private boolean verifyOnlyDigits(String in) {
        for(int i = 0; i < in.length(); i++)
            if(!Character.isDigit(in.charAt(i)))
                return false;
        return true;
    }
    public boolean SearchingWorker() {
        String afm = view.gettheafm();
        if(afm.length() != 9 || !verifyOnlyDigits(afm))
            view.showErrorMessage("Error!", "AFM must be 9 digits long.");
        else { // valid input
            return thisWorkerExists();
            // Finished Successfully
        }
        return false;
    }
}
