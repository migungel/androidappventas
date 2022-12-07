package com.dvnet.agreement;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dvnet.agreement.data.Tecnologia;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class GetKSoap extends AsyncTask<String, Integer, String> {

    private String SOAP_ACTION;
    private String METHOD_NAME;
    private String NAMESPACE;
    private String URL;
    private String DATABASE;
    private String DBUSER;
    private String DBPASSWORD;
    private PartnerDbHelper DB;
    private String mPlanId;
    private Activity Context;
    private LoadingBox loadingBox;


    public GetKSoap(String NAMESPACE, String SOAP_ACTION, String METHOD_NAME, String URL,
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

            System.out.println("********Response : " + result.toString());

            response = result.getProperty(0).toString();
            SoapObject root = (SoapObject) result.getProperty(0);


            System.out.println("********Count : "+ root.getPropertyCount());

            for (int i = 0; i < root.getPropertyCount(); i++)
            {
                Object property = root.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject planes_list = (SoapObject) property;
                    String id = planes_list.getProperty("Id").toString();
                    String name = planes_list.getProperty("Name").toString();
                    String type_service = planes_list.getProperty("Type_service").toString();
                    String active = planes_list.getProperty("Active").toString();
                    mPlanId = id;

                    if (active.equals("True")) {
                        active = "1";
                    }
                    else{
                        active = "0";
                    }

                    Cursor cplan = DB.getAllRowsby("tb_gen_tecnologia", "id LIKE ?", id);

                    if(cplan.getCount() > 0) {
                        String update = String.format("update tb_gen_tecnologia set name = '%s', type_service = '%s' , active = '%s' where id = '%s'", name, type_service, active, id);
                        DB.executedb(update);
                        System.out.println("******** Actualizado : "+ name);
                    }
                    else {
                        String insert = String.format("insert into tb_gen_tecnologia (id, name, type_service, active) values('%s','%s','%s','%s')", id, name,type_service,active);
                        DB.executedb(insert);
                        System.out.println("******** Insertado : "+ name);
                    }
                    cplan.close();
                }
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

    @Override
    protected void onPostExecute(String respons) {
        String METHOD_NAME = "GetSmart";
        String SOAP_ACTION = "http://dvagreement.android.com/GetSmart";

        GetKSoapItem myTask = new GetKSoapItem(NAMESPACE, SOAP_ACTION, METHOD_NAME, URL, DATABASE,
                DBUSER, DBPASSWORD, DB, loadingBox, Context);

        myTask.execute(new String[]{DATABASE, "smart", DBUSER, DBPASSWORD});
        //Toast.makeText(this,"Finalizando Contratos Nuevos", Toast.LENGTH_SHORT).show();
    }

}
