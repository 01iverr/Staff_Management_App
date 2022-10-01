package gr.aueb.sweng22.team05.view.EditWorker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.domain.emplType;
import gr.aueb.sweng22.team05.domain.typeOfAgr;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

public class EditWorkerPresenterTest {

    private Initializer dataHelper;
    private EditWorkerPresenter presenter;
    private EditWorkerViewStub view;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new EditWorkerViewStub();

        WorkerDAO workers = dataHelper.getWorkerDAO();
        AgreementDAO agreements = dataHelper.getAgreementDAO();
        presenter = new EditWorkerPresenter(view, workers, agreements);

        view.setAfm("564238952");

        Worker worker = workers.findByAFM(view.getAFM());

        view.setFirstName(worker.getFirstName());
        view.setLastName(worker.getLastName());
        view.setEmail(worker.getEmail());
        view.setPhone(worker.getPhoneNumber());
        view.setDepartment(worker.getDepartment().getName());
    }

    /**====================================================
     * Test Invalid user input ============================
     */
    @Test
    public void noFirstName(){
        view.setFirstName("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("First name field can't be empty.", view.getErrorMessage());
    }

    @Test
    public void noLastName(){
        view.setLastName("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Last name field can't be empty.", view.getErrorMessage());
    }

    @Test
    public void phoneNumberTooSmall(){
        view.setPhone(1L);
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid phone number.", view.getErrorMessage());
    }

    @Test
    public void phoneNumberTooBig(){
        view.setPhone(1234567891234567L);
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid phone number.", view.getErrorMessage());
    }

    @Test
    public void invalidEmailAddress(){
        view.setEmail("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        view.setEmail("aaa");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        view.setEmail("aaa@");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        view.setEmail("aaa@sgh");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        view.setEmail("aaa@sgh.");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());

        presenter.onSaveWorker();
        view.setEmail("aaa@.");
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Not a valid email address.", view.getErrorMessage());
    }

    @Test
    public void noDepartmentName(){
        view.setDepartment("");
        presenter.onSaveWorker();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Department name field can't be empty.", view.getErrorMessage());
    }

    /**====================================================
     * Test new value given by user =======================
     */
    @Test
    public void newFirstNameGiven(){
        view.setFirstName("Kate");
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Kate' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newLastNameGiven(){
        view.setLastName("Dwaner");
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Dwaner Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newEmailGiven(){
        view.setEmail("valid@email.xyz");
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newPhoneNumberGiven(){
        view.setPhone(6954888121L);
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newDepartmentGivenDoesNotAlreadyExist(){
        view.setDepartment("New fancy department name");
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newDepartmentGivenAlreadyExists(){
        view.setDepartment("IT");
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newAgreementGivenDoesNotAlreadyExist(){
        Agreement newValue = new Agreement();
        newValue.setId(55555555L);
        view.setAgreement(newValue);
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

    @Test
    public void newAgreementGivenAlreadyExists(){
        AgreementType agrType2 = new AgreementType(2L, "Agreement Type 2", 900f, typeOfAgr.FULLTIME,
                emplType.PAIDBYWAGE, 4, 8);
        Agreement newValue = new Agreement(2L, LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 20), LocalDate.of(2022, 8, 20),
                agrType2, true);
        view.setAgreement(newValue);
        presenter.onSaveWorker();
        Assert.assertEquals("Worker 'Duke Heather' updated successfully!", view.getFinishMessage());
    }

}
