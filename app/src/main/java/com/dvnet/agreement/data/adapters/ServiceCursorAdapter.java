package com.dvnet.agreement.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.ActualService;
import com.dvnet.agreement.data.Canton;
import com.dvnet.agreement.data.Providers;
import com.dvnet.agreement.data.Servicio;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import java.util.ArrayList;
import static android.content.ContentValues.TAG;


public class ServiceCursorAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<ActualService> servList;
//    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ListView mActualServiceList;

    public ServiceCursorAdapter(Context context, ArrayList array, ListView mActualServiceList) {
//        super(context, array, 0);
        this.context = context;
        this.servList = array;
        this.mActualServiceList = mActualServiceList;
    }

    @Override
    public int getCount() {
        return servList.size();
    }

    @Override
    public Object getItem(int pos) {
        return servList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return Integer.parseInt(servList.get(pos).getId());
//        return servList.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_service, null);
        }

        //Handle TextView and display string from your list
        TextView name= (TextView)view.findViewById(R.id.service_name);
        ActualService serv = servList.get(position);
        name.setText(serv.getService() + " - " + serv.getProvider());

        //Handle buttons and add onClickListeners
        Button callbtn= (Button)view.findViewById(R.id.delete_service);

        callbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                servList.remove(position);
                notifyDataSetChanged();
                adapterListView(servList.size(), mActualServiceList);
            }
        });
        return view;
    }

    private void adapterListView(int i, ListView list){
        int totalHeight = 0;
        for (int size = 0; size < i; size++) {
            View listItem = getView(size, null, list);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // Change Height of ListView
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = (totalHeight + (list.getDividerHeight() * (i)));
        list.setLayoutParams(params);
    }
}
