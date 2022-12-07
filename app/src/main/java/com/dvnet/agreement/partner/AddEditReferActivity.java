package com.dvnet.agreement.partner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;

public class AddEditReferActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_PARTNER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add_edit);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String partnerId = getIntent().getStringExtra(ReferActivity.EXTRA_PARTNER_ID);

        setTitle(partnerId == null ? "Añadir" : "Editar");

        AddEditReferFragment addEditReferFragment = (AddEditReferFragment) getSupportFragmentManager().findFragmentById(R.id.add_edit_partner_container);
        if (addEditReferFragment == null) {
            addEditReferFragment = addEditReferFragment.newInstance(partnerId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_partner_container, addEditReferFragment)
                    .commit();
        }
    }
}
