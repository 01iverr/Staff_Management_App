package gr.aueb.sweng22.team05.view.HomePage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Department;
import gr.aueb.sweng22.team05.domain.Employee;
import gr.aueb.sweng22.team05.domain.Manager;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

public class HomePagePresenterTest {

    private HomePagePresenter presenter;
    private HomePageViewStub view;
    WorkerDAO workerslist;
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new HomePageViewStub();
        workerslist= new WorkerDAOMemory();
        presenter = new HomePagePresenter (view,workerslist);
    }

    /**
     * No username was given
     */
    @Test
    public void noUsername(){
        view.settheinputUsername("");
        Assert.assertFalse(presenter.checkedusername());
    }

    /**
     * Invalid username was given
     */
    @Test
    public void InvalidUsername(){
        view.settheinputUsername("123AFBS");
        Assert.assertFalse(presenter.checkedusername());
    }

    /**
     * Invalid username was given
     */
    @Test
    public void InvalidUsername2(){
        view.settheinputUsername("Adfvgbhjk");
        Assert.assertFalse(presenter.checkedusername());
    }

    /**
     * No password was given
     */
    @Test
    public void noPassword(){
        view.setthepasswd("");
        Assert.assertFalse(presenter.checkedpassword());
    }

    /**
     * Invalid password was given
     */
    @Test
    public void InvalidPassword(){
        view.setthepasswd("123AFBS");
        Assert.assertFalse(presenter.checkedpassword());
    }

    /**
     * Invalid password was given
     */
    @Test
    public void InvalidPassword2(){
        view.setthepasswd("Adfvgbhjk");
        Assert.assertFalse(presenter.checkedpassword());
    }

    /**
     * Valid password was given
     */
    @Test
    public void validPassword(){
        view.setthepasswd("12345678901234567892");
        Assert.assertTrue(presenter.checkedpassword());
    }

    /**
     * Valid username was given
     */
    @Test
    public void validUsername(){
        view.settheinputUsername("A1234567890");
        Assert.assertTrue(presenter.checkedusername());
    }

    /**
     * Employee tries to sign in
     */
    @Test
    public void employeeTriesToSignIn(){
        Employee emp=new Employee();
        emp.setUsername("A1234567890");
        emp.setPassword("12345678901234567892");
        workerslist.save(emp);
        view.settheinputUsername("A1234567890");
        view.setthepasswd("12345678901234567892");
        Assert.assertEquals("EMPLOYEE",presenter.checkingCredentials());
    }

    /**
     * Manager tries to sign in
     */
    @Test
    public void managerTriesToSignIn(){
        Manager man=new Manager();
        man.setUsername("A1234567890");
        man.setPassword("12345678901234567892");
        workerslist.save(man);
        view.settheinputUsername("A1234567890");
        view.setthepasswd("12345678901234567892");
        Assert.assertEquals("MANAGER",presenter.checkingCredentials());
    }

    /**
     * Wrong credentials must return nothing instead of class
     */
    @Test
    public void wrongCredentialsMustReturnNOTHINGinsteadofclass(){
        Manager man=new Manager();
        man.setUsername("A12334567890");
        man.setPassword("123456778901234567892");
        workerslist.save(man);
        view.settheinputUsername("A1234567890");
        view.setthepasswd("12345678901234567892");
        Assert.assertEquals("NOTHING",presenter.checkingCredentials());
    }

    /**
     * HR Employee tries to sign in
     */
    @Test
    public void hrEmployeeTriesToSignIn(){
        Employee ehr=new Employee();
        Department HR=new Department(1234l,"HR");
        ehr.setDepartment(HR);
        ehr.setUsername("A1234567890");
        ehr.setPassword("12345678901234567892");
        workerslist.save(ehr);
        view.settheinputUsername("A1234567890");
        view.setthepasswd("12345678901234567892");
        Assert.assertTrue(presenter.partofHr());
    }

    /**
     * Employee trying to sign in is not in HR
     */
    @Test
    public void noHREmployeeTriesToSignIn(){
        Employee ehr=new Employee();
        Department aa=new Department(123l,"Delivery");
        ehr.setDepartment(aa);
        ehr.setUsername("A1234567890");
        ehr.setPassword("12345678901234567892");
        workerslist.save(ehr);
        view.settheinputUsername("A1234567890");
        view.setthepasswd("12345678901234567892");
        Assert.assertFalse(presenter.partofHr());
    }
}


