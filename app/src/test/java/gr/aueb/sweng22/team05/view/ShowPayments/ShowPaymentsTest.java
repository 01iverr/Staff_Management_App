package gr.aueb.sweng22.team05.view.ShowPayments;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.AttendanceDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AttendanceDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.HandleLeaveRequest.HandleLeaveRequestPresenter;
import gr.aueb.sweng22.team05.view.HandleLeaveRequest.HandleLeaveRequestViewStub;

public class ShowPaymentsTest {

    private Initializer dataHelper;
    private ShowPaymentsPresenter presenter;
    private ShowPaymentsStub view;
    WorkerDAO workerDAO;
    AgreementDAO agreementDAO;
    AcceptedLeavesDAO acceptedLeavesDAO;
    AttendanceDAO attendanceDAO;

    List<String> allAFM;
    List<String> allPayments;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ShowPaymentsStub();

        workerDAO = new WorkerDAOMemory();
        agreementDAO = new AgreementDAOMemory();
        acceptedLeavesDAO = new AcceptedLeavesDAOMemory();
        attendanceDAO = new AttendanceDAOMemory();

        presenter = new ShowPaymentsPresenter(view, workerDAO,
                agreementDAO, acceptedLeavesDAO, attendanceDAO);

        view.getMonthYear();
    }

    @Test
    public void calculatePaymentEmployee(){
        allAFM = presenter.getAllAFM();
        allPayments = presenter.getAllPayments(
                Integer.parseInt(view.getMONTH()), Integer.parseInt(view.getYEAR()));
        int index = allAFM.indexOf("453168792");
        Assert.assertEquals(16800, Float.valueOf(allPayments.get(index)), 0.001);
    }

    @Test
    public void calculatePaymentManager(){
        allAFM = presenter.getAllAFM();
        allPayments = presenter.getAllPayments(
                Integer.parseInt(view.getMONTH()), Integer.parseInt(view.getYEAR()));
        int index = allAFM.indexOf("213465879");
        Assert.assertEquals(1000, Float.valueOf(allPayments.get(index)), 0.001);
    }

}
