package com.dvnet.agreement.partner;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dvnet.agreement.CustomerManagement;
import com.dvnet.agreement.R;
import com.dvnet.agreement.data.InterestService;
import com.dvnet.agreement.data.Sector;
import com.dvnet.agreement.data.partner.Management;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParseXML {

    private Activity activity;
    private PartnerDbHelper mPartnerDbHelper;
    private XmlPullParser parser;

    public ParseXML(Activity myActivity, XmlPullParser parser) throws IOException, XmlPullParserException {
        this.activity = myActivity;
        this.parser = parser;
        mPartnerDbHelper = new PartnerDbHelper(activity);
        startManagementDialog();
    }

    public void startManagementDialog() throws IOException, XmlPullParserException{
        ArrayList<Partner> refers = new ArrayList<>();
        ArrayList<InterestService> services = new ArrayList<>();
        ArrayList<Management> managements = new ArrayList<>();
        int eventType = parser.getEventType();
        Partner refer = null;
        InterestService service = null;
        Management manage = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;
            String user_id = null;
//            Log.i(TAG, "getName: " + parser.getName());

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
//                    Log.i(TAG, "name: " + eltName);
                    if ( eltName.equals("referido") ) {
                        refer = new Partner();
                        service = new InterestService();
                        refers.add(refer);
                        services.add(service);
                    }else if ( refer != null && service != null ) {
                        if ( eltName.equals("id") ) {
                            String partner_id = parser.nextText();
                            refer.setId(partner_id);
                            service.setRefer(partner_id);
                        }else if ( eltName.equals("name") ) {
                            refer.setName(parser.nextText());
                        }else if ( eltName.equals("canton_id") ) {
                            refer.setCanton_id(parser.nextText());
                        }else if ( eltName.equals("provincia_id") ) {
                            refer.setProvincia_id(parser.nextText());
                        }else if ( eltName.equals("mobile") ) {
                            refer.setMobile(parser.nextText());
                        }else if ( eltName.equals("street") ) {
                            refer.setStreet(parser.nextText());
                        }else if ( eltName.equals("type_partner") ) {
                            refer.setVenta(parser.nextText());
                        }else if ( eltName.equals("user_id") ) {
                            user_id = parser.nextText();
                            refer.setUser_id(user_id);
                        }else if ( eltName.equals("ser_ids") ) {
                            service.setActualService(parser.nextText());
                        }else if ( eltName.equals("interes") ) {
                            service.setServices(parser.nextText());
                        }else if ( eltName.equals("provinvia") ) {
                            refer.setProvincia(parser.nextText());
                        }else if ( eltName.equals("canton") ) {
                            refer.setCanton(parser.nextText());
                        }else if ( eltName.equals("app_id") ) {
                            refer.setApp_id(parser.nextText());
                        }else if ( eltName.equals("vendedor") ) {
                            refer.setVendedor(parser.nextText());
                        }else if ( eltName.equals("nap_id") ) {
                            refer.setNap_id(parser.nextText());
                        }else if ( eltName.equals("nap") ) {
                            refer.setNap(parser.nextText());
                        }else if ( eltName.equals("coordenada") ) {
                            refer.setCoordenadas(parser.nextText());
                        }
                        if ( eltName.equals("service") ) {
                            manage = new Management();
                            managements.add(manage);
                        }
                        else if ( manage != null ) {
                            if ( eltName.equals("refer") ) {
                                manage.setRefer(parser.nextText());
                            }else if ( eltName.equals("motive") ) {
                                manage.setMotive(parser.nextText());
                            }else if ( eltName.equals("management") ) {
                                manage.setManagement(parser.nextText());
                            }else if ( eltName.equals("user_id") ) {
                                manage.setUser_id(user_id);
                            }else if ( eltName.equals("date") ) {
                                manage.setDate(parser.nextText());
                            }
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        saveRefers(refers, services, managements);
    }

    private void saveRefers(ArrayList<Partner> refers, ArrayList<InterestService> services, ArrayList<Management> managements){
        mPartnerDbHelper.deleteRefersByStateA(refers.get(0).getUser_id());
        String old_refers = "";
        Cursor oldPartners = mPartnerDbHelper.getAllRowsby("res_partner", "refer =?", "1");
        oldPartners.moveToFirst();
        while (!oldPartners.isAfterLast()) {
            old_refers += '"' + oldPartners.getString(oldPartners.getColumnIndex("id")) + '"' + ",";
            oldPartners.moveToNext();
        }
        if ( oldPartners.getCount()>0 ) {
            old_refers = old_refers.substring(0, old_refers.length()-1);
            mPartnerDbHelper.deleteServiceByRefers(old_refers);
            mPartnerDbHelper.deleteManagementByRefers(old_refers);
        }

        for (Partner refer: refers){
            Log.i(TAG, "namerefer: " + refers.get(0).getName());
            String[] allName = refer.getName().split(" ");
            String secondLastName = "";
            String name = allName[allName.length-1];
            if ( allName.length>2 && allName[1].length()>1 ){
                secondLastName = allName[1];
            }
            Partner partner = new Partner(
                    "0",
                    allName[0],
                    secondLastName,
                    name,
                    refer.getStreet(),
                    refer.getMobile(),
                    "",////refer.getEmail
                    refer.getProvincia_id(),
                    refer.getProvincia(),
                    refer.getCanton_id(),
                    refer.getCanton(),
                    refer.getVendedor(),
                    "A",
                    "1",
                    refer.getUser_id(),
                    refer.getVenta(),
                    refer.getApp_id(),
                    "1",
                    refer.getCoordenadas(),
                    refer.getNap_id(),////refer.getNap_id
                    refer.getNap(),////refer.getNap
                    refer.getFotoCasa(),
                    refer.getNombreCasa()
                    );
            Cursor repeatPartner = mPartnerDbHelper.getAllRowsby("res_partner", "app_id =?", refer.getApp_id());
            if(repeatPartner.getCount()<=0){
                new AddPartnerTask().execute(partner);

                for (InterestService serv: services){
                    if (refer.getId().equals(serv.getRefer())){
                        InterestService actualServ = new InterestService(
                                "0",
                                partner.getId(),
                                serv.getServices(),
                                serv.getActualService());
                        new AddServicesTask().execute(actualServ);
                    }
                }
                for (Management manag: managements){
                    if (refer.getId().equals(manag.getRefer())){
                        Management manage = new Management(
                                "0",
                                partner.getId(),
                                manag.getMotive(),
                                manag.getManagement(),
                                manag.getUser_id(),
                                "A",
                                manag.getDate()
                        );
                        new AddManageTask().execute(manage);
                    }
                }
            }
            repeatPartner.close();
        }
        oldPartners.close();
    }

    private void showAddError() {
        Toast.makeText(activity,
                "Error al agregar nueva información gestiones", Toast.LENGTH_SHORT).show();
    }

    private void showPartnerScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            activity.setResult(Activity.RESULT_CANCELED);
        } else {
            activity.setResult(Activity.RESULT_OK);
        }
    }

    private void showAddEditError() {
        Toast.makeText(activity,
                "Error al agregar nueva información partner", Toast.LENGTH_SHORT).show();
    }

    private void showManagementScreen(Boolean requery) {
        if (!requery) {
            showAddError();
            activity.setResult(Activity.RESULT_CANCELED);
        } else {
            activity.setResult(Activity.RESULT_OK);
        }
    }


    private class AddPartnerTask extends AsyncTask<Partner, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Partner... partner) {
            return mPartnerDbHelper.savePartner(partner[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showPartnerScreen(result);
        }
    }

    private class AddServicesTask extends AsyncTask<InterestService, Void, Boolean> {

        @Override
        protected Boolean doInBackground(InterestService... aserv) {
            return mPartnerDbHelper.saveServices(aserv[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showPartnerScreen(result);
        }

    }

    private class AddManageTask extends AsyncTask<Management, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Management... manage) {
            return mPartnerDbHelper.saveManagement(manage[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showManagementScreen(result);
        }

    }

}
