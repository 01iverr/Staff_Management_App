package gr.aueb.sweng22.team05.view.addDepartment;

import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Worker;

public class AddDepartmentPresenter {
    private AddDepartmentView view;
    private WorkerDAO workers;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param departments an instance of department
     */
    public AddDepartmentPresenter(AddDepartmentView view, WorkerDAO departments) {
        this.view = view;
        this.workers = departments;
    }

    public void onSaveDepartment(){
        String name = view.getName();

        if(name.length() <= 0){
            view.showErrorMessage("Error!", "Name of the department must be at least one character.");
        }
        else{
            Department dep = new Department(workers.departmentNextId(), name);

            // Get Worker object
            Worker worker = new Worker();
            // TODO: get worker object from other page

            workers.save(worker);
            view.successfullyFinishActivity("Department " + dep.getId() + " successfully created!");
        }
    }


}
