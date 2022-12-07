package com.dvnet.agreement.partner;

import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dvnet.agreement.ConsultaCliente;
import com.dvnet.agreement.DbMainActivity;
import com.dvnet.agreement.GetData;
import com.dvnet.agreement.LoadingBox;
import com.dvnet.agreement.R;
import com.dvnet.agreement.data.Canton;
import com.dvnet.agreement.data.Company;
import com.dvnet.agreement.data.Direccion;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.Provincia;
import com.dvnet.agreement.data.TipoIdentificacion;
import com.dvnet.agreement.data.TipoServicio;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.ValidarIdentificacion;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static android.content.ContentValues.TAG;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class AddEditPartnerFragment  extends Fragment {

    private static final String ARG_PARTNER_ID = "arg_partner_id";
    private static final int RESULT_OK = -1;

    private String mPartnerId;
    private String mProvinciaId;
    private String mProvinciaName;
    private String mTypeidId;
    private String mTypeidName;
    private String mTypeDirId;
    private String mTypeDirName;
    private String mServiceId;
    private String mServiceName;
    private String lServiceId;
    private String mCantonId;
    private String mCantonName;
    private String lProvincia;
    private String lCanton;
    private String lTypeid;
    private String lTypeDirId;
    private String mUserId = null;
    private String mAppId = null;
    private String mStreet = null;
    private String validateID = "0";
    private byte[] mByteCasa;
    private String rutaImagenCasa;
    private String nombreCasa;
    private String actual_state;
    private String actual_used;

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;

    private PartnerDbHelper mPartnerDbHelper;
    private LoadingBox loadingBox;

    private FloatingActionButton mSaveButton;
    private Button mGetLocation, mValidateID, mFotoCasa, mCargarCasa;
    private ImageView mImageCasa;
//    private TextView mValidateTXT;
    private TextInputEditText mFirstLNField;
    private TextInputEditText mSecondLNField;
    private TextInputEditText mNameField;
    private TextInputEditText mIdentificationField;
    private TextInputLayout mUrbanizacion;
    private TextInputLayout mPrimaria;
    private TextInputLayout mBarrio;
    private TextInputLayout mSecundaria;
    private TextInputLayout mEtapa;
    private TextInputLayout mMz;
    private TextInputLayout mLote;
    private TextInputLayout mVilla;
    private TextInputLayout mSolar;
    private TextInputLayout mPiso;
    private TextInputLayout mDepar;
    private TextInputLayout mCnel;
    private TextInputLayout mSumAgua;
    private TextInputLayout mAgua;
    private TextInputLayout mOtros;
    private TextInputLayout mFirstLN;
    private TextInputLayout mSecondLN;
    private TextInputLayout mTypeOtros;
    private TextInputEditText mStreetUrbanizacion;
    private TextInputEditText mStreetEtapa;
    private TextInputEditText mStreetMz;
    private TextInputEditText mStreetVilla;
    private TextInputEditText mStreetBarrio;
    private TextInputEditText mStreetLote;
    private TextInputEditText mStreetSolar;
    private TextInputEditText mStreetPrincipal;
    private TextInputEditText mStreetSecundaria;
    private TextInputEditText mStreetPiso;
    private TextInputEditText mStreetDepar;
    private TextInputEditText mStreet2Field;
    private TextInputEditText mPhoneField;
    private TextInputEditText mMobileField;
    private TextInputEditText mEmailField;
    private TextInputEditText mCoordenadasField;
    private TextInputEditText mNacimientoField;
    private TextInputEditText mCod_CNELField;
    private TextInputEditText mSum_AGUAField;
    private TextInputEditText mCod_AGUAField;
    private TextInputEditText mType_OTROSField;
    private TextInputEditText mCod_OTROSField;
    private TextInputEditText mRefFamiliar;
    private TextInputEditText mNumRefFamiliar;
    private TextInputEditText mRefPersonal;
    private TextInputEditText mNumRefPersonal;
    private Spinner mProvincia;
    private Spinner mCanton;
    private Spinner mTypeId;
    private Spinner mTypeDir;
    private Spinner mService;


    public AddEditPartnerFragment() {
        // Required empty public constructor
    }

    public static AddEditPartnerFragment newInstance(String partnerId) {
        AddEditPartnerFragment fragment = new AddEditPartnerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARTNER_ID, partnerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPartnerId = getArguments().getString(ARG_PARTNER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_partner, container, false);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mGetLocation = (Button) root.findViewById(R.id.getLocation);
        mValidateID = (Button) root.findViewById(R.id.validateID);
//        mValidateTXT = (TextView) root.findViewById(R.id.validateTXT);
        mFirstLNField = (TextInputEditText) root.findViewById(R.id.p_firstln);
        mSecondLNField = (TextInputEditText) root.findViewById(R.id.p_secondln);
        mNameField = (TextInputEditText) root.findViewById(R.id.p_name);
        mIdentificationField = (TextInputEditText) root.findViewById(R.id.p_identificacion_id);
        mPrimaria = (TextInputLayout) root.findViewById(R.id.til_principal);
        mUrbanizacion = (TextInputLayout) root.findViewById(R.id.til_urbanizacion);
        mBarrio = (TextInputLayout) root.findViewById(R.id.til_barrio);
        mSecundaria = (TextInputLayout) root.findViewById(R.id.til_secundaria);
        mEtapa = (TextInputLayout) root.findViewById(R.id.til_etapa);
        mMz = (TextInputLayout) root.findViewById(R.id.til_mz);
        mLote = (TextInputLayout) root.findViewById(R.id.til_lote);
        mVilla = (TextInputLayout) root.findViewById(R.id.til_villa);
        mSolar = (TextInputLayout) root.findViewById(R.id.til_solar);
        mPiso = (TextInputLayout) root.findViewById(R.id.til_piso);
        mDepar = (TextInputLayout) root.findViewById(R.id.til_depar);
        mCnel = (TextInputLayout) root.findViewById(R.id.til_cod_cnel);
        mSumAgua = (TextInputLayout) root.findViewById(R.id.til_sum_agua);
        mAgua = (TextInputLayout) root.findViewById(R.id.til_cod_agua);
        mTypeOtros = (TextInputLayout) root.findViewById(R.id.til_tipo_otros);
        mOtros = (TextInputLayout) root.findViewById(R.id.til_otros);
        mFirstLN = (TextInputLayout) root.findViewById(R.id.til_firstln);
        mSecondLN = (TextInputLayout) root.findViewById(R.id.til_secondln);
        mStreetUrbanizacion = (TextInputEditText) root.findViewById(R.id.p_urbanizacion);
        mStreetEtapa = (TextInputEditText) root.findViewById(R.id.p_etapa);
        mStreetMz = (TextInputEditText) root.findViewById(R.id.p_mz);
        mStreetVilla = (TextInputEditText) root.findViewById(R.id.p_villa);
        mStreetBarrio = (TextInputEditText) root.findViewById(R.id.p_barrio);
        mStreetLote = (TextInputEditText) root.findViewById(R.id.p_lote);
        mStreetSolar = (TextInputEditText) root.findViewById(R.id.p_solar);
        mStreetPrincipal = (TextInputEditText) root.findViewById(R.id.p_principal);
        mStreetSecundaria = (TextInputEditText) root.findViewById(R.id.p_secundaria);
        mStreetPiso = (TextInputEditText) root.findViewById(R.id.p_piso);
        mStreetDepar = (TextInputEditText) root.findViewById(R.id.p_depar);
        mStreet2Field = (TextInputEditText) root.findViewById(R.id.p_street2);
        mPhoneField = (TextInputEditText) root.findViewById(R.id.p_phone);
        mMobileField = (TextInputEditText) root.findViewById(R.id.p_mobile);
        mEmailField = (TextInputEditText) root.findViewById(R.id.p_email);
        mCoordenadasField = (TextInputEditText) root.findViewById(R.id.p_coordenadas);
        mNacimientoField = (TextInputEditText) root.findViewById(R.id.p_nacimiento);
        mCod_CNELField = (TextInputEditText) root.findViewById(R.id.p_cod_cnel);
        mSum_AGUAField = (TextInputEditText) root.findViewById(R.id.p_sum_agua);
        mCod_AGUAField = (TextInputEditText) root.findViewById(R.id.p_cod_agua);
        mType_OTROSField = (TextInputEditText) root.findViewById(R.id.p_tipo_otros);
        mCod_OTROSField = (TextInputEditText) root.findViewById(R.id.p_otros);
        mProvincia = (Spinner) root.findViewById(R.id.p_provincia);
        mCanton = (Spinner) root.findViewById(R.id.p_canton);
        mRefFamiliar = (TextInputEditText) root.findViewById(R.id.p_ref_familiar);
        mNumRefFamiliar = (TextInputEditText) root.findViewById(R.id.p_num_ref_familiar);
        mRefPersonal = (TextInputEditText) root.findViewById(R.id.p_ref_personal);
        mNumRefPersonal = (TextInputEditText) root.findViewById(R.id.p_num_ref_personal);
        mTypeId = (Spinner) root.findViewById(R.id.a_spinner_identification);
        mTypeDir = (Spinner) root.findViewById(R.id.a_spinner_direccion);
        mService = (Spinner) root.findViewById(R.id.a_spinner_service);
        mFotoCasa = (Button) root.findViewById(R.id.foto_casa);
        mCargarCasa = (Button) root.findViewById(R.id.cargar_casa);
        mImageCasa = (ImageView) root.findViewById(R.id.imageViewCasa);

        mPartnerDbHelper = new PartnerDbHelper(getActivity());
        loadingBox = new LoadingBox(getActivity());

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditPartner();
            }
        });

        mGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
                } else {
                    if (!checkLocation()) {
                        return;
                    }
                    //locationManager.removeUpdates(locationListenerGPS);
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1 * 10 * 1000, 10,
                            locationListenerGPS);
                }
            }
        });

        mValidateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = mIdentificationField.getText().toString().replaceAll("\\s+", "").trim();
                    mIdentificationField.setText(id);
                    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                    if (isConnected) {
                        loadingBox.startLoadingDialog();
                        SincroniceIDOdoo();
                    } else {
                        showMsgDialogConnection();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Provincia gProvincia = (Provincia) parent.getItemAtPosition(pos);
                Log.i("ProvinciaId:", gProvincia.getId());

                if (!TextUtils.isEmpty(gProvincia.getId())) {
                    mProvinciaId = gProvincia.getId();
                    mProvinciaName = gProvincia.getname();
                    LoadCanton(gProvincia.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mCanton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Canton gCanton = (Canton) parent.getItemAtPosition(pos);
                Log.i("CantonId:", gCanton.getId());

                if (!TextUtils.isEmpty(gCanton.getId())) {
                    mCantonId = gCanton.getId();
                    mCantonName = gCanton.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
                return;
            }
        });

        mTypeId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TipoIdentificacion gType = (TipoIdentificacion) parent.getItemAtPosition(pos);
                Log.i("mTypeId:", gType.getId());

                if (!TextUtils.isEmpty(gType.getId())) {
                    mTypeidId = gType.getId();
                    mTypeidName = gType.getName();
                }

                if ( !( gType.getId().equals("05") || gType.getId().equals("04") ) ){
                    mValidateID.setVisibility(View.GONE);
                }else {
                    mValidateID.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mTypeDir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Direccion dir = (Direccion) parent.getSelectedItem();
                Log.i("Dir:", dir.getId());
                mUrbanizacion.setVisibility(View.GONE);
                mPrimaria.setVisibility(View.GONE);
                mBarrio.setVisibility(View.GONE);
                mSecundaria.setVisibility(View.GONE);
                mEtapa.setVisibility(View.GONE);
                mMz.setVisibility(View.GONE);
                mLote.setVisibility(View.GONE);
                mVilla.setVisibility(View.GONE);
                mSolar.setVisibility(View.GONE);
                mPiso.setVisibility(View.GONE);
                mDepar.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(dir.getId())) {
                    mTypeDirId = dir.getId();
                    mTypeDirName = dir.getName();
                }
                if(dir.getId().equals("Urbanizacion")){
                    mUrbanizacion.setVisibility(View.VISIBLE);
                    mEtapa.setVisibility(View.VISIBLE);
                    mMz.setVisibility(View.VISIBLE);
                    mVilla.setVisibility(View.VISIBLE);
                }
                if(dir.getId().equals("Cooperativa")){
                    mBarrio.setVisibility(View.VISIBLE);
                    mLote.setVisibility(View.VISIBLE);
                    mSolar.setVisibility(View.VISIBLE);
                }
                if(dir.getId().equals("Calle")){
                    mPrimaria.setVisibility(View.VISIBLE);
                    mSecundaria.setVisibility(View.VISIBLE);
                    mSolar.setVisibility(View.VISIBLE);
                }
                if(dir.getId().equals("Edificio")){
                    mPrimaria.setVisibility(View.VISIBLE);
                    mSecundaria.setVisibility(View.VISIBLE);
                    mPiso.setVisibility(View.VISIBLE);
                    mDepar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TipoServicio service = (TipoServicio) parent.getSelectedItem();
                Log.i("Service:", service.getId());
                mCnel.setVisibility(view.GONE);
                mSumAgua.setVisibility(view.GONE);
                mAgua.setVisibility(view.GONE);
                mOtros.setVisibility(view.GONE);
                mTypeOtros.setVisibility(view.GONE);
                if (!TextUtils.isEmpty(service.getId())) {
                    mServiceId = service.getId();
                    mServiceName = service.getname();
                }
                if(service.getId().equals("CNEL")){
                    mCnel.setVisibility(view.VISIBLE);
                    mAgua.setVisibility(view.VISIBLE);
                }
                if(service.getId().equals("AGUA")){
                    mSumAgua.setVisibility(view.VISIBLE);
                    mAgua.setVisibility(view.VISIBLE);
                }
                if(service.getId().equals("OTROS")){
                    mTypeOtros.setVisibility(view.VISIBLE);
                    mOtros.setVisibility(view.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        LoadCombos();

        mFotoCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()){
                    abrirCamaraCasa();
                }
            }
        });

        mCargarCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkExternalStoragePermission()){
                    cargarCasa();
                }
            }
        });

        // Carga de datos
        if (mPartnerId != null) {
            loadPartner();
        }

        return root;
    }

    private void loadPartner() {
        new GetPartnerByIdTask().execute();
    }

    private void LoadCombos() {
        ArrayList<Provincia> ProvinciaList = new ArrayList<>();
        ArrayList<TipoIdentificacion> TypeIdList = new ArrayList<>();

        Cursor mCursorProvincia = mPartnerDbHelper.getAllRows("tb_gen_provincia");
        Cursor mCursorIdentificacion = mPartnerDbHelper.getAllRows("tb_gen_identificacion");

        mCursorProvincia.moveToFirst();
        while (!mCursorProvincia.isAfterLast()) {
            ProvinciaList.add(new Provincia(
                    mCursorProvincia.getString(mCursorProvincia.getColumnIndex("id")),
                    mCursorProvincia.getString(mCursorProvincia.getColumnIndex("name"))));
            mCursorProvincia.moveToNext();
        }
        mCursorProvincia.close();
        ArrayAdapter<Provincia> adapterProvincia = new ArrayAdapter<Provincia>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ProvinciaList);
        mProvincia.setAdapter(adapterProvincia);

        if(lProvincia != null){
            mProvincia.setSelection(getIndex(mProvincia,lProvincia));
        }


        mCursorIdentificacion.moveToFirst();
        while (!mCursorIdentificacion.isAfterLast()) {
            TypeIdList.add(new TipoIdentificacion(
                    mCursorIdentificacion.getString(mCursorProvincia.getColumnIndex("id")),
                    mCursorIdentificacion.getString(mCursorProvincia.getColumnIndex("name"))));
            mCursorIdentificacion.moveToNext();
        }
        mCursorIdentificacion.close();
        //fill data in spinner Type id
        ArrayAdapter<TipoIdentificacion> adapterType = new ArrayAdapter<TipoIdentificacion>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TypeIdList);
        mTypeId.setAdapter(adapterType);

        if(lTypeid != null){
            mTypeId.setSelection(getIndex(mTypeId,lTypeid));
        }

        //fill data type direction
        ArrayList<Direccion> directionList = new ArrayList<>();
        directionList.add(new Direccion("Urbanizacion", "Urbanizacion/Ciudadela"));
        directionList.add(new Direccion("Cooperativa", "Barrio/Cooperativa"));
        directionList.add(new Direccion("Calle", "Calle"));
        directionList.add(new Direccion("Edificio", "Edificio"));
        //fill data in spinner direction
        ArrayAdapter<Direccion> adapterDir = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, directionList);
        mTypeDir.setAdapter(adapterDir);
        //mTypeDir.setSelection(adapter.getPosition(myItem));//Optional to set the selected item.
        if(lTypeDirId != null){
            mTypeDir.setSelection(getIndex(mTypeDir,lTypeDirId));
        }

        //fill data type services
        ArrayList<TipoServicio> serviceList = new ArrayList<>();
        serviceList.add(new TipoServicio("CNEL", "Electricidad"));
        serviceList.add(new TipoServicio("AGUA", "Agua"));
        serviceList.add(new TipoServicio("OTROS", "Otros"));
        //fill data in spinner service
        ArrayAdapter<TipoServicio> adapterServ = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, serviceList);
        mService.setAdapter(adapterServ);
        if(lServiceId != null){
            mService.setSelection(getIndex(mService,lServiceId));
        }

    }

    private void LoadCanton(String provincia) {
        ArrayList<Canton> CantonList = new ArrayList<>();

        Cursor mCursorCanton = mPartnerDbHelper.getRowsArgOrderby("tb_gen_canton", "provincia_id =?", provincia, "name");

        mCursorCanton.moveToFirst();
        while (!mCursorCanton.isAfterLast()) {
            CantonList.add(new Canton(
                    mCursorCanton.getString(mCursorCanton.getColumnIndex("id")),
                    mCursorCanton.getString(mCursorCanton.getColumnIndex("name")),
                    mCursorCanton.getString(mCursorCanton.getColumnIndex("provincia_id"))
                    ));
            mCursorCanton.moveToNext();
        }
        mCursorCanton.close();
        ArrayAdapter<Canton> adapterCanton = new ArrayAdapter<Canton>(getActivity(), android.R.layout.simple_spinner_dropdown_item, CantonList);
        mCanton.setAdapter(adapterCanton);

        if(lProvincia != null){
            mCanton.setSelection(getIndex(mCanton,lCanton));
        }

    }

    private void addEditPartner() {
        boolean error = false;
        GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();

        String id = "0";
        String used = "0";

        if(mPartnerId != null)
        {
            id = mPartnerId;
            if ( actual_used.equals("1") ) {
                used = "1";
            }
        }

        String identification = mIdentificationField.getText().toString().replaceAll("\\s+", " ").trim();
        String street2 = mStreet2Field.getText().toString().replaceAll("\\s+", " ").trim();
        String phone = mPhoneField.getText().toString();
        String mobile = mMobileField.getText().toString().replaceAll("\\s+", " ").trim();
        String email = mEmailField.getText().toString().replaceAll("\\s+", " ").trim();
        String nacimiento = mNacimientoField.getText().toString();
        String provincia_id =  mProvinciaId;
        String provincia =  mProvinciaName;
        String typeid_id =  mTypeidId;
        String typeid =  mTypeidName;
        String dir_id =  mTypeDirId;
        String dir_name =  mTypeDirName;
        String canton_id = mCantonId;
        String canton = mCantonName;
        String ref_familiar = mRefFamiliar.getText().toString();
        String num_ref_familiar = mNumRefFamiliar.getText().toString();
        String ref_personal = mRefPersonal.getText().toString();
        String num_ref_personal = mNumRefPersonal.getText().toString();
        String state_pendindg = "P";
        String service_id =  mServiceId;
        String service_name =  mServiceName;
        String cod_cnel;
        String cod_agua;
        String principal;
        String secundaria;
        String mz;
        String nro;
        String app_id;
        String refer = "0";
        String street;
        String venta = "VENTA CONCRETADA";

        byte[] fotoCasa = mByteCasa;
        String nombCasa = nombreCasa;

        String firstln = mFirstLNField.getText().toString().replaceAll("\\s+", " ").trim();
        String secondln = mSecondLNField.getText().toString().replaceAll("\\s+", " ").trim();
        String name = mNameField.getText().toString().replaceAll("\\s+", " ").trim();

        String coordenadas = mCoordenadasField.getText().toString().replaceAll("\\s+", "").trim();
        //String xy = mCoordenadasField.getText().toString();
//        if( !(coordenadas.isEmpty() || coordenadas == null) ){
//            String str1 = coordenadas.split(",")[0];
//            String str2 = coordenadas.split(",")[1];
//            coordenadas = str1.replaceAll("\\s+", "") + ", " + str2.replaceAll("\\s+", "");
//        }

        if( !(identification.isEmpty() || identification == null) ){
            identification = identification.replaceAll("\\s+", "");
        }

        if ( mTypeDirId.equals("Urbanizacion") ){
            principal = mStreetUrbanizacion.getText().toString().replaceAll("\\s+", " ").trim();
            secundaria = mStreetEtapa.getText().toString().replaceAll("\\s+", " ").trim();
            mz = mStreetMz.getText().toString().replaceAll("\\s+", " ").trim();
            nro = mStreetVilla.getText().toString().replaceAll("\\s+", " ").trim();
        }else if ( mTypeDirId.equals("Calle") ){
            principal = mStreetPrincipal.getText().toString().replaceAll("\\s+", " ").trim();
            secundaria = mStreetSecundaria.getText().toString().replaceAll("\\s+", " ").trim();
            mz = "";
            nro = mStreetSolar.getText().toString().replaceAll("\\s+", " ").trim();
        }else if ( mTypeDirId.equals("Cooperativa") ){
            principal = mStreetBarrio.getText().toString().replaceAll("\\s+", " ").trim();
            secundaria = "";
            mz = mStreetLote.getText().toString().replaceAll("\\s+", " ").trim();
            nro = mStreetSolar.getText().toString().replaceAll("\\s+", " ").trim();
        }else{
            principal = mStreetPrincipal.getText().toString().replaceAll("\\s+", " ").trim();
            secundaria = mStreetSecundaria.getText().toString().replaceAll("\\s+", " ").trim();
            mz = mStreetPiso.getText().toString().replaceAll("\\s+", " ").trim();
            nro = mStreetDepar.getText().toString().replaceAll("\\s+", " ").trim();
        }

        if (mServiceId.equals("OTROS")){
            cod_cnel = mType_OTROSField.getText().toString().replaceAll("\\s+", " ").trim();
            cod_agua = mCod_OTROSField.getText().toString().replaceAll("\\s+", " ").trim();
        }else if (mServiceId.equals("AGUA")){
            cod_cnel = mSum_AGUAField.getText().toString().replaceAll("\\s+", " ").trim();
            cod_agua = mCod_AGUAField.getText().toString().replaceAll("\\s+", " ").trim();
        }else {
            cod_cnel = mCod_CNELField.getText().toString().replaceAll("\\s+", " ").trim();
            cod_agua = mCod_AGUAField.getText().toString().replaceAll("\\s+", " ").trim();
        }

        String user_id = null;
        if (mUserId == null){
            user_id = globalUser.getData();
        } else {
            if (mUserId !=  globalUser.getData()){
                user_id = mUserId;
            }
        }

        if (mStreet == null){
            street = " ";
        }else{
            street = mStreet;
        }

        if (mAppId == null){
            app_id = "0";
        }else{
            app_id = mAppId;
        }

        Partner partner = new Partner(
                id,
                firstln,
                secondln,
                name,
                identification,
                principal,
                secundaria,
                mz,
                nro,
                street,
                street2,
                phone,
                mobile,
                email,
                coordenadas,
                cod_cnel,
                cod_agua,
                nacimiento,
                provincia_id,
                provincia,
                typeid_id,
                typeid,
                dir_id,
                dir_name,
                service_id,
                service_name,
                canton_id,
                canton,
                ref_familiar,
                num_ref_familiar,
                ref_personal,
                num_ref_personal,
                state_pendindg,
                used,
                user_id,
                venta,
                app_id,
                refer,
                validateID,
                fotoCasa,
                nombCasa
                );

        if ( mPartnerId != null && actual_state.equals("A") ){
            partner.setState("A");
        } else {
            String errors = null;
            errors = validatePartner(partner);
            if ( errors != null){
                partner.setState("B");
                Toast.makeText(getActivity(), errors, Toast.LENGTH_SHORT).show();
                //return;
            } else {
                partner.setState("P");
            }
        }

        new AddEditPartnerTask().execute(partner);
    }

    private String validatePartner(Partner partner) {
        String error = "Los siguientes datos son necesarios: \n";
        String errors = null;
        int contador = 0;

        if ( partner.getValidate().equals("0") ){
            if ( partner.getFirstln().isEmpty() || partner.getFirstln() == null) {
                error += "Primer Apellido \n";
                contador++;
            }
            if ( partner.getSecondln().isEmpty() || partner.getSecondln() == null) {
                error += "Segundo Apellido \n";
                contador++;
            }
        }
        if ( partner.getName().isEmpty() || partner.getName() == null)
        {
            error += "Nombre \n";
            contador++;
        }
        if ( partner.getIdentification_id().isEmpty() || partner.getIdentification_id() == null)
        {
            error += "Cedula / RUC \n";
            contador++;
        }else{
            if (partner.getTypeid_id().equals("05")){
                if (partner.getIdentification_id().length() != 10) {
                    error += "Cedula debe tener solo 10 dígitos\n";
                    contador++;
                }
            }
            if (partner.getTypeid_id().equals("04")){
                if (partner.getIdentification_id().length() != 13) {
                    error += "RUC debe tener 13 dígitos\n";
                    contador++;
                }
            }
        }

        if ( partner.getPrincipal().isEmpty() || partner.getPrincipal() == null || partner.getPrincipal().equals(" ") )
        {
            error += "Calle Principal / Ciudadela / Barrio\n";
            contador++;
        }
        if ( partner.getNro().isEmpty() || partner.getNro() == null)
        {
            error += "Villa/Solar/Departamento\n";
            contador++;
        }

        if ( partner.getTypedir_id().equals("Urbanizacion") ){
            if ( partner.getSecundaria().isEmpty() || partner.getSecundaria() == null) {
                error += "Calle Secundaria / Etapa\n";
                contador++;
            }
            if ( partner.getMz().isEmpty() || partner.getMz() == null){
                error += "Mz / Lote / Piso\n";
                contador++;
            }else{
                String street = "CIUDADELA " + partner.getPrincipal() + " ETAPA " + partner.getSecundaria() + " MZ " + partner.getMz() + " VILLA " + partner.getNro();
                Log.i("msg","urb street: " + street);
                partner.setStreet(street);
            }
        }
        if ( partner.getTypedir_id().equals("Calle") ){
            partner.setMz("NO");
            if ( partner.getSecundaria().isEmpty() || partner.getSecundaria() == null) {
                error += "Calle Secundaria / Etapa\n";
                contador++;
            }else{
                String street = partner.getPrincipal() + " Y " + partner.getSecundaria() + " NRO/SOLAR " + partner.getNro();
                partner.setStreet(street);
            }
        }
        if ( partner.getTypedir_id().equals("Edificio") ){
            if ( partner.getSecundaria().isEmpty() || partner.getSecundaria() == null) {
                error += "Calle Secundaria / Etapa\n";
                contador++;
            }
            if ( partner.getMz().isEmpty() || partner.getMz() == null){
                error += "Mz / Lote / Piso\n";
                contador++;
            }else{
                String street = partner.getPrincipal() + " Y " + partner.getSecundaria() + " PISO " + partner.getMz() + " DEPARTAMENTO " + partner.getNro();
                Log.i("msg","edific street: " + street);
                partner.setStreet(street);
            }
        }
        if ( partner.getTypedir_id().equals("Cooperativa") ){
            partner.setSecundaria("NO");
            if ( partner.getMz().isEmpty() || partner.getMz() == null){
                error += "Mz / Lote / Piso\n";
                contador++;
            }else{
                String street = "BARRIO/COOPERATIVA " + partner.getPrincipal() + " LOTE " + partner.getMz() + " NRO/SOLAR " + partner.getNro();
                Log.i("msg","coop street: " + street);
                partner.setStreet(street);
            }
        }
//        else{
//            error += "Direccion\n";
//            contador++;
//        }

        if ( partner.getMobile().isEmpty() || partner.getMobile() == null)
        {
            error += "Celular\n";
            contador++;
        }else if ( partner.getMobile().length() != 10){
            error += "Número de celular inválido\n";
            contador++;
        }

        if ( partner.getServiceId().equals("CNEL") ){
            if ( partner.getCod_cnel().isEmpty() || partner.getCod_cnel() == null)
            {
                error += "Codigo CNEL\n";
                contador++;
            }
            else
            {
                if ( partner.getCod_cnel().toUpperCase() == "S/N"   )
                {
                    error += "Codigo CNEL no puede ser S/N\n";
                    contador++;
                }

                boolean isNumeric = partner.getCod_cnel().chars().allMatch( Character::isDigit );
                String cod = partner.getCod_cnel();

                if ( isNumeric )
                {
                    Double a = Double.parseDouble(partner.getCod_cnel());
                    cod = a.toString();
                }
                if (cod.length() <= 4)
                {
                    error += "Codigo CNEL no puede ser menor a 6 digitos\n";
                    contador++;
                }
            }
            //AGUA//
            if ( partner.getCod_agua().isEmpty() || partner.getCod_agua() == null) {
                error += "Nro Medidor\n";
                contador++;
            }else {
                if ( partner.getCod_agua().toUpperCase() == "S/N" )
                {
                    error += "Codigo de Agua no puede ser S/N\n";
                    contador++;
                }
                boolean num_cod_agua = partner.getCod_agua().chars().allMatch( Character::isDigit );
                String agua = partner.getCod_agua();

                if ( num_cod_agua )
                {
                    Double a = Double.parseDouble(partner.getCod_agua());
                    agua = a.toString();
                }else{
                    error += "Número de medidor deben ser solo números \n";
                    contador++;
                }
                if (agua.length() < 5)
                {
                    error += "Número de medidor no puede ser menor a 5 digitos \n";
                    contador++;
                }
            }
        }else if ( partner.getServiceId().equals("AGUA") ){
            if ( partner.getCod_cnel().isEmpty() || partner.getCod_cnel() == null)
            {
                error += "Suministro agua\n";
                contador++;
            }
            if ( partner.getCod_agua().isEmpty() || partner.getCod_agua() == null) {
                error += "Nro Medidor\n";
                contador++;
            }else {
                if ( partner.getCod_agua().toUpperCase() == "S/N" )
                {
                    error += "Codigo de Agua no puede ser S/N\n";
                    contador++;
                }
                boolean num_cod_agua = partner.getCod_agua().chars().allMatch( Character::isDigit );
                String agua = partner.getCod_agua();

                if ( num_cod_agua )
                {
                    Double a = Double.parseDouble(partner.getCod_agua());
                    agua = a.toString();
                }else{
                    error += "Número de medidor deben ser solo números \n";
                    contador++;
                }
                if (agua.length() < 5)
                {
                    error += "Número de medidor no puede ser menor a 5 digitos \n";
                    contador++;
                }
            }
        }else if ( partner.getServiceId().equals("OTROS") ){
            if ( partner.getCod_cnel().isEmpty() || partner.getCod_cnel() == null) {
                error += "Codigo servicio\n";
                contador++;
            }
            if ( partner.getCod_agua().isEmpty() || partner.getCod_agua() == null) {
                error += "Tipo de servicio\n";
                contador++;
            }
        }else {
            error += "Codigo Servicio\n";
            contador++;
        }


        if ( partner.getCoordenadas().isEmpty() || partner.getCoordenadas() == null)
        {
            error += "Coordenadas\n";
            contador++;
        } else {
            //"^-[0-9]+\\.[0-9]+,\\s-[0-9]+\\.[0-9]+$"
            Pattern r=Pattern.compile("^-[0-9]+\\.[0-9]+,-[0-9]+\\.[0-9]+$", Pattern.CASE_INSENSITIVE);
            Matcher m=r.matcher(partner.getCoordenadas());
            if(!m.find()){
                error += "Formato inválido de las coordenadas\n";
                contador++;
            }
        }
        if ( partner.getProvincia_id().isEmpty() || partner.getProvincia_id() == null)
        {
            error += "Provincia\n";
            contador++;
        }
        if ( partner.getTypeid_id().isEmpty() || partner.getTypeid_id() == null)
        {
            error += "Tipo Identificacion\n";
            contador++;
        }
        if ( partner.getCanton().isEmpty() || partner.getCanton() == null)
        {
            error += "Canton\n";
            contador++;
        }
        if ( partner.getRef_familiar().isEmpty() || partner.getRef_familiar() == null)
        {
            error += "Referencia Familiar\n";
            contador++;
        }
        if ( partner.getNum_ref_familiar().isEmpty() || partner.getNum_ref_familiar() == null)
        {
            error += "Numero Referencia Familiar\n";
            contador++;
        }

        if ( !( partner.getRef_personal().isEmpty() || partner.getRef_personal() == null ) ) {
            if ( partner.getNum_ref_personal().isEmpty() || partner.getNum_ref_personal() == null ) {
                error += "Numero Referencia Personal\n";
                contador++;
            }
        }

        if ( !( partner.getNum_ref_personal().isEmpty() || partner.getNum_ref_personal() == null ) ) {
            if ( partner.getRef_personal().isEmpty() || partner.getRef_personal() == null ) {
                error += "Referencia Personal\n";
                contador++;
            }
        }

        if(contador>0)
        {
            errors = error;
        }

        return errors;
    }

    private void showPartnerScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showPartner(Partner partner) {
        mFirstLNField.setText(partner.getFirstln());
        mSecondLNField.setText(partner.getSecondln());
        mNameField.setText(partner.getName());
        mIdentificationField.setText(partner.getIdentification_id());
        actual_state = partner.getState();
        actual_used = partner.getUsed();
        mStreet2Field.setText(partner.getStreet2());
        mPhoneField.setText(partner.getPhone());
        mMobileField.setText(partner.getMobile());
        mEmailField.setText(partner.getEmail());
        mCoordenadasField.setText(partner.getCoordenadas());
        mNacimientoField.setText(partner.getNacimiento());
        mProvinciaId = partner.getProvincia_id();
        mProvinciaName = partner.getProvincia();
        lProvincia = partner.getProvincia();

        mTypeidId = partner.getTypeid_id();
        mTypeidName = partner.getTypeid();
        lTypeid = partner.getTypeid();

        mTypeDirId = partner.getTypedir_id();
        mTypeDirName = partner.getTypedir();
        lTypeDirId = partner.getTypedir_id();

        mServiceId = partner.getServiceId();
        mServiceName = partner.getService();
        lServiceId = partner.getServiceId();

        mCantonId = partner.getCanton_id();
        mCantonName = partner.getCanton();
        lCanton = partner.getCanton();

        mUserId = partner.getUser_id();
        mAppId = partner.getApp_id();
        mStreet = partner.getStreet();
        validateID = partner.getValidate();

        if ( hasBytes(partner.getFotoCasa()) ){
            mByteCasa = partner.getFotoCasa();
            nombreCasa = partner.getNombreCasa();
            Bitmap casa = BitmapFactory.decodeByteArray(mByteCasa, 0, mByteCasa.length);
            mImageCasa.setImageBitmap(casa);
        }

        if(partner.getProvincia_id() != null){
            mProvincia.setSelection(getIndex(mProvincia,partner.getProvincia()));

            if(partner.getCanton_id() != null){

                LoadCanton(partner.getProvincia_id());
                mCanton.setSelection(getIndex(mCanton,partner.getCanton()));
            }
        }

        if(partner.getTypeid_id() != null){
            mTypeId.setSelection(getIndex(mTypeId,partner.getTypeid()));
        }

        if(partner.getTypedir_id() != null){
            mTypeDir.setSelection(getIndex(mTypeDir,partner.getTypedir()));
            if ( partner.getTypedir_id().equals("Urbanizacion") ){
                mStreetUrbanizacion.setText(partner.getPrincipal());
                mStreetEtapa.setText(partner.getSecundaria());
                mStreetMz.setText(partner.getMz());
                mStreetVilla.setText(partner.getNro());
            }
            if ( partner.getTypedir_id().equals("Cooperativa") ){
                mStreetBarrio.setText(partner.getPrincipal());
                mStreetLote.setText(partner.getMz());
                mStreetSolar.setText(partner.getNro());
            }
            if ( partner.getTypedir_id().equals("Calle") ){
                mStreetPrincipal.setText(partner.getPrincipal());
                mStreetSecundaria.setText(partner.getSecundaria());
                mStreetSolar.setText(partner.getNro());
            }
            if( partner.getTypedir_id().equals("Edificio")){
                mStreetPrincipal.setText(partner.getPrincipal());
                mStreetSecundaria.setText(partner.getSecundaria());
                mStreetPiso.setText(partner.getMz());
                mStreetDepar.setText(partner.getNro());
            }
        }

        if(partner.getServiceId() != null){
            mService.setSelection(getIndex(mService,partner.getService()));
            if ( partner.getServiceId().equals("OTROS") ){
                mType_OTROSField.setText(partner.getCod_cnel());
                mCod_OTROSField.setText(partner.getCod_agua());
            }else if ( partner.getServiceId().equals("CNEL") ){
                mCod_CNELField.setText(partner.getCod_cnel());
                mCod_AGUAField.setText(partner.getCod_agua());
            }else {
                mSum_AGUAField.setText(partner.getCod_cnel());
                mCod_AGUAField.setText(partner.getCod_agua());
            }
        }

        mRefFamiliar.setText(partner.getRef_familiar());
        mNumRefFamiliar.setText(partner.getNum_ref_familiar());
        mRefPersonal.setText(partner.getRef_personal());
        mNumRefPersonal.setText(partner.getNum_ref_personal());

        if ( partner.getValidate().equals("1") ){
            mFirstLN.setVisibility(View.GONE);
            mSecondLN.setVisibility(View.GONE);
            mFirstLNField.setEnabled(false);
            mSecondLNField.setEnabled(false);
            mNameField.setEnabled(false);
            mIdentificationField.setEnabled(false);
            mTypeId.setEnabled(false);
        }

        if ( partner.getState().equals("A") && partner.getRefer().equals("0") ){
//            mSaveButton.setEnabled(false);
            mGetLocation.setEnabled(false);
//            mFirstLNField.setEnabled(false);
//            mSecondLNField.setEnabled(false);
//            mNameField.setEnabled(false);
            mIdentificationField.setEnabled(false);
            mStreetPrincipal.setEnabled(false);
            mStreetUrbanizacion.setEnabled(false);
            mStreetSecundaria.setEnabled(false);
            mStreetEtapa.setEnabled(false);
            mStreetMz.setEnabled(false);
            mStreetVilla.setEnabled(false);
            mStreetDepar.setEnabled(false);
            mStreetSolar.setEnabled(false);
            mStreetLote.setEnabled(false);
            mStreetBarrio.setEnabled(false);
            mStreetPiso.setEnabled(false);
            mStreet2Field.setEnabled(false);
            mPhoneField.setEnabled(false);
            mMobileField.setEnabled(false);
            mEmailField.setEnabled(false);
            mCoordenadasField.setEnabled(false);
            mNacimientoField.setEnabled(false);
            mCod_CNELField.setEnabled(false);
            mSum_AGUAField.setEnabled(false);
            mCod_AGUAField.setEnabled(false);
            mType_OTROSField.setEnabled(false);
            mCod_OTROSField.setEnabled(false);
//            mProvincia.setEnabled(false);
//            mCanton.setEnabled(false);
            mTypeId.setEnabled(false);
            mTypeDir.setEnabled(false);
            mService.setEnabled(false);
            mRefFamiliar.setEnabled(false);
            mNumRefFamiliar.setEnabled(false);
            mRefPersonal.setEnabled(false);
            mNumRefPersonal.setEnabled(false);
            mValidateID.setEnabled(false);
            mFotoCasa.setEnabled(false);
            mCargarCasa.setEnabled(false);
        }
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar", Toast.LENGTH_SHORT).show();
    }

    private class GetPartnerByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mPartnerDbHelper.getPartnerById(mPartnerId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showPartner(new Partner(cursor));
                cursor.close();
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }
    }

    private class AddEditPartnerTask extends AsyncTask<Partner, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Partner... partner) {
            if (mPartnerId != null) {
                return mPartnerDbHelper.updatePartner(partner[0], mPartnerId) > 0;
            } else {
                return mPartnerDbHelper.savePartner(partner[0]) > 0;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showPartnerScreen(result);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            loadingBox.startLoadingDialog();
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            mCoordenadasField.setText(latitudeGPS + "," + longitudeGPS);
            Toast.makeText(getActivity(), "GPS Provider update", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingBox.dismissDialog();
                }
            }, 3000);

