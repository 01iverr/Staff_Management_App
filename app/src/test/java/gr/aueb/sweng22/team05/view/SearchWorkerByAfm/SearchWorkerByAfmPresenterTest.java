package gr.aueb.sweng22.team05.view.SearchWorkerByAfm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.search_worker_by_afm.SearchWorkerByAfmPresenter;

public class SearchWorkerByAfmPresenterTest {
    private SearchWorkerByAfmPresenter presenter;
    private SearchWorkerByAfmViewStub view;
    WorkerDAO workerslist;
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new SearchWorkerByAfmViewStub();
        workerslist= new WorkerDAOMemory();
        presenter = new SearchWorkerByAfmPresenter(view,workerslist);
    }

    /**
     * Invalid afm was given
     */
    @Test
    public void invalidAFM(){
        view.settheAfm("1234567891");
        presenter.SearchingWorker();
        Assert.assertEquals("Error!",view.getErrorTitle() );
        Assert.assertEquals(view.getErrorMessage(), "AFM must be 9 digits long.");

        view.settheAfm("12345678");
        presenter.SearchingWorker();
        Assert.assertEquals("Error!",view.getErrorTitle() );
        Assert.assertEquals("AFM must be 9 digits long.", view.getErrorMessage());

        view.settheAfm("12345a678");
        presenter.SearchingWorker();
        Assert.assertEquals("Error!",view.getErrorTitle() );
        Assert.assertEquals("AFM must be 9 digits long.",view.getErrorMessage() );
    }

    /**
     * Valid afm form was given, but it doesn't belong to a worker
     */
    @Test
    public void noValidWorker(){
        view.settheAfm("123456789");
        Assert.assertFalse(presenter.SearchingWorker());
    }

    /**
     * Valid afm form was given, and it belongs to a worker
     */
    @Test
    public void ValidWorker(){
        Worker worker = new Worker();
        worker.setAFM("123456789");
        workerslist.save(worker);
        view.settheAfm("123456789");
        Assert.assertTrue(presenter.SearchingWorker());
    }
}





