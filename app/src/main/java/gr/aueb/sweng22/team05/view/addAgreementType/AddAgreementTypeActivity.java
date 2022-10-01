package gr.aueb.sweng22.team05.view.addAgreementType;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.memorydao.AgreementTypeDAOMemory;

public class AddAgreementTypeActivity extends AppCompatActivity implements AddAgreementTypeView {
    public static String AGREEMENT_TYPE_RESULT = "agreement_type_id";
    public static String HIRE_DATE = "hire_date";
    public static String START_DATE = "start_date";
    public static String END_DATE = "end_date";

    /**
     * Creates the layout and initializes the activity.
     * @param savedInstanceState the Instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agreementtype);

        getValues(); // get values given by user from previous task

        final AddAgreementTypePresenter presenter = new AddAgreementTypePresenter(this, new AgreementTypeDAOMemory());
        ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(true);
        ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(true);
        findViewById(R.id.buttoncreateAgrType).setOnClickListener(view -> presenter.onSaveAgreementType());

        findViewById(R.id.buttoncancelAgrType).setOnClickListener(v -> finish());

        //FULL TIME SWITCHER
        findViewById(R.id.switchAgrTypeFT).setOnClickListener(v -> {
            if (((Switch)findViewById(R.id.switchAgrTypeFT)).isChecked()){
                ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(true);
                ((Switch)findViewById(R.id.switchAgrTypePT)).setChecked(false);
            }else{
                ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(false);
                ((Switch)findViewById(R.id.switchAgrTypePT)).setChecked(true);
            }
        });
        //PART TIME SWITCHER
        findViewById(R.id.switchAgrTypePT).setOnClickListener(v -> {
            if (((Switch)findViewById(R.id.switchAgrTypePT)).isChecked()){
                ((Switch)findViewById(R.id.switchAgrTypePT)).setChecked(true);
                ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(false);
            }else{
                ((Switch)findViewById(R.id.switchAgrTypePT)).setChecked(false);
                ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(true);
            }
        });

        //PAID BY SALARY SWITCHER
        findViewById(R.id.switchAgrTypePBS).setOnClickListener(v -> {
            if (((Switch)findViewById(R.id.switchAgrTypePBS)).isChecked()){
                ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(true);
                ((Switch)findViewById(R.id.switchAgrTypePBW)).setChecked(false);
            }else{
                ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(false);
                ((Switch)findViewById(R.id.switchAgrTypePBW)).setChecked(true);
            }
        });

       //PAID BY WAGE SWITCHER
        findViewById(R.id.switchAgrTypePBW).setOnClickListener(v -> {
            if (((Switch)findViewById(R.id.switchAgrTypePBW)).isChecked()){
                ((Switch)findViewById(R.id.switchAgrTypePBW)).setChecked(true);
                ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(false);
            }else{
                ((Switch)findViewById(R.id.switchAgrTypePBW)).setChecked(false);
                ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(true);
            }
        });

    }

    /**
     * Get values already given by user
     */
    private void getValues(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;

        HIRE_DATE = bundle.getString("hire_date");
        START_DATE = bundle.getString("start_date");
        END_DATE = bundle.getString("end_date");
    }

    /**
     * Gets AgreementType's name
     * @return AgreementType's name
     */
    @Override
    public String getName()
    {
        return ((EditText)findViewById(R.id.editTextAgrTypeName)).getText().toString().trim();
    }

    /**
     * Gets AgreementType's salary
     * @return AgreementType's salary
     */
    @Override
    public String getSalary()
    {
        return ((EditText)findViewById(R.id.editTextNumberAgrTypeSal)).getText().toString().trim();
    }

    /**
     * Gets AgreementType's rest days
     * @return AgreementType's rest days
     */
    @Override
    public String getRestDays()
    {
        return ((EditText)findViewById(R.id.editTextNumberAgrTypeRest)).getText().toString().trim();
    }

