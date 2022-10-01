package gr.aueb.sweng22.team05.view.HomePage;

import java.util.List;

import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Worker;

public class HomePagePresenter {
    //TODO save username/password and check credentials
    private final HomePageView view;
    private final WorkerDAO workers;
    public HomePagePresenter(HomePageView view, WorkerDAO workers) {
        this.view =  view;
        this.workers = workers;
    }
    public boolean checkedusername() {
        String inputUsername  = view.gettheinputUsername();
        if(inputUsername.length()!= 11 ){
            view.showErrorMessage("Error!", "USERNAME must be 11 letters/digits long.");}
        else { // valid username
            return true;
        }
        return false;
    }
    public boolean checkedpassword() {
        String passwd = view.getthepasswd();
        if(passwd.length() != 20) {
            view.showErrorMessage("Error!", "PASSWORD must be 20 digits long.");
            return false;
        }else { // valid password
            return true;
        }
    }

    protected String checkingCredentials() {
        List<Worker> allWorker= workers.findAll();
        for (int i =0;i<allWorker.size();i++){
            String username = allWorker.get(i).getUsername();
            String password = allWorker.get(i).getPassword();
            if (username.equals(view.gettheinputUsername())){
                if (password.equals(view.getthepasswd())) {
                    return EmployeeOrManager( i );
                }
            }
        }
        view.showErrorMessage("Error!", "Username & Password dont match with any worker");
        return "NOTHING";
    }

    protected String  EmployeeOrManager(int i ){
        List<Worker> allWorker= workers.findAll();
        if (allWorker.get(i).getClass().toString().contains("Manager") ){
            return "MANAGER";
        }
        return "EMPLOYEE";
    }

    protected boolean partofHr(){
        List<Worker> allWorker= workers.findAll();
        for (int i =0;i<allWorker.size();i++) {
            if (allWorker.get(i).getDepartment().getName().equals("HR")) {
                if (allWorker.get(i).getUsername().equals(view.gettheinputUsername())) {
                    if (allWorker.get(i).getPassword().equals(view.getthepasswd())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected long thisworkerhasid(){
        List<Worker> allWorker= workers.findAll();
        for (int i =0;i<allWorker.size();i++) {
                if (allWorker.get(i).getUsername().equals(view.gettheinputUsername())) {
                    if (allWorker.get(i).getPassword().equals(view.getthepasswd())) {
                        return allWorker.get(i).getId();
                    }
                }
        }
        return 0l;
    }
}
