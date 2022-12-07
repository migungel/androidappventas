package com.dvnet.agreement.data.partner;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static com.dvnet.agreement.data.partner.PartnerContract.PartnerEntry;

import com.dvnet.agreement.data.Canton;

public class Partner {

    private String id;
    private String firstln;
    private String secondln;
    private String name;
    private String identification_id;
    private String street;
    private String principal;
    private String secundaria;
    private String mz;
    private String nro;
    private String number;
    private String street2;
    private String phone;
    private String mobile;
    private String email;
    private String coordenadas;
    private String cod_cnel;
    private String cod_agua;
    private String nacimiento;
    private String provincia_id;
    private String provincia;
    private String typeid_id;
    private String typeid;
    private String typedir_id;
    private String typedir;
    private String service_id;
    private String service;
    private String canton_id;
    private String canton;
    private String ref_familiar;
    private String num_ref_familiar;
    private String ref_personal;
    private String num_ref_personal;
    private String state;
    private String used;
    private String user_id;
    private String venta;
    private String app_id;
    private String vendedor;
    private String refer;
    private String nap_id;
    private String nap;
    private String validate;
    private byte[] fotoCasa;
    private String nombreCasa;

    public Partner (){}

    public Partner(String id,
                   String firstln,
                   String secondln,
                   String name,
                   String street,
                   String mobile,
                   String email,
                   String provincia_id,
                   String provincia,
                   String canton_id,
                   String canton,
                   String vendedor,
                   String state,
                   String used,
                   String user_id,
                   String venta,
                   String app_id,
                   String refer,
                   String coordenadas,
                   String nap_id,
                   String nap,
                   byte[] fotoCasa,
                   String nombreCasa
                   ) {
        if (id != "0")
        {
            this.id = id;
        }
        else
        {
            this.id = UUID.randomUUID().toString();
        }

        this.firstln = firstln;
        this.secondln = secondln;
        this.name = name;
        this.identification_id = "";
        this.street = street;
        this.principal = "";
        this.secundaria = "";
        this.mz = "";
        this.nro = "";
        this.street2 = "";
        this.number = nro;
        this.phone = "";
        this.mobile = mobile;
        this.email = email;
        this.coordenadas = "";
        this.cod_cnel = "";
        this.cod_agua = "";
        this.nacimiento = "";
        this.provincia_id = provincia_id;
        this.provincia = provincia;
        this.typeid_id = "";
        this.typeid = "";
        this.typedir_id = "";
        this.typedir = "";
        this.service_id = "";
        this.service = "";
        this.canton_id = canton_id;
        this.canton = canton;
        this.ref_familiar = "";
        this.num_ref_familiar = "";
        this.ref_personal = "";
        this.num_ref_personal = "";
        this.state = state;
        this.used = used;
        this.user_id = user_id;
        this.venta = venta;
        this.app_id = app_id;
        this.vendedor = vendedor;
        this.refer = refer;
        this.coordenadas = coordenadas;
        this.nap_id = nap_id;
        this.nap = nap;
        this.validate = "0";
        this.fotoCasa = fotoCasa;
        this.nombreCasa = nombreCasa;
    }

    public Partner(String id,
                   String firstln,
                   String secondln,
                   String name,
                   String identification_id,
                   String principal,
                   String secundaria,
                   String mz,
                   String nro,
                   String street,
                   String street2,
                   String phone,
                   String mobile,
                   String email,
                   String coordenadas,
                   String cod_cnel,
                   String cod_agua,
                   String nacimiento,
                   String provincia_id,
                   String provincia,
                   String typeid_id,
                   String typeid,
                   String typedir_id,
                   String typedir,
                   String service_id,
                   String service,
                   String canton_id,
                   String canton,
                   String ref_familiar,
                   String num_ref_familiar,
                   String ref_personal,
                   String num_ref_personal,
                   String state,
                   String used,
                   String user_id,
                   String venta,
                   String app_id,
                   String refer,
                   String validate,
                   byte[] fotoCasa,
                   String nombreCasa
        ) {
        if (id != "0")
        {
            this.id = id;
        }
        else
        {
            this.id = UUID.randomUUID().toString();
        }

        this.firstln = firstln;
        this.secondln = secondln;
        this.name = name;
        this.identification_id = identification_id;
        this.street = street;
        this.principal = principal;
        this.secundaria = secundaria;
        this.mz = mz;
        this.nro = nro;
        this.street2 = street2;
        this.number = nro;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.coordenadas = coordenadas;
        this.cod_cnel = cod_cnel;
        this.cod_agua = cod_agua;
        this.nacimiento = nacimiento;
        this.provincia_id = provincia_id;
        this.provincia = provincia;
        this.typeid_id = typeid_id;
        this.typeid = typeid;
        this.typedir_id = typedir_id;
        this.typedir = typedir;
        this.service_id = service_id;
        this.service = service;
        this.canton_id = canton_id;
        this.canton = canton;
        this.ref_familiar = ref_familiar;
        this.num_ref_familiar = num_ref_familiar;
        this.ref_personal = ref_personal;
        this.num_ref_personal = num_ref_personal;
        this.state = state;
        this.used = used;
        this.user_id = user_id;
        this.venta = venta;
        this.app_id = app_id;
        this.refer = refer;
        this.vendedor = "";
        this.nap_id = "0";
        this.nap = "";
        this.validate = validate;
        this.fotoCasa = fotoCasa;
        this.nombreCasa = nombreCasa;
    }

