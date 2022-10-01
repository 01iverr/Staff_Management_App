package gr.aueb.sweng22.team05.util;

import android.annotation.SuppressLint;
import android.content.Context;
import gr.aueb.sweng22.team05.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.List;

/**
 * Class used for listView
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    List<String> listLeaveRequestId;
    List<String> listLeaveRequestType;
    List<String> listName;
    List<LocalDate> listStartDate;
    List<LocalDate> listEndDate;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, List<String> leaveRequestId, List<String> leaveRequestType,
                         List<String> name, List<LocalDate> startDate, List<LocalDate> endDate) {
        this.context = applicationContext;
        this.listLeaveRequestId = leaveRequestId;
        this.listLeaveRequestType = leaveRequestType;
        this.listName = name;
        this.listStartDate = startDate;
        this.listEndDate = endDate;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return listName!=null ? listName.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0L;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_view_item_leave_request, null);

        TextView id = (TextView)view.findViewById(R.id.listItem_LeaveRequest_id);
        TextView type = (TextView)view.findViewById(R.id.listItem_LeaveRequest_Type);
        TextView name = (TextView)view.findViewById(R.id.listItem_LeaveRequest_Name);
        TextView startDate = (TextView)view.findViewById(R.id.listItem_LeaveRequest_StartDate);
        TextView endDate = (TextView)view.findViewById(R.id.listItem_LeaveRequest_EndDate);

        id.setText(listLeaveRequestId.get(i));
        type.setText(listLeaveRequestType.get(i));
        name.setText(listName.get(i));
        startDate.setText(listStartDate.get(i).toString());
        endDate.setText(listEndDate.get(i).toString());

        return view;
    }
}
