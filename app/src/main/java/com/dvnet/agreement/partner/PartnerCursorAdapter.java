package com.dvnet.agreement.partner;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.partner.PartnerContract.PartnerEntry;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import java.util.concurrent.ExecutionException;


public class PartnerCursorAdapter extends CursorAdapter {

    private PartnerDbHelper mPartnerDbHelper;

    public PartnerCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPartnerDbHelper = new PartnerDbHelper(context);
        return inflater.inflate(R.layout.list_item_partner, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.partner_name);
        TextView ciText = (TextView) view.findViewById(R.id.partners_ci);
        TextView cityText = (TextView) view.findViewById(R.id.partners_city);
//        Button delete = (Button) view.findViewById(R.id.delete_partner);

        // Get valores.
        String id = cursor.getString(cursor.getColumnIndex(PartnerEntry.ID));
        String firstln = cursor.getString(cursor.getColumnIndex(PartnerEntry.FIRSTLN));
        String name = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAME));
        String ci = cursor.getString(cursor.getColumnIndex(PartnerEntry.IDENTIFICATION_ID));
        String city = cursor.getString(cursor.getColumnIndex(PartnerEntry.CANTON));
        String Provincia = cursor.getString(cursor.getColumnIndex(PartnerEntry.PROVINCIA));
        String direccion = Provincia + " - " + city;
        String state = cursor.getString(cursor.getColumnIndex(PartnerEntry.STATE));
        String refer = cursor.getString(cursor.getColumnIndex(PartnerEntry.REFER));
        String validated = cursor.getString(cursor.getColumnIndex(PartnerEntry.VALIDATE));

        if (validated.equals("0")) {
            name = firstln + " " + name;
        }

        if( state.equals("A") && refer.equals("0") ){
            name = name + " *";
            nameText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            ciText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            cityText.setTextColor(context.getColor(R.color.material_on_background_disabled));
        } else if ( state.equals("B") ) {
            name = name + "<html><font color=\"red\"> - Datos incompletos</font></html>";
        }

        // Setup.
        nameText.setText(Html.fromHtml(name));
        ciText.setText(ci);
        cityText.setText(direccion);

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMsgDeleteConfirm(context, id);
//            }
//        });

    }

    private class PartnersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            return mPartnerDbHelper.getAllPartnerByUserId(globalUser.getData());
        }
    }

    private void showMsgDeleteConfirm(Context context, String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(" Eliminar registro ");
        builder.setMessage("Esto no afectará a los datos que ya esten sincronizados en el sistema, " +
                "únicamente se verá afectado en su dispositivo.\n\n" +
                "Está seguro que desea eleminar este cliente?");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(" SI ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPartnerDbHelper.deletePartner(id);
                try {
                    swapCursor(new PartnersLoadTask().execute().get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton(" Cancelar ", null);
        builder.show();
    }

}
