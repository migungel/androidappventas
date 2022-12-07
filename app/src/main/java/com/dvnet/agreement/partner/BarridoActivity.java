package com.dvnet.agreement.partner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;

public class BarridoActivity extends AppCompatActivity {

    public static final String EXTRA_PARTNER_ID = "extra_partner_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barridos);

        BarridoFragment fragment = (BarridoFragment)
                getSupportFragmentManager().findFragmentById(R.id.barridos_container);

        if (fragment == null) {
            fragment = BarridoFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.barridos_container, fragment)
                    .commit();
        }
    }
}
