package com.dvnet.agreement.partner;

import android.os.Bundle;
import com.dvnet.agreement.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEditPartnerActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_PARTNER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add_edit);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String partnerId = getIntent().getStringExtra(PartnerActivity.EXTRA_PARTNER_ID);

        setTitle(partnerId == null ? "Añadir" : "Editar");

        AddEditPartnerFragment addEditPartnerFragment = (AddEditPartnerFragment) getSupportFragmentManager().findFragmentById(R.id.add_edit_partner_container);
        if (addEditPartnerFragment == null) {
            addEditPartnerFragment = addEditPartnerFragment.newInstance(partnerId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_partner_container, addEditPartnerFragment)
                    .commit();
        }
    }
}
