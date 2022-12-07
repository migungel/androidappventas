package com.dvnet.agreement.data.agreement;

import android.provider.BaseColumns;

public class AgreementContract {

    private AgreementContract() {
    }

    public static abstract class AgreementEntry implements BaseColumns {

        public static final String TABLE_NAME = "account_analytic_account";
        public static final String ID = "id";
        public static final String PARTNER_ID = "partner_id";
        public static final String PARTNER = "partner";
        public static final String COMPANY_ID = "company_id";
        public static final String COMPANY = "company";
        public static final String TIPOSERVICIO_ID = "type_service";
        public static final String TIPOSERVICIO = "type_service_name";
        public static final String PLAN_ID = "plan_id";
        public static final String PLAN = "plan_name";
        public static final String SMART_ID = "smart_id";
        public static final String SMART = "smart_name";
        public static final String SECTOR_ID = "sector_id";
        public static final String SECTOR = "sector_name";
        public static final String CANTON_ID = "city_id";
        public static final String CANTON = "city_name";
        public static final String TIPOVIVIENDA_ID = "housing_type_id";
        public static final String TIPOVIVIENDA = "housing_type_name";
        public static final String POSTE_ID = "poste_id";
        public static final String POSTE = "poste_name";
        public static final String NAP_ID = "nap_id";
        public static final String NAP = "nap_name";
        public static final String AUTORIZACION_ID = "autorizacion_id";
        public static final String AUTORIZACION = "autorizacion_name";
        public static final String STATE = "state";
        public static final String SING = "signature";
        public static final String FOTOCEDULA = "doc_identification";
        public static final String NOMBRECEDULA = "doc_name_id";
        public static final String FOTOCEDULAPOST = "doc_identification_post";
        public static final String NOMBRECEDULAPOST = "doc_name_id_post";
        public static final String FOTOPLANILLA = "doc_servicebasic";
        public static final String NOMBREPLANILLA = "doc_name_serbasic";
        public static final String FOTOCASA = "photo_house";
        public static final String NOMBRECASA = "photo_name";
        public static final String CONTRATOPDF = "doc_contract";
        public static final String NOMBREPDF = "doc_name_contract";
        public static final String RECIBO = "recibo";
        public static final String VALOR = "valor";
        public static final String USER_ID = "user_id";
        public static final String OBSERVACION = "observacion";
        public static final String CONTRATO = "contrato";
        public static final String TIPOCONTRATO = "tipo_contrato";

    }
}
