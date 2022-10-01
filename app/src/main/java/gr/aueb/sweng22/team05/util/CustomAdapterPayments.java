package gr.aueb.sweng22.team05.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.List;

import gr.aueb.sweng22.team05.R;

public class CustomAdapterPayments  extends BaseAdapter {
    Context context;
    List<String> listAFM;
    List<String> listPayments;
    LayoutInflater inflater;

    public CustomAdapterPayments(Context applicationContext, List<String> listAFM, List<String> listPayments) {
        this.context = applicationContext;
        this.listAFM = listAFM;
        this.listPayments = listPayments;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return listAFM!=null ? listAFM.size() : 0;
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
        view = inflater.inflate(R.layout.list_view_item_payment, null);

        TextView afm = (TextView)view.findViewById(R.id.listItem_Payments_afm);
        TextView payment = (TextView)view.findViewById(R.id.listItem_Payments_pay);

        afm.setText(listAFM.get(i));
        payment.setText(listPayments.get(i));

        return view;
    }

}
