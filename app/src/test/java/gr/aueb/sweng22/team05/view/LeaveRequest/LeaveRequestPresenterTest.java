package gr.aueb.sweng22.team05.view.LeaveRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;


public class LeaveRequestPresenterTest {


    private LeaveRequestDAO lrdd;
    private AcceptedLeavesDAO acdaoo;
    private Initializer dataHelper;
    private AgreementDAO adaoo;
    private WorkerDAO workerl;
    private LeaveRequestPresenter presenter;
    private LeaveRequestViewStub view;

    @Before
    public void setUp(){
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new LeaveRequestViewStub();
        lrdd=new LeaveRequestDAOMemory();
        adaoo=new AgreementDAOMemory();
        workerl=new WorkerDAOMemory();
        acdaoo=new AcceptedLeavesDAOMemory();
        presenter = new LeaveRequestPresenter(view,lrdd,acdaoo,adaoo,workerl);
    }

    @After
    public void cleanUp(){
        lrdd.removeAll();
        acdaoo.removeAll();
        adaoo.removeAll();
        workerl.removeAll();
    }


    @Test
    public void LastDateBeforeFirstDateRestDay(){
        view.setifIsSickDR(false) ;
        view.setIDofTheWorker(1l);
        view.setisSingleDR(false);
        int lrsize =lrdd.size();
        view.setStartDateLR("12/06/2022");
        view.setEndDateLR("10/06/2022");
        adaoo.save(adaoo.find(1L), workerl.findById(1l));
        presenter.checkingDays();
        Assert.assertFalse(lrdd.size()==lrsize+1);
    }

    @Test
    public void wrongEndDate(){
        view.setifIsSickDR(false) ;
        view.setIDofTheWorker(1l);
        view.setisSingleDR(false);
        int lrsize =lrdd.size();
        view.setStartDateLR("12/06/2022");
        view.setEndDateLR("10/06.2022");
        adaoo.save(adaoo.find(1L), workerl.findById(1l));
        presenter.checkingDays();
        Assert.assertFalse(lrdd.size()==lrsize+1);
    }

    @Test
    public void LastDateBeforeFirstDateSickDay(){
        view.setifIsSickDR(true) ;
        view.setIDofTheWorker(1l);
        view.setisSingleDR(false);
        int lrsize =lrdd.size();
        view.setStartDateLR("12/06/2022");
        view.setEndDateLR("10/06/2022");
        adaoo.save(adaoo.find(1L), workerl.findById(1l));
        presenter.checkingDays();
        Assert.assertFalse(lrdd.size()==lrsize+1);
    }

    @Test
    public void DateWritedWrongSick(){
        view.setifIsSickDR(true) ;
        view.setIDofTheWorker(1l);
        view.setisSingleDR(true);
        int lrsize =lrdd.size();
        view.setStartDateLR("2023");
        adaoo.save(adaoo.find(1L), workerl.findById(1l));
        presenter.checkingDays();
        Assert.assertFalse(lrdd.size()==lrsize+1);
    }
}
