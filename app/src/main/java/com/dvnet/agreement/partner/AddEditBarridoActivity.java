package com.dvnet.agreement.partner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;

public class AddEditBarridoActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_PARTNER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_add_edit);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String partnerId = getIntent().getStringExtra(BarridoActivity.EXTRA_PARTNER_ID);

        setTitle(partnerId == null ? "Añadir" : "Editar");

        AddEditBarridoFragment addEditBarridoFragment = (AddEditBarridoFragment) getSupportFragmentManager().findFragmentById(R.id.add_edit_partner_container);
        if (addEditBarridoFragment == null) {
            addEditBarridoFragment = addEditBarridoFragment.newInstance(partnerId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_partner_container, addEditBarridoFragment)
                    .commit();
        }
    }
}
