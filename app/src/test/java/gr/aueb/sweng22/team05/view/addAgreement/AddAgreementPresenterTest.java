package gr.aueb.sweng22.team05.view.addAgreement;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;

public class AddAgreementPresenterTest {
    private Initializer dataHelper;
    private AddAgreementPresenter presenter;
    private AddAgreementViewStub view;

    private static int INITIAL_AGREEMENT_COUNT = 6;

    @Before
    public void setUp(){
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddAgreementViewStub();
        presenter = new AddAgreementPresenter(view, new AgreementDAOMemory());
    }
    
    /**
     * checks for empty input in Hire Date
     */
    @Test
    public void wrongFormHireDate(){
        view.setHireDate("");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Hire Date must be like 25/10/2022.", view.getErrorMessage());
    }

    /**
     * checks for "." in Start Date
     */
    @Test
    public void wrongFormStartDate(){
        view.setHireDate("01/02/2022");
        view.setStartDate("01/05.2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Start Date must be like 25/10/2022.", view.getErrorMessage());
    }

    /**
     * checks for more than two "/"
     */
    @Test
    public void wrongFormEndDate(){
        view.setHireDate("01/02/2022");
        view.setStartDate("01/03/2022");
        view.setEndDate("01/02/20/22");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("End Date must be like 25/10/2022.", view.getErrorMessage());
    }

    /**
     * checks that Hire Date is before Start Date
     */
    @Test
    public void hireDateAfterStartDate(){
        view.setHireDate("01/04/2022");
        view.setStartDate("01/03/2022");
        view.setEndDate("01/05/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Hire Date must be before Start Date.", view.getErrorMessage());
    }

    /**
     * checks that Hire Date is before End Date
     */
    @Test
    public void hireDateAfterEndDate(){
        view.setHireDate("01/04/2022");
        view.setStartDate("01/05/2022");
        view.setEndDate("01/03/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Hire Date must be before End Date.", view.getErrorMessage());
    }

    /**
     * checks that Start Date is before End Date
     */
    @Test
    public void startDateAfterEndDate(){
        view.setHireDate("01/03/2022");
        view.setStartDate("01/05/2022");
        view.setEndDate("01/04/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Start Date must be before End Date.", view.getErrorMessage());
    }

    /**
     * checks that Day is between 1 and 31
     */
    @Test
    public void wrongNumberInDay(){
        view.setHireDate("01/03/2022");
        view.setStartDate("01/04/2022");
        view.setEndDate("41/05/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Day must be between 1 and 31.", view.getErrorMessage());
    }

    /**
     * checks that Month is between 1 and 12
     */
    @Test
    public void wrongNumberInMonth(){
        view.setHireDate("01/03/2022");
        view.setStartDate("01/15/2022");
        view.setEndDate("01/05/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Month must be between 1 and 12.", view.getErrorMessage());
    }

    /**
     * checks that Year is four digits
     */
    @Test
    public void wrongNumberInYear(){
        view.setHireDate("01/03/22");
        view.setStartDate("01/04/2022");
        view.setEndDate("01/05/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Year must be 4 digits.", view.getErrorMessage());
    }

    /**
     * checks that Agreement saved successfully
     */
    @Test
    public void savedAgreementSuccessfully(){
        view.setHireDate("01/03/2022");
        view.setStartDate("01/04/2022");
        view.setEndDate("01/05/2022");
        presenter.onSaveAgreement();
        Assert.assertEquals("Agreement "+(INITIAL_AGREEMENT_COUNT+1)+" successfully created!", view.getFinishMessage());
    }
}