//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mCoordenadasField.setText(latitudeGPS + " , " + longitudeGPS);
//                    Toast.makeText(getActivity(), "GPS Provider update", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };

    private void showMsgDialogConnection() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this.getActivity());
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

    private void validateIDOdoo(Partner partnerbyid) {
        // establish AlertDialog Constructors Builder object ,AlertDialog It is recommended to use android.support.v7.app Under bag .
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        // Set dialog title
        builder.setTitle(" Cargar datos del cliente ");
        // Set the prompt message
        builder.setMessage("La identificación "+ partnerbyid.getIdentification_id() + " cuenta con datos en el sistema.\n" +
                "Desea importar datos del cliente?");
        // Settings dialog icon
        builder.setIcon(R.mipmap.ic_launcher);
        // Add OK button
        builder.setPositiveButton(" Aceptar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
                //loadingBox.startLoadingDialog();
                validateID = "1";
                mNameField.setText(partnerbyid.getName());
                mTypeidId = partnerbyid.getTypeid_id();
                mIdentificationField.setText(partnerbyid.getIdentification_id());
                mCantonName = partnerbyid.getCanton();
                lCanton = partnerbyid.getCanton();
                mProvinciaName = partnerbyid.getProvincia();
                lProvincia = partnerbyid.getProvincia();
                if (!partnerbyid.getPhone().equals("False")){
                    mPhoneField.setText(partnerbyid.getPhone());
                }
                if (!partnerbyid.getMobile().equals("False")){
                    mMobileField.setText(partnerbyid.getMobile());
                }
                mFirstLN.setVisibility(View.GONE);
                mFirstLNField.setText("");
                mSecondLNField.setText("");
                mSecondLN.setVisibility(View.GONE);
                mNameField.setEnabled(false);
                mIdentificationField.setEnabled(false);
                mTypeId.setEnabled(false);

                if(lProvincia != null){
                    mProvincia.setSelection(getIndex(mProvincia,lProvincia));
                    mCanton.setSelection(getIndex(mCanton,lCanton));
                }
                if(partnerbyid.getIdentification_id().length()>10){
                    mTypeId.setSelection(getIndex(mTypeId,"RUC"));
                }else{
                    mTypeId.setSelection(getIndex(mTypeId,"Cédula"));
                }

                Toast.makeText(getActivity(), "Datos cargados exitosamente...", Toast.LENGTH_SHORT).show();

            }
        });
        // Add cancel button
        builder.setNegativeButton(" Cancelar ", null);
        // Create and display dialog boxes
        builder.show();
    }

    public boolean checkCameraPermission() {
        boolean permission = true;
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("TAG", "Permission not granted CAMERA.");
            Toast.makeText(getActivity(),"Permisos no otorgados para acceder a la camara", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        226);
            }
            permission = false;
        }
        return permission;
    }

    private void abrirCamaraCasa(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagenCasa();
        }catch (IOException e){
            Log.e("Error casa", e.toString());
        }
        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getActivity(), "com.dvnet.agreement.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 5);
        }
    }

    private File crearImagenCasa() throws IOException {
        String nombreImage = "fot_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreImage, ".jpg", dir);
        //Log.e("foto casa", "name: " + image.getName());
        nombreCasa = image.getName();
        rutaImagenCasa = image.getAbsolutePath();
        return image;
    }

    private BitmapFactory.Options options(){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;  //4 se lo reduce mas
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[16 * 1024];
        return options;
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60 , baos);
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return baos.toByteArray();
        }
    }

    private void cargarCasa(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 10);
    }

    private void cargarImagenCasa(Intent data){
        try {
            File photoFile = null;
            try {
                photoFile = crearImagenCasa();
            } catch (IOException ex) {
                Log.d("TAG", "Error occurred while creating the file");
            }
            InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
            FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
            copyStream(inputStream, fileOutputStream);
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            Log.d("TAG", "onActivityResult: " + e.toString());
        }
    }

    public boolean checkExternalStoragePermission() {
        boolean permission = true;
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("TAG", "Permission not granted READ_EXTERNAL_STORAGE.");
            Toast.makeText(getActivity(),"Permisos no otorgados para acceder a los archivos", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this.getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        227);
            }
            permission = false;
        }
        return permission;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 5 && resultCode == RESULT_OK ) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCasa, options());
            mImageCasa.setImageBitmap(imgBitmap);
            mByteCasa = bitmapToByte(imgBitmap);
        }
        else if( requestCode == 10 && resultCode == RESULT_OK ) {
            cargarImagenCasa(data);
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCasa, options());
            mImageCasa.setImageBitmap(imgBitmap);
            mByteCasa = bitmapToByte(imgBitmap);
        }
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private static boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

    private void SincroniceIDOdoo(){
        SendId();
    }

    public void SendId(){
        new IdSoap().execute("user");
    }

    public class IdSoap extends AsyncTask<String, Integer, Partner> {
        String METHOD_NAME = "GetPartner";
        String SOAP_ACTION = "http://dvagreement.android.com/GetPartner";
        String NAMESPACE = "http://dvagreement.android.com/";
        String URL = "http://45.170.44.3:15002/dvagreement.asmx";
        String db = "dvtelevision";
//        String URL = "http://192.168.0.118:8070/dvagreement.asmx";
//        String db = "dvtv";
        //mIdentificationField

        public IdSoap(){}

        @Override
        protected Partner doInBackground(String... params) {
            Partner response = null;
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            Cursor cuser = mPartnerDbHelper.getUserById(globalUser.getData());

            cuser.moveToFirst();
            User u = new User(cuser);
            cuser.close();
            String identi = mIdentificationField.getText().toString();
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("dbname", db);
            Request.addProperty("user", u.getUser());
            Request.addProperty("pass", u.getPass());
            Request.addProperty("valor", identi);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            Log.i("DVAgreement", "doInBackground: " + soapEnvelope.bodyOut.toString());

            try {
                // Allow for debugging - needed to output the request
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.debug = true;
//                androidHttpTransport.setReadTimeout(40000);

                androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

                // Get the SoapResult from the envelope body.
                SoapObject result = (SoapObject) soapEnvelope.bodyIn;
                System.out.println("********Response : " + result.toString());
                //response = result.getProperty(0).toString();
                SoapObject root = (SoapObject) result.getProperty(0);
                System.out.println("********Count : "+ root.getPropertyCount());

                if ( Integer.parseInt(root.getProperty("Id").toString())>0 && root.getProperty("Id").toString().length()>0 ) {
                    String Parner_Id = root.getProperty("Id").toString();
                    String Name = root.getProperty("Name").toString();
                    String Identification_type = root.getProperty("Identification_type").toString();
                    String Identification_id = root.getProperty("Identification_id").toString();
                    String Canton = root.getProperty("Canton").toString();
                    String Provincia = root.getProperty("Provincia").toString();
                    String Phone = root.getProperty("Phone").toString();
                    String Mobile = root.getProperty("Mobile").toString();
                    Partner partner = new Partner(
                            Parner_Id,
                            "",
                            "",
                            Name,
                            Identification_id,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            Phone,
                            Mobile,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            Provincia,
                            Identification_type,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            Canton,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            null,
                            ""
                    );
                    response = partner;
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
        protected void onPostExecute(Partner respons) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingBox.dismissDialog();
                }
            }, 3000);
            if (respons != null){
                validateIDOdoo(respons);
            }else{
                Toast.makeText(getActivity(), "La identificación no esta en el sistema", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean ValidaIdentificacion(String identificacion) throws Exception {

        boolean valida = false;
//        boolean valida = true;

        if(identificacion.length() == 10){
            valida = ValidarIdentificacion.getInstance().validarCedula(identificacion);
        }
        else if (identificacion.length() == 13){
            if(ValidarIdentificacion.getInstance().validarRucPersonaNatural(identificacion)){
                valida = true;
            }else if (ValidarIdentificacion.getInstance().validarRucSociedadPublica(identificacion)){
                valida = true;
            } else {
                valida = ValidarIdentificacion.getInstance().validarRucSociedadPrivada(identificacion);
            }
//            valida = ValidarIdentificacion.getInstance().validarRucPersonaNatural(identificacion);
//            valida = ValidarIdentificacion.getInstance().validarRucSociedadPrivada(identificacion);
        }

        return valida;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
        locationManager.removeUpdates(locationListenerGPS);
        locationManager = null;
//        locationListenerGPS.
//        locationManager
    }

}
