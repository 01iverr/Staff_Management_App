package gr.aueb.sweng22.team05.view.ShowLeaveRequests;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.sweng22.team05.R;
import gr.aueb.sweng22.team05.dao.Initializer;
import gr.aueb.sweng22.team05.dao.LeaveRequestDAO;
import gr.aueb.sweng22.team05.domain.Leave;
import gr.aueb.sweng22.team05.domain.Worker;
import gr.aueb.sweng22.team05.memorydao.LeaveRequestDAOMemory;
import gr.aueb.sweng22.team05.memorydao.MemoryInitializer;
import gr.aueb.sweng22.team05.util.CustomAdapter;
import gr.aueb.sweng22.team05.view.HandleLeaveRequest.HandleLeaveRequestActivity;

public class ShowLeaveRequestsActivity extends AppCompatActivity implements ShowLeaveRequestsView {
    private static String LEAVE_REQUEST_ID = "leave_request_id";
    private static String MANAGER_DEPARTMENT = "manager_department"; // The manager's department name

    CustomAdapter customAdapter;

    ListView leaveRequestList;
    LeaveRequestDAO leaveRequestDAO;

    List<String> allLeaveRequestId;
    List<String> allLeaveRequestType;
    List<String> allWorkerName;
    List<LocalDate> allLeaveRequestStart;
    List<LocalDate> allLeaveRequestEnd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_leave_requests);
        MANAGER_DEPARTMENT = getManagerDepartment();
        leaveRequestList = (ListView) findViewById(R.id.list_view_leave_requests);

        leaveRequestDAO = new LeaveRequestDAOMemory();
        fillLists();

        customAdapter = new CustomAdapter(getApplicationContext(), allLeaveRequestId,
                allLeaveRequestType, allWorkerName, allLeaveRequestStart, allLeaveRequestEnd);
        leaveRequestList.setAdapter(customAdapter);

        // Clicks on leave request
        leaveRequestList.setOnItemClickListener((parent, view, position, id) -> {
            onSelectedLeave();
        });

        Button cancelButton = findViewById(R.id.button_cancel_show_leave_requests);
        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Get data for lists from leaveRequestDAO
     */
    private void fillLists(){
        List<Leave> allLeaveRequests = leaveRequestDAO.findAllFor(MANAGER_DEPARTMENT);
        allLeaveRequestId = new ArrayList<>();
        allLeaveRequestType = new ArrayList<>();
        allWorkerName = new ArrayList<>();
        allLeaveRequestStart = new ArrayList<>();
        allLeaveRequestEnd = new ArrayList<>();
        for (Leave leave : allLeaveRequests){
            Worker worker = leaveRequestDAO.findWorkerFor(leave);
            allLeaveRequestId.add("["+String.valueOf(leave.getId())+"]");
            allLeaveRequestType.add(leave.getType().toString());
            allWorkerName.add(worker.getFirstName()+" "+worker.getLastName());
            allLeaveRequestStart.add(leave.getStart());
            allLeaveRequestEnd.add(leave.getEnd());
        }
    }

    /**
     * Get manager's department from previous activity
     * @return manager's department name
     */
    private String getManagerDepartment(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return MANAGER_DEPARTMENT;
        return bundle.getString("manager_department");
    }

    private void onSelectedLeave(){
        setLeaveRequestID(getID());
        Intent intent = new Intent(this, HandleLeaveRequestActivity.class);
        intent.putExtra("leave_request_id", LEAVE_REQUEST_ID);
        startActivity(intent);
        successfullyFinishActivity("Leave request selected");
    }

    @Override
    public String getID() {
        return ((TextView)findViewById(R.id.listItem_LeaveRequest_id)).getText().toString().trim();
    }

    @Override
    public String getType() {
        return ((TextView)findViewById(R.id.listItem_LeaveRequest_Type)).getText().toString().trim();
    }

    @Override
    public String getName() {
        return ((TextView)findViewById(R.id.listItem_LeaveRequest_Name)).getText().toString().trim();
    }

    @Override
    public String getStartDate() {
        return ((TextView)findViewById(R.id.listItem_LeaveRequest_StartDate)).getText().toString().trim();
    }

    @Override
    public String getEndDate() {
        return ((TextView)findViewById(R.id.listItem_LeaveRequest_EndDate)).getText().toString().trim();
    }

    /**
     * Sets id value of leave request to be handled
     * @param value id of leave request
     */
    @Override
    public void setLeaveRequestID(String value) {
        LEAVE_REQUEST_ID = value;
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
