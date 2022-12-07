package com.dvnet.agreement.agreement;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dvnet.agreement.AgreementPrint;
import com.dvnet.agreement.ConsultaCliente;
import com.dvnet.agreement.DbMainActivity;
import com.dvnet.agreement.GetData;
import com.dvnet.agreement.R;
import com.dvnet.agreement.SingActivity;
import com.dvnet.agreement.data.Autorizacion;
import com.dvnet.agreement.data.Company;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.NAP;
import com.dvnet.agreement.data.Poste;
import com.dvnet.agreement.data.Provincia;
import com.dvnet.agreement.data.Sector;
import com.dvnet.agreement.data.Smart;
import com.dvnet.agreement.data.Tecnologia;
import com.dvnet.agreement.data.TipoServicio;
import com.dvnet.agreement.data.TipoVivienda;
import com.dvnet.agreement.data.agreement.Agremeent;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.AddEditPartnerActivity;
import com.dvnet.agreement.partner.AddEditPartnerFragment;
import com.dvnet.agreement.partner.PartnerActivity;
import com.dvnet.agreement.partner.PartnerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEditAgreementFragment extends Fragment implements PaymentFragment.PaymentDialogListener {
    private static final String ARG_AGREEMENT_ID = "arg_agreement_id";
    private static final String ARG_PARTNER_ID = "arg_partner_id";
    private static final int RESULT_OK = -1;

    private String mAgreementId;
    private String mPartnerId;

    private String mCompanyId;
    private String mCompanyName;
    private String lCompany;
    private String mTipoServicioId = "0";
    private String mTipoServicioName;
    private String lTipoServicio;
    private String mPlanId;
    private String mPlanName;
    private String lPlan;
    private String mSmartId;
    private String mSmartName;
    private String lSmart;
    private String mCantonId = "0";
    private String mCantonName;
    private String lCanton;
    private String mSectorId = "0";
    private String mSectorName;
    private String lSector;
    private String mPosteId;
    private String mPosteName;
    private String lPoste;
    private String mNapId;
    private String mNapName;
    private String lNap;
    private String mTipoViviendaId;
    private String mTipoViviendaName;
    private String lTipoVivienda;
    private String mAutorizacionId;
    private String mAutorizacionName;
    private String lAutorizacion;
    private String mContratoSelected;
    private String actual_state;
    private byte[] mFirma;
    private boolean validateContract = false;
    private boolean clientActivo = false;

    private byte[] mByteCedula;
    private String rutaImagenCedula;
    private String nombreCedula;

    private byte[] mByteCedulaPost;
    private String rutaImagenCedulaPost;
    private String nombreCedulaPost;

    private byte[] mBytePlanilla;
    private String rutaImagenPlanilla;
    private String nombrePlanilla;

    private byte[] mByteCasa;
    private String rutaImagenCasa;
    private String nombreCasa;

    private byte[] mBytePDF;
    private String nombrePDF;
    private String rutaContractoPDF;

    private String mRecibov = "0";
    private Double mValorv = 0.00;
    private String mUserId = null;


    private FloatingActionButton mSaveButton;
    private TextView mPartnerName;
    private TextView mPartnerID;
    private Button mGetPartner;
    private Button mButtonCedula;
    private Button mCargarCedula;
    private Button mPDFCedula;
    private Button mButtonCedulaPost;
    private Button mCargarCedulaPost;
    private Button mButtonPlanilla;
    private Button mCargarPlanilla;
    private Button mPDFPlanilla;
    private Button mButtonCasa;
    private Button mCargarCasa;
    private Button mButtonContrato;
    //private TextView mTextCedula;
    //private TextView mTextPlanilla;
    private ImageView mImageCedula;
    private ImageView mImageCedulaPost;
    private ImageView mImagePlanilla;
    private ImageView mImageCasa;
    private TextView mTextoContrato;
    private Spinner mCompany;
    private Spinner mTiposervicio;
    private Spinner mPlan;
    private Spinner mSmart;
    private Spinner mSector;
    private Spinner mPoste;
    private Spinner mNap;
    private Spinner mTipoVivienda;
    private Spinner mAutorizacion;
    private Spinner mTipoContrato;
    private Button mSign;
    private Button mPDF;
    private Button mPayNow;
    private String mAgreementPrint;
    private TextInputEditText mRecive;
    private TextInputEditText mValor;
    private TextInputEditText mObservacion;
    private TextInputEditText mContrato;
    private TextView mSmartView;

    //android:layout_toRightOf="@+id/s_save"

    private PartnerDbHelper mAgreementDbHelper;

    public AddEditAgreementFragment() {
        // Required empty public constructor
    }

    public static AddEditAgreementFragment newInstance(String agreementId) {
        AddEditAgreementFragment fragment = new AddEditAgreementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AGREEMENT_ID, agreementId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAgreementId = getArguments().getString(ARG_AGREEMENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_agreement, container, false);

        mAgreementDbHelper = new PartnerDbHelper(getActivity());

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_agreement);
        mSign = (Button) getActivity().findViewById(R.id.a_sign);
        mPDF = (Button) getActivity().findViewById(R.id.a_pdf);
        mGetPartner = (Button) root.findViewById(R.id.a_search);
        mButtonCedula = (Button) root.findViewById(R.id.button_cedula);
        mCargarCedula = (Button) root.findViewById(R.id.cargar_cedula);
        mPDFCedula = (Button) root.findViewById(R.id.pdf_cedula);
        mButtonCedulaPost = (Button) root.findViewById(R.id.button_cedula_post);
        mCargarCedulaPost = (Button) root.findViewById(R.id.cargar_cedula_post);
        mButtonPlanilla = (Button) root.findViewById(R.id.button_planilla);
        mCargarPlanilla = (Button) root.findViewById(R.id.cargar_planilla);
        mPDFPlanilla = (Button) root.findViewById(R.id.pdf_planilla);
        mButtonCasa = (Button) root.findViewById(R.id.foto_casa);
        mCargarCasa = (Button) root.findViewById(R.id.cargar_casa);
        mButtonContrato = (Button) root.findViewById(R.id.cargar_contrato);
        //mTextCedula = (TextView) root.findViewById(R.id.text_docCedula);
        mImageCedula = (ImageView) root.findViewById(R.id.imageViewCedula);
        mImageCedulaPost = (ImageView) root.findViewById(R.id.imageViewCedulaPost);
        //mTextPlanilla = (TextView) root.findViewById(R.id.text_docPlanilla);
        mImagePlanilla = (ImageView) root.findViewById(R.id.imageViewPlanilla);
        mImageCasa = (ImageView) root.findViewById(R.id.imageViewCasa);
        mTextoContrato = (TextView) root.findViewById(R.id.text_nombreContrato);
        mPayNow = (Button) root.findViewById(R.id.a_paynow);
        mPartnerID = (TextView) root.findViewById(R.id.a_partner_id);
        mPartnerName = (TextView) root.findViewById(R.id.a_partner);
        mCompany = (Spinner) root.findViewById(R.id.a_spinner_company);
        mTiposervicio = (Spinner) root.findViewById(R.id.a_spinner_tiposervicio);
        mPlan = (Spinner) root.findViewById(R.id.a_spinner_tecnologia);
        mSmart = (Spinner) root.findViewById(R.id.a_spinner_smart);
        mSector = (Spinner) root.findViewById(R.id.a_spinner_sector);
        mPoste = (Spinner) root.findViewById(R.id.a_spinner_poste);
        mTipoVivienda = (Spinner) root.findViewById(R.id.a_spinner_tipovivienda);
        mAutorizacion = (Spinner) root.findViewById(R.id.a_spinner_autorizacion);
        mNap = (Spinner) root.findViewById(R.id.a_spinner_nap);
        mTipoContrato = (Spinner) root.findViewById(R.id.a_spinner_tipocontrato);
        mSmartView = (TextView) root.findViewById(R.id.txt_til_smart);

        mRecive = (TextInputEditText) root.findViewById(R.id.a_recibes);
        mValor = (TextInputEditText) root.findViewById(R.id.a_valors);

        mObservacion = (TextInputEditText) root.findViewById(R.id.a_observacion);
        mContrato = (TextInputEditText) root.findViewById(R.id.a_contrato);

        mValor.setEnabled(false);
        mRecive.setEnabled(false);

        mButtonCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()){
                    abrirCamaraCedula();
                }
            }
        });

        mCargarCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkExternalStoragePermission()){
                    cargarCedula();
                }
            }
        });

        mPDFCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pdfCedula(); }
        });

        mButtonCedulaPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()){
                    abrirCamaraCedulaPost();
                }
            }
        });

        mCargarCedulaPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkExternalStoragePermission()){
                    cargarCedulaPost();
                }
            }
        });

        mButtonPlanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()){
                    abrirCamaraPlanilla();
                }
            }
        });

        mCargarPlanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkExternalStoragePermission()){
                    cargarPlanilla();
                }
            }
        });

        mPDFPlanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pdfPlanilla(); }
        });

        mButtonCasa.setOnClickListener(new View.OnClickListener() {
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

        mButtonContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditAgreement();
            }
        });

        mGetPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchPartnerActivity.class);
                startActivityForResult(intent, PartnerFragment.REQUEST_UPDATE_DELETE_PARTNER);
            }
        });

        mPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneratePayment();
            }
        });

        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgreementSing(mAgreementId);
            }
        });

        mPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAgreementId != null) {
                    try {
                        Cursor c = new AddEditAgreementFragment.GetAgreementByIdTask().execute().get();
                        if (c != null && c.moveToLast()) {
                            c.moveToFirst();
                            Agremeent ag = new Agremeent(c);
                            String msg = validateAgreement(ag);
                            if ( msg==null ) {
                                showAgreementPrint(mAgreementId);
                            } else {
                                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Error async", e.toString());
                    }
                }
            }
        });

        mCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                Company gCompany = (Company) parent.getItemAtPosition(pos);
                Log.i("mCompanyId:", gCompany.getId());

                if (!TextUtils.isEmpty(gCompany.getId())) {
                    mCompanyId = gCompany.getId();
                    mCompanyName = gCompany.getname();
                    LoadAutorizacion(mCompanyId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mTiposervicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                TipoServicio gTipoServicio = (TipoServicio) parent.getItemAtPosition(pos);
                Log.i("mTTipoServicioId:", gTipoServicio.getId());

                if (!TextUtils.isEmpty(gTipoServicio.getId())) {
                    mTipoServicioId = gTipoServicio.getId();
                    mTipoServicioName = gTipoServicio.getname();
                    LoadPlan(mTipoServicioId);
                    if (mTipoServicioId.equals("smart")) {
                        mSmartView.setVisibility(View.GONE);
                        mSmart.setVisibility(View.GONE);
                        mSmart.setSelection(0);
                    } else {
                        mSmartView.setVisibility(View.VISIBLE);
                        mSmart.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mPlan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Tecnologia gPlan = (Tecnologia) parent.getItemAtPosition(pos);
                Log.i("mPlanId:", gPlan.getId());

                if (!TextUtils.isEmpty(gPlan.getId())) {
                    mPlanId = gPlan.getId();
                    mPlanName = gPlan.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mSmart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Smart gSmart = (Smart) parent.getItemAtPosition(pos);
                Log.i("mSmartId:", gSmart.getId());

                if (!TextUtils.isEmpty(gSmart.getId())) {
                    mSmartId = gSmart.getId();
                    mSmartName = gSmart.getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mSector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                Sector gSector = (Sector) parent.getItemAtPosition(pos);
                Log.i("mSectorId:", gSector.getId());

                if (!TextUtils.isEmpty(gSector.getId())) {
                    mSectorId = gSector.getId();
                    mSectorName = gSector.getname();
                    LoadPoste(mSectorId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mPoste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Poste gPoste = (Poste) parent.getItemAtPosition(pos);
                Log.i("mPosteId:", gPoste.getId());

                if (!TextUtils.isEmpty(gPoste.getId())) {
                    mPosteId = gPoste.getId();
                    mPosteName = gPoste.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mNap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                NAP gNap = (NAP) parent.getItemAtPosition(pos);
                Log.i("mNapId:", gNap.getId());

                if (!TextUtils.isEmpty(gNap.getId())) {
                    mNapId = gNap.getId();
                    mNapName = gNap.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mAutorizacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                Autorizacion gAutorizacion = (Autorizacion) parent.getItemAtPosition(pos);
                Log.i("mAutorizacionId:", gAutorizacion.getId());

                if (!TextUtils.isEmpty(gAutorizacion.getId())) {
                    mAutorizacionId = gAutorizacion.getId();
                    mAutorizacionName = gAutorizacion.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mTipoVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                TipoVivienda gTipoVivienda = (TipoVivienda) parent.getItemAtPosition(pos);
                Log.i("mTipoViviendaId:", gTipoVivienda.getId());

                if (!TextUtils.isEmpty(gTipoVivienda.getId())) {
                    mTipoViviendaId = gTipoVivienda.getId();
                    mTipoViviendaName = gTipoVivienda.getname();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        mTipoContrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Log.i("mTipoContrato: ", "" + parent.getItemAtPosition(pos));
                mContratoSelected = (String) parent.getItemAtPosition(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });

        setData();

        // Carga de datos
        if (mAgreementId != null) {
            loadAgreement();
        }

        return root;
    }

    private void LoadPlan(String tiposervicio_id){
        ArrayList<Tecnologia> TecnologiaList = new ArrayList<>();
        Cursor mCursorTecnologia = mAgreementDbHelper.getAllRowsby("tb_gen_tecnologia","type_service =?", tiposervicio_id);

        mCursorTecnologia.moveToFirst();
        while (!mCursorTecnologia.isAfterLast()) {
            TecnologiaList.add(
                    new Tecnologia(
                            mCursorTecnologia.getString(mCursorTecnologia.getColumnIndex("id")),
                            mCursorTecnologia.getString(mCursorTecnologia.getColumnIndex("name")),
                            mCursorTecnologia.getString(mCursorTecnologia.getColumnIndex("type_service")),
                            mCursorTecnologia.getString(mCursorTecnologia.getColumnIndex("active"))
                    ));
            mCursorTecnologia.moveToNext();
        }

        //fill data in spinner Tecnologia
        ArrayAdapter<Tecnologia> adapterTecnologia = new ArrayAdapter<Tecnologia>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TecnologiaList);
        mPlan.setAdapter(adapterTecnologia);

        if(lPlan != null){
            mPlan.setSelection(getIndex(mPlan, lPlan));
        }
        mCursorTecnologia.close();
    }

    private void LoadSector(String canton_id){
        ArrayList<Sector> SectorList = new ArrayList<>();
        Cursor mCursorSector = mAgreementDbHelper.getAllRowsby("tb_gen_sector", "canton_id =?", canton_id);

        mCursorSector.moveToFirst();
        while (!mCursorSector.isAfterLast()) {
            SectorList.add(new Sector(
                    mCursorSector.getString(mCursorSector.getColumnIndex("id")),
                    mCursorSector.getString(mCursorSector.getColumnIndex("name")),
                    mCursorSector.getString(mCursorSector.getColumnIndex("canton_id"))
            ));
            mCursorSector.moveToNext();
        }

        //fill data in spinner Sector
        ArrayAdapter<Sector> adapterSector = new ArrayAdapter<Sector>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SectorList);
        mSector.setAdapter(adapterSector);

        if (lSector != null){
            mSector.setSelection(getIndex(mSector, lSector));
        }
        mCursorSector.close();
    }

    private void LoadNap(String canton_id){
        ArrayList<NAP> NapList = new ArrayList<>();
        //Cursor mCursorNap = mAgreementDbHelper.getAllRowsby("tb_gen_nap", "canton_id =?", canton_id);
        Cursor mCursorNap = mAgreementDbHelper.getNapbyCanton(canton_id);

        mCursorNap.moveToFirst();
        while (!mCursorNap.isAfterLast()) {
            NapList.add(new NAP(
                    mCursorNap.getString(mCursorNap.getColumnIndex("id")),
                    mCursorNap.getString(mCursorNap.getColumnIndex("name")),
                    mCursorNap.getString(mCursorNap.getColumnIndex("canton_id"))
            ));
            mCursorNap.moveToNext();
        }

        //fill data in spinner Sector
        ArrayAdapter<NAP> adapterNap = new ArrayAdapter<NAP>(getActivity(), android.R.layout.simple_spinner_dropdown_item, NapList);
        mNap.setAdapter(adapterNap);

        if (lNap != null){
            mNap.setSelection(getIndex(mNap, lNap));
        }
        mCursorNap.close();
    }

    private void LoadPoste(String sector_id){
        ArrayList<Poste> PosteList = new ArrayList<>();
        Cursor mCursorPoste = mAgreementDbHelper.getAllRowsby("tb_gen_poste", "sector_id =?", sector_id);

        mCursorPoste.moveToFirst();
        while (!mCursorPoste.isAfterLast()) {
            PosteList.add(new Poste(
                    mCursorPoste.getString(mCursorPoste.getColumnIndex("id")),
                    mCursorPoste.getString(mCursorPoste.getColumnIndex("name")),
                    mCursorPoste.getString(mCursorPoste.getColumnIndex("sector_id"))
            ));
            mCursorPoste.moveToNext();
        }

        //fill data in spinner Poste
        ArrayAdapter<Poste> adapterPoste = new ArrayAdapter<Poste>(getActivity(), android.R.layout.simple_spinner_dropdown_item, PosteList);
        mPoste.setAdapter(adapterPoste);

        if (lPoste != null){
            mPoste.setSelection(getIndex(mPoste, lPoste));
        }
        mCursorPoste.close();
    }

    private void LoadAutorizacion(String company_id){
        ArrayList<Autorizacion> AutorizacionList = new ArrayList<>();
        Cursor mCursorAutorizacion = mAgreementDbHelper.getAllRowsby("tb_gen_autorizacion", "company_id =?", company_id);

        mCursorAutorizacion.moveToFirst();
        while (!mCursorAutorizacion.isAfterLast()) {
            AutorizacionList.add(
                    new Autorizacion(
                            mCursorAutorizacion.getString(mCursorAutorizacion.getColumnIndex("id")),
                            mCursorAutorizacion.getString(mCursorAutorizacion.getColumnIndex("name")),
                            mCursorAutorizacion.getString(mCursorAutorizacion.getColumnIndex("company_id"))
                    ));
            mCursorAutorizacion.moveToNext();
        }

        //fill data in spinner Tecnologia
        ArrayAdapter<Autorizacion> adapterAutorizacion = new ArrayAdapter<Autorizacion>(getActivity(), android.R.layout.simple_spinner_dropdown_item, AutorizacionList);
        mAutorizacion.setAdapter(adapterAutorizacion);

        if (lAutorizacion != null){
            mAutorizacion.setSelection(getIndex(mAutorizacion, lAutorizacion));
        }
        mCursorAutorizacion.close();
    }

    private void setData() {
        ArrayList<Company> CompanyList = new ArrayList<>();
        ArrayList<TipoServicio> TipoServicioList = new ArrayList<>();
        ArrayList<TipoVivienda> TipoViviendaList = new ArrayList<>();
        ArrayList<Smart> SmartList = new ArrayList<>();

        Cursor mCursorCompany = mAgreementDbHelper.getAllRows("tb_gen_company");
        Cursor mCursorTService = mAgreementDbHelper.getAllRows("tb_gen_tiposervicio");
        Cursor mCursorTipoVivienda = mAgreementDbHelper.getAllRows("tb_gen_tipovivienda");
        Cursor mCursorSmart = mAgreementDbHelper.getAllRowsOrderby("tb_gen_smart", "id");

        mCursorCompany.moveToFirst();
        while (!mCursorCompany.isAfterLast()) {
            CompanyList.add(
                    new Company(
                            mCursorCompany.getString(mCursorCompany.getColumnIndex("id")),
                            mCursorCompany.getString(mCursorCompany.getColumnIndex("name"))
                    ));
            mCursorCompany.moveToNext();
        }

        //fill data in spinner Company
        ArrayAdapter<Company> adapterCompany = new ArrayAdapter<Company>(getActivity(), android.R.layout.simple_spinner_dropdown_item, CompanyList);
        mCompany.setAdapter(adapterCompany);

        if(lCompany != null){
            mCompany.setSelection(getIndex(mCompany, lCompany));
        }
        mCursorCompany.close();


        mCursorSmart.moveToFirst();
        while (!mCursorSmart.isAfterLast()) {
            SmartList.add(
                    new Smart(
                            mCursorSmart.getString(mCursorSmart.getColumnIndex("id")),
                            mCursorSmart.getString(mCursorSmart.getColumnIndex("name")),
                            mCursorSmart.getString(mCursorSmart.getColumnIndex("code")),
                            mCursorSmart.getString(mCursorSmart.getColumnIndex("active"))
                    ));
            mCursorSmart.moveToNext();
        }

        //fill data in spinner Smart
        ArrayAdapter<Smart> adapterSmart = new ArrayAdapter<Smart>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SmartList);
        mSmart.setAdapter(adapterSmart);

        if(lSmart != null){
            mSmart.setSelection(getIndex(mSmart, lSmart));
        }
        mCursorSmart.close();

        mCursorTService.moveToFirst();
        while (!mCursorTService.isAfterLast()) {
            TipoServicioList.add(
                    new TipoServicio(
                            mCursorTService.getString(mCursorTService.getColumnIndex("id")),
                            mCursorTService.getString(mCursorTService.getColumnIndex("name"))
                    ));
            mCursorTService.moveToNext();
        }

        //fill data in spinner Tipo Servicio
        ArrayAdapter<TipoServicio> adapterTServicio = new ArrayAdapter<TipoServicio>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TipoServicioList);
        mTiposervicio.setAdapter(adapterTServicio);

        if(lTipoServicio != null){
            mTiposervicio.setSelection(getIndex(mTiposervicio, lTipoServicio));
        }
        mCursorTService.close();

        LoadSector(mCantonId);

        LoadNap(mCantonId);

        LoadPlan(mTipoServicioId);

        mCursorTipoVivienda.moveToFirst();
        while (!mCursorTipoVivienda.isAfterLast()) {
            TipoViviendaList.add(
                    new TipoVivienda(
                            mCursorTipoVivienda.getString(mCursorTipoVivienda.getColumnIndex("id")),
                            mCursorTipoVivienda.getString(mCursorTipoVivienda.getColumnIndex("name"))
                    ));
            mCursorTipoVivienda.moveToNext();
        }

        //fill data in spinner Tecnologia
        ArrayAdapter<TipoVivienda> adapterTipoVivienda = new ArrayAdapter<TipoVivienda>(getActivity(), android.R.layout.simple_spinner_dropdown_item, TipoViviendaList);
        mTipoVivienda.setAdapter(adapterTipoVivienda);

        if(lTipoVivienda != null){
            mTipoVivienda.setSelection(getIndex(mTipoVivienda, lTipoVivienda));
        }
        mCursorTipoVivienda.close();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.tipo_contrato, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTipoContrato.setAdapter(adapter);

        if(mContratoSelected != null){
            mTipoContrato.setSelection(getIndex(mTipoContrato, mContratoSelected));
        }

    }

    public void loadPartner(String partner_id){
        Cursor cpartner = mAgreementDbHelper.getPartnerById(partner_id);
//        cpartner.moveToLast();
        cpartner.moveToFirst();
        Partner partner = new Partner(cpartner);

        mPartnerID.setText(partner.getId());
        String name = partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName();
        mPartnerName.setText(name);
        mCantonId = partner.getCanton_id();
        mCantonName = partner.getCanton();

        if (mCantonId != null) {
            LoadSector(mCantonId);
            LoadNap(mCantonId);
            LoadPoste(mSectorId);
            mSector.setSelection(0);
        }
        cpartner.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                mPartnerId = data.getStringExtra("partner_id");
                clientActivo = false;

                if (mPartnerId != null) {
                    loadPartner(mPartnerId);
//                    Cursor cpartner = mAgreementDbHelper.getPartnerById(mPartnerId);
//                    cpartner.moveToLast();
//                    Partner partner = new Partner(cpartner);
//
//                    mPartnerID.setText(partner.getId());
//                    String name = partner.getFirstln() + " " + partner.getSecondln() + " " + partner.getName();
//                    mPartnerName.setText(name);
//                    mCantonId = partner.getCanton_id();
//                    mCantonName = partner.getCanton();
//
//                    if (mCantonId != null) {
//                        LoadSector(mCantonId);
//                        LoadNap(mCantonId);
//                        mSector.setSelection(0);
//                    }
//                    cpartner.close();
                }
            }
        }
        else if (requestCode == 3){
            if (resultCode == RESULT_OK) {
                mFirma = data.getByteArrayExtra("signature");
            }
        }
        //CEDULA//
        else if( requestCode == 5 && resultCode == RESULT_OK ) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCedula, options());
            mImageCedula.setImageBitmap(imgBitmap);
            mByteCedula = bitmapToByte(imgBitmap);
        }else if( requestCode == 6 && resultCode == RESULT_OK ) {
            try {
                // Creating file
                File photoFile = null;
                try {
                    photoFile = crearImagenCedula();
                } catch (IOException ex) {
                    Log.d("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                // Copying
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();
                Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCedula, options());
                mImageCedula.setImageBitmap(imgBitmap);
                mByteCedula = bitmapToByte(imgBitmap);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        //planilla
        else if( requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenPlanilla, options());
            mImagePlanilla.setImageBitmap(imgBitmap);
            mBytePlanilla = bitmapToByte(imgBitmap);
        }else if( requestCode == 8 && resultCode == RESULT_OK ) {
            try {
                File photoFile = null;
                try {
                    photoFile = crearImagenPlanilla();
                } catch (IOException ex) {
                    Log.d("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();
                Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenPlanilla, options());
                mImagePlanilla.setImageBitmap(imgBitmap);
                mBytePlanilla = bitmapToByte(imgBitmap);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        ////CASA////
        else if( requestCode == 9 && resultCode == RESULT_OK ) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCasa, options());
            mImageCasa.setImageBitmap(imgBitmap);
            mByteCasa = bitmapToByte(imgBitmap);
        }else if( requestCode == 10 && resultCode == RESULT_OK ) {
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
                Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCasa, options());
                mImageCasa.setImageBitmap(imgBitmap);
                mByteCasa = bitmapToByte(imgBitmap);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        //PDF contrato
        else if( requestCode == 11 && resultCode == RESULT_OK ) {
            //Log.d("TAG", "onActivityResult PDF: " + data.getData());
            try {
                File pdf = null;
                try {
                    pdf = crearPDFContrato();
                    validateContract = true;
                } catch (IOException ex) {
                    Log.e("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(pdf);
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();
                Path pdfFilePath = Paths.get(rutaContractoPDF);
                mBytePDF = Files.readAllBytes(pdfFilePath);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        //PDF cedula
        else if( requestCode == 12 && resultCode == RESULT_OK ) {
            Log.d("TAG", "onActivityResult PDF: " + data.getData());
            try {
                File pdfcedula = null;
                try {
                    pdfcedula = crearPDFCedula();
                } catch (IOException ex) {
                    Log.e("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(pdfcedula);
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();
                Path pdfFilePath = Paths.get(rutaImagenCedula);
                mByteCedula = Files.readAllBytes(pdfFilePath);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        //PDF planilla
        else if( requestCode == 13 && resultCode == RESULT_OK ) {
            Log.d("TAG", "onActivityResult PDF: " + data.getData());
            try {
                File pdfplanilla = null;
                try {
                    pdfplanilla = crearPDFPlanilla();
                } catch (IOException ex) {
                    Log.e("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(pdfplanilla);
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close();
                inputStream.close();
                Path pdfFilePath = Paths.get(rutaImagenPlanilla);
                mBytePlanilla = Files.readAllBytes(pdfFilePath);
                //Bitmap myBitmap = BitmapFactory.decodeFile(rutaImagenPlanilla);
                //mImagePlanilla.setImageBitmap(myBitmap);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
        //CEDULA POST
        else if( requestCode == 14 && resultCode == RESULT_OK ) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCedulaPost, options());
            mImageCedulaPost.setImageBitmap(imgBitmap);
            mByteCedulaPost = bitmapToByte(imgBitmap);
        }else if( requestCode == 15 && resultCode == RESULT_OK ) {
            try {
                File photoFile = null;
                try {
                    photoFile = crearImagenCedulaPost();
                } catch (IOException ex) {
                    Log.d("TAG", "Error occurred while creating the file");
                }
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                copyStream(inputStream, fileOutputStream);
                fileOutputStream.close(); inputStream.close();
                Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagenCedulaPost, options());
                mImageCedulaPost.setImageBitmap(imgBitmap);
                mByteCedulaPost = bitmapToByte(imgBitmap);
            } catch (Exception e) {
                Log.d("TAG", "onActivityResult: " + e.toString());
            }
        }
    }

    private void abrirCamaraCedula(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagenCedula();
        }catch (IOException e){
            Log.e("Error cedula", e.toString());
        }
        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getActivity(), "com.dvnet.agreement.fileprovider", imagenArchivo);
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagenArchivo));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 5);
        }
    }

    private void cargarCedula(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 6);
    }

    private void abrirCamaraCedulaPost(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagenCedulaPost();
        }catch (IOException e){
            Log.e("Error cedula", e.toString());
        }
        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getActivity(), "com.dvnet.agreement.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 14);
        }
    }

    private void cargarCedulaPost(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 15);
    }

    private void abrirCamaraPlanilla(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagenPlanilla();
        }catch (IOException e){
            Log.e("Error cedula", e.toString());
        }
        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getActivity(), "com.dvnet.agreement.fileprovider", imagenArchivo);
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagenArchivo));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 7);
        }
    }

    private void cargarPlanilla(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 8);
    }

    private void abrirCamaraCasa(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagenCasa();
        }catch (IOException e){
            Log.e("Error cedula", e.toString());
        }
        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getActivity(), "com.dvnet.agreement.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 9);
        }
    }

    private void cargarCasa(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 10);
    }

    public void selectPDF(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Seleccione el contrato (PDF)"), 11);
    }

    public void pdfCedula(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Seleccione el archivo de la cedula"), 12);
    }

    public void pdfPlanilla(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Seleccione el archivo de la planilla"), 13);
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

    private BitmapFactory.Options options(){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;  //4 se lo reduce mas
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[16 * 1024];
        return options;
    }

    private File crearImagenCedula() throws IOException {
        String nombreImage = "cedula_ruc_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreImage, ".jpg", dir);
        nombreCedula = image.getName();
        //mTextCedula.setText(nombreCedula);
        rutaImagenCedula = image.getAbsolutePath();
        return image;
    }

    private File crearImagenCedulaPost() throws IOException {
        String nombreImage = "cedula_ruc_post_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreImage, ".jpg", dir);
        nombreCedulaPost = image.getName();
        rutaImagenCedulaPost = image.getAbsolutePath();
        return image;
    }

    private File crearImagenPlanilla() throws IOException {
        String nombreImage = "planilla_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreImage, ".jpg", dir);
        nombrePlanilla = image.getName();
        //mTextPlanilla.setText(nombrePlanilla);
        rutaImagenPlanilla = image.getAbsolutePath();
        return image;
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

    private File crearPDFContrato() throws IOException {
        String nombreImage = "contrato_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File pdf = File.createTempFile(nombreImage, ".pdf", dir);
        nombrePDF = pdf.getName();
        rutaContractoPDF = pdf.getAbsolutePath();
        mTextoContrato.setText(pdf.getName());
        return pdf;
    }

    private File crearPDFCedula() throws IOException {
        String nombre = "cedula_ruc_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File pdf = File.createTempFile(nombre, ".pdf", dir);
        nombreCedula = pdf.getName();
        //mTextCedula.setText(nombreCedula);
        mImageCedula.setImageResource(android.R.color.transparent);
        rutaImagenCedula = pdf.getAbsolutePath();
        return pdf;
    }

    private File crearPDFPlanilla() throws IOException {
        String nombre = "planilla_";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File pdf = File.createTempFile(nombre, ".pdf", dir);
        nombrePlanilla = pdf.getName();
        //mTextPlanilla.setText(nombrePlanilla);
        mImagePlanilla.setImageResource(android.R.color.transparent);
        rutaImagenPlanilla = pdf.getAbsolutePath();
        return pdf;
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private void addEditAgreement() {
        boolean error = false;
        GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();

        String id = "0";

        if(mAgreementId != null)
        {
            id = mAgreementId;
        }

        String Partner_Id = mPartnerID.getText().toString();
        String Partner = mPartnerName.getText().toString();
        String Company_Id = mCompanyId;
        String Company = mCompanyName;
        String TipoServicio_Id = mTipoServicioId;
        String TipoServicio = mTipoServicioName;
        String Plan_id = mPlanId;
        String Plan = mPlanName;
        String Smart_id = mSmartId;
        String Smart = mSmartName;
        String Canton_id = mCantonId;
        String Canton = mCantonName;
//        String Sector_id = mSectorId;
//        String Sector = mSectorName;
        String Sector_id = ((mSectorId == null) ? "" : mSectorId);
        String Sector = ((mSectorName == null) ? "" : mSectorName);
//        String Poste_id = mPosteId;
//        String Poste = mPosteName;
        String Poste_id = ((mPosteId == null) ? "" : mPosteId);
        String Poste = ((mPosteName == null) ? "" : mPosteName);
//        String Nap_id = mNapId;
        String Nap_id = ((mNapId == null) ? "" : mNapId);
//        String Nap = mNapName;
        String Nap = ((mNapName == null) ? "" : mNapName);
        String TipVivienda_id = mTipoViviendaId;
        String TipVivienda = mTipoViviendaName;
        String Autorizacion_id = mAutorizacionId;
        String Autorizacion = mAutorizacionName;
        String State = "P";
        byte[] Sing = mFirma;
        byte[] fotoCedula = mByteCedula;
        String nombCedula = nombreCedula;
        byte[] fotoCedulaPost = mByteCedulaPost;
        String nombCedulaPost = nombreCedulaPost;
        byte[] fotoPlanilla = mBytePlanilla;
        String nombPlanilla = nombrePlanilla;
        byte[] fotoCasa = mByteCasa;
        String nombCasa = nombreCasa;
        byte[] contratoPdf = mBytePDF;
        String nombPDF = mTextoContrato.getText().toString();

        String user_id = null;
        if (mUserId == null){
            user_id = globalUser.getData();
        } else {
            if (mUserId !=  globalUser.getData()){
                user_id = mUserId;
            }
        }

        String Observacion = mObservacion.getText().toString();
        String Contrato = mContrato.getText().toString().trim().toUpperCase();
        String TipoContrato = mContratoSelected.toString();

        Agremeent agreement = new Agremeent(
                id,
                Partner_Id,
                Partner,
                Company_Id,
                Company,
                TipoServicio_Id,
                TipoServicio,
                Plan_id,
                Plan,
                Smart_id,
                Smart,
                Canton_id,
                Canton,
                Sector_id,
                Sector,
                Poste_id,
                Poste,
                Nap_id,
                Nap,
                TipVivienda_id,
                TipVivienda,
                Autorizacion_id,
                Autorizacion,
                State,
                Sing,
                fotoCedula,
                nombCedula,
                fotoCedulaPost,
                nombCedulaPost,
                fotoPlanilla,
                nombPlanilla,
                fotoCasa,
                nombCasa,
                contratoPdf,
                nombPDF,
                mRecibov,
                mValorv,
                user_id,
                Observacion,
                Contrato,
                TipoContrato
        );

        if( Partner_Id.length()>0 && Partner.length()>0 ){
            if ( mAgreementId != null && actual_state.equals("A") ){
                agreement.setState("A");
            } else {
                String errors = validateAgreement(agreement);
                if ( errors != null){
                    agreement.setState("B");
                    Toast.makeText(getActivity(), errors, Toast.LENGTH_SHORT).show();
                } else {
                    agreement.setState("P");
                }
            }
            new AddEditAgreementFragment.AddEditAgreementTask().execute(agreement);
        }else{
            Toast.makeText(getActivity(), "No se ha añadido ningun cliente", Toast.LENGTH_SHORT).show();
        }

    }

    private String validateAgreement(Agremeent agremeent) {
        String error = "Los siguientes datos son necesarios: \n";
        String errors = null;
        int contador = 0;
        if ( agremeent.getPartner_Id().isEmpty() || agremeent.getPartner_Id() == null)
        {
            error += "Cliente \n";
            contador++;
        }
        if ( agremeent.getCompany_Id().isEmpty() || agremeent.getCompany_Id() == null)
        {
            error += "Compañia \n";
            contador++;
        }
        if ( agremeent.getTipoServicio_Id().isEmpty() || agremeent.getTipoServicio_Id() == null)
        {
            error += "Tipo Servicio \n";
            contador++;
        }
        if ( agremeent.getPlan_id().isEmpty() || agremeent.getPlan_id() == null)
        {
            error += "Plan \n";
            contador++;
        }
        if ( agremeent.getSmart_id().isEmpty() || agremeent.getSmart_id() == null)
        {
            error += "Smart \n";
            contador++;
        }
        if ( agremeent.getSector_id().isEmpty() || agremeent.getSector_id() == null)
        {
            error += "Sector \n";
            contador++;
        }
        if ( agremeent.getNap_id().isEmpty() || agremeent.getNap_id() == null)
        {
            error += "NAP \n";
            contador++;
        }
        if ( !hasBytes(agremeent.getSing()))
        {
            error += "Necesita Registrar la Firmar \n";
            contador++;
        }
        if ( !hasBytes(agremeent.getFotoCedula()))
        {
            error += "Necesita Registrar la foto de la cédula \n";
            contador++;
        }
        if ( !hasBytes(agremeent.getFotoPlanilla()))
        {
            error += "Necesita Registrar la planilla \n";
            contador++;
        }
        if ( !hasBytes(agremeent.getFotoCasa()))
        {
            error += "Necesita Registrar la casa \n";
            contador++;
        }
        if ( agremeent.getAutorizacion_id().isEmpty() || agremeent.getAutorizacion_id() == null)
        {
            error += "Autorizacion \n";
            contador++;
        }

        if ( !mContratoSelected.equals("NUEVO") )
        {
            if ( agremeent.getContrato().isEmpty() || agremeent.getContrato() == null ) {
                error += "Contrato a modificar \n";
                contador++;
            } else {
                Pattern r=Pattern.compile("^[O]P[0-9]{2}+-.{2,8}$", Pattern.CASE_INSENSITIVE);
                Matcher m=r.matcher( agremeent.getContrato() );
                if(!m.find()){
                    error += "Formato de contrato inválido \n";
                    contador++;
                }
            }
        }

        if (validateContract){
            if ( !hasBytes(agremeent.getPDF()) ) {
                error += "PDF del contrato \n";
                contador++;
            }
        }

        if(contador>0)
        {
            errors = error;
        }

        return errors;
    }

    private static boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

    private class AddEditAgreementTask extends AsyncTask<Agremeent, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Agremeent... agreement) {
            if (mAgreementId != null) {
                int id = mAgreementDbHelper.updateAgremeent(agreement[0], mAgreementId);
                mAgreementId = String.valueOf(id);
                return  id > 0;
            } else {
                long id = mAgreementDbHelper.saveAgremeent(agreement[0]);
                String query = "update res_partner set used ='1' where id = '" + agreement[0].getPartner_Id() + "'";
                mAgreementDbHelper.executedb(query);
                mAgreementId = String.valueOf(id);
                mAgreementDbHelper.executedb("update tb_gen_users set r_desde = '" + agreement[0].getRecibo() + "' where id = '" + agreement[0].getUser_id() +"'");
                return  id > 0;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showAgreementScreen(result);
        }
    }

    private void showAgreementScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }
        mRecibov = null;
        mValorv = 0.00;
        mRecive.setText(mRecibov);
        mValor.setText(mValorv.toString());
        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void loadAgreement() {
        new AddEditAgreementFragment.GetAgreementByIdTask().execute();
    }

    private class GetAgreementByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mAgreementDbHelper.getAgremeentById(mAgreementId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showAgrrement(new Agremeent(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }

    }

    private void showAgrrement(Agremeent agreement) {
        mGetPartner.setEnabled(false);
        mGetPartner.isClickable();

        //mPartnerID.setText(agreement.getPartner_Id());
        //mPartnerName.setText(agreement.getPartner());
        loadPartner(agreement.getPartner_Id());

        actual_state = agreement.getState();
        mCompanyId = agreement.getCompany_Id();
        lCompany = agreement.getCompany();
        mTipoServicioId = agreement.getTipoServicio_Id();
        lTipoServicio = agreement.getTipoServicio();
        mPlanId = agreement.getPlan_id();
        mPlanName = agreement.getPlan();
        lPlan = agreement.getPlan();
        mSmartId = agreement.getSmart_id();
        mSmartName = agreement.getSmart();
        mSmartId = agreement.getSmart_id();
        lSmart = agreement.getSmart();
//        mCantonId = agreement.getCanton_id();
//        mCantonName = agreement.getCanton();
        mSectorId = agreement.getSector_id();
        lSector = agreement.getSector();
        mPosteId = agreement.getPoste_id();
        lPoste = agreement.getPoste();
        mNapId = agreement.getNap_id();
        lNap = agreement.getNap();
        mTipoViviendaId = agreement.getTipVivienda_id();
        lTipoVivienda = agreement.getTipVivienda();
        mAutorizacionId = agreement.getAutorizacion_id();
        lAutorizacion = agreement.getAutorizacion();
        mFirma = agreement.getSing();

        mByteCedula = agreement.getFotoCedula();
        nombreCedula = agreement.getNombreCedula();
        if ( hasBytes(mByteCedula) ) {
            Bitmap cedula = BitmapFactory.decodeByteArray(mByteCedula, 0, mByteCedula.length);
            mImageCedula.setImageBitmap(cedula);
        }
        //mTextCedula.setText(agreement.getNombreCedula());

        if ( agreement.getNombreCedulaPost() != null && agreement.getFotoCedulaPost() != null ){
            mByteCedulaPost = agreement.getFotoCedulaPost();
            nombreCedulaPost = agreement.getNombreCedulaPost();
            Bitmap cedulaPost = BitmapFactory.decodeByteArray(mByteCedulaPost, 0, mByteCedulaPost.length);
            mImageCedulaPost.setImageBitmap(cedulaPost);
        }

        mBytePlanilla = agreement.getFotoPlanilla();
        nombrePlanilla = agreement.getNombrePlanilla();
        if ( hasBytes(mBytePlanilla) ) {
            Bitmap planilla = BitmapFactory.decodeByteArray(mBytePlanilla, 0, mBytePlanilla.length);
            mImagePlanilla.setImageBitmap(planilla);
        }
        //mTextPlanilla.setText(agreement.getNombrePlanilla());

        mByteCasa = agreement.getFotoCasa();
        nombreCasa = agreement.getNombreCasa();
        if ( hasBytes(mByteCasa) ) {
            Bitmap casa = BitmapFactory.decodeByteArray(mByteCasa, 0, mByteCasa.length);
            mImageCasa.setImageBitmap(casa);
        }

        mBytePDF = agreement.getPDF();
        mTextoContrato.setText(agreement.getNombrePDF());

        mValorv = agreement.getValor();
        mRecibov = agreement.getRecibo();

        mValor.setText(mValorv.toString());
        mRecive.setText(mRecibov);
        mObservacion.setText(agreement.getObservacion());
        mContrato.setText(agreement.getContrato());
//        mContrato.setText(agreement.getContrato());
        mContratoSelected = agreement.getTipoContrato();

        mUserId = agreement.getUser_id();

//        if(mValorv > 0.00 || mValor.getText().toString() != null){
//            mPayNow.setEnabled(false);
//            mPayNow.isClickable();
//        }

        mCompany.setSelection(getIndex(mCompany, agreement.getCompany()));

        LoadAutorizacion(agreement.getCompany_Id());
        mAutorizacion.setSelection(getIndex(mAutorizacion, agreement.getAutorizacion()));

        mTiposervicio.setSelection(getIndex(mTiposervicio, agreement.getTipoServicio()));

//        LoadSector(agreement.getCanton_id());
        mSector.setSelection(getIndex(mSector, agreement.getSector()));

//        LoadPoste(agreement.getSector_id());
        mPoste.setSelection(getIndex(mPoste, agreement.getPoste()));

//        LoadNap(agreement.getCanton_id());
        mNap.setSelection(getIndex(mNap, agreement.getNap()));

        LoadPlan(agreement.getTipoServicio_Id());
        mPlan.setSelection(getIndex(mPlan, agreement.getPlan()));

        mSmart.setSelection(getIndex(mSmart, agreement.getSmart()));
        mTipoVivienda.setSelection(getIndex(mTipoVivienda, agreement.getTipVivienda()));
        mTipoContrato.setSelection(getIndex(mTipoContrato, agreement.getTipoContrato()));

        Log.i("Estado:", agreement.getState());

        if(agreement.getState().equals("A")){
//            mSaveButton.setEnabled(false);
            mPayNow.setEnabled(false);
            mObservacion.setEnabled(false);
            mSmart.setEnabled(false);
            mPartnerID.setEnabled(false);
            mPartnerName.setEnabled(false);
            mCompany.setEnabled(false);
//            mTiposervicio.setEnabled(false);
//            mPlan.setEnabled(false);
            mSector.setEnabled(false);
            mPoste.setEnabled(false);
            mTipoVivienda.setEnabled(false);
            mAutorizacion.setEnabled(false);
            mNap.setEnabled(false);
            mRecive.setEnabled(false);
            mValor.setEnabled(false);
            mContrato.setEnabled(false);
            mButtonCedula.setEnabled(false);
            mCargarCedula.setEnabled(false);
            mPDFCedula.setEnabled(false);
            mButtonCedulaPost.setEnabled(false);
            mCargarCedulaPost.setEnabled(false);
            mButtonPlanilla.setEnabled(false);
            mCargarPlanilla.setEnabled(false);
            mPDFPlanilla.setEnabled(false);
            mButtonCasa.setEnabled(false);
            mCargarCasa.setEnabled(false);
            mButtonContrato.setEnabled(false);
            mTipoContrato.setEnabled(false);
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

    private void showAgreementSing(String agreementid){
        Intent intent = new Intent(getActivity(), SingActivity.class);
        intent.putExtra("mAgreementId", agreementid);
        startActivityForResult(intent, 3);
    }

    private void showAgreementPrint(String agreementid){
        Intent intent = new Intent(getActivity(), AgreementPrint.class);
        intent.putExtra("mAgreementId", mAgreementId);
        startActivityForResult(intent, 4);
    }

    public void GeneratePayment() {
        DialogFragment newFragment = new PaymentFragment();
        newFragment.setTargetFragment(this,0);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        newFragment.show( ft, "payment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String recibo, String valor) {
        // User touched the dialog's positive button

        if(recibo.isEmpty() || recibo == null){
            Toast.makeText(getActivity(),"Debe ingresar numero de recibo.", Toast.LENGTH_SHORT).show();
        }

        if(valor.isEmpty() || recibo == null){
            Toast.makeText(getActivity(),"Debe ingresar valor.", Toast.LENGTH_SHORT).show();
        }

        mRecibov = recibo;
        mValorv = Double.parseDouble(valor);

        mRecive.setText(mRecibov);
        mValor.setText(mValorv.toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button

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

    public void internetConection (String id) {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            GetData consultPartner = new GetData(getActivity());
            try {
                ArrayList<ConsultaCliente> result = consultPartner.execute("CEDULA/RUC", id).get();
                for (int i = 0; i < result.size(); i++) {
                    if ( result.get(i).getState().equals("Activo") ) {
                        clientActivo = true;
                        return;
                    }
                }
            } catch (Exception e) {
                Log.e("Error async", e.toString());
            }
        } else {
//            showMsgDialogConnection();
        }
    }

    private void showMsgDialogConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(" Sin conexión a Internet ");
        builder.setMessage("No se encuentra conectado a ninguna red por lo que no sabra si es cliente activo DVNET.");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the processing code for clicking the OK button
            }
        });
        builder.show();
    }

}