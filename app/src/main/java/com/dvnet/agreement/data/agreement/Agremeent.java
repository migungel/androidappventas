package com.dvnet.agreement.data.agreement;

import android.app.Person;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.sip.SipSession;

import static com.dvnet.agreement.data.agreement.AgreementContract.AgreementEntry;

import com.dvnet.agreement.data.partner.PartnerContract;

import java.util.Stack;
import java.util.UUID;

import kotlin.text.UStringsKt;

public class Agremeent
{
    private String id;
    private String Partner_Id;
    private String Partner;
    private String Company_Id;
    private String Company;
    private String TipoServicio_Id;
    private String TipoServicio;
    private String Plan_id;
    private String Plan;
    private String Smart_id;
    private String Smart;
    private String Canton_id;
    private String Canton;
    private String Sector_id;
    private String Sector;
    private String Poste_id;
    private String Poste;
    private String Nap_id;
    private String Nap;
    private String TipVivienda_id;
    private String TipVivienda;
    private String Autorizacion_id;
    private String Autorizacion;
    private String State;
    private byte[] Sing;
    private byte[] FotoCedula;
    private String nombreCedula;
    private byte[] FotoCedulaPost;
    private String nombreCedulaPost;
    private byte[] FotoPlanilla;
    private String nombrePlanilla;
    private byte[] FotoCasa;
    private String nombreCasa;
    private byte[] contratoPDF;
    private String nombrePDF;
    private String Recibo;
    private double Valor;
    private String User_id;
    private String Observacion;
    private String Contrato;
    private String tipoContrato;

    public Agremeent(String id,
                     String Partner_Id,
                     String Partner,
                     String company_Id,
                     String company,
                     String tipoServicio_Id,
                     String tipoServicio,
                     String plan_id,
                     String plan,
                     String smart_id,
                     String smart,
                     String canton_id,
                     String canton,
                     String sector_id,
                     String sector,
                     String poste_id,
                     String poste,
                     String nap_id,
                     String nap,
                     String tipVivienda_id,
                     String tipVivienda,
                     String autorizacion_id,
                     String autorizacion,
                     String state,
                     byte[] Sing,
                     byte[] FotoCedula,
                     String nombreCedula,
                     byte[] FotoCedulaPost,
                     String nombreCedulaPost,
                     byte[] FotoPlanilla,
                     String nombrePlanilla,
                     byte[] FotoCasa,
                     String nombreCasa,
                     byte[] contratoPDF,
                     String nombrePDF,
                     String Recibo,
                     double Valor,
                     String User_id,
                     String Observacion,
                     String Contrato,
                     String tipoContrato) {
        if (id != "0")
        {
            this.id = id;
        }
        else
        {
            this.id = UUID.randomUUID().toString();
        }
        this.Partner_Id = Partner_Id;
        this.Partner = Partner;
        this.Company_Id = company_Id;
        this.Company = company;
        this.TipoServicio_Id = tipoServicio_Id;
        this.TipoServicio = tipoServicio;
        this.Plan_id = plan_id;
        this.Plan = plan;
        this.Smart_id = smart_id;
        this.Smart = smart;
        this.Canton_id = canton_id;
        this.Canton = canton;
        this.Sector_id = sector_id;
        this.Sector = sector;
        this.Poste_id = poste_id;
        this.Poste = poste;
        this.Nap_id = nap_id;
        this.Nap = nap;
        this.TipVivienda_id = tipVivienda_id;
        this.TipVivienda = tipVivienda;
        this.Autorizacion_id = autorizacion_id;
        this.Autorizacion = autorizacion;
        this.State = state;
        this.Sing = Sing;
        this.FotoCedula = FotoCedula;
        this.nombreCedula = nombreCedula;
        this.FotoCedulaPost = FotoCedulaPost;
        this.nombreCedulaPost = nombreCedulaPost;
        this.FotoPlanilla = FotoPlanilla;
        this.nombrePlanilla = nombrePlanilla;
        this.FotoCasa = FotoCasa;
        this.nombreCasa = nombreCasa;
        this.contratoPDF = contratoPDF;
        this.nombrePDF = nombrePDF;
        this.Recibo = Recibo;
        this.Valor = Valor;
        this.User_id = User_id;
        this.Observacion = Observacion;
        this.Contrato = Contrato;
        this.tipoContrato = tipoContrato;
    }

