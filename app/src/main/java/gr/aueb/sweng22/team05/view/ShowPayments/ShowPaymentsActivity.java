package gr.aueb.sweng22.team05.view.ShowPayments;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.AgreementDAO;
import gr.aueb.sweng22.team05.dao.AttendanceDAO;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AttendanceDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.util.CustomAdapterPayments;

public class ShowPaymentsActivity extends AppCompatActivity implements ShowPaymentsView {

    private static String MONTH = "month";
    private static String YEAR = "year";

    WorkerDAO workerDAO = new WorkerDAOMemory();
    AgreementDAO agreementDAO = new AgreementDAOMemory();
    AcceptedLeavesDAO acceptedLeavesDAO = new AcceptedLeavesDAOMemory();
    AttendanceDAO attendanceDAO = new AttendanceDAOMemory();

    CustomAdapterPayments customAdapter;
    ListView paymentsList;
    List<String> allAFM;
    List<String> allPayments;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_calculated_payments);
        paymentsList = (ListView) findViewById(R.id.list_view_payments);

        ShowPaymentsPresenter presenter = new ShowPaymentsPresenter(this, workerDAO, agreementDAO, acceptedLeavesDAO, attendanceDAO);

        getMonthYear();
        allAFM = presenter.getAllAFM();
        allPayments = presenter.getAllPayments(Integer.parseInt(MONTH), Integer.parseInt(YEAR));

        customAdapter = new CustomAdapterPayments(getApplicationContext(), allAFM, allPayments);
        paymentsList.setAdapter(customAdapter);

        Button cancelButton = findViewById(R.id.button_show_payments_back);
        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Get month and year required
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getMonthYear(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        MONTH = bundle.getString("month");
        YEAR = bundle.getString("year");
    }

    @Override
    public String getAFM() {
        return ((TextView)findViewById(R.id.listItem_Payments_afm)).getText().toString().trim();
    }

    @Override
    public String getPayment() {
        return ((TextView)findViewById(R.id.listItem_Payments_pay)).getText().toString().trim();
    }

    /**
     * Sets date value
     * @param value date (month) for which
     */
    @Override
    public void setDate(String value) {
        ((TextView)findViewById(R.id.show_pays_date)).setText(value);
    }
}
