package gr.aueb.sweng22.team05.view.addDepartment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.memorydao.WorkerDAOMemory;


public class AddDepartmentActivity extends AppCompatActivity implements AddDepartmentView {
    private EditText dname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddepartment);

        final AddDepartmentPresenter presenter = new AddDepartmentPresenter(this, new WorkerDAOMemory());

        dname= findViewById(R.id.editTextDepName);
        Button createDepButton = findViewById(R.id.buttonCreateDep);
        Button cancelDepButton = findViewById(R.id.buttonCancel);
        createDepButton.setOnClickListener(v -> {
//            if (dname.getText().length() > 0) {
//                String toastMessage = "Name of Department: " + dname.getText().toString();
//                Toast.makeText(AddDepartmentActivity.this.getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//                //TODO GO TO THE NEXT PAGE
//            } else {
//                String toastMessage = "Name of Department are too small";
//                Toast.makeText(AddDepartmentActivity.this.getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
//            }
        });

        cancelDepButton.setOnClickListener(v -> {
            //TODO GO TO THE PREVIOUS PAGE
        });
    }

    @Override
    public String getName(){
        return ((EditText)findViewById(R.id.editTextDepName)).getText().toString().trim();
    }

    @Override
    public void setName(String value) {
        ((EditText)findViewById(R.id.editTextDepName)).setText(value);
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
        new AlertDialog.Builder(AddDepartmentActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create().show();
    }
}
