package com.dvnet.agreement;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class GetData extends AsyncTask<String, Integer, ArrayList<ConsultaCliente>> {

    private String METHOD_NAME = "GetData";
    private String SOAP_ACTION = "http://dvagreement.android.com/GetData";
    private String NAMESPACE = "http://dvagreement.android.com/";
    private String URL = "http://45.170.44.3:15002/dvagreement.asmx";
    private String DB = "dvtelevision";
//    private String URL = "http://192.168.0.118:8070/dvagreement.asmx";
//    private String DB = "dvtv";
    private Activity activity;
//    private ArrayList<ConsultaCliente> consulta;
    private LoadingBox loadingBox;
    private PartnerDbHelper mPartnerDbHelper;

    public GetData(Activity activity){
        this.activity = activity;
        loadingBox = new LoadingBox(activity);
//        this.loadingBox = loadingBox;
    }

    protected void onPreExecute() {
        loadingBox.startLoadingDialog();
    }

    @Override
    protected ArrayList<ConsultaCliente> doInBackground(String... params) {
        ArrayList<ConsultaCliente> response = new ArrayList<ConsultaCliente>();
        mPartnerDbHelper = new PartnerDbHelper(this.activity);

        GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
        Cursor cuser = mPartnerDbHelper.getUserById(globalUser.getData());

        if(cuser.getCount()>0){
            cuser.moveToFirst();
            User user = new User(cuser);

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty("dbname", DB);
            Request.addProperty("user", user.getUser());
            Request.addProperty("pass", user.getPass());
            Request.addProperty("tipo", params[0]);
            Request.addProperty("valor", params[1]);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());

            try {
                // Allow for debugging - needed to output the request
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.debug = true;
                androidHttpTransport.setReadTimeout(40000);

                androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                // Get the SoapResult from the envelope body.
                SoapObject result = (SoapObject) soapEnvelope.bodyIn;

                System.out.println("********Response : " + result.toString());

                //response = result.getProperty(0).toString();
                SoapObject root = (SoapObject) result.getProperty(0);

                System.out.println("********Count : "+ root.getPropertyCount());

                for (int i = 0; i < root.getPropertyCount(); i++)
                {
                    Object property = root.getProperty(i);
                    if (property instanceof SoapObject)
                    {
                        SoapObject userdata = (SoapObject) property;
                        String Partner_id = userdata.getProperty("Partner_id").toString();
                        String Partner = userdata.getProperty("Partner").toString();
                        String Agreement_id = userdata.getProperty("Agreement_id").toString();
                        String Agreement = userdata.getProperty("Agreement").toString();
                        String State = userdata.getProperty("State").toString();
                        String Type_service = userdata.getProperty("Type_service").toString();
                        String Street = userdata.getProperty("Street").toString();
                        String Canton = userdata.getProperty("Canton").toString();
                        String Company = userdata.getProperty("Company").toString();
                        String Valor = userdata.getProperty("Valor").toString();

                        response.add(new ConsultaCliente(Partner_id,
                                Partner,
                                Agreement_id,
                                Agreement,
                                State,
                                Type_service,
                                Street,
                                Canton,
                                Company,
                                Valor));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground Error: " + soapEnvelope.bodyIn.toString() , e );
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cuser.close();
        return response;
    }

    @Override
    protected void onPostExecute(ArrayList<ConsultaCliente> respons) {
        mPartnerDbHelper.close();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingBox.dismissDialog();
            }
        }, 2000);
    }

//    public ArrayList<ConsultaCliente> getConsulta() {
//        return consulta;
//    }
}