    public Agremeent(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(AgreementEntry.ID));
        Partner_Id = cursor.getString(cursor.getColumnIndex(AgreementEntry.PARTNER_ID));
        Partner = cursor.getString(cursor.getColumnIndex(AgreementEntry.PARTNER));
        Company_Id = cursor.getString(cursor.getColumnIndex(AgreementEntry.COMPANY_ID));
        Company = cursor.getString(cursor.getColumnIndex(AgreementEntry.COMPANY));
        TipoServicio_Id = cursor.getString(cursor.getColumnIndex(AgreementEntry.TIPOSERVICIO_ID));
        TipoServicio = cursor.getString(cursor.getColumnIndex(AgreementEntry.TIPOSERVICIO));
        Plan_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.PLAN_ID));
        Plan = cursor.getString(cursor.getColumnIndex(AgreementEntry.PLAN));
        Smart_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.SMART_ID));
        Smart = cursor.getString(cursor.getColumnIndex(AgreementEntry.SMART));
        Sector_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.SECTOR_ID));
        Sector = cursor.getString(cursor.getColumnIndex(AgreementEntry.SECTOR));
        Canton_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.CANTON_ID));
        Canton = cursor.getString(cursor.getColumnIndex(AgreementEntry.CANTON));
        Poste_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.POSTE_ID));
        Poste = cursor.getString(cursor.getColumnIndex(AgreementEntry.POSTE));
        Nap_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.NAP_ID));
        Nap = cursor.getString(cursor.getColumnIndex(AgreementEntry.NAP));
        TipVivienda_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.TIPOVIVIENDA_ID));
        TipVivienda = cursor.getString(cursor.getColumnIndex(AgreementEntry.TIPOVIVIENDA));
        Autorizacion_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.AUTORIZACION_ID));
        Autorizacion = cursor.getString(cursor.getColumnIndex(AgreementEntry.AUTORIZACION));
        State = cursor.getString(cursor.getColumnIndex(AgreementEntry.STATE));
        Sing = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.SING));
        FotoCedula = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.FOTOCEDULA));
        nombreCedula = cursor.getString(cursor.getColumnIndex(AgreementEntry.NOMBRECEDULA));
        FotoCedulaPost = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.FOTOCEDULAPOST));
        nombreCedulaPost = cursor.getString(cursor.getColumnIndex(AgreementEntry.NOMBRECEDULAPOST));
        FotoPlanilla = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.FOTOPLANILLA));
        nombrePlanilla = cursor.getString(cursor.getColumnIndex(AgreementEntry.NOMBREPLANILLA));
        FotoCasa = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.FOTOCASA));
        nombreCasa = cursor.getString(cursor.getColumnIndex(AgreementEntry.NOMBRECASA));
        contratoPDF = cursor.getBlob(cursor.getColumnIndex(AgreementEntry.CONTRATOPDF));
        nombrePDF = cursor.getString(cursor.getColumnIndex(AgreementEntry.NOMBREPDF));
        Recibo = cursor.getString(cursor.getColumnIndex(AgreementEntry.RECIBO));
        Valor = cursor.getDouble(cursor.getColumnIndex(AgreementEntry.VALOR));
        User_id = cursor.getString(cursor.getColumnIndex(AgreementEntry.USER_ID));
        Observacion = cursor.getString(cursor.getColumnIndex(AgreementEntry.OBSERVACION));
        Contrato = cursor.getString(cursor.getColumnIndex(AgreementEntry.CONTRATO));
        tipoContrato = cursor.getString(cursor.getColumnIndex(AgreementEntry.TIPOCONTRATO));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(AgreementEntry.ID, id);
        values.put(AgreementEntry.PARTNER_ID, Partner_Id);
        values.put(AgreementEntry.PARTNER, Partner);
        values.put(AgreementEntry.COMPANY_ID, Company_Id);
        values.put(AgreementEntry.COMPANY, Company);
        values.put(AgreementEntry.TIPOSERVICIO_ID, TipoServicio_Id);
        values.put(AgreementEntry.TIPOSERVICIO, TipoServicio);
        values.put(AgreementEntry.PLAN_ID, Plan_id);
        values.put(AgreementEntry.PLAN, Plan);
        values.put(AgreementEntry.SMART_ID, Smart_id);
        values.put(AgreementEntry.SMART, Smart);
        values.put(AgreementEntry.CANTON_ID, Canton_id);
        values.put(AgreementEntry.CANTON, Canton);
        values.put(AgreementEntry.SECTOR_ID, Sector_id);
        values.put(AgreementEntry.SECTOR, Sector);
        values.put(AgreementEntry.POSTE_ID, Poste_id);
        values.put(AgreementEntry.POSTE, Poste);
        values.put(AgreementEntry.NAP_ID, Nap_id);
        values.put(AgreementEntry.NAP, Nap);
        values.put(AgreementEntry.TIPOVIVIENDA_ID, TipVivienda_id);
        values.put(AgreementEntry.TIPOVIVIENDA, TipVivienda);
        values.put(AgreementEntry.AUTORIZACION_ID, Autorizacion_id);
        values.put(AgreementEntry.AUTORIZACION, Autorizacion);
        values.put(AgreementEntry.STATE, State);
        values.put(AgreementEntry.SING, Sing);
        values.put(AgreementEntry.FOTOCEDULA, FotoCedula);
        values.put(AgreementEntry.NOMBRECEDULA, nombreCedula);
        values.put(AgreementEntry.FOTOCEDULAPOST, FotoCedulaPost);
        values.put(AgreementEntry.NOMBRECEDULAPOST, nombreCedulaPost);
        values.put(AgreementEntry.FOTOPLANILLA, FotoPlanilla);
        values.put(AgreementEntry.NOMBREPLANILLA, nombrePlanilla);
        values.put(AgreementEntry.FOTOCASA, FotoCasa);
        values.put(AgreementEntry.NOMBRECASA, nombreCasa);
        values.put(AgreementEntry.CONTRATOPDF, contratoPDF);
        values.put(AgreementEntry.NOMBREPDF, nombrePDF);
        values.put(AgreementEntry.RECIBO, Recibo);
        values.put(AgreementEntry.VALOR, Valor);
        values.put(AgreementEntry.USER_ID, User_id);
        values.put(AgreementEntry.OBSERVACION, Observacion);
        values.put(AgreementEntry.CONTRATO, Contrato);
        values.put(AgreementEntry.TIPOCONTRATO, tipoContrato);
        return values;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getPartner_Id() { return Partner_Id; }

    public void setPartner_Id(String partner_Id) { Partner_Id = partner_Id; }

    public String getPartner() { return Partner; }

    public void setPartner(String partner) { Partner = partner; }

    public String getCompany_Id() { return Company_Id; }

    public void setCompany_Id(String company_Id) { Company_Id = company_Id; }

    public String getCompany() { return Company; }

    public void setCompany(String company) { Company = company; }

    public String getTipoServicio_Id() { return TipoServicio_Id; }

    public void setTipoServicio_Id(String tipoServicio_Id) { TipoServicio_Id = tipoServicio_Id; }

    public String getTipoServicio() { return TipoServicio; }

    public void setTipoServicio(String tipoServicio) { TipoServicio = tipoServicio; }

    public String getPlan_id() { return Plan_id; }

    public void setPlan_id(String plan_id) { Plan_id = plan_id; }

    public String getPlan() { return Plan; }

    public void setPlan(String plan) { Plan = plan; }

    public String getSmart_id() { return Smart_id; }

    public void setSmart_id(String smart_id) { Smart_id = smart_id; }

    public String getSmart() { return Smart; }

    public void setSmart(String smart) { Smart = smart; }

    public String getCanton_id() {
        return Canton_id;
    }

    public void setCanton_id(String canton_id) {
        Canton_id = canton_id;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String canton) {
        Canton = canton;
    }

    public String getSector_id() {
        return Sector_id;
    }

    public void setSector_id(String sector_id) {
        Sector_id = sector_id;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public String getPoste_id() {
        return Poste_id;
    }

    public void setPoste_id(String poste_id) {
        Poste_id = poste_id;
    }

    public String getPoste() {
        return Poste;
    }

    public void setNap_id(String nap_id) {
        Nap_id = nap_id;
    }

    public String getNap_id() {
        return Nap_id;
    }

    public void setNap(String nap) {
        Nap = nap;
    }

    public String getNap() {
        return Nap;
    }

    public void setPoste(String poste) {
        Poste = poste;
    }

    public String getTipVivienda_id() {
        return TipVivienda_id;
    }

    public void setTipVivienda_id(String tipVivienda_id) {
        TipVivienda_id = tipVivienda_id;
    }

    public String getTipVivienda() {
        return TipVivienda;
    }

    public void setTipVivienda(String tipVivienda) {
        TipVivienda = tipVivienda;
    }

    public String getAutorizacion_id() {
        return Autorizacion_id;
    }

    public void setAutorizacion_id(String autorizacion_id) {
        Autorizacion_id = autorizacion_id;
    }

    public String getAutorizacion() {
        return Autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        Autorizacion = autorizacion;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public byte[] getSing() { return Sing; }

    public void setSing(byte[] sing) { Sing = sing; }

    public byte[] getFotoCedula() { return FotoCedula; }

    public void setFotoCedula(byte[] foto) { FotoCedula = foto; }

    public String getNombreCedula() { return nombreCedula; }

    public void setNombreCedula(String nombreCedula) { nombreCedula = nombreCedula; }

    public byte[] getFotoCedulaPost() { return FotoCedulaPost; }

    public void setFotoCedulaPost(byte[] foto) { FotoCedulaPost = foto; }

    public String getNombreCedulaPost() { return nombreCedulaPost; }

    public void setNombreCedulaPost(String nombreCedulaPost) { nombreCedulaPost = nombreCedulaPost; }

    public byte[] getFotoPlanilla() { return FotoPlanilla; }

    public void setFotoPlanilla(byte[] foto) { FotoPlanilla = foto; }

    public String getNombrePlanilla() { return nombrePlanilla; }

    public void setNombrePlanilla(String nombrePlanilla) { nombrePlanilla = nombrePlanilla; }

    public byte[] getFotoCasa() { return FotoCasa; }

    public void setFotoCasa(byte[] foto) { FotoCasa = foto; }

    public String getNombreCasa() { return nombreCasa; }

    public void setNombreCasa(String nombreCasa) { nombreCasa = nombreCasa; }

    public byte[] getPDF() { return contratoPDF; }

    public void setPDF(byte[] pdf) { contratoPDF = pdf; }

    public String getNombrePDF() { return nombrePDF; }

    public void setNombrePDF(String nombrePDF) { nombrePDF = nombrePDF; }

    public String getRecibo() {
        return Recibo;
    }

    public void setRecibo(String recibo) {
        Recibo = recibo;
    }

    public double getValor() { return Valor; }

    public void setValor(double valor) { Valor = valor; }

    public String getUser_id() { return User_id; }

    public void setUser_id(String user_id) { User_id = user_id; }

    public String getObservacion() { return Observacion; }

    public void setObservacion(String observacion) { Observacion = observacion; }

    public String getContrato() { return Contrato; }

    public void setContrato(String contrato) { Contrato = contrato; }

    public String getTipoContrato() { return tipoContrato; }

    public void setTipoContrato(String tipoContrato) { tipoContrato = tipoContrato; }

}