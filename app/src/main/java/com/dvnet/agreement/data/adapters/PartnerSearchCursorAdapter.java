package com.dvnet.agreement.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.partner.PartnerContract;

public class PartnerSearchCursorAdapter extends CursorAdapter {

    public PartnerSearchCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_partner_search, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.s_partner_name);
        TextView ciText = (TextView) view.findViewById(R.id.s_partners_ci);
        TextView cityText = (TextView) view.findViewById(R.id.s_partners_city);

        // Get valores.
        String name = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.NAME));
        String first = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.FIRSTLN));
        String second = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.SECONDLN));
        String validated = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.VALIDATE));
        if (validated.equals("0")) {
            name = first + " " + second + " " + name;
        }
        String ci = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.IDENTIFICATION_ID));
        String city = cursor.getString(cursor.getColumnIndex(PartnerContract.PartnerEntry.CANTON));

        // Setup.
        nameText.setText(name);
        ciText.setText(ci);
        cityText.setText(city);

    }





}
