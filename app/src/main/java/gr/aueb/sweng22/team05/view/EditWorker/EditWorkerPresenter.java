package gr.aueb.sweng22.team05.view.EditWorker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Worker;

public class EditWorkerPresenter {

    private EditWorkerView view;
    private WorkerDAO workers;
    private AgreementDAO agreements;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param workers an instance of worker
     */
    public EditWorkerPresenter(EditWorkerView view, WorkerDAO workers, AgreementDAO agreements) {
        this.view = view;
        this.workers = workers;
        this.agreements = agreements;
    }

    public void onSaveWorker() {
        String
                firstName,
                lastName,
                email,
                departmentName;
        Agreement agreement;
        Long phone;

        String afm = view.getAFM();
        Worker worker = workers.findByAFM(afm);

        firstName = view.getFirstName();
        lastName = view.getLastName();
        email = view.getEmail();
        departmentName = view.getDepartment();
        agreement = view.getAgreement();
        phone = view.getPhone();

        if(firstName.length() == 0)
            view.showErrorMessage("Error!", "First name field can't be empty.");
        else if(lastName.length() == 0)
            view.showErrorMessage("Error!", "Last name field can't be empty.");
        else if(String.valueOf(phone).length() < 2 || String.valueOf(phone).length() > 15)
            view.showErrorMessage("Error!", "Not a valid phone number.");
        else if(email.length() < 2 || email.length() > 100 || !validateEmail(email))
            view.showErrorMessage("Error!", "Not a valid email address.");
        else if(departmentName.length() == 0)
            view.showErrorMessage("Error!", "Department name field can't be empty.");
        else { // valid input

            // Set new values if necessary
            setNewFirstName(worker, firstName);
            setNewLastName(worker, lastName);
            setNewEmail(worker,email);
            setNewPhoneNumber(worker, phone);
            setNewDepartment(worker, departmentName);
            setNewAgreement(worker, agreement);

            // Finished Successfully
            view.successfullyFinishActivity("Worker '"+lastName+" "+firstName+"' updated successfully!");
        }
    }

    //////////////////
    // Helper methods

    /**
     * Validates email address
     * @param email the email address
     * @return whether it's a valid email address or not
     */
    private boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // Set new values (if necessary):
    /**
     * Set new value in first name if necessary
     * @param worker worker to be edited
     * @param firstName new value to be set
     */
    private void setNewFirstName(Worker worker, String firstName){
        if (!worker.getFirstName().equals(firstName))
            worker.setFirstName(firstName);
    }

    /**
     * Set new value in last name if necessary
     * @param worker worker to be edited
     * @param lastName new value to be set
     */
    private void setNewLastName(Worker worker, String lastName){
        if (!worker.getLastName().equals(lastName))
            worker.setLastName(lastName);
    }

    /**
     * Set new value in email if necessary
     * @param worker worker to be edited
     * @param email new value to be set
     */
    private void setNewEmail(Worker worker, String email){
        if (!worker.getEmail().equals(email))
            worker.setEmail(email);
    }

    /**
     * Set new value in phone number if necessary
     * @param worker worker to be edited
     * @param phone new value to be set
     */
    private void setNewPhoneNumber(Worker worker, Long phone){
        if (worker.getPhoneNumber() != phone)
            worker.setPhoneNumber(phone);
    }

    /**
     * Set new value in department if necessary
     * @param worker worker to be edited
     * @param departmentName new value to be set
     */
    private void setNewDepartment(Worker worker, String departmentName){
        if (!worker.getDepartment().getName().equals(departmentName)) {
            // Check if Department already exists
            List<Worker> allWorkers = workers.findAll();
            boolean departmentFound = false;
            Department department = null;
            for (Worker w : allWorkers){
                if (w.getDepartment().getName().equals(departmentName)){
                    departmentFound = true;
                    department = w.getDepartment();
                    break;
                }
            }
            // If Department doesn't already exist, create it
            if (!departmentFound){
                department = new Department(workers.departmentNextId(), departmentName);
            }
            worker.setDepartment(department);
        }
    }

    /**
     * Set new value in agreement if necessary
     * @param worker worker to be edited
     * @param agreement new value to be set
     */
    private void setNewAgreement(Worker worker, Agreement agreement){
        if (agreement != null) { // If an agreement object was given
            boolean agreementAlreadyExists = false;
            List<Agreement> allAgreements = agreements.findAllFor(worker);
            for (int i = 0; i < allAgreements.size(); i++) {
                if (allAgreements.get(i).getId() == agreement.getId()) {
                    agreementAlreadyExists = true;
                    break;
                }
            }
            if (!agreementAlreadyExists) {
                agreements.save(agreement, worker);
            }
        }
    }


}
