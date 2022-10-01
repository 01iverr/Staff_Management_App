package gr.aueb.sweng22.team05.view.LeaveRequest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LeaveRequestActivity extends AppCompatActivity implements LeaveRequestView {
    Long idow;
    final LeaveRequestPresenter presenter = new LeaveRequestPresenter(this, new LeaveRequestDAOMemory(), new AcceptedLeavesDAOMemory(), new AgreementDAOMemory(),new WorkerDAOMemory()) ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        Bundle extras = getIntent().getExtras();
        idow= extras.getLong("idOfWorker");

        TextView warningTextMess = findViewById(R.id.warningtextLR);
        TextView TextLastDay = findViewById(R.id.textlrld);
        EditText ld=findViewById(R.id.inputLRLD);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switcherSingleDay = findViewById(R.id.switchSDR);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switcherMultipleDay = findViewById(R.id.switchMDR);

        //SINGLE DAY IS SELECTED BY DEFAULT
        switcherSingleDay.setChecked(true);
        warningTextMess.setVisibility(View.INVISIBLE);
        TextLastDay.setVisibility(View.INVISIBLE);
        ld.setVisibility(View.INVISIBLE);

        switcherSingleDay.setOnClickListener(v -> {
            if (switcherSingleDay.isChecked()) {
                switcherSingleDay.setChecked(true);
                switcherMultipleDay.setChecked(false);
                warningTextMess.setVisibility(View.INVISIBLE);
                TextLastDay.setVisibility(View.INVISIBLE);
                ld.setVisibility(View.INVISIBLE);
            }else{
                warningTextMess.setVisibility(View.VISIBLE);
                TextLastDay.setVisibility(View.VISIBLE);
                ld.setVisibility(View.VISIBLE);
                switcherSingleDay.setChecked(false);
                switcherMultipleDay.setChecked(true);
            }
        });
        switcherMultipleDay.setOnClickListener(v -> {
            if (switcherMultipleDay.isChecked()) {
                switcherSingleDay.setChecked(false);
                switcherMultipleDay.setChecked(true);
                warningTextMess.setVisibility(View.VISIBLE);
                TextLastDay.setVisibility(View.VISIBLE);
                ld.setVisibility(View.VISIBLE);
            }else{
                warningTextMess.setVisibility(View.INVISIBLE);
                TextLastDay.setVisibility(View.INVISIBLE);
                ld.setVisibility(View.INVISIBLE);
                switcherSingleDay.setChecked(true);
                switcherMultipleDay.setChecked(false);
            }
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switcherSickDay = findViewById(R.id.switchLRSD);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switcherRestDay = findViewById(R.id.switchLRRD);

        //SICK DAY IS SELECTED BY DEFAULT
        switcherRestDay.setChecked(true);


        switcherSickDay.setOnClickListener(v -> {
            if (switcherSickDay.isChecked()) {
                switcherSickDay.setChecked(true);
                switcherRestDay.setChecked(false);
            }else{
                switcherSickDay.setChecked(false);
                switcherRestDay.setChecked(true);
            }
        });

        switcherRestDay.setOnClickListener(v -> {
            if (switcherRestDay.isChecked()) {
                switcherSickDay.setChecked(false);
                switcherRestDay.setChecked(true);
            }else{
                switcherSickDay.setChecked(true);
                switcherRestDay.setChecked(false);
            }
        });

        Button cancelLR= findViewById(R.id.buttonLRCANCEL);
        cancelLR.setOnClickListener(v -> finish());
        Button continueLR= findViewById(R.id.buttonLRCONTINUE);

        continueLR.setOnClickListener(v -> {
            presenter.checkingDays();
            finish();
        });

    }

    @Override
    public Long getIDofTheWorker() {return idow; }

    @Override
    public String getStartDateLR() { return ((EditText)findViewById(R.id.inputLRFD)).getText().toString().trim(); }

    @Override
    public String getEndDateLR() { return ((EditText)findViewById(R.id.inputLRLD)).getText().toString().trim(); }


    @Override
    public boolean isSingleDR()
    {
        return ((Switch)findViewById(R.id.switchSDR)).isChecked();
    }
    @Override
    public void setisSingleDR(boolean a){
        ((Switch)findViewById(R.id.switchSDR)).setChecked(a);
    }
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(LeaveRequestActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
    @Override
    public boolean isSickDR()
    {
        return ((Switch)findViewById(R.id.switchLRSD)).isChecked();
    }

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishActivity(String message) {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }
}
