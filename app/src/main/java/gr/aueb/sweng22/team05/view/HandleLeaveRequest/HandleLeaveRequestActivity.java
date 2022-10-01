package gr.aueb.sweng22.team05.view.HandleLeaveRequest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.dao.AcceptedLeavesDAO;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.AcceptedLeavesDAOMemory;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.view.ShowLeaveRequests.ShowLeaveRequestsActivity;

public class HandleLeaveRequestActivity extends AppCompatActivity implements HandleLeaveRequestView {
    public static String LEAVE_REQUEST_ID = "leave_request_id";
    private LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAOMemory();
    private AcceptedLeavesDAO acceptedLeavesDAO = new AcceptedLeavesDAOMemory();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_leave_request);

        final HandleLeaveRequestPresenter presenter = new HandleLeaveRequestPresenter(this, leaveRequestDAO, acceptedLeavesDAO);
        setValues(presenter); // Set required Values
        Leave leave = leaveRequestDAO.find(Long.parseLong(LEAVE_REQUEST_ID.substring(1, LEAVE_REQUEST_ID.length() - 1)));

        Button approveButton = findViewById(R.id.button_approve_leave);
        approveButton.setOnClickListener(v -> {
            presenter.onApprove(leave);
        });

        Button refuseButton = findViewById(R.id.button_refuse_leave);
        refuseButton.setOnClickListener(v -> {
            presenter.onRefuse(leave);
        });

        Button cancelButton = findViewById(R.id.button_cancel_handle_leave);
        cancelButton.setOnClickListener(v -> {
            onCancel();
        });
    }

    /**
     * Set Leave Request ID of leave that was selected
     * Set values for worker/leave
     * If there are saved data in worker/leave
     * if no data exists, set blank
     */
    public void setValues(HandleLeaveRequestPresenter presenter){
        setLeaveRequestId();
        Leave leave = leaveRequestDAO.find(Long.parseLong(getLeaveRequestID().substring(1, getLeaveRequestID().length() - 1)));
        Worker worker = leaveRequestDAO.findWorkerFor(leave);

        presenter.getData(leave, worker);
    }

    private void onCancel(){
        startActivity(new Intent(this, ShowLeaveRequestsActivity.class));
        successfullyFinishActivity("Action canceled.");
    }

    @Override
    public void setLeaveRequestId(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        LEAVE_REQUEST_ID = bundle.getString("leave_request_id");
    }

    @Override
    public String getID() {
        return ((TextView)findViewById(R.id.handleLeave_leave_id)).getText().toString().trim();
    }

    @Override
    public String getLeaveType() {
        return ((TextView)findViewById(R.id.handleLeave_leave_type)).getText().toString().trim();
    }

    @Override
    public String getStartDate() {
        return ((TextView)findViewById(R.id.handleLeave_leave_from)).getText().toString().trim();
    }

    @Override
    public String getEndDate() {
        return ((TextView)findViewById(R.id.handleLeave_leave_to)).getText().toString().trim();
    }

    @Override
    public String getName() {
        return ((TextView)findViewById(R.id.handleLeave_worker_name)).getText().toString().trim();
    }

    @Override
    public String getAFM() {
        return ((TextView)findViewById(R.id.handleLeave_worker_afm)).getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return ((TextView)findViewById(R.id.handleLeave_worker_email)).getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return ((TextView)findViewById(R.id.handleLeave_worker_phone)).getText().toString().trim();
    }

    @Override
    public String getRestTaken() {
        return ((TextView)findViewById(R.id.handleLeave_worker_rest_taken)).getText().toString().trim();
    }

    @Override
    public String getSickTaken() {
        return ((TextView)findViewById(R.id.handleLeave_worker_sick_taken)).getText().toString().trim();
    }

    @Override
    public String getLeaveRequestID() {
        return LEAVE_REQUEST_ID;
    }

    /**
     * Sets leave's id
     * @param value leave's id
     */
    @Override
    public void setID(String value) {
        ((TextView)findViewById(R.id.handleLeave_leave_id)).setText(value);
    }

    /**
     * Sets leave's type
     * @param value leave's type
     */
    @Override
    public void setLeaveType(String value) {
        ((TextView)findViewById(R.id.handleLeave_leave_type)).setText(value);
    }

    /**
     * Sets leave's start date
     * @param value leave's start date
     */
    @Override
    public void setStart(String value) {
        ((TextView)findViewById(R.id.handleLeave_leave_from)).setText(value);
    }

    /**
     * Sets leave's end date
     * @param value leave's end date
     */
    @Override
    public void setEnd(String value) {
        ((TextView)findViewById(R.id.handleLeave_leave_to)).setText(value);
    }

    /**
     * Sets worker's name
     * @param value worker's name
     */
    @Override
    public void setName(String value) {
        ((TextView)findViewById(R.id.handleLeave_worker_name)).setText(value);
    }

    /**
     * Sets worker's afm
     * @param value worker's afm
     */
    @Override
    public void setAFM(String value) {
        ((TextView)findViewById(R.id.handleLeave_worker_afm)).setText(value);
    }

    /**
     * Sets worker's phone number
     * @param value worker's phone number
     */
    @Override
    public void setPhone(Long value) {
        ((TextView)findViewById(R.id.handleLeave_worker_phone)).setText(String.valueOf(value));
    }

    /**
     * Sets worker email address
     * @param value worker's email address
     */
    @Override
    public void setEmail(String value) {
        ((TextView)findViewById(R.id.handleLeave_worker_email)).setText(value);
    }

    /**
     * Sets worker's total sick days taken
     * @param value worker's total sick days
     */
    @Override
    public void setSickTaken(String value) {
        ((TextView)findViewById(R.id.handleLeave_worker_sick_taken)).setText(value);
    }

    /**
     * Sets worker's total rest days taken
     * @param value worker's total rest days
     */
    @Override
    public void setRestTaken(String value) {
        ((TextView)findViewById(R.id.handleLeave_worker_rest_taken)).setText(value);
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
