package gr.aueb.sweng22.team05.view.HandleLeaveRequest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.List;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;

public class HandleLeaveRequestPresenter {
    private HandleLeaveRequestView view;
    private LeaveRequestDAO leaveRequestDAO;
    private AcceptedLeavesDAO acceptedLeavesDAO;

    /**
     * Initialises Presenter
     * @param view and instance of view
     * @param leaveRequests an instance of leaveRequests
     * @param acceptedLeaves an instance of acceptedLeaves
     */
    public HandleLeaveRequestPresenter(HandleLeaveRequestView view, LeaveRequestDAO leaveRequests,
                                       AcceptedLeavesDAO acceptedLeaves) {
        this.view = view;
        this.leaveRequestDAO = leaveRequests;
        this.acceptedLeavesDAO = acceptedLeaves;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onApprove(Leave leave){
        handleLeave(leave, true);
        view.successfullyFinishActivity("Request Approved.");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefuse(Leave leave){
        handleLeave(leave, false);
        view.successfullyFinishActivity("Request Refused.");
    }

    /**
     * Set values from saved data
     * @param leave leave request object
     * @param worker worker object
     */
    public void getData(Leave leave, Worker worker){
        String id = "";
        String leaveType = "";
        String start = "";
        String end = "";
        String name = "";
        String afm = "";
        String emailAddress = "";
        Long phoneNumber = 0L;
        int restTaken = 0;
        int sickTaken = 0;

        if (worker!=null) {
            id = String.valueOf(leave.getId());
            leaveType = String.valueOf(leave.getType());
            start = leave.getStart().toString();
            end = leave.getEnd().toString();
            name = worker.getFirstName()+" "+worker.getLastName();
            afm = worker.getAFM();
            emailAddress = worker.getEmail();
            phoneNumber = worker.getPhoneNumber();

            List<Leave> allAcceptedLeaves = acceptedLeavesDAO.findAllFor(worker);
            for (Leave acceptedLeave : allAcceptedLeaves){
                if (acceptedLeave.getType().toString().equals("REST"))
                    restTaken++;
                else
                    sickTaken++;
            }
        }

        view.setID(id);
        view.setLeaveType(leaveType);
        view.setStart(start);
        view.setEnd(end);
        view.setName(name);
        view.setAFM(afm);
        view.setEmail(emailAddress);
        view.setPhone(phoneNumber);
        view.setRestTaken(String.valueOf(restTaken));
        view.setSickTaken(String.valueOf(sickTaken));
    }

    /**
     * Handle worker leave request
     * @param leave Leave object
     * @param accepted Whether request was accepted or not
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleLeave(Leave leave, Boolean accepted){
        if (checkDate(leave.getStart().getYear(), leave.getStart().getMonthValue(), leave.getStart().getDayOfMonth())){
            if (accepted) {
                Worker worker = leaveRequestDAO.findWorkerFor(leave);
                acceptedLeavesDAO.save(leave, worker);
            }
        }
        leaveRequestDAO.delete(leave);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkDate(int year, int month, int day){
        LocalDate nowDate = LocalDate.now();
        LocalDate rd = LocalDate.of(year, month, day);
        return rd.isAfter(nowDate);
    }
}
