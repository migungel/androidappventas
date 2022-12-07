package com.dvnet.agreement.partner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.dvnet.agreement.R;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

public class PartnerActivity extends AppCompatActivity {

    public static final String EXTRA_PARTNER_ID = "extra_partner_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);

        PartnerFragment fragment = (PartnerFragment)
                getSupportFragmentManager().findFragmentById(R.id.partners_container);

        if (fragment == null) {
            fragment = PartnerFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.partners_container, fragment)
                    .commit();
        }
    }




}
