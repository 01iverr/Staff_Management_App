package gr.aueb.sweng22.team05.view.addAgreementType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.memorydao.AgreementTypeDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;

public class AddAgreementTypePresenterTest {
    private Initializer dataHelper;
    private AddAgreementTypePresenter presenter;
    private AddAgreementTypeViewStub view;

    @Before
    public void setUp(){
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddAgreementTypeViewStub();
        presenter = new AddAgreementTypePresenter(view, new AgreementTypeDAOMemory());
    }

    /**
     * checks that name is not empty
     */
    @Test
    public void emptyAgrTypeName(){
        view.setName("");
        presenter.onSaveAgreementType();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Name must be at least one character.", view.getErrorMessage());
    }

    /**
     * checks that salary does not have more than two decimal points
     */
    @Test
    public void salaryMoreThanTwoDecimalPoints(){
        view.setName("Test");
        view.setSalary("12.523");
        presenter.onSaveAgreementType();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Τhe salary must be at most two decimal places.", view.getErrorMessage());
    }

    /**
     * checks that AgreementType saved successfully
     */
    @Test
    public void savedAgreementTypeSuccessfully(){
        view.setName("Test");
        view.setSalary("12.52");
        view.setFullTime(true);
        view.setPaidBySalary(true);
        view.setRestDays("10");
        view.setSickDays("15");
        presenter.onSaveAgreementType();
        Assert.assertEquals("Agreement Type 5 successfully created!", view.getFinishMessage());
    }
}
