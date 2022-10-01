package gr.aueb.sweng22.team05.view.addWorker;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Employee;
import gr.aueb.sweng22.team05.domain.Manager;
import gr.aueb.sweng22.team05.domain.Worker;

public class AddWorkerPresenter{

    private AddWorkerView view;
    private WorkerDAO workers;
    private AgreementDAO agreements;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param workers an instance of worker
     */
    public AddWorkerPresenter(AddWorkerView view, WorkerDAO workers, AgreementDAO agreements) {
        this.view = view;
        this.workers = workers;
        this.agreements = agreements;
    }

    public void onSaveWorker() {
        String
            firstName = view.getFirstName(),
            lastName = view.getLastName(),
            email = view.getEmail(),
            afm = view.getAFM(),
            departmentName = view.getDepartment(),
            workerType = view.getManagerEmployee(); // Manager or Employee
        Agreement
            agreement = view.getAgreement();
        Long
            phone = view.getPhone();

        if(checkInput()){ // valid input
            // Generate id, username, password
            Long id = generateID(10);
            String username = generateUsername(lastName, id);
            String password = generatePassword(20);

            Department department = setDepartmentValue(departmentName);

            // Save worker
            Worker worker = null;
            if (workerType.equals("Manager"))
                worker = new Manager(id, firstName, lastName, email, phone, afm, username, password, department);
            else // Employee
                worker = new Employee(id, firstName, lastName, email, phone, afm, username, password, department);
            workers.save(worker);
            agreements.save(agreement, worker);

            // Finished Successfully
            view.successfullyFinishActivity(workerType+" '"+lastName+" "+firstName+"' successfully created!\n" +
                    "Username: "+worker.getUsername()+" - Password: "+worker.getPassword());
        }
    }

    /**
     * Check user input for its validity
     * @return whether it's valid input
     */
    protected boolean checkInput() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        Long phone = view.getPhone();
        String email = view.getEmail();
        String departmentName = view.getDepartment();

        if (firstName.length() <= 0) {
            view.showErrorMessage("Error!", "First name field can't be empty.");
            return false;
        } else if (lastName.length() <= 0){
            view.showErrorMessage("Error!", "Last name field can't be empty.");
            return false;
        }else if(String.valueOf(phone).length() < 2 || String.valueOf(phone).length() > 15) {
            view.showErrorMessage("Error!", "Not a valid phone number.");
            return false;
        }else if(email.length() < 2 || email.length() > 100 || !validateEmail(email)) {
            view.showErrorMessage("Error!", "Not a valid email address.");
            return false;
        }else if(departmentName.length() <= 0){
            view.showErrorMessage("Error!", "Department name field can't be empty.");
            return false;
        }
        return true;
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

    /**
     * Generate Random ID
     * @param len ID length to be generated
     * @return generated ID
     */
    private Long generateID(int len) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // ID mustn't start with 0
        // Generate first character
        int num = rand.nextInt(9) + 1;
        sb.append(num);

        // Generate rest of ID
        for (int i = 0; i < len-1; i++) {
            num = rand.nextInt(9);
            sb.append(num);
        }
        return Long.parseLong(sb.toString());
    }

    /**
     * Generate Random Password
     * @param passlen password length to be generated
     * @return generated password
     */
    private String generatePassword(int passlen) {
        // ASCII range
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < passlen; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    /**
     * Generate Username for worker
     * (username is first character of last name (lowercase) and id)
     * @param lastName worker's last name
     * @param id worker's id
     * @return generated username
     */
    private String generateUsername(String lastName, Long id){
        return Character.toLowerCase(lastName.indexOf(0)) + String.valueOf(id);
    }

    /**
     * Get Department object.
     * If it already exists retrieve it, if not create it.
     * @param departmentName department name to be added
     * @return Department object
     */
    private Department setDepartmentValue(String departmentName){
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
        return department;
    }
}
