package com.dvnet.agreement;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.partner.Management;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.AddEditReferFragment;
import com.dvnet.agreement.partner.ManagementCursorAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerManagement {

    private Activity activity;
    private AlertDialog dialog;
    private PartnerDbHelper mPartnerDbHelper;
    private Partner refer;
    private String motive;
    private TextInputEditText management;
    private Button mCancel, mSave;
    private Spinner mMotive, mSale;
    private LoadingBox loadingBox;

    public CustomerManagement(Activity myActivity, Partner refer){
        this.activity = myActivity;
        this.refer = refer;
    }

    public void startManagementDialog(Spinner mSale){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        loadingBox = new LoadingBox(activity);
        this.mSale = mSale;

        LayoutInflater inflater = activity.getLayoutInflater();
        View root = inflater.inflate(R.layout.customer_management, null);
        builder.setView(root);
        builder.setCancelable(false);

        mPartnerDbHelper = new PartnerDbHelper(activity);
        management = (TextInputEditText) root.findViewById(R.id.p_management);
        mCancel = (Button) root.findViewById(R.id.cancel_refer);
        mSave = (Button) root.findViewById(R.id.add_refer);
        mMotive = (Spinner) root.findViewById(R.id.a_spinner_motive);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.customer_management, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMotive.setAdapter(adapter);
        mMotive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                motive = (String) mMotive.getSelectedItem();
                Log.i("motive:", motive);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissDialog();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveManagement();
                loadingBox.startLoadingDialog();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    public void saveManagement(){
        String id = "0";

        String refer = this.refer.getId();
        String motive = this.motive;
        String management = this.management.getText().toString();
        String state = "P";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String date = formatter.format(new Date());

        String user_id = this.refer.getUser_id();

        Management manage = new Management(
                id,
                refer,
                motive,
                management,
                user_id,
                state,
                date
                );

        String errors = null;
        errors = validateManagement(manage);

        if ( errors != null){
            Toast.makeText(activity, errors, Toast.LENGTH_SHORT).show();
            loadingBox.dismissDialog();
            return;
        }

        new AddManageTask().execute(manage);
    }


    private String validateManagement(Management manage){
        String error = "Los siguientes datos son necesarios: \n";
        String errors = null;
        int contador = 0;
        if ( manage.getManagement().isEmpty() || manage.getManagement() == null ) {
            error += "Ingrese un motivo de gestion \n";
            contador++;
        }
        if(contador>0) {
            errors = error;
        }
        return errors;
    }

    public void dismissDialog(){
        dialog.dismiss();
    }

    private class AddManageTask extends AsyncTask<Management, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Management... manage) {
            Log.i("doInBackground","" + manage[0]);
            return mPartnerDbHelper.saveManagement(manage[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showManagementScreen(result);
        }

    }

    private void showManagementScreen(Boolean requery) {
        if (!requery) {
            showAddError();
            activity.setResult(Activity.RESULT_CANCELED);
        } else {
            activity.setResult(Activity.RESULT_OK);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingBox.dismissDialog();
//                    activity.finish();
//                    activity.startActivity(activity.getIntent());
                }
            }, 2000);
        }

        if (motive.equals("VENTA CONCRETADA") ||
                motive.equals("VENTA NO CONCRETADA") ||
                motive.equals("NO INTERESADO")){
            mSale.setSelection(getIndex(mSale, motive));
        }

        dismissDialog();
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private void showAddError() {
        Toast.makeText(activity,
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

}
