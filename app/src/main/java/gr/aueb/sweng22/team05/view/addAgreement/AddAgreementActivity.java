package gr.aueb.sweng22.team05.view.addAgreement;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.domain.AgreementType;
import gr.aueb.sweng22.team05.memorydao.AgreementDAOMemory;
import gr.aueb.sweng22.team05.memorydao.AgreementTypeDAOMemory;
import gr.aueb.sweng22.team05.view.addAgreementType.AddAgreementTypeActivity;

public class AddAgreementActivity extends AppCompatActivity implements AddAgreementView {
    AgreementTypeDAOMemory agreementTypeDAO = new AgreementTypeDAOMemory();

    public static String AGREEMENT_RESULT = "agreement_id";
    public static String FIRST_NAME = "first_name";
    public static String LAST_NAME = "last_name";
    public static String EMAIL_ADDRESS = "email_address";
    public static String PHONE_NUMBER = "phone_number";
    public static String DEPARTMENT_NAME = "department_name";
    public static String WORKER_TYPE = "worker_type";

    /**
     * Creates the layout and initializes the activity.
     * @param savedInstanceState the Instance state
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agreement);

        getValues(); // get values given by user from previous task
        setValues(); // if page was changed, new values that were previously must be passed and shown on screen

        final AddAgreementPresenter presenter = new AddAgreementPresenter(this, new AgreementDAOMemory());

        findViewById(R.id.buttonCreateAgr).setOnClickListener(v -> presenter.onSaveAgreement());

        findViewById(R.id.buttonaddAgrType).setOnClickListener(v -> onAddAgreementType());

        findViewById(R.id.buttonCancel).setOnClickListener(v -> finish());
    }

    /**
     * Get values already given by user
     */
    private void getValues(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;

        FIRST_NAME = bundle.getString("first_name");
        LAST_NAME = bundle.getString("last_name");
        EMAIL_ADDRESS = bundle.getString("email_address");
        PHONE_NUMBER = bundle.getString("phone_number");
        DEPARTMENT_NAME = bundle.getString("department_name");
        WORKER_TYPE = bundle.getString("worker_type");
    }

    /**
     * Gets worker's Hire Date
     * @return Hire date in String
     */
    @Override
    public String getHireDate()
    {
        return ((EditText)findViewById(R.id.editDateAgreeHireDate)).getText().toString().trim();
    }

    /**
     * Gets worker's Start Date
     * @return Start date in String
     */
    @Override
    public String getStartDate()
    {
        return ((EditText)findViewById(R.id.editDateAgreeStartDate)).getText().toString().trim();
    }

    /**
     * Gets worker's End Date
     * @return End date in String
     */
    @Override
    public String getEndDate()
    {
        return ((EditText)findViewById(R.id.editDateAgreeEndDate)).getText().toString().trim();
    }

    /**
     * Sets worker's Hire Date
     * @param value worker's Hire Date
     */
    @Override
    public void setHireDate(String value) {
        ((EditText)findViewById(R.id.editDateAgreeHireDate)).setText(value);
    }

    /**
     * Sets worker's Start Date
     * @param value worker's Start Date
     */
    @Override
    public void setStartDate(String value) {
        ((EditText)findViewById(R.id.editDateAgreeStartDate)).setText(value);
    }

    /**
     * Sets worker's End Date
     * @param value worker's End Date
     */
    @Override
    public void setEndDate(String value) {
        ((EditText)findViewById(R.id.editDateAgreeEndDate)).setText(value);
    }

    /**
     * Set new value to AGREEMENT_RESULT
     * @param value new value
     */
    @Override
    public void setAgreementResult(String value){
        AGREEMENT_RESULT = value;
    }

    /**
     * Set values for worker from before (if they exist)
     * aka in case you moved to AddAgreementType
     */
    private void setValues(){
        if (getIntent().getExtras() == null)
            return;

        String hireDate = getIntent().getExtras().getString("hire_date");
        String startDate = getIntent().getExtras().getString("start_date");
        String endDate = getIntent().getExtras().getString("end_date");

        if(hireDate != null){
            this.setHireDate(!hireDate.equals("hire_date") ? hireDate : "");
        }

        if(startDate != null){
            this.setStartDate(!startDate.equals("start_date") ? startDate : "");
        }

        if(endDate != null){
            this.setEndDate(!endDate.equals("end_date") ? endDate : "");
        }
    }

    /**
     * When new Agreement is to be added
     */
    private void onAddAgreementType(){
        Intent intent2 = new Intent(AddAgreementActivity.this, AddAgreementTypeActivity.class);
        Bundle bundle2 = new Bundle();

        bundle2.putString("hire_date", getHireDate());
        bundle2.putString("start_date", getStartDate());
        bundle2.putString("end_date", getEndDate());

        intent2.putExtras(bundle2);
        startActivity(intent2);
    }

    /**
     * Gets the Agreement Type with the id given by AddAgreementTypeActivity
     * @return Agreement Type object
     */
    @Override
    public AgreementType getAgreementType(){
        return agreementTypeDAO.find(agreementTypeDAO.nextId()-1);
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

        retData.putExtra("agreement_id", AGREEMENT_RESULT);
        retData.putExtra("first_name", FIRST_NAME);
        retData.putExtra("last_name", LAST_NAME);
        retData.putExtra("email_address", EMAIL_ADDRESS);
        retData.putExtra("phone_number", PHONE_NUMBER);
        retData.putExtra("department_name", DEPARTMENT_NAME);
        retData.putExtra("worker_type", WORKER_TYPE);

        finish();
    }

    /**
     * Message shown in case of error
     * @param title message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(AddAgreementActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
}
