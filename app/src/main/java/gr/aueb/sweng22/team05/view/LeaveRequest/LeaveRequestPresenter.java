package gr.aueb.sweng22.team05.view.LeaveRequest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.domain.leaveType;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LeaveRequestPresenter {

    private final LeaveRequestView view;
    private final LeaveRequestDAO lrd;
    private final AcceptedLeavesDAO acdao;
    private final AgreementDAO adao;
    private WorkerDAO workers;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param lrdd an instance of leaveRequestsDAO
     * @param acdaoo an instance of AcceptedLeavesDAO
     * @param adaoo an instance of AgreementDAO
     */
    public LeaveRequestPresenter(LeaveRequestView view, LeaveRequestDAO lrdd,AcceptedLeavesDAO acdaoo,AgreementDAO adaoo,WorkerDAO workerl) {
        this.view = view;
        this.lrd = lrdd;
        this.acdao=acdaoo;
        this.adao=adaoo;
        this.workers=workerl;
    }

    /**
     * Check if the input date(s) are valid and they are today or later
     * if the dates are ok then we call the method leaveRequest to check if they have remaining days.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkingDays() {
        String[] lastSplit;
        String firstD = view.getStartDateLR();
        String lastD = view.getEndDateLR();
        String[] firstDSplit = firstD.split("/");
        if ((lastD==null)){lastD=firstD;}
        lastSplit = lastD.split("/");
        if (firstD.length() < 8 || firstD.contains(".") || firstDSplit.length > 3) {
            view.showErrorMessage("Error!", "Start Date must be like 25/10/2022.");
        } else if ( checkDate(Integer.parseInt(firstDSplit[2]), Integer.parseInt(firstDSplit[1]), Integer.parseInt(firstDSplit[0]))) {
            view.showErrorMessage("Error!", "Start Date must be today or after.");
        } else if (!view.isSingleDR()) {
            if (lastD.length() < 8 || lastD.contains(".") || lastSplit.length > 3) {
                view.showErrorMessage("Error!", "End Date must be like 25/10/2022.");
            } else if(checkTwoDates( Integer.parseInt(lastSplit[2]), Integer.parseInt(lastSplit[1]), Integer.parseInt(lastSplit[0]),Integer.parseInt(firstDSplit[2]), Integer.parseInt(firstDSplit[1]), Integer.parseInt(firstDSplit[0]))){
                view.showErrorMessage("Error!", "Start Date must be before End Date.");
            }
        }else {
            leaveType typeofDayOff;
            if (view.isSickDR()) {
                typeofDayOff = leaveType.SICK;
            } else {
                typeofDayOff = leaveType.REST;
            }
            if (!view.isSingleDR()) {
                leaveRequest(Integer.parseInt(firstDSplit[0]), Integer.parseInt(firstDSplit[1]), Integer.parseInt(firstDSplit[2]), Integer.parseInt(lastSplit[0]), Integer.parseInt(lastSplit[1]), Integer.parseInt(lastSplit[1]), typeofDayOff);
            } else {
                leaveRequest(Integer.parseInt(firstDSplit[0]), Integer.parseInt(firstDSplit[1]), Integer.parseInt(firstDSplit[2]), Integer.parseInt(firstDSplit[0]), Integer.parseInt(firstDSplit[1]), Integer.parseInt(firstDSplit[2]), typeofDayOff);
            }
        }
    }

    /**
     *Checks that the date is valid (for example 30 february throws an exception that we are catching)and that the day is later than today or today
     * @param year is the number of year that we want to check about
     * @param month is the number of month that we want to check about
     * @param day  is the number of day that we want to check about
     * @return true if the date is valid
     * @return false if the date is valid
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkDate(int year, int month, int day){
        LocalDate nowdate;
        LocalDate rd;
        try{
        nowdate = LocalDate.now();
        rd =LocalDate.of(year, month, day);
        }catch (DateTimeException a ){
            view.showErrorMessage("Error!", "This day does not exist.");
            return false;
        }
        return rd.isAfter(nowdate);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void  leaveRequest(int fd, int fm, int fy, int ld, int lm, int ly, leaveType typeDayOff){
        if (hasRemainingDays(fy,typeDayOff)){
            Leave item=new Leave ((lrd.size()+acdao.size()+1L),LocalDate.of(fy, fm, fd), LocalDate.of(ly, lm,ld), typeDayOff);
            lrd.save(item , getTheWorker());
            view.successfullyFinishActivity("Leave request will be sent to the manager for approve.");
            }
        view.successfullyFinishActivity("Request Refused.No Remaining Days!");
        view.showErrorMessage("Error!", "No Remaining Days");
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean hasRemainingDays(int year, leaveType type){
        List <Agreement> agreem=adao.findAllFor(getTheWorker());
        List<Leave> AcceptedLeaves=acdao.findAllFor(getTheWorker());
        int j=0;
        for (int i=0;i<AcceptedLeaves.size();i++){
            if ((AcceptedLeaves.get(i)).getStart().getYear() ==year){
                if(AcceptedLeaves.get(i).getType()==type){
                    j=j+1;
                }
            }
        }
            if (type.equals(leaveType.SICK)){
                return  (j< agreem.get(agreem.size()-1).getAgrType().getSickDays());
            }
            else if (type.equals(leaveType.REST)){
                return j< agreem.get(agreem.size()-1).getAgrType().getRestDays();
            }
            else{
                view.showErrorMessage("Error!", "TYPE IS WRONG");
                return false;
            }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkTwoDates(int fy, int fm, int fd, int ly, int lm, int ld){
        LocalDate first;
        LocalDate last;
        try{
            first =LocalDate.of(fy, fm, fd);
            last =LocalDate.of(ly, lm, ld);}
        catch(DateTimeException a ){
            view.showErrorMessage("Error!", "Last day does not exist.");
            return false;
        }
        return last.isAfter(first);
    }

    private Worker getTheWorker(){
        return workers.findById(view.getIDofTheWorker());
    }


}


