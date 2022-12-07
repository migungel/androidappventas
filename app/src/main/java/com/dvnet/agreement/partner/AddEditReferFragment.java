package com.dvnet.agreement.partner;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.dvnet.agreement.AgreementPrint;
import com.dvnet.agreement.CustomerManagement;
import com.dvnet.agreement.R;
import com.dvnet.agreement.data.ActualService;
import com.dvnet.agreement.data.InterestService;
import com.dvnet.agreement.data.Canton;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.NAP;
import com.dvnet.agreement.data.Providers;
import com.dvnet.agreement.data.Provincia;
import com.dvnet.agreement.data.Servicio;
import com.dvnet.agreement.data.adapters.ServiceCursorAdapter;
import com.dvnet.agreement.data.partner.Management;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;

public class AddEditReferFragment extends Fragment {

    private static final String ARG_PARTNER_ID = "arg_partner_id";
    private static final int RESULT_OK = -1;

    private String mPartnerId, mServiceId, mProvinciaId, mProvinciaName, mCantonId, mCantonName, lProvincia, lCanton,
                    mActualServiceId, mActualServiceName, mActualProviderName,  mNapId, mNapName, lNap;

    private String mUserId, mAppId = null;
    private String services = "", actualService = "";
//    private ArrayAdapter adapterActual;
    private ServiceCursorAdapter adapterActual;
    private ArrayList<ActualService> arrayActualService;

    private byte[] mByteCasa;
    private String rutaImagenCasa;
    private String nombreCasa;

    LocationManager locationManager;

    private PartnerDbHelper mPartnerDbHelper;
    private FloatingActionButton mSaveButton;
    private TextInputEditText mFirstLNField, mSecondLNField, mNameField, mStreetField, mMobileField, mVendedorField,
                                mServiceField, mProviderField;

    private TextInputLayout inputService, inputProvider;
    private Spinner mProvincia, mCanton, mSale, mServicio, mProveedor, mNap;
    private Button mRefer, mAddActualService, mRefresh, mFotoCasa, mCargarCasa;
    private ImageView mImageCasa;
    private CustomerManagement customerManagement;
    private ManagementCursorAdapter mManagementAdapter;

    private ListView mServiceList, mActualServiceList, mManagementList;


    public AddEditReferFragment() {
        // Required empty public constructor
    }

