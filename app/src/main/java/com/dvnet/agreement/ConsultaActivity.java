package com.dvnet.agreement;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.TipoServicio;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.adapters.AdapterConsultaCliente;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button mBtnSearch;
    private TextInputEditText mIdentificacion;
    private TextInputLayout mName;
    private PartnerDbHelper mPartnerDbHelper;
    private Spinner mDocument;
    private String tipoDoc;
    LoadingBox loadingBox;

    String NAMESPACE = "http://dvagreement.android.com/";// cambiar la base
    String URL = "http://45.170.44.3:15002/dvagreement.asmx";
    String DB = "dvtelevision";
//    String URL = "http://192.168.0.118:8070/dvagreement.asmx";
//    String DB = "dvtv";
    String DOCUMENT = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        //View root = inflater.inflate(R.layout.fragment_add_edit_agreement, container, false);

        mBtnSearch = (Button) findViewById(R.id.ccl_btn_search);
        mIdentificacion = (TextInputEditText) findViewById(R.id.ccl_identificacion);
        mPartnerDbHelper = new PartnerDbHelper(this);
        mDocument = (Spinner) findViewById(R.id.a_spinner_document);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_documento, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDocument.setAdapter(adapter);
        mDocument.setOnItemSelectedListener(this);
        mName = (TextInputLayout) findViewById(R.id.ccl_title_identificacion);
        loadingBox = new LoadingBox(ConsultaActivity.this);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String identification = mIdentificacion.getText().toString().replaceAll("\\s+", " ").toUpperCase().trim();
                if ( identification.isEmpty() ) {
                    return;
                }
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected){
//                    loadingBox.startLoadingDialog();
                    consultaCliente(tipoDoc, identification);
//                    consultaCliente(mIdentificacion.getText().toString().replaceAll("\\s+", " ").toUpperCase().trim());
                }else{
                    showMsgDialogConnection();
                }
            }
        });

        mDocument.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Log.i("tipo de documento: ", "" + parent.getItemAtPosition(pos));
                tipoDoc = (String) parent.getItemAtPosition(pos);
                mIdentificacion.getText().clear();
                if (tipoDoc.equals("APELLIDOS Y NOMBRES")) {
                    //mIdentificacion.setVisibility(View.GONE);
                    mName.setHint("Apellidos/Nombres");
                }else{
                    //mIdentificacion.setVisibility(View.VISIBLE);
                    mName.setHint("Ingresar Identificacion");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });
    }

    private void consultaCliente(String document, String Identificacion) {
        GetData data = new GetData(this);
        try{
//            ArrayList<ConsultaCliente> result = data.execute(user.getUser(), user.getPass(), document, Identificacion).get();
            ArrayList<ConsultaCliente> result = data.execute(document, Identificacion).get();
            LoadConsulta(result);
        } catch (Exception e){
            Log.e("Error async", e.toString());
        }
    }

    private void LoadConsulta(ArrayList<ConsultaCliente> resultado){
        // Create the adapter to convert the array to views
        AdapterConsultaCliente adapter = new AdapterConsultaCliente(this, resultado);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.ccl_agreement_result);
        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        adapterListView(arrayActualService.size(), adapterActual, mActualServiceList);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //String document = adapterView.getItemAtPosition(i).toString();
        DOCUMENT = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), document, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    private void showMsgDialogConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Sin conexión a Internet ");
        builder.setMessage("No se encuentra conectado a ninguna red, por favor conéctese a una antes de realizar esta acción");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
            }
        });
        builder.show();
    }


//    public class GetData extends AsyncTask<String, Integer, ArrayList<ConsultaCliente>> {
//
//        String METHOD_NAME = "GetData";
//        String SOAP_ACTION = "http://dvagreement.android.com/GetData";
//
//        private Context mContext;
//        private int NOTIFICATION_ID = 1;
//        private Notification mNotification;
//        private NotificationManager mNotificationManager;
//
//        public GetData(Context context){
//            this.mContext = context;
//
//            //Get the notification manager
//            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//        }
//
//        protected void onPreExecute() {
//            // createNotification("Validando credenciales de acceso...","");
//        }
//
//        @Override
//        protected ArrayList<ConsultaCliente> doInBackground(String... params) {
//
//            ArrayList<ConsultaCliente> response = new ArrayList<ConsultaCliente>();
//
//            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
//
//            Request.addProperty("dbname", DB);
//            Request.addProperty("user", params[0]);
//            Request.addProperty("pass", params[1]);
//            //Request.addProperty("cedula", params[2]);
//            //Request.addProperty("tipo", DOCUMENT);
//            Request.addProperty("tipo", params[2]);
//            Request.addProperty("valor", params[3]);
//
//            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            soapEnvelope.dotNet = true;
//            soapEnvelope.setOutputSoapObject(Request);
//            Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());
//
//            try {
//                // Allow for debugging - needed to output the request
//                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//                androidHttpTransport.debug = true;
//                androidHttpTransport.setReadTimeout(40000);
//
//                androidHttpTransport.call(SOAP_ACTION, soapEnvelope);
//
//                // Get the SoapResult from the envelope body.
//                SoapObject result = (SoapObject) soapEnvelope.bodyIn;
//
//                System.out.println("********Response : " + result.toString());
//
//                //response = result.getProperty(0).toString();
//                SoapObject root = (SoapObject) result.getProperty(0);
//
//                System.out.println("********Count : "+ root.getPropertyCount());
//
//                for (int i = 0; i < root.getPropertyCount(); i++)
//                {
//                    Object property = root.getProperty(i);
//                    if (property instanceof SoapObject)
//                    {
//                        SoapObject userdata = (SoapObject) property;
//                        String Partner_id = userdata.getProperty("Partner_id").toString();
//                        String Partner = userdata.getProperty("Partner").toString();
//                        String Agreement_id = userdata.getProperty("Agreement_id").toString();
//                        String Agreement = userdata.getProperty("Agreement").toString();
//                        String State = userdata.getProperty("State").toString();
//                        String Type_service = userdata.getProperty("Type_service").toString();
//                        String Street = userdata.getProperty("Street").toString();
//                        String Canton = userdata.getProperty("Canton").toString();
//                        String Company = userdata.getProperty("Company").toString();
//                        String Valor = userdata.getProperty("Valor").toString();
//
//                        response.add(new ConsultaCliente(Partner_id,
//                                Partner,
//                                Agreement_id,
//                                Agreement,
//                                State,
//                                Type_service,
//                                Street,
//                                Canton,
//                                Company,
//                                Valor));
//                    }
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e("DVAgreement", "doInBackground Error: " + soapEnvelope.bodyIn.toString() , e );
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//                Log.e("DVAgreement", "doInBackground: ", e );
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<ConsultaCliente> respons) {
//            LoadConsulta(respons);
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    loadingBox.dismissDialog();
//                }
//            }, 2000);
//        }
//
//        private boolean hasBytes(final byte[] data) {
//            if (data != null && data.length > 0) {
//                return true;
//            }
//            return false;
//        }
//
//        private void createNotification(String contentTitle, String contentText) {
//
//            //Build the notification using Notification.Builder
//            Notification.Builder builder = new Notification.Builder(mContext)
//                    .setSmallIcon(android.R.drawable.stat_sys_download)
//                    .setAutoCancel(true)
//                    .setContentTitle(contentTitle)
//                    .setContentText(contentText);
//
//            //Get current notification
//            mNotification = builder.getNotification();
//
//            //Show the notification
//            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
//        }
//    }

}
