package gr.aueb.sweng22.team05.view.EditWorker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.domain.Agreement;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;
import gr.aueb.sweng22.team05.view.addAgreement.AddAgreementActivity;

public class EditWorkerActivity  extends AppCompatActivity implements EditWorkerView {
    private static String WORKER_AFM = "afm";

    AgreementDAOMemory agreementDAO = new AgreementDAOMemory();
    WorkerDAOMemory workerDAO = new WorkerDAOMemory();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_worker);

        Bundle bundle = getIntent().getExtras();
        this.setAFM(bundle.getString("afm"));
        Worker worker = workerDAO.findByAFM(this.getAFM());
        setValues(worker);

        final EditWorkerPresenter presenter = new EditWorkerPresenter(this, workerDAO, agreementDAO);

        Button saveWorkerButton = findViewById(R.id.button_save_edited_worker);
        saveWorkerButton.setOnClickListener(v -> presenter.onSaveWorker());

        Button addAgreementButton = findViewById(R.id.button_add_new_agreement);
        addAgreementButton.setOnClickListener(v -> onAddAgreement());

        Button deleteWorkerButton = findViewById(R.id.button_delete_worker);
        deleteWorkerButton.setOnClickListener(v -> {
            workerDAO.delete(worker);
            finish();
        });

        Button cancelButton = findViewById(R.id.buttonEWCancel);
        cancelButton.setOnClickListener(v -> {
            finish();
        });

    }

    /**
     * Set values for worker
     * If there are data from intent,
     * if not, get saved data from worker
     * if no data exists in worker either, set blank
     * @param worker worker who's data to set
     */
    private void setValues(Worker worker){
        String firstName = "";
        String lastName = "";
        String emailAddress = "";
        Long phoneNumber = 0L;
        String departmentName = "";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String firstNameIntent = bundle.getString("first_name");
            String lastNameIntent = bundle.getString("last_name");
            String emailAddressIntent = bundle.getString("email_address");
            String phoneNumberIntent = bundle.getString("phone_number");
            String departmentNameIntent = bundle.getString("department_name");

            if (!firstNameIntent.equals("first_name"))
                firstName = firstNameIntent;
            if (!lastNameIntent.equals("last_name"))
                lastName = lastNameIntent;
            if (!emailAddressIntent.equals("email_address"))
                emailAddress = emailAddressIntent;
            if (!phoneNumberIntent.equals("phone_number"))
                phoneNumber = Long.parseLong(phoneNumberIntent);
            if (!departmentNameIntent.equals("department_name"))
                departmentName = departmentNameIntent;
        }

        if (worker!=null) {
            if (firstName.equals(""))
                firstName = worker.getFirstName();
            if (lastName.equals(""))
                lastName = worker.getLastName();
            if (emailAddress.equals(""))
                emailAddress = worker.getEmail();
            if (phoneNumber == 0L)
                phoneNumber = worker.getPhoneNumber();
            if (departmentName.equals(""))
                departmentName = worker.getDepartment().getName();
        }

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(emailAddress);
        this.setPhone(phoneNumber);
        this.setDepartment(departmentName);
    }

    /**
     * When new Agreement is to be added
     */
    private void onAddAgreement(){
        Intent intent = new Intent(EditWorkerActivity.this, AddAgreementActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("first_name", getFirstName());
        bundle.putString("last_name", getLastName());
        bundle.putString("email_address", getEmail());
        bundle.putString("phone_number", String.valueOf(getPhone()));
        bundle.putString("department_name", getDepartment());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getFirstName() {
        return ((EditText)findViewById(R.id.editNewFirstName)).getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return ((EditText)findViewById(R.id.editNewLastName)).getText().toString().trim();
    }

    @Override
    public Long getPhone() {
        String input = ((EditText)findViewById(R.id.editNewPhoneNumber)).getText().toString().trim();
        return Long.parseLong(!input.equals("") ? input : "0");
    }

    @Override
    public String getEmail() {
        return ((EditText)findViewById(R.id.editNewEmailAddress)).getText().toString().trim();
    }

    @Override
    public String getAFM() {
        return WORKER_AFM;
    }

    @Override
    public String getDepartment() {
        return ((EditText)findViewById(R.id.editNewDepartmentName)).getText().toString().trim();
    }

    @Override
    public Agreement getAgreement(){
        String data = getIntent().getExtras().getString("agreement_id");
        Long id = Long.parseLong(data);
        Agreement agreement = agreementDAO.find(id);
        return agreement;
    }

    /**
     * Sets worker's first name
     * @param value worker's first name
     */
    @Override
    public void setFirstName(String value) {
        ((EditText)findViewById(R.id.editNewFirstName)).setText(value);
    }

    /**
     * Sets worker's last name
     * @param value worker's last name
     */
    @Override
    public void setLastName(String value) {
        ((EditText)findViewById(R.id.editNewLastName)).setText(value);
    }

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    @Override
    public void setPhone(Long value) {
        ((EditText)findViewById(R.id.editNewPhoneNumber)).setText(value!=0L ? value.toString() : "");
    }

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    @Override
    public void setEmail(String value) {
        ((EditText)findViewById(R.id.editNewEmailAddress)).setText(value);
    }

    /**
     * Sets worker department name
     * @param value worker's department name
     */
    @Override
    public void setDepartment(String value) {
        ((EditText)findViewById(R.id.editNewDepartmentName)).setText(value);
    }

    /**
     * Sets worker afm
     * @param value worker's afm
     */
    @Override
    public void setAFM(String value) {
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
        new AlertDialog.Builder(EditWorkerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }

}
