package gr.aueb.sweng22.team05.view.ShowPayments;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.AttendanceDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Attendance;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.domain.emplType;
import gr.aueb.sweng22.team05.domain.leaveType;

public class ShowPaymentsPresenter {
    ShowPaymentsView view;
    WorkerDAO workerDAO;
    AgreementDAO agreementDAO;
    AcceptedLeavesDAO acceptedLeavesDAO;
    AttendanceDAO attendanceDAO;

    ShowPaymentsPresenter(ShowPaymentsView view, WorkerDAO workerDAO, AgreementDAO agreementDAO,
                          AcceptedLeavesDAO acceptedLeavesDAO, AttendanceDAO attendanceDAO){
        this.view = view;
        this.workerDAO = workerDAO;
        this.agreementDAO = agreementDAO;
        this.acceptedLeavesDAO = acceptedLeavesDAO;
        this.attendanceDAO = attendanceDAO;
    }

    /**
     * Get all workers' afm
     * @return list with workers' afm
     */
    protected List<String> getAllAFM(){
        List<String> allAFM = new ArrayList<>();
        List<Worker> allWorkers = workerDAO.findAll();
        for (Worker worker : allWorkers){
            allAFM.add(worker.getAFM());
        }
        return allAFM;
    }

    /**
     * Get all workers' afm
     * @return list with workers' afm
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected List<String> getAllPayments(int month, int year){
        List<String> allPayments = new ArrayList<>();
        List<Worker> allWorkers = workerDAO.findAll();
        HashMap<Worker, Float> calculatedPayments = calculatePay(month, year);
        for (Worker worker : allWorkers){
            allPayments.add(String.valueOf(calculatedPayments.get(worker)));
        }
        return allPayments;
    }

    /**
      * Calculate payments for all workers
      * @param month Month for which to calculate payments
      * @param year Year for which to calculate payments
      * @return Each worker and their payment for that period
      */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private HashMap<Worker,Float> calculatePay(int month, int year){
        HashMap<Worker,Float> payments = new HashMap<>();
        List<Worker> allWorkers = workerDAO.findAll();
        for (Worker worker : allWorkers){
            float pay = 0L;
            switch (EmployeeOrManager(worker)){
                case("EMPLOYEE"):
                    pay = calcPayEmployee(worker, month, year);
                    break;
                case("MANAGER"):
                    pay = calcPayManager(worker, month, year);
                    break;
            }
            payments.put(worker, pay);
        }
        return payments;
    }

    /**
     * Finds if it's Employee or Manager
     * @param worker worker object
     * @return "MANAGER" or "EMPLOYEE"
     */
    private String EmployeeOrManager(Worker worker){
        List<Worker> allWorker = workerDAO.findAll();
        if (worker.getClass().toString().contains("Manager"))
            return "MANAGER";
        return "EMPLOYEE";
    }

