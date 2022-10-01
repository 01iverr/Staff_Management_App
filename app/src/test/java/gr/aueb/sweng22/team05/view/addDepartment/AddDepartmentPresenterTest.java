package gr.aueb.sweng22.team05.view.addDepartment;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddDepartmentPresenterTest {
    private Initializer dataHelper;
    private AddDepartmentPresenter presenter;
    private AddDepartmentViewStub view;

    @Before
    public void setUp(){
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddDepartmentViewStub();
        presenter = new AddDepartmentPresenter(view, new WorkerDAOMemory());
    }

    @Test
    public void validDepartmentName(){
        view.setName("");
        presenter.onSaveDepartment();
        Assert.assertEquals("Error!", view.getErrorTitle());
        Assert.assertEquals("Name of the department must be at least one character.", view.getErrorMessage());
    }

    @Test
    public void savedDepartmentSuccessfully(){
        view.setName("Test");
        presenter.onSaveDepartment();
        Assert.assertEquals("Department 4 successfully created!", view.getFinishMessage());
    }
}
