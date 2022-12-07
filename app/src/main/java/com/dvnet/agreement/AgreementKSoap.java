package com.dvnet.agreement;

import android.os.AsyncTask;
import android.util.Log;

import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class AgreementKSoap extends AsyncTask<Agremeent, Integer, String> {


    private String SOAP_ACTION;
    private String METHOD_NAME;
    private String NAMESPACE;
    private String URL;
    private String DATABASE;
    private String DBUSER;
    private String DBPASSWORD;
    private PartnerDbHelper DB;


    public AgreementKSoap(String NAMESPACE, String SOAP_ACTION, String METHOD_NAME, String URL,
                        String DATABASE, String DBUSER, String DBPASSWORD, PartnerDbHelper DB){
        this.NAMESPACE = NAMESPACE;
        this.SOAP_ACTION = SOAP_ACTION;
        this.METHOD_NAME = METHOD_NAME;
        this.URL = URL;
        this.DATABASE = DATABASE;
        this.DBUSER = DBUSER;
        this.DBPASSWORD = DBPASSWORD;
        this.DB = DB;
    }

    @Override
    protected String doInBackground(Agremeent... agreement) {
        String response = null;
        SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

        Agremeent p = agreement[0];

        Request.addProperty("dbname", DATABASE);
        Request.addProperty("user", DBUSER);
        Request.addProperty("pass", DBPASSWORD);
        Request.addProperty("id", p.getId());
        Request.addProperty("partner_Id", p.getPartner_Id());
        Request.addProperty("partner", p.getPartner());
        Request.addProperty("company_Id", p.getCompany_Id());
        Request.addProperty("company", p.getCompany());
        Request.addProperty("tipoServicio_Id", p.getTipoServicio_Id());
        Request.addProperty("tipoServicio", p.getTipoServicio());
        Request.addProperty("plan_id", p.getPlan_id());
        Request.addProperty("plan", p.getPlan());
        Request.addProperty("smart_id", p.getSmart_id());
        Request.addProperty("smart", p.getSmart());
        Request.addProperty("canton_id", p.getCanton_id());
        Request.addProperty("canton", p.getCanton());
        Request.addProperty("sector_id", p.getSector_id());
        Request.addProperty("sector", p.getSector());
        Request.addProperty("poste_id", p.getPoste_id());
        Request.addProperty("poste", p.getPoste());
        Request.addProperty("nap_id", p.getNap_id());
        Request.addProperty("nap", p.getNap());
        Request.addProperty("tipvivienda_id", p.getTipVivienda_id());
        Request.addProperty("tipvivienda", p.getTipVivienda());
        Request.addProperty("autorizacion_id", p.getAutorizacion_id());
        Request.addProperty("autorizacion", p.getAutorizacion());
        Request.addProperty("state", p.getState());

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(Request);

        Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());

        try {
            // Allow for debugging - needed to output the request
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.debug = true;

            androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

            // Get the SoapResult from the envelope body.
            SoapObject result = (SoapObject) soapEnvelope.bodyIn;

            response = result.getProperty(0).toString();
            //response = result.toString();

            if(response != null){
                p.setState("A");
                DB.updateAgremeent(p, p.getId());

                Log.i("DVAgreement", "doInBackground: " + p.getPartner() + " migrado correctamente con id: " + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: ", e );
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: ", e );
        }

        return response;
    }

}
