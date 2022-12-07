package com.dvnet.agreement.agreement;

import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

public class PaymentFragment extends DialogFragment {
    private EditText mrecibo;
    private EditText mValor;
    private Context context;
    private PartnerDbHelper mPartnerDbHelper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_payment,null);
        mrecibo = (EditText) view.findViewById(R.id.c_recibo);
        mValor = (EditText) view.findViewById(R.id.c_valor);
        GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
        mPartnerDbHelper = new PartnerDbHelper(getActivity());

        String userid = globalUser.getData();
        Cursor user = mPartnerDbHelper.getUserById(userid);

        user.moveToFirst();
        User u = new User(user);
        int rec = Integer.parseInt(u.getR_desde()) + 1;
        mrecibo.setText(String.valueOf(rec));
        mValor.setText("0.00");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //builder.setView(inflater.inflate(R.layout.fragment_payment, null))
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String recibo = mrecibo.getText().toString();
                        String  valor = mValor.getText().toString();
                        listener.onDialogPositiveClick(PaymentFragment.this, recibo, valor);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PaymentFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public interface PaymentDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String recibo, String valor);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    PaymentDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface

        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (PaymentDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getTargetFragment().toString() + " must implement NoticeDialogListener");
        }
    }


}
