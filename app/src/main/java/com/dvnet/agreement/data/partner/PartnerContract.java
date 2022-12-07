package com.dvnet.agreement.data.partner;

import android.provider.BaseColumns;

import java.util.Date;

public class PartnerContract {

    private PartnerContract() {

    }

    public static abstract class PartnerEntry implements BaseColumns {

        public static final String TABLE_NAME = "res_partner";

        public static final String ID = "id";
        public static final String FIRSTLN = "firstln";
        public static final String SECONDLN = "secondln";
        public static final String NAME = "name";
        public static final String IDENTIFICATION_ID = "identification_id";
        public static final String STREET = "street";
        public static final String PRINCIPAL = "main_street";
        public static final String SECUNDARIA = "second_street";
        public static final String MZ = "mz";
        public static final String NRO = "villa";
        public static final String NUMBER = "number";
        public static final String STREET2 = "street2";
        public static final String PHONE = "phone";
        public static final String MOBILE = "mobile";
        public static final String EMAIL = "email";
        public static final String COORDENADAS = "coordenadas";
        public static final String COD_CNEL = "cod_cnel";
        public static final String COD_AGUA = "cod_agua";
        public static final String NACIMIENTO = "nacimiento";
        public static final String PROVINCIA_ID = "provincia_id";
        public static final String PROVINCIA = "provincia";
        public static final String TYPEID_ID = "typeid_id";
        public static final String TYPEID = "typeid";
        public static final String TYPEDIR_ID = "type_street";
        public static final String TYPEDIR = "typedir";
        public static final String SERVICE_ID = "type_planilla";
        public static final String SERVICE = "service";
        public static final String CANTON_ID = "canton_id";
        public static final String CANTON = "canton";
        public static final String REF_FAMILIAR = "ref_familiar";
        public static final String NUM_REF_FAMILIAR = "num_ref_familiar";
        public static final String REF_PERSONAL = "ref_personal";
        public static final String NUM_REF_PERSONAL = "num_ref_personal";
        public static final String STATE = "state";
        public static final String USED = "used";
        public static final String USER_ID = "user_id";
        public static final String VENTA = "venta";
        public static final String APP_ID = "app_id";
        public static final String VENDEDOR = "vendedor";
        public static final String REFER = "refer";
        public static final String NAP_ID = "nap_id";
        public static final String NAP = "nap";
        public static final String VALIDATE = "validate";
        public static final String FOTOCASA = "doc_casa";
        public static final String NOMBRECASA = "doc_name_casa";
    }

}
