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


public class BarridoCursorAdapter extends CursorAdapter {

    public BarridoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_barrido, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.partner_name);
        TextView movilText = (TextView) view.findViewById(R.id.partners_movil);
        TextView cityText = (TextView) view.findViewById(R.id.partners_city);

        // Get valores.
        String firstln = cursor.getString(cursor.getColumnIndex(PartnerEntry.FIRSTLN));
        //String name = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAME));
        String name = cursor.getString(cursor.getColumnIndex(PartnerEntry.COORDENADAS));
//        String movil = cursor.getString(cursor.getColumnIndex(PartnerEntry.MOBILE));
        String movil = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAP));
        String city = cursor.getString(cursor.getColumnIndex(PartnerEntry.CANTON));
        String Provincia = cursor.getString(cursor.getColumnIndex(PartnerEntry.PROVINCIA));
        String direccion = Provincia + " - " + city;
        String state = cursor.getString(cursor.getColumnIndex(PartnerEntry.STATE));
        name = "Coord: " + name;

        if(state.equals("A")){
            name = name + " *";
            nameText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            movilText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            cityText.setTextColor(context.getColor(R.color.material_on_background_disabled));
        }

        // Setup.
        nameText.setText(name);
        movilText.setText(movil);
        cityText.setText(direccion);

    }

}
