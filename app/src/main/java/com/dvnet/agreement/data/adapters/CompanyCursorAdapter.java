package com.dvnet.agreement.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.agreement.AgreementContract;

public class CompanyCursorAdapter extends CursorAdapter {

    public CompanyCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_agreement, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView idText = (TextView) view.findViewById(R.id.list_agreement_id);
        TextView nameText = (TextView) view.findViewById(R.id.list_agrement_name);
        TextView typeserviceText = (TextView) view.findViewById(R.id.list_agrement_type_service);

        // Get valores.
        String id = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.ID));
        String name = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.PARTNER));
        String typeservice = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.TIPOSERVICIO_ID));


        // Setup.
        idText.setText(id);
        nameText.setText(name);
        typeserviceText.setText(typeservice);

    }

}
