package gr.aueb.sweng22.team05.view.addWorker;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

public class AddWorkerPresenterTest {

    private Initializer dataHelper;
    private AddWorkerPresenter presenter;
    private AddWorkerViewStub view;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddWorkerViewStub();
        WorkerDAO workers = dataHelper.getWorkerDAO();
        AgreementDAO agreements = dataHelper.getAgreementDAO();
        presenter = new AddWorkerPresenter(view, workers, agreements);
    }

    /**
     * No first name was given
     */
    @Test
    public void noFirstName(){
        view.setFirstName("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("First name field can't be empty.", view.getErrorMessage());
    }

    /**
     * No last name was given
     */
    @Test
    public void noLastName(){
        view.setFirstName("l");
        view.setLastName("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Last name field can't be empty.", view.getErrorMessage());
    }

    /**
     * Invalid phone number was given
     * Phone number too small
     */
    @Test
    public void phoneNumberTooSmall(){
        view.setFirstName("l");
        view.setLastName("k");
        view.setPhone(1L);
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid phone number.", view.getErrorMessage());
    }

    /**
     * Invalid phone number was given
     * Phone number too big
     */
    @Test
    public void phoneNumberTooBig(){
        view.setFirstName("l");
        view.setLastName("k");
        view.setPhone(1234567891234567L);
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid phone number.", view.getErrorMessage());
    }

    /**
     * Invalid Email address given
     */
    @Test
    public void invalidEmailAddress(){
        view.setFirstName("l");
        view.setLastName("k");
        view.setPhone(123456L);

        // No email given
        view.setEmail("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        // Wrong email format
        view.setEmail("aaa");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        // Wrong email format
        view.setEmail("aaa@");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        // Wrong email format
        view.setEmail("aaa@sgh");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        // Wrong email format
        view.setEmail("aaa@sgh.");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        // Wrong email format
        view.setEmail("aaa@.");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());
    }

    /**
     * No department name was given
     */
    @Test
    public void noDepartmentName(){
        view.setFirstName("l");
        view.setLastName("k");
        view.setPhone(123456L);
        view.setEmail("aaa@bf.cm");
        view.setDepartment("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Department name field can't be empty.", view.getErrorMessage());
    }

    /**
     * Valid input was given, and Worker saved successfully
     */
    @Test
    public void savedSuccessfully(){

        view.setFirstName("l");
        view.setLastName("k");
        view.setPhone(123456L);
        view.setEmail("aaa@bf.cm");
        view.setDepartment("h");
        view.setWorkerType("Manager");
        presenter.onSaveWorker();
        String message = view.getFinishMessage();
        int index = message.indexOf("!");
        Assert.assertEquals("Manager 'k l' successfully created!", message.substring(0,index+1));

        view.setWorkerType("Employee");
        presenter.onSaveWorker();
        message = view.getFinishMessage();
        index = message.indexOf("!");
        Assert.assertEquals("Employee 'k l' successfully created!", message.substring(0,index+1));
    }

}
