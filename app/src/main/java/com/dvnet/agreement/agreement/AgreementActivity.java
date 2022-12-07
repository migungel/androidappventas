package com.dvnet.agreement.agreement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.R;
import com.dvnet.agreement.partner.PartnerFragment;

public class AgreementActivity extends AppCompatActivity {

    public static final String EXTRA_AGREEMENT_ID = "extra_agreement_id";
    public static final String EXTRA_PARTNER_ID = "extra_partner_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        AgreementFragment fragment = (AgreementFragment)
                getSupportFragmentManager().findFragmentById(R.id.agreement_container);

        if (fragment == null) {
            fragment = AgreementFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.agreement_container, fragment)
                    .commit();
        }
    }

}
