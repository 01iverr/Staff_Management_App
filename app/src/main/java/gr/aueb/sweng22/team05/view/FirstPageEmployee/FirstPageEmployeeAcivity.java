package gr.aueb.sweng22.team05.view.FirstPageEmployee;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.view.LeaveRequest.LeaveRequestActivity;
import gr.aueb.sweng22.team05.view.hrPage.HrPageActivity;

public class FirstPageEmployeeAcivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
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
        Button continuelr = findViewById(R.id.buttoncontinuefpempl);
        Long idow= extras.getLong("idOfWorker");
        continuelr.setOnClickListener(v -> {
            Intent temp=new Intent( FirstPageEmployeeAcivity.this, LeaveRequestActivity.class);
            temp.putExtra("idOfWorker",idow);
            startActivity(temp);
        });

        btnconthr.setOnClickListener(v -> {
            if (IsHR) {
                Intent temp=new Intent( FirstPageEmployeeAcivity.this, HrPageActivity.class);
                temp.putExtra("idOfWorker",idow);
                startActivity(temp);
            }
        });

    }

}
