package com.dvnet.agreement.agreement;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.agreement.AgreementContract;
import com.dvnet.agreement.data.partner.PartnerContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.PartnerCursorAdapter;

import java.util.concurrent.ExecutionException;

public class AgreementCursorAdapter extends CursorAdapter {

    private PartnerDbHelper mPartnerDbHelper;

    public AgreementCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPartnerDbHelper = new PartnerDbHelper(context);
        return inflater.inflate(R.layout.list_item_agreement, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Referencias UI.
        TextView idText = (TextView) view.findViewById(R.id.list_agreement_id);
        TextView nameText = (TextView) view.findViewById(R.id.list_agrement_name);
        TextView typeserviceText = (TextView) view.findViewById(R.id.list_agrement_type_service);
//        Button delete = (Button) view.findViewById(R.id.delete_agreement);

        // Get valores.
        String id = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.ID));
        String name = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.PARTNER));
        String canton = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.CANTON));
        String typeservice = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.TIPOSERVICIO));

        String texto = canton + " - " + typeservice;

        String state = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.STATE));
//        String pdf = cursor.getString(cursor.getColumnIndex(AgreementContract.AgreementEntry.CONTRATOPDF));
        byte[] contratoPDF = cursor.getBlob(cursor.getColumnIndex(AgreementContract.AgreementEntry.CONTRATOPDF));

        if(state.equals("A")){
            name = name + " *";
            idText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            nameText.setTextColor(context.getColor(R.color.material_on_background_disabled));
            typeserviceText.setTextColor(context.getColor(R.color.material_on_background_disabled));
        } else if (state.equals("B")) {
            name = name + "<html><font color=\"red\"> - Contrato incompleto</font></html>";
        }

        if( !hasBytes(contratoPDF) ){
            name = name + "<html><font color=\"red\"> - Falta contrato</font></html>";
        }

        // Setup.
        idText.setText(id);
        //nameText.setText(name);
        nameText.setText(Html.fromHtml(name));
        typeserviceText.setText(texto);

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMsgDeleteConfirm(context, id);
//            }
//        });
    }

    private boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

    private void showMsgDeleteConfirm(Context context, String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(" Eliminar contrato ");
        builder.setMessage("Esto no afectará a los datos que ya esten sincronizados en el sistema, " +
                "únicamente se verá afectado en su dispositivo.\n\n" +
                "Está seguro que desea eleminar este contrato?");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(" SI ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPartnerDbHelper.deleteAgremeent(id);
                try {
                    swapCursor(new AgreementsLoadTask().execute().get());
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

    private class AgreementsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            return mPartnerDbHelper.getAllAgremeentByUserId(globalUser.getData());
        }
    }
}
