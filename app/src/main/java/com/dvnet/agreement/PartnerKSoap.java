package com.dvnet.agreement;

import android.os.AsyncTask;
import android.util.Log;

import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

public class PartnerKSoap extends AsyncTask<Partner, Integer, String> {

    private String SOAP_ACTION;
    private String METHOD_NAME;
    private String NAMESPACE;
    private String URL;
    private String DATABASE;
    private String DBUSER;
    private String DBPASSWORD;
    private PartnerDbHelper DB;


    public PartnerKSoap(String NAMESPACE, String SOAP_ACTION, String METHOD_NAME, String URL,
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
    protected String doInBackground(Partner... partner) {
        String response = null;
        SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

        Partner p = partner[0];

        Request.addProperty("dbname", DATABASE);
        Request.addProperty("user", DBUSER);
        Request.addProperty("pass", DBPASSWORD);
        Request.addProperty("id", p.getId());
        Request.addProperty("name", p.getName());
        Request.addProperty("identification_id", p.getIdentification_id());
        Request.addProperty("street", p.getStreet());
        Request.addProperty("principal", p.getPrincipal());
        Request.addProperty("secundaria", p.getSecundaria());
        Request.addProperty("mz", p.getMz());
        Request.addProperty("nro", p.getNro());
        Request.addProperty("street2", p.getStreet2());
        //Request.addProperty("number", p.getNumber());
        Request.addProperty("phone", p.getPhone());
        Request.addProperty("mobile", p.getMobile());
        Request.addProperty("email", p.getEmail());
        Request.addProperty("coordenadas", p.getCoordenadas());
        Request.addProperty("cod_cnel", p.getCod_cnel());
        Request.addProperty("cod_agua", p.getCod_agua());
        Request.addProperty("nacimiento", p.getNacimiento());
        Request.addProperty("provincia_id", p.getProvincia_id());
        Request.addProperty("provincia", p.getProvincia());
        Request.addProperty("identification_type", p.getTypeid_id());
        //Request.addProperty("typeid", p.getTypeid());
        Request.addProperty("canton_id", p.getCanton_id());
        Request.addProperty("canton", p.getCanton());
        Request.addProperty("ref_familiar", p.getRef_familiar());
        Request.addProperty("num_ref_familiar", p.getNum_ref_familiar());
        Request.addProperty("ref_personal", p.getRef_personal());
        Request.addProperty("num_ref_personal", p.getNum_ref_personal());
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
                DB.updatePartner(p, p.getId());

                Log.i("DVAgreement", "doInBackground: " + p.getName() + " migrado correctamente con id: " + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: " + soapEnvelope.bodyIn.toString() , e );
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: ", e );
        }

        return response;
    }

}
