package com.dvnet.agreement.partner;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.partner.PartnerContract.PartnerEntry;


public class ManagementCursorAdapter extends CursorAdapter {

    public ManagementCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_management, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView motiveText = (TextView) view.findViewById(R.id.refer_motive);
        TextView managementText = (TextView) view.findViewById(R.id.refer_management);
        TextView dateText = (TextView) view.findViewById(R.id.refer_date);

        // Get valores.
        String motive = cursor.getString(cursor.getColumnIndex("motive"));
        String management = cursor.getString(cursor.getColumnIndex("management"));
        String date = cursor.getString(cursor.getColumnIndex("date"));

        // Setup.
        motiveText.setText(motive);
        managementText.setText(management);
        dateText.setText(date);
    }





}
