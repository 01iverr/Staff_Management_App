package gr.aueb.sweng22.team05.view.addWorker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.addAgreement.AddAgreementActivity;

public class AddWorkerActivity extends AppCompatActivity implements AddWorkerView {
    private static String WORKER_AFM = "afm";

    AgreementDAOMemory agreementDAO = new AgreementDAOMemory();
    WorkerDAOMemory workerDAO = new WorkerDAOMemory();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        setValues(); // if page was changed, new values that were previously must be passed and shown on screen

        final AddWorkerPresenter presenter = new AddWorkerPresenter(this, workerDAO, agreementDAO);

        Button createWorkerButton = findViewById(R.id.button_create_worker);
        createWorkerButton.setOnClickListener(v -> presenter.onSaveWorker());

        Button addAgreementButton = findViewById(R.id.button_add_agreement);
        addAgreementButton.setOnClickListener(v -> onAddAgreement());

        Button cancelButton = findViewById(R.id.buttonCWCancel);
        cancelButton.setOnClickListener(v -> {
            finish();
        });

    }

    /**
     * Set values for worker from before (if they exist)
     * aka in case you moved to AddAgreement
     */
    private void setValues(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;

        String firstName = bundle.getString("first_name");
        String lastName = bundle.getString("last_name");
        String emailAddress = bundle.getString("email_address");
        String phoneNumber = bundle.getString("phone_number");
        String departmentName = bundle.getString("department_name");
        String workerType = bundle.getString("worker_type");
        String workerAFM = bundle.getString("afm");

        this.setFirstName(!firstName.equals("first_name") ? firstName : "");
        this.setLastName(!lastName.equals("last_name") ? lastName : "");
        this.setEmail(!emailAddress.equals("email_address") ? emailAddress : "");
        this.setPhone(phoneNumber.equals("phone_number") ? Long.parseLong(phoneNumber) : null);
        this.setDepartment(!departmentName.equals("department_name") ? departmentName : "");
        this.setWorkerType(!workerType.equals("worker_type") ? workerType : "Employee");
        this.setWorkerAFM(workerAFM);
    }

    /**
     * When new Agreement is to be added
     */
    private void onAddAgreement(){
        Intent intent = new Intent(AddWorkerActivity.this, AddAgreementActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("first_name", getFirstName());
        bundle.putString("last_name", getLastName());
        bundle.putString("email_address", getEmail());
        bundle.putString("phone_number", String.valueOf(getPhone()));
        bundle.putString("department_name", getDepartment());
        bundle.putString("worker_type", getManagerEmployee());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getFirstName() {
        return ((EditText)findViewById(R.id.editFName)).getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return ((EditText)findViewById(R.id.editLName)).getText().toString().trim();
    }

    @Override
    public Long getPhone() {
        String input = ((EditText)findViewById(R.id.editTextPhone)).getText().toString().trim();
        return Long.parseLong(!input.equals("") ? input : "0");
    }

    @Override
    public String getEmail() {
        return ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString().trim();
    }

    @Override
    public String getAFM() {
        return WORKER_AFM;
    }

    @Override
    public String getDepartment() {
        return ((EditText)findViewById(R.id.editDepartmentName)).getText().toString().trim();
    }

    @Override
    public String getManagerEmployee(){
        return ((Switch) findViewById(R.id.switch_manager_employee)).isChecked() ? "Employee" : "Manager";
    }

    @Override
    public Agreement getAgreement(){
        return agreementDAO.find(agreementDAO.nextId()-1);
    }

    /**
     * Sets worker's first name
     * @param value worker's first name
     */
    @Override
    public void setFirstName(String value) {
        ((EditText)findViewById(R.id.editFName)).setText(value);
    }

    /**
     * Sets worker's last name
     * @param value worker's last name
     */
    @Override
    public void setLastName(String value) {
        ((EditText)findViewById(R.id.editLName)).setText(value);
    }

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    @Override
    public void setPhone(Long value) {
        ((EditText)findViewById(R.id.editTextPhone)).setText(value.toString());
    }

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    @Override
    public void setEmail(String value) {
        ((EditText)findViewById(R.id.editTextTextEmailAddress)).setText(value);
    }

    /**
     * Sets worker department name
     * @param value worker's department name
     */
    @Override
    public void setDepartment(String value) {
        ((EditText)findViewById(R.id.editDepartmentName)).setText(value);
    }

    /**
     * Sets worker type
     * @param value worker's type (Manager or Employee)
     */
    @Override
    public void setWorkerType(String value){
        ((Switch)findViewById(R.id.switch_manager_employee)).setChecked(!value.equals("Manager"));
    }

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    @Override
    public void setWorkerAFM(String value) {
        WORKER_AFM = value;
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

    /**
     * Message shown in case of error
     * @param title message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(AddWorkerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
}





