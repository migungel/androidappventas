package com.dvnet.agreement;

public class ConsultaCliente {
    private String partner_id;
    private String partner;
    private String agreement_id;
    private String agreement;
    private String state;
    private String type_service;
    private String street;
    private String canton;
    private String company;
    private String valor;

    public ConsultaCliente(String partner_id,
                           String partner,
                           String agreement_id,
                           String agreement,
                           String state,
                           String type_service,
                           String street,
                           String canton,
                           String company,
                           String valor) {
        this.partner_id = partner_id;
        this.partner = partner;
        this.agreement_id = agreement_id;
        this.agreement = agreement;
        this.state = state;
        this.type_service = type_service;
        this.street = street;
        this.canton = canton;
        this.company = company;
        this.valor = valor;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getAgreement_id() {
        return agreement_id;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType_service() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
