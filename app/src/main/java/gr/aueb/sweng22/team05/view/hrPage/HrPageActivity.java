package gr.aueb.sweng22.team05.view.hrPage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.ShowPayments.ShowPaymentsActivity;
import gr.aueb.sweng22.team05.view.search_worker_by_afm.SearchWorkerByAfmActivity;

public class HrPageActivity extends AppCompatActivity implements HrPageView {
    private Button ced_worker;
    private Button calcPayments;
    private Button back;
    private EditText month;
    private EditText year;

    /**
     * Creates the layout and initializes the activity.
     * @param savedInstanceState the Instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_page);

        final HrPagePresenter presenter = new HrPagePresenter(this);

        ced_worker = findViewById(R.id.crEdDelWorker);
        calcPayments = findViewById(R.id.calcPayments);
        back = findViewById(R.id.buttonBack);
        month = findViewById(R.id.editTextNumberMonth);
        year = findViewById(R.id.editTextNumberYear);

        ced_worker.setOnClickListener(v -> startActivity(new Intent( HrPageActivity.this, SearchWorkerByAfmActivity.class)));
        calcPayments.setOnClickListener(v -> setValues(presenter));
        back.setOnClickListener(v -> finish());
    }

    /**
     * Gets month for calc pay
     * @return month for calc pay
     */
    @Override
    public String getMonth() {
        return month.getText().toString().trim();
    }

    /**
     * Gets year for calc pay
     * @return year for calc pay
     */
    @Override
    public String getYear() {
        return year.getText().toString().trim();
    }

    /**
     * Sets month for calc pay
     * @param value month for calc pay
     */
    @Override
    public void setMonth(String value) {
        month.setText(value);
    }

    /**
     * Sets year for calc pay
     * @param value year for calc pay
     */
    @Override
    public void setYear(String value) {
        year.setText(value);
    }

    /**
     * Message shown when a task of an activity finishes successfully
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishTask(String title, String message) {
        new AlertDialog.Builder(HrPageActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Message shown when activity finishes successfully
     * @param message message to be shown
     */
    @Override
    public void successfullyFinishActivity(String message) {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message); // -0 idk about that "message_to_toast" ??
        setResult(RESULT_OK, retData);
        finish();
    }

    /**
     * Message shown in case of error
     * @param title message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(HrPageActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Sets the values for next Activity
     */
    private void setValues(HrPagePresenter presenter){
        if(presenter.calcPayments()){
            Intent hr = new Intent(HrPageActivity.this, ShowPaymentsActivity.class);
            hr.putExtra("month", this.getMonth());
            hr.putExtra("year", this.getYear());
            startActivity(hr);
        }
    }
}