    /**
     * Calculate payment for Employee
     * @param worker employee object
     * @param month month to calculate for
     * @param year year to calculate for
     * @return payment
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private float calcPayEmployee(Worker worker, int month, int year){
        float payment = 0f;
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        Agreement lastAgreement = allAgreements.get(allAgreements.size()-1);

        if (thisAgreementStartedBeforethisMonth(worker,month,year)||(!thisAgreementEndedBeforeNextMonth(worker,month,year))) {
            if(paidByHour(worker,allAgreements.size()-1)){ // paidByHour
                payment += paymentForPaidByHour(worker,month,year,allAgreements.size());
                payment+=paymentRSDAYS(worker,month,year);
            }else{ //paidByMonth
                payment += paymentForPaidByMonth(worker,month,year,allAgreements.size(),true);
                payment-=paymentForSickDays(worker,month,year);
            }
        }else{
            if(paidByHour(worker,allAgreements.size())){ // paidByHour
                payment += paymentForPaidByHour(worker,month,year, allAgreements.size());
                payment+=paymentRSDAYS(worker,month,year);
            }else{ //paidByMonth
                int datestart = lastAgreement.getStartDate().getDayOfMonth();
                payment += (lastAgreement.getAgrType().getSalary()/(float)25)*daysOfWorkThisMonth(worker,month,year,allAgreements.size());
                payment += totalRestDays(worker,month,year)*(lastAgreement.getAgrType().getSalary()/(float)25);
                payment -= paymentForSickDays(worker,month, year);
                payment += paymentForPaidByMonth(worker,month,year, allAgreements.size(),false) ;//yperwries
            }
        }
        return payment;
    }

    /**
     * Calculate Payment for Manager
     * @param worker Manager object
     * @param month month to calculate for
     * @param year year to calculate for
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private float calcPayManager(Worker worker, int month, int year){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        Agreement lastAgreement = allAgreements.get(allAgreements.size()-1);
        float payment=0f;
        if (thisAgreementStartedBeforethisMonth(worker,month,year)||(!thisAgreementEndedBeforeNextMonth(worker,month,year))) {
            if(paidByHour(worker,allAgreements.size()-1)){ // paidByHour
                payment += paymentForPaidByHour(worker,month,year,allAgreements.size());
            }else{ //paidByMonth
                payment += paymentForPaidByMonth(worker,month,year,allAgreements.size()-1,true);
            }
        }else{
            if(paidByHour(worker,allAgreements.size())){ // paidByHour
                payment += paymentForPaidByHour(worker,month,year,allAgreements.size());
            }else{ //paidByMonth
                int datestart=lastAgreement.getStartDate().getDayOfMonth();
                payment +=(lastAgreement.getAgrType().getSalary()/(float)25)*daysOfWorkThisMonth(worker,month,year,allAgreements.size());
                payment+=paymentForPaidByMonth(worker,month,year,allAgreements.size()-1,false) ;//yperwries
            }
        }
        return payment;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float paymentForPaidByHour(Worker worker, int month, int year, int ag){
        List<Attendance> calendar = attendanceDAO.findAllFor(worker);
        float payment=0f;
        for (int i=0;i<calendar.size();i++){
            if (calendar.get(i).getDate().getYear()==year){
                if (calendar.get(i).getDate().getMonth()== Month.of(month)){
                    payment+=PaymentwithextraMinsForADay(worker,i,ag);
                }
            }
        }
        return payment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int daysOfWorkThisMonth(Worker worker, int month, int year, int ag){
        List<Attendance> calendar = attendanceDAO.findAllFor(worker);
        int total=0;
        for (int i=0;i<calendar.size();i++){
            System.out.println("step"+i);
            if (calendar.get(i).getDate().getYear()==year){
                if (calendar.get(i).getDate().getMonth()==Month.of(month)){
                    total++;
                }
            }
        }
        return total;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float PaymentwithextraMinsForADay(Worker worker, int i, int ag){  //i=itterator for calendar ,ag= number of agreement for hourly paid
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        int a = minsOfWorkingtoday(worker,i);
        float c;
        int b = allAgreements.get(ag).getAgrType().getTypehours()*60;
        if (a>b)
            c=((a-b)*((((float)125/(float)100)* paymentByMin(worker,ag)))+b*paymentByMin(worker,ag));
        else
            c=b*paymentByMin(worker,ag);
        return c;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float PaymentwithextraMinsForADay2(Worker worker, int i, int ag){  //i=itterator for calendar ,ag= number of agreement for monthly paid
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        int a = minsOfWorkingtoday(worker,i);
        float c = 0f;
        int b = allAgreements.get(ag).getAgrType().getTypehours()*60;
        if (a>b){
            c = ((a - b) * ((float) 125 / 100) * paymentByMinMonth(worker,ag));
        }
        return c;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private int minsOfWorkingtoday(Worker worker, int i){ //itterator for calendar
        List<Attendance> calendar = attendanceDAO.findAllFor(worker);
        LocalTime total = calendar.get(i).getLeave().minusHours(calendar.get(i).getArrival().getHour()).minusMinutes(calendar.get(i).getArrival().getMinute());
        int hours = total.getHour();
        int min=total.getMinute();
        return hours*60+min; // total min of working
    }

    private float paymentByMin(Worker worker, int i){ //for paid by hour
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        return allAgreements.get(i).getAgrType().getSalary()/60;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate findThePreviusMonth(int month , int year){
        if (month==2||month==4||month==6||month==8||month==9||month==11){
            return LocalDate.of(year, month-1, 31);
        }else if (month==5||month==7||month==10||month==12){
            return LocalDate.of(year, month-1, 30);
        }else if (month==3){
            return LocalDate.of(year, month-1, 28);
        }else{
            return LocalDate.of(year-1, 12, 31);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate findTheNextMonth(int month, int year){
        if (month!=12)
            return LocalDate.of(year,month + 1,1);
        else
            return LocalDate.of(year+1,1,1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean thisAgreementStartedBeforethisMonth(Worker worker, int month, int year){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        return allAgreements.get(allAgreements.size() - 1).getStartDate().isBefore(findThePreviusMonth(month, year))
                || (allAgreements.get(allAgreements.size() - 1).getStartDate() == (findThePreviusMonth(month, year)));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean thisAgreementEndedBeforeNextMonth(Worker worker, int month, int year){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        return allAgreements.get(allAgreements.size() - 1).getEndDate().isBefore(findTheNextMonth(month, year))
                || (allAgreements.get(allAgreements.size() - 1).getStartDate() == (findTheNextMonth(month, year)));
    }

    private boolean paidByHour(Worker worker, int i){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        return !allAgreements.get(i).getAgrType().getEmType().equals(emplType.PAIDBYSALARY);
    }

    private float paymentByMinMonth(Worker worker, int i){ //for paid by month
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        return ((allAgreements.get(i).getAgrType().getSalary()/25)/allAgreements.get(i).getAgrType().getTypehours())/60;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float paymentForPaidByMonth(Worker worker, int month,int year,int ag,boolean a){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        float payment=0f;
        if (a)
            payment = allAgreements.get(ag).getAgrType().getSalary();
        List<Attendance> calendar = attendanceDAO.findAllFor(worker);
        for (int i=0;i<calendar.size();i++){
            if (calendar.get(i).getDate().getYear()==year){
                if (calendar.get(i).getDate().getMonth()==Month.of(month)){
                    payment += PaymentwithextraMinsForADay2(worker,i,ag);
                }
            }
        }

        return payment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int daysOfLeave(Worker worker, int i){
        List<Leave> allAcceptedLeaves = acceptedLeavesDAO.findAllFor(worker);
        int fd = allAcceptedLeaves.get(i).getStart().getDayOfMonth();
        int ld = allAcceptedLeaves.get(i).getEnd().getDayOfMonth();
        return ld-fd+1; // total min of working
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private float paymentForSickANDRestDays(Worker worker, int month, int year){ //for hourly-paid
        List<Leave> allAcceptedLeaves = acceptedLeavesDAO.findAllFor(worker);
        int totalRestDAYS=0;
        int totalSickDAYS=0;
        for (int i =0; i <allAcceptedLeaves.size();i++){
            if (allAcceptedLeaves.get(i).getStart().isAfter(findThePreviusMonth(month,year))){
                if ((allAcceptedLeaves.get(i)).getType().equals(leaveType.REST)){
                    //find the duration of the leave
                    totalRestDAYS+=daysOfLeave(worker,i);
                }else{
                    totalSickDAYS+=daysOfLeave(worker,i);
                }
            }
        }
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        float paymentforsickdays=totalSickDAYS*(allAgreements.get(allAgreements.size()-1).getAgrType().getSalary()/2)*(allAgreements.get(allAgreements.size()-1).getAgrType().getTypehours());
        float paymentforrestdays=totalRestDAYS*(allAgreements.get(allAgreements.size()-1).getAgrType().getSalary()*(allAgreements.get(allAgreements.size()-1).getAgrType().getTypehours()));
        return paymentforsickdays+paymentforrestdays;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int totalRestDays(Worker worker, int month, int year){ //for montly-paid
        List<Leave> allAcceptedLeaves = acceptedLeavesDAO.findAllFor(worker);
        int total=0;
        for (int i =0; i <allAcceptedLeaves.size();i++){
            if (allAcceptedLeaves.get(i).getStart().isAfter(findThePreviusMonth(month,year))){
                if ((allAcceptedLeaves.get(i)).getType().equals(leaveType.REST)){
                    total+=daysOfLeave(worker,i);
                }
            }
        }
        return total;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private float paymentForSickDays(Worker worker, int month, int year){ //for montly-paid
        List<Leave> allAcceptedLeaves = acceptedLeavesDAO.findAllFor(worker);
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        int totalSickDAYS=0;
        for (int i =0; i <allAcceptedLeaves.size();i++){
            if (allAcceptedLeaves.get(i).getStart().isAfter(findThePreviusMonth(month, year))){
                if (!(allAcceptedLeaves.get(i)).getType().equals(leaveType.REST)){
                    totalSickDAYS+=daysOfLeave(worker,i);
                }
            }
        }
        return totalSickDAYS*(allAgreements.get(allAgreements.size()-1).getAgrType().getSalary()/(float)25/(float)2);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private float paymentRSDAYS(Worker worker, int month, int year){
        List<Agreement> allAgreements = agreementDAO.findAllFor(worker);
        if (paidByHour(worker, allAgreements.size()-1)){
            return paymentForSickANDRestDays(worker,month,year);
        }else{
            return paymentForSickDays(worker,month,year);
        }

    }

}
