package com.dvnet.agreement;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.InterestService;
import com.dvnet.agreement.data.Provincia;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.Management;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.ParseXML;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DbMainActivity extends AppCompatActivity {

    Button mSend;
    Button mSendBarrido;
    Button mReceive;
    Button mUpdate;
    Button mUploadData;
    Button mRestoreDB;
    Button mDropDB;
    Button mExportDB;
    Button mUser;
    Spinner mAmbiente;
    TextInputEditText mDbName;
    TextInputEditText mDbUser;
    TextInputEditText mDbPass;
    PartnerDbHelper mPartnerDbHelper;
    LoadingBox loadingBox;
    boolean barridos = false;

    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    ArrayList<String> pathHistory;
    int count = 0;

    private APIService mAPIService;

    String url;
    String db;
    String username;
    String password;

    String NAMESPACE = "http://dvagreement.android.com/";
    String URL = "http://10.11.13.245:15002/dvagreement.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conect_odoo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mSend = (Button) findViewById(R.id.btnupload);
        mSendBarrido = (Button) findViewById(R.id.btnuploadbarrido);
        mReceive = (Button) findViewById(R.id.btndownload);
        mUpdate = (Button) findViewById(R.id.btnupdate);
        mUploadData = (Button) findViewById(R.id.btnuploaddata);
        mRestoreDB = (Button) findViewById(R.id.btnrestore);
        mDropDB = (Button) findViewById(R.id.btndropdb);
        mExportDB = (Button) findViewById(R.id.btnexportdb);
        mUser = (Button) findViewById(R.id.btnuser);
        mDbName = (TextInputEditText) findViewById(R.id.db_database);
        mDbUser = (TextInputEditText) findViewById(R.id.db_UserName);
        mDbPass = (TextInputEditText) findViewById(R.id.db_Password);
        mAmbiente = (Spinner) findViewById(R.id.c_spinner_ambiente);

        mPartnerDbHelper = new PartnerDbHelper(DbMainActivity.this);
        loadingBox = new LoadingBox(DbMainActivity.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ambiente_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mAmbiente.setAdapter(adapter);

        mDbUser.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        mDbPass.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        mAmbiente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String ambiente = (String) mAmbiente.getSelectedItem();


                if (!TextUtils.isEmpty(ambiente)) {
                    if (ambiente.equals("Desarrollo")) {
                        URL = "http://192.168.0.118:8070/dvagreement.asmx";
                        db = "dvtv";
//                        URL = "http://10.11.13.245:15002/dvagreement.asmx";
//                        db = "dvtelevision";
                    } else if (ambiente.equals("Local")) {
                        URL = "http://10.13.11.246:15002/dvagreement.asmx";
                        db = "dvtelevision";
                    } else {
                        URL = "http://45.170.44.3:15002/dvagreement.asmx";
                        db = "dvtelevision";
                    }
                    mDbName.setText(db);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mSendBarrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barridos = true;
                mSend.callOnClick();
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mDbUser.getText().toString();
                Cursor cuser = mPartnerDbHelper.getUserByLogin(user);
                if(cuser.getCount() <= 0) {
                    Toast.makeText(DbMainActivity.this, "Usuario no existe en el dispositivo", Toast.LENGTH_SHORT).show();
                    return;
                }

                cuser.moveToFirst();
                User u = new User(cuser);
                if ( !( mDbPass.getText().toString().equals(u.getPass()) && user.equals(u.getUser()) ) ){
                    Toast.makeText(DbMainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sendText = "Esta seguro de enviar informacion de clientes y contratos?";
                if (barridos) {
                    sendText = "Esta seguro de enviar informacion de barridos?";
                }
                GetCredential("Enviar Información", sendText, "Enviar");
                //showMsgDialogGeneric("Enviar Información", "Esta seguro de enviar informacion de clientes y Contratos?","Enviar");
                cuser.close();
            }
        });

        mReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCredential("Recibir Información", "Esta seguro de enviar informacion de usuario y planes?", "Recibir");
                //SenderUser();
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMsgDialogGeneric("Actualizar datos", "Seguro que desea actualizar los datos?", "Actualizar");
            }
        });

        mUploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

        mRestoreDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMsgDialogRestore();
            }
        });

        mDropDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMsgDialog();
            }
        });

        mExportDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatabase();
            }
        });

        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) DbMainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    startActivity(new Intent(DbMainActivity.this, UserActivity.class));
                } else {
                    showMsgDialogConnection();
                }
            }
        });
    }

    private void showMsgDialogGeneric(String title, String menssage, String task) {
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set dialog title
        builder.setTitle(title);
        // Set the prompt message
        builder.setMessage(menssage);
        // Settings dialog icon
        builder.setIcon(R.mipmap.icon_url);
        // Add OK button
        builder.setPositiveButton(task, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadingBox.startLoadingDialog();
                try {
                    String versionActual = new GetDatabaseVersion(DbMainActivity.this).execute().get();
                    if (versionActual!=null) {
                        String versionApp = getString(R.string.version_app);
                        if (versionActual.equals(versionApp)){
                            if (task.equals("Enviar")) {
                                try {
                                    SincronicePartner();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (task.equals("Recibir")) {
                                SenderUser();
                            } else {
                                UpdateTables();
                            }
                        } else {
                            dialog.cancel();
                            dismissLoadingBox(2000);
                            showMsgDialogErrorDatabaseVersion(versionActual);
                        }
                    }
                } catch (Exception e) {
                    Log.e("Error async", e.toString());
                }
            }
        });
//        builder.setNegativeButton(" Cancelar ", null);
        builder.setNegativeButton(" Cancelar ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                barridos = false;
                dialog.cancel();
            }
        });
        // Create and display dialog boxes
        builder.show();
    }

    private void showMsgDialogConnection() {
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set dialog title
        builder.setTitle(" Sin conexión a Internet ");
        // Set the prompt message
        builder.setMessage("No se encuentra conectado a ninguna red, por favor conéctese a una antes de realizar esta acción");
        // Settings dialog icon
        builder.setIcon(R.mipmap.ic_launcher);
        // Add OK button
        builder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
                mDbUser.getText().clear();
                mDbPass.getText().clear();
            }
        });
        // Create and display dialog boxes
        builder.show();
    }

    private void showMsgDialogRestore() {
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set dialog title
        builder.setTitle(" Restaurar Base de Datos ");
        // Set the prompt message
        builder.setMessage("Mantendrá unicamente los datos de los referidos, todo lo demás será eliminado.\n" +
                "Esta seguro de restaurar la base de datos?");
        // Settings dialog icon
        builder.setIcon(R.mipmap.ic_launcher);
        // Add OK button
        builder.setPositiveButton(" Restaurar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
                loadingBox.startLoadingDialog();
                Cursor data_refer_barrido = mPartnerDbHelper.getAllReferAndBarrido();
                Cursor data_serv = mPartnerDbHelper.getAllRows("tb_list_service");

                deleteDatabase(mPartnerDbHelper.DATABASE_NAME);
                Toast.makeText(DbMainActivity.this, "Base de Datos Restaurada exitosamente...", Toast.LENGTH_SHORT).show();
                mDbUser.getText().clear();
                mDbPass.getText().clear();

                ////////////////data no eliminada
                Handler handler_data = new Handler();
                handler_data.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRefers(data_refer_barrido, data_serv);
                    }
                }, 5000);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingBox.dismissDialog();
                        data_refer_barrido.close();
                        data_serv.close();
                        loadingBox = new LoadingBox(DbMainActivity.this);
                    }
                }, 5000);

            }
        });
        // Add cancel button
        builder.setNegativeButton(" Cancelar ", null);
        // Create and display dialog boxes
        builder.show();
    }

    private void showMsgDialog() {
        //mPartnerDbHelper.deleteAgreements();
        //mPartnerDbHelper.deletePartners();
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set dialog title
        builder.setTitle(" Eliminar Base de Datos ");
        // Set the prompt message
        builder.setMessage("Todos los datos de la aplicacion serán eliminados sin excepción.\n" +
                "Esta seguro de eliminar la base de datos?");
        // Settings dialog icon
        builder.setIcon(R.mipmap.ic_launcher);
        // Add OK button
        builder.setPositiveButton(" Eliminar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
                loadingBox.startLoadingDialog();
                deleteDatabase(mPartnerDbHelper.DATABASE_NAME);
                Toast.makeText(DbMainActivity.this, "Base de Datos Eliminada exitosamente...", Toast.LENGTH_SHORT).show();
                mDbUser.getText().clear();
                mDbPass.getText().clear();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingBox.dismissDialog();
                        loadingBox = new LoadingBox(DbMainActivity.this);
                    }
                }, 5000);
            }
        });
        // Add cancel button
        builder.setNegativeButton(" Cancelar ", null);
        // Create and display dialog boxes
        builder.show();
    }

    private void showMsgDialogError(String tipo, String nombre) {
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set dialog title
        builder.setTitle(" Error con el registro ");
        // Set the prompt message
        builder.setMessage("Se ha encontrado un error con la sincronizacion de " + tipo + ": " + nombre);
        // Settings dialog icon
        builder.setIcon(R.mipmap.ic_launcher);
        // Add OK button
        builder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
            }
        });
        // Create and display dialog boxes
        builder.show();
    }

    private void showMsgDialogErrorDatabaseVersion(String version) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Error con la versión ");
        builder.setMessage("Su aplicación tiene una versión desactualizada, " +
                            "contacte con su supervisor para la nueva versión.\n" +
                            "Versión actual: " + version);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setNegativeButton("OK", null);
        builder.show();
    }

    private void setRefers(Cursor refer, Cursor serv) {
        mPartnerDbHelper = new PartnerDbHelper(DbMainActivity.this);
        if (refer.getCount() > 0) {
            refer.moveToFirst();
            while (!refer.isAfterLast()) {
                String nap_id = refer.getString(refer.getColumnIndex("nap_id"));
                String nap = refer.getString(refer.getColumnIndex("nap"));
                String cooordenadas = refer.getString(refer.getColumnIndex("coordenadas"));
                if ( nap_id == null || nap_id.equals("null") ) {
                    nap_id = "0";
                }
                if ( nap == null || nap.equals("null") ) {
                    nap = "";
                }
                if ( cooordenadas == null || cooordenadas.equals("null") || cooordenadas.equals("") ) {
                    cooordenadas = "0";
                }
                Partner restorePartner = new Partner(refer.getString(refer.getColumnIndex("id")),
                        refer.getString(refer.getColumnIndex("firstln")),
                        refer.getString(refer.getColumnIndex("secondln")),
                        refer.getString(refer.getColumnIndex("name")),
                        refer.getString(refer.getColumnIndex("street")),
                        refer.getString(refer.getColumnIndex("mobile")),
                        refer.getString(refer.getColumnIndex("email")),
                        refer.getString(refer.getColumnIndex("provincia_id")),
                        refer.getString(refer.getColumnIndex("provincia")),
                        refer.getString(refer.getColumnIndex("canton_id")),
                        refer.getString(refer.getColumnIndex("canton")),
                        refer.getString(refer.getColumnIndex("vendedor")),
                        refer.getString(refer.getColumnIndex("state")),
                        refer.getString(refer.getColumnIndex("used")),
                        refer.getString(refer.getColumnIndex("user_id")),
                        refer.getString(refer.getColumnIndex("venta")),
                        refer.getString(refer.getColumnIndex("app_id")),
                        refer.getString(refer.getColumnIndex("refer")),
                        cooordenadas,
                        nap_id,
                        nap,
                        refer.getBlob(refer.getColumnIndex("doc_casa")),
                        refer.getString(refer.getColumnIndex("doc_name_casa"))
                        );
                mPartnerDbHelper.savePartner(restorePartner);

                if (serv.getCount() > 0) {
                    serv.moveToFirst();
                    while (!serv.isAfterLast()) {
                        String id_refer = serv.getString(serv.getColumnIndex("refer"));
                        if (restorePartner.getId().equals(id_refer)) {
                            InterestService restoreServices = new InterestService(serv.getString(serv.getColumnIndex("id")),
                                    id_refer,
                                    serv.getString(serv.getColumnIndex("services")),
                                    serv.getString(serv.getColumnIndex("actual_service")));
                            mPartnerDbHelper.saveServices(restoreServices);
                        }
                        serv.moveToNext();
                    }
                }
                refer.moveToNext();
            }
        }
    }

    private void GetCredential(String msg1, String msg2, String msg3) {
        url = URL;
        db = mDbName.getText().toString();
        username = mDbUser.getText().toString();
        password = mDbPass.getText().toString();
        String error = "Los siguientes datos son necesarios: \n";
        String errors = null;
        int contador = 0;
        if (username.isEmpty() || username == null) {
            error += "Usuario \n";
            contador++;
        }
        if (password.isEmpty() || password == null) {
            error += "Contraseña \n";
            contador++;
        }
        if (contador > 0) {
            errors = error;
        }
        if (errors != null) {
            Toast.makeText(this, errors, Toast.LENGTH_SHORT).show();
            return;
        }
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            showMsgDialogGeneric(msg1, msg2, msg3);
        }else{
            showMsgDialogConnection();
        }
    }

    private void showresult(String result){
        SendToad(result);
    }

    private void SendToad(String mensaje){
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    private void dismissLoadingBox(int timeset){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingBox.dismissDialog();
            }
        }, timeset);
    }

    // ENVIAR INFORMACION USUARIO
    private void SincroniceSendUser(){
        SendUserSincronice();
    }

    public void SendUserSincronice(){
        new  SendUserSoap().execute("user");
    }

    public class SendUserSoap extends AsyncTask<String, Integer, String> {
        String METHOD_NAME = "UpdateLogin";
        String SOAP_ACTION = "http://dvagreement.android.com/UpdateLogin";

        public SendUserSoap(){}

        @Override
        protected String doInBackground(String... params) {
            String response = null;

            Cursor cuser = mPartnerDbHelper.getUserByLogin(username);

            cuser.moveToFirst();
            User u = new User(cuser);

            String fir = null;
            if(hasBytes(u.getSing())){
                fir = Base64.encodeToString(u.getSing(), Base64.DEFAULT);
            }

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("dbname", db);
            Request.addProperty("user", username);
            Request.addProperty("pass", password);
            Request.addProperty("identificacion", u.getIdentificacion());
            Request.addProperty("name", u.getName());
            Request.addProperty("firma", fir);
            //Request.addProperty("firma", DatatypeConverter.printBase64Binary(u.getSing()));
            Request.addProperty("r_desde", u.getR_desde());
            Request.addProperty("r_hasta", u.getR_hasta());

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
                Log.i("DVAgreement", u.getName() + " send user: " + result);

                response = result.getProperty(0).toString();

                Log.i("DVAgreement", "doInBackground: " + u.getName() + " migrado correctamente con id: " + result);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            }
            cuser.close();
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            showresult("Sincronizacion de Usuario finalizado...");
            Log.i("DV", "Sincronizacion de Usuario finalizado..." );
            dismissLoadingBox(18000);
        }
    }

    private void SincronicePartner(){ SendPartnerSincronice(); }

    public void SendPartnerSincronice(){
        Toast.makeText(this,"Iniciando Proceso de Sincronizacion de Clientes", Toast.LENGTH_SHORT).show();
        new  PartnerSoap(this).execute("partner");
    }


    public class PartnerSoap extends AsyncTask<String, Integer, String> {

        String METHOD_NAME = "InsertPartner";
        String SOAP_ACTION = "http://dvagreement.android.com/InsertPartner";

        private Context mContext;
        //private int NOTIFICATION_ID = 1;
        //private Notification mNotification;
        private NotificationManager mNotificationManager;

        public PartnerSoap(Context context){
            this.mContext = context;

            //Get the notification manager
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        protected void onPreExecute() {
            //createNotification("Sincronizacion de Clientes en progreso...","");
            showresult("Sincronizacion de Clientes inicializada...");
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;

            Cursor luser = mPartnerDbHelper.getUserByLogin(username);
            luser.moveToFirst();
            User u = new User(luser);

            Cursor lpartner;
            if ( barridos ) {
                lpartner = mPartnerDbHelper.getBarridoByPending(u.getId());
            } else {
                lpartner = mPartnerDbHelper.getPartnerReferByPending(u.getId());
            }
            lpartner.moveToFirst();

            while (!lpartner.isAfterLast()) {
                Partner p = new Partner(lpartner);
                //if ( !( p.getVenta().equals("VENTA CONCRETADA") && p.getRefer().equals("1") )){}

                if(Integer.parseInt(p.getApp_id())>0){
                    METHOD_NAME = "UpdatePartner";
                    SOAP_ACTION = "http://dvagreement.android.com/UpdatePartner";
                }else{
                    METHOD_NAME = "InsertPartner";
                    SOAP_ACTION = "http://dvagreement.android.com/InsertPartner";
                }
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("dbname", db);
                Request.addProperty("user", username);
                Request.addProperty("pass", password);
                String name = p.getFirstln() + " " + p.getSecondln() + " " + p.getName();
                if ( p.getSecondln().equals("") ){
                    name = p.getFirstln() + " " + p.getName();
                }
                if ( p.getValidate().equals("1") ) {
                    name = p.getName();
                }
                Request.addProperty("name", name);
                Request.addProperty("identification_id", p.getIdentification_id());
                Request.addProperty("street", p.getStreet());
                Request.addProperty("street2", p.getStreet2());
                Request.addProperty("phone", p.getPhone());
                Request.addProperty("mobile", p.getMobile());
                Request.addProperty("email", p.getEmail());
                Request.addProperty("coordenadas", p.getCoordenadas());
                Request.addProperty("nacimiento", p.getNacimiento());
                Request.addProperty("provincia_id", p.getProvincia_id());
                Request.addProperty("provincia", p.getProvincia());
                Request.addProperty("identification_type", p.getTypeid_id());
                Request.addProperty("canton_id", p.getCanton_id());
                Request.addProperty("canton", p.getCanton());
                Request.addProperty("ref_familiar", p.getRef_familiar());
                Request.addProperty("num_ref_familiar", p.getNum_ref_familiar());
                Request.addProperty("ref_personal", p.getRef_personal());
                Request.addProperty("num_ref_personal", p.getNum_ref_personal());
                Request.addProperty("state", p.getState());
                Request.addProperty("userid", p.getUser_id());
                Request.addProperty("type_planilla", p.getServiceId());
                Request.addProperty("cod_cnel", p.getCod_cnel());
                Request.addProperty("cod_agua", p.getCod_agua());
                Request.addProperty("type_street", p.getTypedir_id());
                Request.addProperty("main_street", p.getPrincipal());
                Request.addProperty("second_street", p.getSecundaria());
                Request.addProperty("mz", p.getMz());
                Request.addProperty("villa", p.getNro());
                Request.addProperty("number", p.getNumber());
                Request.addProperty("type_partner", p.getVenta());
                Request.addProperty("id", p.getId());
                Request.addProperty("refer", p.getRefer());
                Request.addProperty("vendedor", p.getVendedor());
                Request.addProperty("nap", p.getNap());
                Request.addProperty("nap_id", p.getNap_id());
                Request.addProperty("doc_name_casa", p.getNombreCasa());
                if (hasBytes(p.getFotoCasa())) {
                    Request.addProperty("doc_casa", Base64.encodeToString(p.getFotoCasa(), Base64.DEFAULT).toString());
                }else{
                    Request.addProperty("doc_casa", "");
                }

                Cursor cursor = mPartnerDbHelper.getAllRowsby("tb_list_service", "refer =?", p.getId());
                cursor.moveToFirst();
                String servicios = "";
                String actual_servicios = "";
                if ( cursor.getCount()>0 ){
                    InterestService serv = new InterestService(cursor);
                    servicios = serv.getServices();
                    actual_servicios = serv.getActualService();
                }
                Request.addProperty("interes", servicios);
                Request.addProperty("ser_ids", actual_servicios);

                if(Integer.parseInt(p.getApp_id())>0){///update
                    Request.addProperty("app_id", p.getApp_id());
                }

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);

                Log.i("DVPartner", "doInBackground: " + soapEnvelope.bodyOut.toString());

                try {
                    // Allow for debugging - needed to output the request
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                    androidHttpTransport.debug = true;
                    androidHttpTransport.setReadTimeout(20000);
                    System.setProperty("http.keepAlive", "false");

                    androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                    // Get the SoapResult from the envelope body.
                    SoapObject result = (SoapObject) soapEnvelope.bodyIn;

                    String id = result.getProperty(0).toString();
                    response = "Cliente: " + p.getFirstln() + " " + p.getName() + " Respuesta: " + result.getProperty(0).toString();
                    //response = result.toString();

                    if(id != null){
                        if (Integer.parseInt(id) >  0){
                            p.setState("A");
                            p.setApp_id(id);
                            mPartnerDbHelper.updatePartner(p, p.getId());
                            response += " Estado: Exitoso\n";
                        }else{
                            response = id + "," + p.getFirstln() + " " + p.getName();
                        }
                        Log.i("DVPartner", "doInBackground: " + p.getName() + " migrado correctamente con id: " + result);
                    }
                    if(androidHttpTransport!=null){
                        androidHttpTransport.reset();
                        try {
                            androidHttpTransport.getServiceConnection().disconnect();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    //SendToas("Cliente: " + p.getName() + " enviado...");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("DVPartner", "doInBackground: ", e );
//                    Log.e("DVPartner", "doInBackground Error: " + soapEnvelope.bodyIn.toString() , e );
                    response += " Estado: " + e.getMessage() + "\n";
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Log.e("DVPartner", "doInBackground: ", e );
                    response += " Estado: " + e.getMessage() + "\n";
                }
                lpartner.moveToNext();
                cursor.close();
            }
            luser.close();
            lpartner.close();
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            showresult("Sincronizacion de Clientes finalizado...");
            if (respons != null)
            {
                showresult(respons);
                if ( respons.split(",")[0].equals("-2") ) {
                    showMsgDialogError( "cliente/referido", respons.split(",")[1]);
                }
            }
            if ( barridos ) {
                SincroniceSendUser();
            } else {
                showresult("Sincronizacion de Gestiones inicializada...");
                SincroniceManagement();
            }
            barridos = false;
        }
    }

    private void SincroniceManagement(){
        SendManagementSincronice();
    }

    public void SendManagementSincronice(){
        new  ManagementSoap().execute("management");
    }

    public class ManagementSoap extends AsyncTask<String, Integer, String> {

        String METHOD_NAME = "InsertGestion";
        String SOAP_ACTION = "http://dvagreement.android.com/InsertGestion";

        @Override
        protected String doInBackground(String... params) {
            String response = null;
            Cursor luser = mPartnerDbHelper.getUserByLogin(username);
            luser.moveToFirst();
            User u = new User(luser);

            Cursor management = mPartnerDbHelper.getManagementByPending(u.getId());
            management.moveToFirst();
            while (!management.isAfterLast()) {
                Management manage = new Management(management);
                Cursor cursor = mPartnerDbHelper.getPartnerById(manage.getRefer());
                cursor.moveToFirst();
                Partner partner = new Partner(cursor);

                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("dbname", db);
                Request.addProperty("user", username);
                Request.addProperty("pass", password);
                Request.addProperty("refer", manage.getRefer());
                Request.addProperty("remote_partner_id", partner.getApp_id());
                Request.addProperty("motive", manage.getMotive());
                Request.addProperty("management", manage.getManagement());
                Request.addProperty("user_id", manage.getUser_id());
                Request.addProperty("date", manage.getDate());

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);

                Log.i("DVManagement", "doInBackground: " + soapEnvelope.bodyOut.toString());

                try {
                    // Allow for debugging - needed to output the request
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                    androidHttpTransport.debug = true;
                    androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                    // Get the SoapResult from the envelope body.
                    SoapObject result = (SoapObject) soapEnvelope.bodyIn;
                    response = result.getProperty(0).toString();
                    Log.i("DVManagement", manage.getRefer() + " Management: " + result);

                    if(response != null){
                        if (Integer.parseInt(response) >  0){
                            manage.setState("A");
                            mPartnerDbHelper.updateManagement(manage, manage.getId());
                        }
                        Log.i("DVManagement", "doInBackground: " + manage.getRefer() + " migrado correctamente con id: " + result);
                    }
                    if(androidHttpTransport!=null){
                        androidHttpTransport.reset();
                        try {
                            androidHttpTransport.getServiceConnection().disconnect();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("DVManagement", "doInBackground: ", e );
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Log.e("DVManagement", "doInBackground: ", e );
                }
                management.moveToNext();
                cursor.close();
            }
            luser.close();
            management.close();
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            showresult("Sincronizacion de Gestiones finalizado...");
            showresult("Sincronizacion de Contratos inicializada...");
            SincroniceAgreement();
        }
    }


    private void SincroniceAgreement(){ SendAgreementSincronice(); }

    public void SendAgreementSincronice(){
        new  AgreementSoap().execute("agremeent");
    }

    public class AgreementSoap extends AsyncTask<String, Integer, String> {

        String METHOD_NAME = "InsertAgreement";
        String SOAP_ACTION = "http://dvagreement.android.com/InsertAgreement";

        public AgreementSoap(){}

        @Override
        protected String doInBackground(String... params) {
            String response = null;

            Cursor luser = mPartnerDbHelper.getUserByLogin(username);
            luser.moveToFirst();
            User u = new User(luser);

            Cursor lagreement = mPartnerDbHelper.getAgremeentByPending(u.getId());
            lagreement.moveToFirst();

            while (!lagreement.isAfterLast()) {
                Agremeent p = new Agremeent(lagreement);
                //Partner c = new Partner(mPartnerDbHelper.getPartnerById(p.getPartner_Id()));
                Cursor cursor = mPartnerDbHelper.getPartnerById(p.getPartner_Id());
                cursor.moveToFirst();
                Partner partner = new Partner(cursor);

                if ( hasBytes(p.getPDF()) && partner.getState().equals("A") ) {
                    SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

                    Request.addProperty("dbname", db);
                    Request.addProperty("user", username);
                    Request.addProperty("pass", password);
                    Request.addProperty("id", p.getId());
                    Request.addProperty("partner_Id", p.getPartner_Id());
                    Request.addProperty("partner", p.getPartner());
                    Request.addProperty("company_Id", p.getCompany_Id());
                    Request.addProperty("company", p.getCompany());
                    Request.addProperty("tipoServicio_Id", p.getTipoServicio_Id());
                    Request.addProperty("tipoServicio", p.getTipoServicio());
                    Request.addProperty("plan_id", p.getPlan_id());
                    Request.addProperty("plan", p.getPlan());
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
                    Request.addProperty("sign", Base64.encodeToString(p.getSing(), Base64.DEFAULT).toString());
                    Request.addProperty("userid", p.getUser_id());
                    Request.addProperty("observacion", p.getObservacion());
                    Request.addProperty("contrato", p.getContrato());
                    Request.addProperty("tipo_contrato", p.getTipoContrato());
                    Request.addProperty("item_id", p.getSmart_id());
                    Request.addProperty("doc_identification", Base64.encodeToString(p.getFotoCedula(), Base64.DEFAULT).toString());
                    Request.addProperty("doc_name_id", p.getNombreCedula());
                    if (hasBytes(p.getFotoCedulaPost())) {
                        Request.addProperty("doc_identification_post", Base64.encodeToString(p.getFotoCedulaPost(), Base64.DEFAULT).toString());
                    }else{
                        Request.addProperty("doc_identification_post", "");
                    }
                    Request.addProperty("doc_name_id_post", p.getNombreCedulaPost());
                    Request.addProperty("doc_servicebasic", Base64.encodeToString(p.getFotoPlanilla(), Base64.DEFAULT).toString());
                    Request.addProperty("doc_name_serbasic", p.getNombrePlanilla());
                    Request.addProperty("photo_house", Base64.encodeToString(p.getFotoCasa(), Base64.DEFAULT).toString());
                    Request.addProperty("photo_name", p.getNombreCasa());
                    Request.addProperty("doc_contract", Base64.encodeToString(p.getPDF(), Base64.DEFAULT).toString());
                    Request.addProperty("doc_name_contract", p.getNombrePDF());
                    Request.addProperty("nro_recibo", p.getRecibo());
                    Request.addProperty("valor_instalacion", String.valueOf(p.getValor()));

                    SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    soapEnvelope.dotNet = true;
                    soapEnvelope.setOutputSoapObject(Request);

                    Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());

                    try {
                        // Allow for debugging - needed to output the request
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                        androidHttpTransport.debug = true;
                        androidHttpTransport.setReadTimeout(20000);
                        System.setProperty("http.keepAlive", "false");

                        androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                        // Get the SoapResult from the envelope body.
                        SoapObject result = (SoapObject) soapEnvelope.bodyIn;

                        response = result.getProperty(0).toString();

                        Log.i("DVAgreement", p.getPartner() + " agreement: " + result);

                        if(response != null){
                            if (Integer.parseInt(response) >  0){
                                p.setState("A");
                                mPartnerDbHelper.updateAgremeent(p, p.getId());
                            }else{
                                response += "," + p.getPartner();
                            }
                            Log.i("DVAgreement", "doInBackground: " + p.getPartner() + " migrado correctamente con id: " + result);
                        }
                        if(androidHttpTransport!=null){
                            androidHttpTransport.reset();
                            try {
                                androidHttpTransport.getServiceConnection().disconnect();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("DVAgreement", "doInBackground: ", e );
                        response += " Estado: " + e.getMessage() + "\n";
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                        Log.e("DVAgreement", "doInBackground: ", e );
                        response += " Estado: " + e.getMessage() + "\n";
                    }
                }else{
                    response = "Cliente no esta sincronizado o falta el contrato";
                }

                lagreement.moveToNext();
                cursor.close();
            }
            lagreement.close();
            luser.close();
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            showresult("Sincronizacion de Contratos finalizado...");
            if (respons != null) {
                if ( respons.split(",")[0].equals("-2") ) {
                    showMsgDialogError( "contrato", respons.split(",")[1]);
                }
            }
            showresult("Sincronizacion de Usuario inicializada...");
            SincroniceSendUser();
        }

    }



    // RECIBE iNFORMACION
    private void SenderUser(){
        new UserSoap(this).execute("user");
    }

    public class UserSoap extends AsyncTask<String, Integer, String> {

        String METHOD_NAME = "GetLogin";
        String SOAP_ACTION = "http://dvagreement.android.com/GetLogin";

        private Context mContext;
        private int NOTIFICATION_ID = 1;
        private Notification mNotification;
        private NotificationManager mNotificationManager;



        public UserSoap(Context context){
            this.mContext = context;

            //Get the notification manager
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        protected void onPreExecute() {
            // createNotification("Validando credenciales de acceso...","");
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty("dbname", db);
            Request.addProperty("user", username);
            Request.addProperty("pass", password);

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
                SoapObject root = (SoapObject) result.getProperty(0);
                Object property = result.getProperty(0);

                if (property instanceof SoapObject)
                {
                    byte[] sing = null;
                    SoapObject userdata = (SoapObject) property;
                    String id = userdata.getProperty("User_id").toString();
                    String name = userdata.getProperty("Name").toString();
                    String identificacion = userdata.getProperty("Identificacion").toString();
                    if(userdata.getPropertyCount() == 7){
                        sing = Base64.decode(userdata.getProperty("Firma").toString(),1);
                    }
                    String r_desde = userdata.getProperty("R_desde").toString();
                    String r_hasta = userdata.getProperty("R_hasta").toString();
                    String active = userdata.getProperty("Active").toString();

                    response = id;
                    /*
                    if (Integer.parseInt(id) <= 0){
                        if (Integer.parseInt(id) == -1){
                            throw new Exception("Error Login remoto...");
                        }
                        if (Integer.parseInt(id) == -2){
                            throw new Exception("Error el obtener respuesta remota...");
                        }
                    }
                    */

                    if (active.equals("True")) {
                        active = "1";
                    }
                    else{
                        active = "0";
                    }

                    Cursor cuser = mPartnerDbHelper.getAllRowsby("tb_gen_users", "id LIKE ?", id);

                    if(cuser.getCount() > 0 && Integer.parseInt(id) > 0) { //// añadido Integer.parseInt(id) > 0
                        cuser.moveToFirst();
                        User u = new User(cuser);

                        if (!u.getR_hasta().equals(r_hasta) || u.getR_hasta().isEmpty()){
                            u.setR_desde(r_desde);
                            u.setR_hasta(r_hasta);
                        }

                        if(!hasBytes(u.getSing())){
                            u.setSing(sing);
                        }

                        u.setId(id);
                        u.setName(name);
                        u.setUser(username);
                        u.setPass(password);
                        u.setIdentificacion(identificacion);

                        mPartnerDbHelper.updateUser(u, u.getId());
                    }
                    else if ( Integer.parseInt(id) > 0 ) {
                        User u = new User(id,
                                username,
                                password,
                                identificacion,
                                name,
                                r_desde,
                                r_hasta,
                                sing,
                                active);

                        mPartnerDbHelper.saveUser(u);
                        System.out.println("******** Insertado : "+ name);
                    }
                    cuser.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
//                Log.e("DVAgreement", "doInBackground Error: " + soapEnvelope.bodyIn.toString() , e );
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            String mensaje = null;
            boolean lanzar = false;
            if (respons != null){
                if(Integer.parseInt(respons) > 0 ){
                    mensaje="Acceso al sistema exitoso";
                    lanzar = true;
                }
                if(Integer.parseInt(respons) == -1 ){
                    mensaje="Verifique su usuario/clave";
                    lanzar = false;
                }
                if(Integer.parseInt(respons) == -2 ){
                    mensaje="Error en Servidor comunique a su administrador";
                    lanzar = false;
                }
            }
            createNotification(mensaje,"");
            showresult(mensaje);

            if (lanzar){
                //SincronicePartner();
                RecievedData();
            }else{
                dismissLoadingBox(3000);
            }
        }

//        private boolean hasBytes(final byte[] data) {
//            if (data != null && data.length > 0) {
//                return true;
//            }
//            return false;
//        }

        private void createNotification(String contentTitle, String contentText) {

            //Build the notification using Notification.Builder
            Notification.Builder builder = new Notification.Builder(mContext)
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setAutoCancel(true)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText);

            //Get current notification
            mNotification = builder.getNotification();

            //Show the notification
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        }
    }

    private static boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

    public void RecievedData(){ ReceiveSincronice(); }

    public void ReceiveSincronice(){

        String METHOD_NAME = "GetPlans";
        String SOAP_ACTION = "http://dvagreement.android.com/GetPlans";

        GetKSoap myTask = new GetKSoap(NAMESPACE, SOAP_ACTION, METHOD_NAME, URL, db, username, password, mPartnerDbHelper, loadingBox, this);

        Toast.makeText(this,"Iniciando Proceso de Sincronizacion hacia el Movil", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Sincronizando Planes", Toast.LENGTH_SHORT).show();
        myTask.execute(new String[]{db, "planes", username, password});
        Toast.makeText(this,"Finalizando Proceso de Sincronizacion hacia el Movil", Toast.LENGTH_SHORT).show();

    }


    // ACTUALIZA INFO
    private void UpdateTables(){
        new TablesSoap(this).execute();
    }

    public class TablesSoap extends AsyncTask<Void, Integer, String> {
        private Context mContext;

        public TablesSoap(Context context){
            this.mContext = context;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String response = mPartnerDbHelper.updateNaps();
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            showresult("Datos actualizados");
            Log.i("DV", "Sincronizacion de Datos finalizado..." );
            dismissLoadingBox(2000);
        }
    }

    private void uploadData(){
//        String[] mimetypes =
//                { "application/vnd.ms-excel",
//                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
//                };
        Intent intent = new Intent();;
        //intent.setType("*/*");
        intent.setType("text/xml");
        //intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Seleccione el archivo (xml)"), 40);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40 && resultCode == RESULT_OK ) {
            Log.d("TAG", "onActivityResult xml: " + data.getData());
            XmlPullParserFactory parserFactory;
//            LoadingBox loadingBox = new LoadingBox(this);
            loadingBox.startLoadingDialog();
            try {
                parserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserFactory.newPullParser();
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(inputStream, null);
                //processParsing(parser);
                ParseXML xml = new ParseXML(this, parser);
            }catch (XmlPullParserException e){
                Log.e(TAG, "Error XmlPullParserException: " + e.getMessage());
            }catch (IOException e){
                Log.e(TAG, "Error IOException: " + e.getMessage());
            }finally{
                dismissLoadingBox(5000);
            }
        }
    }

    public void getDatabase(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            createFolder();
        } else {
            ActivityCompat.requestPermissions(DbMainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ( requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
            createFolder();
        } else {
            Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void createFolder(){
        String nameFolder = "DVNET";
        boolean export = false;
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), nameFolder);
        if (dir.exists()) {
            export = true;
//            Toast.makeText(getApplicationContext(), "Directory is already exits", Toast.LENGTH_SHORT).show();
        } else {
            dir.mkdir();
            if ( dir.isDirectory() ) {
                export = true;
//                Toast.makeText(getApplicationContext(), "Directory is created", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(DbMainActivity.this);
                String msg = "Message: failed to create directory\n"+
                        "Path: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + nameFolder + "\n" +
                        "mkdir: " + dir.mkdir();
                builder.setMessage(msg);
                builder.show();
            }
        }

        if (export){
            exportDB(dir);
        }
    }

    private void exportDB(File dir) {
        // TODO Auto-generated method stub
        String nameDB = "DVNet.db";
        String project = "com.dvnet.agreement";
        try {
            File data = Environment.getDataDirectory();
            if (dir.canWrite()) {
                String currentDBPath= "//data//" + project + "//databases//" + nameDB;
                //String backupDBPath  = "/BackupFolder/DatabaseName";
                String backupDBPath  = "/" + nameDB;
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(dir, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), "Base de datos exportada en Documentos/DVNET", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, e.toString());
        }
    }

    public class GetDatabaseVersion extends AsyncTask<String, Void, String> {

        String METHOD_NAME = "HelloWorld";
        String SOAP_ACTION = "http://dvagreement.android.com/HelloWorld";

        private Context mContext;
        private int NOTIFICATION_ID = 1;
        private Notification mNotification;
        private NotificationManager mNotificationManager;



        public GetDatabaseVersion(Context context){
            this.mContext = context;
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        protected void onPreExecute() {
            // createNotification("Validando credenciales de acceso...","");
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());
//            String versionApp = getString(R.string.version_app);

            try {
                // Allow for debugging - needed to output the request
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.debug = true;
                androidHttpTransport.setReadTimeout(100000);
                System.setProperty("http.keepAlive", "false");

                androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                // Get the SoapResult from the envelope body.
                SoapObject result = (SoapObject) soapEnvelope.bodyIn;

                response = result.getProperty(0).toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Log.e("DVAgreement", "doInBackground: ", e );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String respons) {
            String mensaje;
            if (respons != null){
                mensaje="Comprobar version de app";
            } else {
                dismissLoadingBox(2000);
                mensaje="Error en Servidor comunique a su administrador";
            }
            showresult(mensaje);
        }

        private void createNotification(String contentTitle, String contentText) {

            //Build the notification using Notification.Builder
            Notification.Builder builder = new Notification.Builder(mContext)
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setAutoCancel(true)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText);

            //Get current notification
            mNotification = builder.getNotification();

            //Show the notification
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
    }

}

