package gr.aueb.sweng22.team05.view.FirstPageManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.dao.WorkerDAO;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.ShowLeaveRequests.ShowLeaveRequestsActivity;
import gr.aueb.sweng22.team05.view.hrPage.HrPageActivity;

public class FirstPageManagerActivity extends AppCompatActivity  {
    WorkerDAO workerDAO = new WorkerDAOMemory();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_first_page);
        Button btnconthr = (Button) findViewById(R.id.buttoncontasHr);
        boolean IsHR;
        Bundle extras = getIntent().getExtras();
        IsHR= extras.getBoolean("partofHR");
        if (!IsHR) {
            btnconthr.setVisibility(View.GONE);
        }
        Long idow= extras.getLong("idOfWorker");
        Worker worker = workerDAO.findById(idow);
        Button continuelr = findViewById(R.id.buttoncontinuefpempl);
        continuelr.setOnClickListener(v -> {
            Intent intent = new Intent( FirstPageManagerActivity.this, ShowLeaveRequestsActivity.class);
            intent.putExtra("manager_department",worker.getDepartment().getName());
            startActivity(intent);
        });
        btnconthr.setOnClickListener(v -> {
            if (IsHR) {
                startActivity(new Intent( FirstPageManagerActivity.this, HrPageActivity.class));
            }
        });
    }
}