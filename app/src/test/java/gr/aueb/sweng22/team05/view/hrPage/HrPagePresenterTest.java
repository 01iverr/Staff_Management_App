package gr.aueb.sweng22.team05.view.hrPage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

public class HrPagePresenterTest {
    private Initializer dataHelper;
    private HrPagePresenter presenter;
    private HrPageViewStub view;

    @Before
    public void setUp(){
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new HrPageViewStub();
        presenter = new HrPagePresenter(view);
    }

    /**
     * checks than Month has at most two digits
     */
    @Test
    public void wrongFormMonth(){
        view.setMonth("123");
        presenter.calcPayments();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Month must be at most two digits.", view.getErrorMessage());
    }

    /**
     * checks than Month is between 1 and 12
     */
    @Test
    public void wrongNumberMonth(){
        view.setMonth("14");
        view.setYear("2022");
        presenter.calcPayments();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Month must be between 1 and 12.", view.getErrorMessage());
    }

    /**
     * checks that Year is four digits
     */
    @Test
    public void wrongFormYear(){
        view.setMonth("10");
        view.setYear("22");
        presenter.calcPayments();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Year must be four digits.", view.getErrorMessage());
    }

    /**
     * checks that caclPayments completed successfully
     */
    @Test
    public void savedAgreementSuccessfully(){
        view.setMonth("10");
        view.setYear("2022");
        Assert.assertTrue(presenter.calcPayments());
    }
}
