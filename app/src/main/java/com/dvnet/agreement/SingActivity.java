package com.dvnet.agreement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.agreement.AddEditAgreementActivity;

public class SingActivity extends AppCompatActivity {

    CaptureBitmapView mSig;
    Button mCancel;
    Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signlayout);

        LinearLayout mContent = (LinearLayout) findViewById(R.id.signLayout);
        mSig = new CaptureBitmapView(this, null);
        mCancel = (Button) findViewById(R.id.s_cancel);
        mSave = (Button) findViewById(R.id.s_save);
        //mContent.addView(mSig, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        mContent.addView(mSig, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bitmap signature = mSig.getBitmap();
                byte[] signature = mSig.getBytes();
                showDetailScreen(signature);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSig.ClearCanvas();
            }
        });
    }

    private void showDetailScreen(byte[] bsignature) {
        Intent intent = new Intent(this, AddEditAgreementActivity.class);
        intent.putExtra("signature", bsignature);
        setResult(RESULT_OK, intent);
        finish();
    }


}
