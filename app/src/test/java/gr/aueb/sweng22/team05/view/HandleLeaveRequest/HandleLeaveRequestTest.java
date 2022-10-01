package gr.aueb.sweng22.team05.view.HandleLeaveRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;

public class HandleLeaveRequestTest {

    private Initializer dataHelper;
    private HandleLeaveRequestPresenter presenter;
    private HandleLeaveRequestViewStub view;
    private LeaveRequestDAO leaveRequestDAO;
    private AcceptedLeavesDAO acceptedLeavesDAO;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new HandleLeaveRequestViewStub();

        leaveRequestDAO = new LeaveRequestDAOMemory();
        acceptedLeavesDAO = new AcceptedLeavesDAOMemory();

        view.setLeaveRequestId();
        presenter = new HandleLeaveRequestPresenter(view, leaveRequestDAO, acceptedLeavesDAO);

        Leave leave = leaveRequestDAO.find(Long.parseLong(view.getLeaveRequestID()));
        Worker worker = leaveRequestDAO.findWorkerFor(leave);

        presenter.getData(leave, worker);
    }

    /**====================================================
     * Test data isn't null ===============================
     */
    @Test
    public void noID(){
        Assert.assertNotNull(view.getID());
    }

    @Test
    public void noLeaveType(){
        Assert.assertNotNull(view.getLeaveType());
    }

    @Test
    public void noStartDate(){
        Assert.assertNotNull(view.getStartDate());
    }

    @Test
    public void noEndDate(){
        Assert.assertNotNull(view.getEndDate());
    }

    @Test
    public void noName(){
        Assert.assertNotNull(view.getName());
    }

    @Test
    public void noAFM(){
        Assert.assertNotNull(view.getAFM());
    }

    @Test
    public void noEmail(){
        Assert.assertNotNull(view.getEmail());
    }

    @Test
    public void noPhone(){
        Assert.assertNotNull(view.getPhone());
    }

    @Test
    public void noRestTaken(){
        Assert.assertNotNull(view.getRestTaken());
    }

    @Test
    public void noSickTaken(){
        Assert.assertNotNull(view.getSickTaken());
    }

    @Test
    public void noLeaveRequestID(){
        Assert.assertNotNull(view.getLeaveRequestID());
    }

    /**
     * Approve leave request
     */
    @Test
    public void approveLeaveRequest(){
        Leave leave = leaveRequestDAO.find(13L);
        presenter.onApprove(leave);
        Assert.assertEquals("Request Approved.", view.getFinishMessage());
    }

    /**
     * Refuse leave request
     */
    @Test
    public void refuseLeaveRequest(){
        Leave leave = leaveRequestDAO.find(13L);
        presenter.onRefuse(leave);
        Assert.assertEquals("Request Refused.", view.getFinishMessage());
    }

}