    /**
     * Gets AgreementType's sick days
     * @return AgreementType's sick days
     */
    @Override
    public String getSickDays()
    {
        return ((EditText)findViewById(R.id.editTextNumberAgrTypeSick)).getText().toString().trim();
    }

    /**
     * Gets AgreementType's FullTime
     * @return True if AgreementType is FullTime else False
     */
    @Override
    public Boolean getFullTime()
    {
        return ((Switch)findViewById(R.id.switchAgrTypeFT)).isChecked();
    }

    /**
     * Gets AgreementType's PartTime
     * @return True if AgreementType is PartTime else False
     */
    @Override
    public Boolean getPartTime()
    {
        return ((Switch)findViewById(R.id.switchAgrTypePT)).isChecked();
    }

    /**
     * Gets AgreementType's PaidBySalary
     * @return True if AgreementType is PaidBySalary else False
     */
    @Override
    public Boolean getPaidBySalary()
    {
        return ((Switch)findViewById(R.id.switchAgrTypePBS)).isChecked();
    }

    /**
     * Gets AgreementType's PaidByWage
     * @return True if AgreementType is PaidByWage else False
     */
    @Override
    public Boolean getPaidByWage()
    {
        return ((Switch)findViewById(R.id.switchAgrTypePBW)).isChecked();
    }

    /**
     * Sets AgreementType's name
     * @param value AgreementType's name
     */
    @Override
    public void setName(String value) {
        ((EditText)findViewById(R.id.editTextAgrTypeName)).setText(value);
    }

    /**
     * Sets AgreementType's salary
     * @param value AgreementType's salary
     */
    @Override
    public void setSalary(String value) {
        ((EditText)findViewById(R.id.editTextNumberAgrTypeSal)).setText(value);
    }

    /**
     * Sets AgreementType's rest days
     * @param value AgreementType's rest days
     */
    @Override
    public void setRestDays(String value) {
        ((EditText)findViewById(R.id.editTextNumberAgrTypeRest)).setText(value);
    }

    /**
     * Sets AgreementType's sick days
     * @param value AgreementType's sick days
     */
    @Override
    public void setSickDays(String value) {
        ((EditText)findViewById(R.id.editTextNumberAgrTypeSick)).setText(value);
    }

    /**
     * Sets AgreementType's FullTime
     * @param i AgreementType's FullTime
     */
    @Override
    public void setFullTime(boolean i)
    {
        ((Switch)findViewById(R.id.switchAgrTypeFT)).setChecked(i);
    }

    /**
     * Sets AgreementType's PartTime
     * @param i AgreementType's PartTime
     */
    @Override
    public void setPartTime(boolean i)
    {
        ((Switch)findViewById(R.id.switchAgrTypePT)).setChecked(i);
    }

    /**
     * Sets AgreementType's PaidBySalary
     * @param i AgreementType's PaidBySalary
     */
    @Override
    public void setPaidBySalary(boolean i)
    {
        ((Switch)findViewById(R.id.switchAgrTypePBS)).setChecked(i);
    }

    /**
     * Sets AgreementType's PaidByWage
     * @param i AgreementType's PaidByWage
     */
    @Override
    public void setPaidByWage(boolean i)
    {
        ((Switch)findViewById(R.id.switchAgrTypePBW)).setChecked(i);
    }

    /**
     * Sets AGREEMENT_TYPE_RESULT
     * @param value string with the id
     */
    @Override
    public void setAgreementTypeResult(String value){
        AGREEMENT_TYPE_RESULT = value;
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

        retData.putExtra("hire_date", HIRE_DATE);
        retData.putExtra("start_date", START_DATE);
        retData.putExtra("end_date", END_DATE);
        retData.putExtra("agreement_type_id", AGREEMENT_TYPE_RESULT);

        finish();
    }

    /**
     * Message shown in case of error
     * @param title message title
     * @param message message content
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(AddAgreementTypeActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
}
