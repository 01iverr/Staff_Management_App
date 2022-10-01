//package gr.aueb.sweng22.team05.domain;
//import java.time.LocalDate;
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Locale;
//
//public class HumanResources {
//
//    ArrayList<Worker> workerList;                   // list with worker
//    ArrayList<Department> departmentList;           // list with departments
//    ArrayList<AgreementType> agreementTypeList;     // list with agreement types
//
//    HumanResources(){
//        workerList = new ArrayList<Worker>();
//        departmentList = new ArrayList<Department>();
//        agreementTypeList = new ArrayList<AgreementType>();
//    }
//
//    public ArrayList<Worker> getWorkerList(){
//        return workerList;
//    }
//
//    public ArrayList<Department> getDepartmentList(){
//        return departmentList;
//    }
//
//    public ArrayList<AgreementType> getAgreementTypeList(){
//        return agreementTypeList;
//    }
//
//    /**
//     * Create new worker.
//     * Generates id, username and password.
//     * @param type Type of worker object to be created ("manager", "employee")
//     */
//    public void createWorker(String type, String name, String lastname, String email,
//                            int phone, String afm, Department department, Agreement agreement){
//
//        Long id = generateID(10);
//        String username = lastname.charAt(0)+String.valueOf(id); // Username is First character from worker's last name, and their id
//        String password = generatePassword(20);
//
//        switch(type.toLowerCase()){
//        case "manager":
//            Manager manager = new Manager(id, name, lastname, email, phone, afm, username, password, department, agreement);
//            workerList.add(manager);
//            break;
//        case "employee":
//            Employee employee = new Employee(id, name, lastname, email, phone, afm, username, password, department, agreement);
//            workerList.add(employee);
//            break;
//        }
//    }
//
//    /**
//     * Generate Random id
//     * @param len id length to be generated
//     * @return generated id
//     */
//    private Long generateID(int len) {
//        // ASCII range
//        final String chars = "0123456789";
//
//        SecureRandom random;
//        StringBuilder sb;
//
//        while(true){
//            random = new SecureRandom();
//            sb = new StringBuilder();
//
//            // Generate id
//            for (int i = 0; i < len-1; i++) {
//                int randomIndex = random.nextInt(chars.length());
//                sb.append(chars.charAt(randomIndex));
//            }
//            sb.insert(0, "1"); // all ids start with 1
//
//            // Make sure it doesn't already exist
//            boolean alreadyExists = false;
//            for (Worker worker : workerList){
//                if (worker.getId()==Long.parseLong(sb.toString())){
//                    alreadyExists = true;
//                }
//            }
//            if (!alreadyExists){ break; }
//        }
//
//        return Long.parseLong(sb.toString());
//    }
//
//    /**
//     * Generate Random Password
//     * @param len password length to be generated
//     * @return generated password
//     */
//    private String generatePassword(int len) {
//        // ASCII range
//        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
//
//        SecureRandom random = new SecureRandom();
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < len; i++) {
//            int randomIndex = random.nextInt(chars.length());
//            sb.append(chars.charAt(randomIndex));
//        }
//        return sb.toString();
//    }
//
//    /**
//     * Edit existing worker.
//     * Fields that can be modified:
//     * name, lastname, email, phone, afm, department, agreement, attendance
//     * Note: agreement can only add a new object
//     * @param afm AFM of worker to be modified
//     * @param field Field to be modified
//     * @param value New value to be set
//     */
//    public void editWorker(String afm, String field, Object value){
//        // Get Worker object
//        Worker worker = null;
//        for (Worker w : workerList){
//            if (w.getAFM().equals(afm)){
//                worker = w;
//                break;
//            }
//        }
//
//        // Set field
//        switch(field){
//        case "name":
//            worker.setFirstName((String)value);
//            break;
//        case "lastname":
//            worker.setLastName((String)value);
//            break;
//        case "email":
//            worker.setEmail((String)value);
//            break;
//        case "phone":
//            worker.setPhoneNumber((Integer)value);
//            break;
//        case "afm":
//            worker.setAFM((String)value);
//            break;
//        case "department":
//            worker.setDepartment((Department)value);
//            boolean found = false;
//            for (Department d : this.departmentList){
//                if (d.getName().equals((String)value)){
//                    found = true;
//                    break;
//                }
//            }
//            if (!found){
//                createDepartment(departmentList.size(), (String)value);
//            }
//            break;
//        case "agreement":
//            worker.addAgreement((Agreement)value);
//            boolean foundAgr = false;
//            for (AgreementType a : this.agreementTypeList){
//                if (a == ((Agreement)value).getAgrType()){
//                    found = true;
//                    break;
//                }
//            }
//            if (!foundAgr){
//                createAgreementType(((Agreement)value).getAgrType().getId(), ((Agreement)value).getAgrType().getName(),
//                                    ((Agreement)value).getAgrType().getSalary(), ((Agreement)value).getAgrType().getAgrType(),
//                                    ((Agreement)value).getAgrType().getEmType(), ((Agreement)value).getAgrType().getRestDays(),
//                                    ((Agreement)value).getAgrType().getSickDays());
//            }
//            break;
//        case "attendance":
//            worker.addAttendance((Attendance) value);
//            break;
//        }
//    }
//
//    /**
//     * Remove worker from list
//     * @param afm AFM of worker to be removed
//     */
//    public void deleteWorker(String afm){
//        // Get Worker object
//        Worker worker = null;
//        for (Worker w : workerList){
//            if (w.getAFM().equals(afm)){
//                worker = w;
//                break;
//            }
//        }
//        // Remove
//        workerList.remove(worker);
//    }
//
//    /**
//     * Create new department
//     * @param id Department ID
//     * @param name Department name
//     */
//    public void createDepartment(long id, String name){
//        Department dep = new Department(id, name);
//        departmentList.add(dep);
//    }
//
//    /**
//     * Delete department
//     * @param id ID of department to be deleted
//     */
//    public Boolean deleteDepartment(long id){
//        for(Department d: departmentList){
//            if(d.getId() == id){
//				if(d.getWorkers().size() > 0){
//					return false;
//				}
//                else{
//					departmentList.remove(d);
//					return true;
//				}
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Create new agreement
//     */
//    public Agreement createAgreement(long ID, LocalDate h_date, LocalDate s_Date, LocalDate e_Date, AgreementType agreement, Boolean act){
//        return new Agreement(ID, h_date, s_Date, e_Date, agreement, act);
//    }
//
//    /**
//     * Create new agreement type
//     */
//    public AgreementType createAgreementType(long id, String agrName, float sal, typeOfAgr tOA, emplType eT, int restD, int sickD){
//        AgreementType agrT = new AgreementType(id, agrName, sal, tOA, eT, restD, sickD);
//        agreementTypeList.add(agrT);
//        return agrT;
//    }
//
//    /**
//     * Calculate payments for all workers
//     * @param month Month for which to calculate payments
//     * @param year Year for which to calculate payments
//     * @return Each worker and their payment for that period
//     */
//    public HashMap<Worker,Float> calculatePay(int month, int year){
//        HashMap<Worker,Float> payments = new HashMap<>();
//        for (Worker worker : workerList){
//            Float pay = worker.calcPay(month, year);
//            payments.put(worker, pay);
//        }
//        return payments;
//    }
//
//}
