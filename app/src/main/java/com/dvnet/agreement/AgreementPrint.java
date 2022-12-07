package com.dvnet.agreement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.agreement.AddEditAgreementActivity;
import com.dvnet.agreement.agreement.AgreementActivity;
import com.dvnet.agreement.data.Smart;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.agreement.AgreementContract;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import static android.content.ContentValues.TAG;

public class AgreementPrint extends AppCompatActivity {

    // creating object of WebView
    WebView printWeb;
    String mAgreementId;
    PartnerDbHelper mPartnerDbHelper;

    Agremeent agreement;
    Partner partner;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreementview);
        Contratos c = new Contratos(this);

        mAgreementId = getIntent().getStringExtra("mAgreementId");

        // Initializing the WebView
        final WebView webView = (WebView) findViewById(R.id.webViewMain);
//        webView.setWebViewClient(new WebViewClient());

        // Initializing the Button
        Button savePdfBtn = (Button) findViewById(R.id.savePdfBtn);
        mPartnerDbHelper = new PartnerDbHelper(AgreementPrint.this);

        // Setting we View Client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // initializing the printWeb Object
                printWeb = webView;
            }
        });

        GetPrintAgreement(mAgreementId);
        Log.i(TAG,"agreement: " + agreement + " partner: " + partner + " user: " + user);

//        Contratos c = new Contratos();
        c.setAgrement(agreement);
        c.setPartner(partner);
        c.setUser(user);

        // loading the html
        Log.i(TAG, "Imprimir contrato " + c.getContrato());
        webView.loadData( c.getContrato(), "text/html", "UTF-8" );
//        webView.loadData(c.getContrato(), "text/html; charset=UTF-8", null);
//        webView.evaluateJavascript(c.getContrato(), "text/html; charset=UTF-8");

        // setting clickListener for Save Pdf Button
        savePdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (printWeb != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Calling createWebPrintJob()
                        PrintTheWebPage(printWeb);
                    } else {
                        // Showing Toast message to user
                        Toast.makeText(AgreementPrint.this, "Not available for device below Android LOLLIPOP", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Showing Toast message to user
                    Toast.makeText(AgreementPrint.this, "WebPage not fully loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // object of print job
    PrintJob printJob;

    // a boolean to check the status of printing
    boolean printBtnPressed = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void PrintTheWebPage(WebView webView) {

        // set printBtnPressed true
        printBtnPressed = true;

        // Creating  PrintManager instance
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

        // setting the name of job
        //String jobName = getString(R.string.app_name) + " webpage" + webView.getUrl();
        String jobName = getString(R.string.filename) + agreement.getPartner().toString().replace(" ","_");

        // Creating  PrintDocumentAdapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        assert printManager != null;
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A4);
        builder.setMinMargins(new PrintAttributes.Margins(8,10,8,10));

        //printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
        printJob = printManager.print(jobName, printAdapter, builder.build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (printJob != null && printBtnPressed) {
            if (printJob.isCompleted()) {
                // Showing Toast Message
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
                showDetailScreen(printJob);
            } else if (printJob.isStarted()) {
                // Showing Toast Message
                Toast.makeText(this, "isStarted", Toast.LENGTH_SHORT).show();

            } else if (printJob.isBlocked()) {
                // Showing Toast Message
                Toast.makeText(this, "isBlocked", Toast.LENGTH_SHORT).show();

            } else if (printJob.isCancelled()) {
                // Showing Toast Message
                Toast.makeText(this, "isCancelled", Toast.LENGTH_SHORT).show();

            } else if (printJob.isFailed()) {
                // Showing Toast Message
                Toast.makeText(this, "isFailed", Toast.LENGTH_SHORT).show();

            } else if (printJob.isQueued()) {
                // Showing Toast Message
                Toast.makeText(this, "isQueued", Toast.LENGTH_SHORT).show();

            }
            // set printBtnPressed false
            printBtnPressed = false;
        }
    }

    private void GetPrintAgreement(String AgreementId){
        Cursor cagreement = mPartnerDbHelper.getAgremeentById(AgreementId);
        cagreement.moveToFirst();
        agreement = new Agremeent(cagreement);


        Cursor cpartner = mPartnerDbHelper.getPartnerById(agreement.getPartner_Id());
        cpartner.moveToFirst();
        partner = new Partner(cpartner);

        Cursor cuser = mPartnerDbHelper.getUserById(agreement.getUser_id());
        cuser.moveToFirst();
        user = new User(cuser);
    }

    private void showDetailScreen(PrintJob printJob) {
        Intent intent = new Intent(this, AddEditAgreementActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
    }

}