package com.dvnet.agreement.partner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;

public class ReferActivity extends AppCompatActivity {

    public static final String EXTRA_PARTNER_ID = "extra_partner_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refers);

        ReferFragment fragment = (ReferFragment)
                getSupportFragmentManager().findFragmentById(R.id.refers_container);

        if (fragment == null) {
            fragment = ReferFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.refers_container, fragment)
                    .commit();
        }
    }
}
