package com.dvnet.agreement;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.dvnet.agreement.data.partner.PartnerDbHelper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

//public class GetKSoapItem {
//}

public class GetKSoapItem extends AsyncTask<String, Integer, String> {

    private String SOAP_ACTION;
    private String METHOD_NAME;
    private String NAMESPACE;
    private String URL;
    private String DATABASE;
    private String DBUSER;
    private String DBPASSWORD;
    private PartnerDbHelper DB;
    private String mSmartId;
    private Activity Context;
    private LoadingBox loadingBox;


    public GetKSoapItem(String NAMESPACE, String SOAP_ACTION, String METHOD_NAME, String URL,
                    String DATABASE, String DBUSER, String DBPASSWORD, PartnerDbHelper DB, LoadingBox loadingBox, Activity Context){
        this.NAMESPACE = NAMESPACE;
        this.SOAP_ACTION = SOAP_ACTION;
        this.METHOD_NAME = METHOD_NAME;
        this.URL = URL;
        this.DATABASE = DATABASE;
        this.DBUSER = DBUSER;
        this.DBPASSWORD = DBPASSWORD;
        this.DB = DB;
        this.Context = Context;
        this.loadingBox = loadingBox;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = null;
        SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

        Request.addProperty("dbname", params[0]);
        Request.addProperty("modulo", params[1]);
        Request.addProperty("user", params[2]);
        Request.addProperty("pass", params[3]);
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

            System.out.println("********Response item : " + result.toString());

            response = result.getProperty(0).toString();
            SoapObject root = (SoapObject) result.getProperty(0);

            System.out.println("********Count item : "+ root.getPropertyCount());

//            String del = String.format("DELETE FROM tb_gen_smart WHERE id = 999");
//            DB.executedb(del);

            for (int i = 0; i < root.getPropertyCount(); i++)
            {
                Object property = root.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject smart_list = (SoapObject) property;
                    String id = smart_list.getProperty("Id").toString();
                    String name = smart_list.getProperty("Name").toString();
                    String code = smart_list.getProperty("Code").toString();
                    String active = smart_list.getProperty("Active").toString();
                    mSmartId = id;

                    if (active.equals("True")) {
                        active = "1";
                    }
                    else{
                        active = "0";
                    }

                    Cursor csmart = DB.getAllRowsby("tb_gen_smart", "id LIKE ?", id);

                    if(csmart.getCount() > 0) {
                        String update = String.format("update tb_gen_smart set name = '%s', code = '%s' , active = '%s' where id = '%s'", name, code, active, id);
                        DB.executedb(update);
                        System.out.println("******** Actualizado smart: "+ name);
                    }
                    else {
                        String insert = String.format("insert into tb_gen_smart (id, name, code, active) values('%s','%s','%s','%s')", id, name, code,active);
                        DB.executedb(insert);
                        System.out.println("******** Insertado smart: "+ name);
                    }
                    csmart.close();
                }
            }

            Cursor csmart = DB.getAllRowsby("tb_gen_smart", "id LIKE ?", "0");

            if( !(csmart.getCount() > 0) ) {
                String na = String.format("insert into tb_gen_smart (id, name, code, active) values('%s','%s','%s','%s')", "0", "Ninguno", "NA", "1");
                DB.executedb(na);
                System.out.println("******** Insertado smart campo Ninguno ");
            }
            csmart.close();
//            boolean t = true;
//            while (t){
//                int id_new = Integer.parseInt(ultimoid) + 1;
//                ultimoid = String.valueOf(id_new);
//                Cursor csmart = DB.getAllRowsby("tb_gen_smart", "id LIKE ?", ultimoid);
//                if (!(csmart.getCount() > 0)) {
            //String del = String.format("DELETE FROM tb_gen_smart WHERE id = 2 ");
            //DB.executedb(del);
//                    t = false;
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: ", e );
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.e("DVAgreement", "doInBackground: ", e );
        }
        return response;
    }

    @Override
    protected void onPostExecute(String respons) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingBox.dismissDialog();
            }
        }, 10000);
    }

}