    public static AddEditReferFragment newInstance(String partnerId) {
        AddEditReferFragment fragment = new AddEditReferFragment();
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
        View root = inflater.inflate(R.layout.fragment_add_edit_refer, container, false);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mFirstLNField = (TextInputEditText) root.findViewById(R.id.p_firstln);
        mSecondLNField = (TextInputEditText) root.findViewById(R.id.p_secondln);
        mNameField = (TextInputEditText) root.findViewById(R.id.p_name);
        mStreetField = (TextInputEditText) root.findViewById(R.id.p_street);
        mMobileField = (TextInputEditText) root.findViewById(R.id.p_mobile);
        inputService = (TextInputLayout) root.findViewById(R.id.til_service);
        inputProvider = (TextInputLayout) root.findViewById(R.id.til_provider);
        mServiceField = (TextInputEditText) root.findViewById(R.id.p_service);
        mProviderField = (TextInputEditText) root.findViewById(R.id.p_provider);
        mProvincia = (Spinner) root.findViewById(R.id.p_provincia);
        mCanton = (Spinner) root.findViewById(R.id.p_canton);
        mSale = (Spinner) root.findViewById(R.id.a_spinner_sale);
        mServicio = (Spinner) root.findViewById(R.id.p_servicio);
        mProveedor = (Spinner) root.findViewById(R.id.p_provedor);
        mNap = (Spinner) root.findViewById(R.id.a_spinner_nap);
        mVendedorField = (TextInputEditText) root.findViewById(R.id.p_vendedor);
        mRefer = (Button) root.findViewById(R.id.add_refer);
        mAddActualService = (Button) root.findViewById(R.id.add_servicio);
        mRefresh = (Button) root.findViewById(R.id.refresh);
        mManagementList = (ListView) root.findViewById(R.id.ccl_management_result);
        mFotoCasa = (Button) root.findViewById(R.id.foto_casa);
        mCargarCasa = (Button) root.findViewById(R.id.cargar_casa);
        mImageCasa = (ImageView) root.findViewById(R.id.imageViewCasa);
        mManagementAdapter = new ManagementCursorAdapter(getActivity(), null);

        mServiceList = (ListView) root.findViewById(R.id.service_result);
        mActualServiceList = (ListView) root.findViewById(R.id.actual_service);

        mPartnerDbHelper = new PartnerDbHelper(getActivity());
        arrayActualService = new ArrayList<ActualService>();

        // Setup
        mManagementList.setAdapter(mManagementAdapter);
        mManagementList.setTextFilterEnabled(true);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditPartner();
            }
        });

        mAddActualService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serv = mActualServiceName;
                String prov = mActualProviderName;
                if (mActualServiceName.equals("OTROS")){
                    serv = mServiceField.getText().toString();
                }
                if (mActualProviderName.equals("OTROS")){
                    prov = mProviderField.getText().toString();
                }
                if ((!serv.isEmpty() || !serv.equals("")) && (!prov.isEmpty() || !prov.equals(""))){
                    ActualService as = new ActualService(mActualServiceId, serv, prov);
                    if (arrayActualService.size()>0){
                        if(arrayActualService.contains(as)){
                            Toast.makeText(getActivity(), "Servicio ya existe", Toast.LENGTH_SHORT).show();
                        }else {
                            arrayActualService.add(as);
                        }
                    }else {
                        arrayActualService.add(as);
                    }
                    adapterActual.notifyDataSetChanged();
                    adapterListView(arrayActualService.size(), adapterActual, mActualServiceList);
                    mServicio.setSelection(0);
                    mProveedor.setSelection(0);
                    mServiceField.setText("");
                    mProviderField.setText("");
                }
            }
        });

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPartnerId!=null){loadManagement();}
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
                LoadNap(mCantonId);
                mNapName = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
                return;
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.conf_sale, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSale.setAdapter(adapter);
        mSale.setSelection(0);

        mServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Servicio servicio = (Servicio) parent.getItemAtPosition(pos);
                Log.i("Servicio:", servicio.getId());
                if (!TextUtils.isEmpty(servicio.getId())) {
                    mActualServiceId = servicio.getId();
                    mActualServiceName = servicio.getName();
                }
                if (servicio.getName().equals("OTROS")){
                    inputService.setVisibility(View.VISIBLE);
                }else {
                    inputService.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                Log.i("Message", "Nothing is selected");
            }
        });


        mProveedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Providers prov = (Providers) parent.getItemAtPosition(pos);
                Log.i("Proveedor:", prov.getId());
                if (!TextUtils.isEmpty(prov.getId())) {
                    mActualProviderName = prov.getName();
                }
                if (prov.getName().equals("OTROS")){
                    inputProvider.setVisibility(View.VISIBLE);
                }else {
                    inputProvider.setVisibility(View.GONE);
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


        mRefer.setVisibility(View.GONE);
        mRefresh.setVisibility(View.GONE);
        inputService.setVisibility(View.GONE);
        inputProvider.setVisibility(View.GONE);

        LoadCombos();

        mServiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Servicio currentItem = (Servicio) adapterView.getItemAtPosition(i);
                String name = currentItem.getName();
                Log.i("selected:",""+ name);
                if (mServiceList.isItemChecked(i)){
                    mServiceList.setItemChecked(i, true);
                }else{
                    mServiceList.setItemChecked(i, false);
                }
            }
        });

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

    private void loadPartner() { new GetPartnerByIdTask().execute(); }

    private void LoadCombos() {
        ArrayList<Provincia> ProvinciaList = new ArrayList<>();
        Cursor mCursorProvincia = mPartnerDbHelper.getAllRows("tb_gen_provincia");
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

        ArrayList<Servicio> servList = new ArrayList<>();
        Cursor mCursorServicio = mPartnerDbHelper.getAllRows("tb_gen_service");
        mCursorServicio.moveToFirst();
        while (!mCursorServicio.isAfterLast()) {
            servList.add(new Servicio(
                    mCursorServicio.getString(mCursorServicio.getColumnIndex("id")),
                    mCursorServicio.getString(mCursorServicio.getColumnIndex("name"))));
            mCursorServicio.moveToNext();
        }
        ArrayAdapter<Servicio> adapterServicio = new ArrayAdapter<Servicio>(getActivity(), android.R.layout.simple_list_item_checked, servList);
        mServiceList.setAdapter(adapterServicio);
        adapterListView(mCursorServicio.getCount(), adapterServicio, mServiceList);
        mCursorServicio.close();

        ArrayList<Servicio> actualServ = new ArrayList<>();
        Cursor mCursorActual = mPartnerDbHelper.getAllRows("tb_gen_actual_service");
        mCursorActual.moveToFirst();
        while (!mCursorActual.isAfterLast()) {
            actualServ.add(new Servicio(
                    mCursorActual.getString(mCursorActual.getColumnIndex("id")),
                    mCursorActual.getString(mCursorActual.getColumnIndex("name"))));
            mCursorActual.moveToNext();
        }
        mCursorActual.close();
        ArrayAdapter<Servicio> adapterServ = new ArrayAdapter<Servicio>(getActivity(), android.R.layout.simple_spinner_dropdown_item, actualServ);
        mServicio.setAdapter(adapterServ);



        ArrayList<Providers> proveedorList = new ArrayList<>();
        Cursor mCursorProveedor = mPartnerDbHelper.getAllRows("tb_gen_providers");
        mCursorProveedor.moveToFirst();
        while (!mCursorProveedor.isAfterLast()) {
            proveedorList.add(new Providers(
                    mCursorProveedor.getString(mCursorProveedor.getColumnIndex("id")),
                    mCursorProveedor.getString(mCursorProveedor.getColumnIndex("name"))));
            mCursorProveedor.moveToNext();
        }
        mCursorProveedor.close();
        ArrayAdapter<Providers> adapterProveedor = new ArrayAdapter<Providers>(getActivity(), android.R.layout.simple_spinner_dropdown_item, proveedorList);
        mProveedor.setAdapter(adapterProveedor);

//        adapterActual = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayActualService);
        adapterActual = new ServiceCursorAdapter(getActivity(), arrayActualService, mActualServiceList);
        mActualServiceList.setAdapter(adapterActual);
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

    private void LoadNap(String canton_id){
        ArrayList<NAP> NapList = new ArrayList<>();
        Cursor mCursorNap = mPartnerDbHelper.getNapbyCanton(canton_id);

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

    private void addEditServiceList() {
        String id = "0";
        Cursor serList = mPartnerDbHelper.getAllRowsby("tb_list_service", "refer =?", mPartnerId);
        if( (serList != null) && (serList.getCount() > 0) ){
            serList.moveToFirst();
            mServiceId = serList.getString(serList.getColumnIndex("id"));
            id = mServiceId;
            serList.close();
        }
        String refer = "0";
        if(mPartnerId != null) {
            refer = mPartnerId;
        }
        for (int i = 0; i<arrayActualService.size(); i++){
            actualService += arrayActualService.get(i).getService() + "-" + arrayActualService.get(i).getProvider() + ",";
        }
        actualService = actualService.substring(0, actualService.length() - 1);

        InterestService aserv = new InterestService(id, refer, services, actualService);

        new AddEditServicesTask().execute(aserv);
    }

    private void addEditPartner() {
        GlobalUser globalUser = GlobalUser.getInstance();

        String id = "0";

        if(mPartnerId != null) {
            id = mPartnerId;
        }

        String firstln = mFirstLNField.getText().toString().replaceAll("\\s+", " ").trim();
        String secondln = mSecondLNField.getText().toString().replaceAll("\\s+", " ").trim();
        if( secondln.isEmpty() ){
            secondln = "";
        }
        String name = mNameField.getText().toString().replaceAll("\\s+", " ").trim();
        String mobile = mMobileField.getText().toString().replaceAll("\\s+", " ").trim();
        String provincia_id =  mProvinciaId;
        String provincia =  mProvinciaName;
        String canton_id = mCantonId;
        String canton = mCantonName;
        String nap_id = mNapId;
        String nap = mNapName;
        String vendedor = mVendedorField.getText().toString();
        String street = mStreetField.getText().toString().replaceAll("\\s+", " ").trim();
        String venta = (String) mSale.getSelectedItem();
        String state_pendindg = "P";
        String app_id;
        String used = "1";
        String refer = "1";
        byte[] fotoCasa = mByteCasa;
        String nombCasa = nombreCasa;

        String user_id = null;
        if (mUserId == null){
            user_id = globalUser.getData();
        } else {
            if (mUserId !=  globalUser.getData()){
                user_id = mUserId;
            }
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
                street,
                mobile,
                "",
                provincia_id,
                provincia,
                canton_id,
                canton,
                vendedor,
                state_pendindg,
                used,
                user_id,
                venta,
                app_id,
                refer,
                "",
                nap_id,
                nap,
                fotoCasa,
                nombCasa);

        String errors = null;
        errors = validateReferPartner(partner);

        if ( errors != null){
            Toast.makeText(getActivity(), errors, Toast.LENGTH_SHORT).show();
            return;
        }

        new AddEditPartnerTask().execute(partner);
    }

    private String validateReferPartner(Partner partner){
        String error = "Los siguientes datos son necesarios: \n";
        String errors = null;
        int contador = 0;
        if ( partner.getFirstln().isEmpty() || partner.getFirstln() == null) {
            error += "Primer Apellido \n";
            contador++;
        }
        if ( partner.getName().isEmpty() || partner.getName() == null) {
            error += "Nombre \n";
            contador++;
        }
        if ( partner.getMobile().isEmpty() || partner.getMobile() == null) {
            error += "Celular\n";
            contador++;
        }else if ( partner.getMobile().length() != 10){
            error += "Número de celular inválido\n";
            contador++;
        }
        if ( partner.getCanton().isEmpty() || partner.getCanton() == null) {
            error += "Canton\n";
            contador++;
        }
        if ( partner.getNap() == null) {
            error += "Nap\n";
            contador++;
        }
        services = "";
        for (int i = 0; i < mServiceList.getCount(); i++) {
            if(mServiceList.isItemChecked(i)){
                int item = (int) mServiceList.getItemIdAtPosition(i) + 1;
                services += item + ",";
            }
        }
        if (services.length()>0){
            services = services.substring(0, services.length() - 1);
        }else{
            error += "Interes del cliente \n";
            contador++;
        }

        if (!(arrayActualService.size()>0)){
            error += "Un servicio actual \n";
            contador++;
        }

        if(contador>0) {
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
        mStreetField.setText(partner.getStreet());
        mMobileField.setText(partner.getMobile());
        mProvinciaId = partner.getProvincia_id();
        mProvinciaName = partner.getProvincia();
        lProvincia = partner.getProvincia();

        mCantonId = partner.getCanton_id();
        mCantonName = partner.getCanton();
        lCanton = partner.getCanton();

        mNapId = partner.getNap_id();
        mNapName = partner.getNap();
        lNap = partner.getNap();
        mUserId = partner.getUser_id();
        mAppId = partner.getApp_id();
        mVendedorField.setText(partner.getVendedor());

        if ( hasBytes(partner.getFotoCasa()) ){
            mByteCasa = partner.getFotoCasa();
            nombreCasa = partner.getNombreCasa();
            Bitmap casa = BitmapFactory.decodeByteArray(mByteCasa, 0, mByteCasa.length);
            mImageCasa.setImageBitmap(casa);
        }

        if(partner.getId() != null){
            mRefer.setVisibility(View.VISIBLE);
            mRefresh.setVisibility(View.VISIBLE);
            mRefer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customerManagement = new CustomerManagement(getActivity(), partner);
                    customerManagement.startManagementDialog(mSale);
                }
            });
        }

        if(partner.getProvincia_id() != null){
            mProvincia.setSelection(getIndex(mProvincia,partner.getProvincia()));
            if(partner.getCanton_id() != null){
                LoadCanton(partner.getProvincia_id());
                mCanton.setSelection(getIndex(mCanton,partner.getCanton()));
                LoadNap(partner.getCanton_id());
                mNap.setSelection(getIndex(mNap, partner.getNap()));
            }
        }

        if(partner.getVenta() != null){
            mSale.setSelection(getIndex(mSale,partner.getVenta()));
        }

        if(partner.getState().equals("A")){
            mVendedorField.setEnabled(false);
        }

        loadServices();
    }

    private void showListServices(InterestService service) {
        String[] serv = service.getServices().split(",");
        for (int i = 0; i < serv.length; i++){
            for (int j = 0; j < mServiceList.getCount(); j++){
                int item = (int) mServiceList.getItemIdAtPosition(j) + 1;
                if ( serv[i].equals(String.valueOf(item)) ) {
                    mServiceList.setItemChecked(j, true);
                }
            }
        }

        String[] actual = service.getActualService().split(",");
        for (int i = 0; i < actual.length; i++){
            String[] ser_ac = actual[i].split("-");
            arrayActualService.add(new ActualService(String.valueOf(i), ser_ac[0], ser_ac[1]));
            adapterActual.notifyDataSetChanged();
            adapterListView(arrayActualService.size(), adapterActual, mActualServiceList);
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

    private void adapterListView(int i, Adapter adapter, ListView list){
        int totalHeight = 0;
        for (int size = 0; size < i; size++) {
            View listItem = adapter.getView(size, null, list);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // Change Height of ListView
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = (totalHeight + (list.getDividerHeight() * (i)));
        list.setLayoutParams(params);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
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
                mPartnerId = partner[0].getId();
                return mPartnerDbHelper.savePartner(partner[0]) > 0;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result != null){
                addEditServiceList();
            }
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

    private class AddEditServicesTask extends AsyncTask<InterestService, Void, Boolean> {

        @Override
        protected Boolean doInBackground(InterestService... aserv) {
//            return mPartnerDbHelper.saveServices(aserv[0]) > 0;
            if (mServiceId != null) {
                return mPartnerDbHelper.updateServices(aserv[0], mServiceId) > 0;
            } else {
                return mPartnerDbHelper.saveServices(aserv[0]) > 0;
            }
        }

    }

    private void loadManagement() { new ManagementLoadTask().execute(); }

    private class ManagementLoadTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mPartnerDbHelper.getAllManagementByReferId(mPartnerId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Log.d(TAG, "onPostExecute() returned: " + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                mManagementAdapter.swapCursor(cursor);
                adapterListView(cursor.getCount(), mManagementAdapter, mManagementList);
                cursor.moveToFirst();
                Management manage = new Management(cursor);

                if (manage.getMotive().equals("VENTA CONCRETADA")){
                    mSale.setSelection(getIndex(mSale,"VENTA CONCRETADA"));
                }
                else if (manage.getMotive().equals("VENTA NO CONCRETADA")){
                    mSale.setSelection(getIndex(mSale,"VENTA NO CONCRETADA"));
                }
                else if (manage.getMotive().equals("NO INTERESADO")){
                    mSale.setSelection(getIndex(mSale,"NO INTERESADO"));
                }
                else{
                    mSale.setSelection(0);
                }
            }
        }
    }

    private void loadServices() { new ServiceLoadTask().execute(); }

    private class ServiceLoadTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mPartnerDbHelper.getAllRowsby("tb_list_service", "refer =?", mPartnerId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Log.d(TAG, "onPostExecute() returned: " + cursor.getCount());
            if (cursor != null && cursor.moveToLast()) {
                showListServices(new InterestService(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
            cursor.close();
            loadManagement();
        }
    }

}