    public Partner(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(PartnerEntry.ID));
        firstln = cursor.getString(cursor.getColumnIndex(PartnerEntry.FIRSTLN));
        secondln = cursor.getString(cursor.getColumnIndex(PartnerEntry.SECONDLN));
        name = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAME));
        identification_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.IDENTIFICATION_ID));
        street = cursor.getString(cursor.getColumnIndex(PartnerEntry.STREET));
        principal = cursor.getString(cursor.getColumnIndex(PartnerEntry.PRINCIPAL));
        secundaria = cursor.getString(cursor.getColumnIndex(PartnerEntry.SECUNDARIA));
        mz = cursor.getString(cursor.getColumnIndex(PartnerEntry.MZ));
        nro = cursor.getString(cursor.getColumnIndex(PartnerEntry.NRO));
        number = cursor.getString(cursor.getColumnIndex(PartnerEntry.NUMBER));
        street2 = cursor.getString(cursor.getColumnIndex(PartnerEntry.STREET2));
        phone = cursor.getString(cursor.getColumnIndex(PartnerEntry.PHONE));
        mobile = cursor.getString(cursor.getColumnIndex(PartnerEntry.MOBILE));
        email = cursor.getString(cursor.getColumnIndex(PartnerEntry.EMAIL));
        coordenadas = cursor.getString(cursor.getColumnIndex(PartnerEntry.COORDENADAS));
        cod_cnel = cursor.getString(cursor.getColumnIndex(PartnerEntry.COD_CNEL));
        cod_agua = cursor.getString(cursor.getColumnIndex(PartnerEntry.COD_AGUA));
        nacimiento = cursor.getString(cursor.getColumnIndex(PartnerEntry.NACIMIENTO));
        provincia_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.PROVINCIA_ID));
        provincia = cursor.getString(cursor.getColumnIndex(PartnerEntry.PROVINCIA));
        typeid_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.TYPEID_ID));
        typeid = cursor.getString(cursor.getColumnIndex(PartnerEntry.TYPEID));
        typedir_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.TYPEDIR_ID));
        typedir = cursor.getString(cursor.getColumnIndex(PartnerEntry.TYPEDIR));
        service_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.SERVICE_ID));
        service = cursor.getString(cursor.getColumnIndex(PartnerEntry.SERVICE));
        canton_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.CANTON_ID));
        canton = cursor.getString(cursor.getColumnIndex(PartnerEntry.CANTON));
        ref_familiar = cursor.getString(cursor.getColumnIndex(PartnerEntry.REF_FAMILIAR));
        num_ref_familiar = cursor.getString(cursor.getColumnIndex(PartnerEntry.NUM_REF_FAMILIAR));
        ref_personal = cursor.getString(cursor.getColumnIndex(PartnerEntry.REF_PERSONAL));
        num_ref_personal = cursor.getString(cursor.getColumnIndex(PartnerEntry.NUM_REF_PERSONAL));
        state = cursor.getString(cursor.getColumnIndex(PartnerEntry.STATE));
        used = cursor.getString(cursor.getColumnIndex(PartnerEntry.USED));
        user_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.USER_ID));
        venta = cursor.getString(cursor.getColumnIndex(PartnerEntry.VENTA));
        app_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.APP_ID));
        vendedor = cursor.getString(cursor.getColumnIndex(PartnerEntry.VENDEDOR));
        refer = cursor.getString(cursor.getColumnIndex(PartnerEntry.REFER));
        nap_id = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAP_ID));
        nap = cursor.getString(cursor.getColumnIndex(PartnerEntry.NAP));
        validate = cursor.getString(cursor.getColumnIndex(PartnerEntry.VALIDATE));
        fotoCasa = cursor.getBlob(cursor.getColumnIndex(PartnerEntry.FOTOCASA));
        nombreCasa = cursor.getString(cursor.getColumnIndex(PartnerEntry.NOMBRECASA));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(PartnerEntry.ID, id);
        values.put(PartnerEntry.FIRSTLN, firstln);
        values.put(PartnerEntry.SECONDLN, secondln);
        values.put(PartnerEntry.NAME, name);
        values.put(PartnerEntry.IDENTIFICATION_ID, identification_id);
        values.put(PartnerEntry.STREET, street);
        values.put(PartnerEntry.PRINCIPAL, principal);
        values.put(PartnerEntry.SECUNDARIA, secundaria);
        values.put(PartnerEntry.MZ, mz);
        values.put(PartnerEntry.NRO, nro);
        values.put(PartnerEntry.NUMBER, number);
        values.put(PartnerEntry.STREET2, street2);
        values.put(PartnerEntry.PHONE, phone);
        values.put(PartnerEntry.MOBILE, mobile);
        values.put(PartnerEntry.EMAIL, email);
        values.put(PartnerEntry.COORDENADAS, coordenadas);
        values.put(PartnerEntry.COD_CNEL, cod_cnel);
        values.put(PartnerEntry.COD_AGUA, cod_agua);
        values.put(PartnerEntry.NACIMIENTO, nacimiento);
        values.put(PartnerEntry.PROVINCIA_ID, provincia_id);
        values.put(PartnerEntry.PROVINCIA, provincia);
        values.put(PartnerEntry.TYPEID_ID, typeid_id);
        values.put(PartnerEntry.TYPEID, typeid);
        values.put(PartnerEntry.TYPEDIR_ID, typedir_id);
        values.put(PartnerEntry.TYPEDIR, typedir);
        values.put(PartnerEntry.SERVICE_ID, service_id);
        values.put(PartnerEntry.SERVICE, service);
        values.put(PartnerEntry.CANTON_ID, canton_id);
        values.put(PartnerEntry.CANTON, canton);
        values.put(PartnerEntry.REF_FAMILIAR, ref_familiar);
        values.put(PartnerEntry.NUM_REF_FAMILIAR, num_ref_familiar);
        values.put(PartnerEntry.REF_PERSONAL, ref_personal);
        values.put(PartnerEntry.NUM_REF_PERSONAL, num_ref_personal);
        values.put(PartnerEntry.STATE, state);
        values.put(PartnerEntry.USED, used);
        values.put(PartnerEntry.USER_ID, user_id);
        values.put(PartnerEntry.VENTA, venta);
        values.put(PartnerEntry.APP_ID, app_id);
        values.put(PartnerEntry.VENDEDOR, vendedor);
        values.put(PartnerEntry.REFER, refer);
        values.put(PartnerEntry.NAP_ID, nap_id);
        values.put(PartnerEntry.NAP, nap);
        values.put(PartnerEntry.VALIDATE, validate);
        values.put(PartnerEntry.FOTOCASA, fotoCasa);
        values.put(PartnerEntry.NOMBRECASA, nombreCasa);
        return values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getFirstln() { return firstln; }

    public String getSecondln() { return secondln; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getIdentification_id() {
        return identification_id;
    }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getPrincipal() { return principal; }

    public void setPrincipal(String principal) { this.principal = principal; }

    public String getSecundaria() { return secundaria; }

    public void setSecundaria(String secundario) { this.secundaria = secundario; }

    public String getMz() { return mz; }

    public void setMz(String mz) { this.mz = mz; }

    public String getNro() { return nro; }

    public void setNro(String nro) { this.nro = nro; }

    public String getNumber() { return number; }

    public String getStreet2() {
        return street2;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() {
        return email;
    }

    public String getCoordenadas() { return coordenadas; }

    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }

    public String getCod_cnel() { return cod_cnel; }

    public void setCod_cnel(String cnel) { this.cod_cnel = cnel; }

    public String getCod_agua() { return cod_agua; }

    public void setCod_agua(String agua) { this.cod_agua = agua; }

    public String getNacimiento() { return nacimiento; }

    public String getProvincia_id() { return provincia_id; }

    public void setProvincia_id(String provincia_id) { this.provincia_id = provincia_id; }

    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getTypeid_id() { return typeid_id; }

    public String getTypeid() { return typeid; }

    public String getTypedir_id() { return typedir_id; }

    public String getTypedir() { return typedir; }

    public String getServiceId() { return service_id; }

    public String getService() { return service; }

    public String getCanton_id() { return canton_id; }

    public void setCanton_id(String canton_id) { this.canton_id = canton_id; }

    public String getCanton() { return canton; }

    public void setCanton(String canton) { this.canton = canton; }

    public String getRef_familiar() { return ref_familiar; }

    public void setRef_familiar(String ref_familiar) { this.ref_familiar = ref_familiar; }

    public String getNum_ref_familiar() { return num_ref_familiar; }

    public void setNum_ref_familiar(String num_ref_familiar) { this.num_ref_familiar = num_ref_familiar; }

    public String getRef_personal() { return ref_personal; }

    public void setRef_personal(String ref_personal) { this.ref_personal = ref_personal; }

    public String getNum_ref_personal() { return num_ref_personal; }

    public void setNum_ref_personal(String num_ref_personal) { this.num_ref_personal = num_ref_personal; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getUsed() { return used; }

    public void setUsed(String used) { this.used = used; }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getVenta() { return venta; }

    public void setVenta(String venta) { this.venta = venta; }

    public String getApp_id() { return app_id; }

    public void setApp_id(String app_id) { this.app_id = app_id; }

    public String getVendedor() { return vendedor; }

    public void setVendedor(String vendedor) { this.vendedor = vendedor; }

    public String getRefer() { return refer; }

    public void setRefer(String refer) { this.refer = refer; }

    public String getNap_id() { return nap_id; }

    public void setNap_id(String nap_id) { this.nap_id = nap_id; }

    public String getNap() { return nap; }

    public void setNap(String nap) { this.nap = nap; }

    public String getValidate() { return validate; }

    public void setValidate(String validate) { this.validate = validate; }

    public byte[] getFotoCasa() { return fotoCasa; }

    public String getNombreCasa() { return nombreCasa; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Partner){
            Partner p = (Partner) obj;
            if(p.getName().equals(name) &&
                    p.getId().equals(id) &&
                    p.getFirstln().equals(firstln) &&
                    p.getSecondln().equals(secondln) &&
                    p.getName().equals(name) &&
                    p.getIdentification_id().equals(identification_id) &&
                    p.getStreet().equals(street) &&
                    p.getPrincipal().equals(principal) &&
                    p.getSecundaria().equals(secundaria) &&
                    p.getMz().equals(mz) &&
                    p.getNro().equals(nro) &&
                    p.getNumber().equals(number) &&
                    p.getStreet2().equals(street2) &&
                    p.getPhone().equals(phone) &&
                    p.getMobile().equals(mobile) &&
                    p.getEmail().equals(email) &&
                    p.getCoordenadas().equals(coordenadas) &&
                    p.getCod_cnel().equals(cod_cnel) &&
                    p.getCod_agua().equals(cod_agua) &&
                    p.getNacimiento().equals(nacimiento) &&
                    p.getProvincia_id().equals(provincia_id) &&
                    p.getProvincia().equals(provincia) &&
                    p.getTypeid_id().equals(typeid_id) &&
                    p.getTypeid().equals(typeid) &&
                    p.getTypedir_id().equals(typedir_id) &&
                    p.getTypedir().equals(typedir) &&
                    p.getServiceId().equals(service_id) &&
                    p.getService().equals(service) &&
                    p.getCanton_id().equals(canton_id) &&
                    p.getCanton().equals(canton) &&
                    p.getRef_familiar().equals(ref_familiar) &&
                    p.getNum_ref_familiar().equals(num_ref_familiar) &&
                    p.getRef_personal().equals(ref_personal) &&
                    p.getNum_ref_personal().equals(num_ref_personal) &&
                    p.getState().equals(state) &&
                    p.getUsed().equals(used) &&
                    p.getUser_id().equals(user_id) &&
                    p.getVenta().equals(venta) &&
                    p.getApp_id().equals(app_id) &&
                    p.getVendedor().equals(vendedor) &&
                    p.getRefer().equals(refer) &&
                    p.getNap_id().equals(nap_id) &&
                    p.getNap().equals(nap)
            ) return true;
        }
        return false;
    }
}
