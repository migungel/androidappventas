package com.dvnet.agreement.agreement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;
import com.dvnet.agreement.agreement.AddEditAgreementFragment;
import com.dvnet.agreement.agreement.AgreementActivity;

public class AddEditAgreementActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_AGREEMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrement_add_edit);

        String agreementId = getIntent().getStringExtra(AgreementActivity.EXTRA_AGREEMENT_ID);

        setTitle(agreementId == null ? "Añadir" : "Editar");

        AddEditAgreementFragment addEditAgreementFragment = (AddEditAgreementFragment) getSupportFragmentManager().findFragmentById(R.id.agreement_add_edit_container);
        if (addEditAgreementFragment == null) {
            addEditAgreementFragment = addEditAgreementFragment.newInstance(agreementId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.agreement_add_edit_container, addEditAgreementFragment)
                    .commit();
        }
    }


}
