package com.dvnet.agreement.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dvnet.agreement.ConsultaCliente;
import com.dvnet.agreement.R;

import java.util.ArrayList;

public class AdapterConsultaCliente extends ArrayAdapter<ConsultaCliente> {

    public AdapterConsultaCliente(Context context, ArrayList<ConsultaCliente> clientes) {
        super(context, 0, clientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ConsultaCliente cliente = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_search_agreement, parent, false);
        }
        // Lookup view for data population
        TextView cl_agreement_name = (TextView) convertView.findViewById(R.id.cl_agreement_name);
        TextView cl_partner_name = (TextView) convertView.findViewById(R.id.cl_partner_name);
        TextView cl_partners_street = (TextView) convertView.findViewById(R.id.cl_partners_street);
        TextView s_partners_city = (TextView) convertView.findViewById(R.id.s_partners_city);
        TextView cl_agreement_type = (TextView) convertView.findViewById(R.id.cl_agreement_type);



        // Populate the data into the template view using the data object
        String agreement =  "Contrato: " + cliente.getAgreement() + " Estado: " + cliente.getState();
        String partner =  "Cliente: " + cliente.getPartner();
        String partnerstreet =  "Canton: " + cliente.getCanton() + " Dirección: "  + cliente.getStreet();
        String agreementdetail = "Compañia: " + cliente.getCompany() + " Valor: " + cliente.getValor();
        String agreementtype = "Tipo Servicio: " + cliente.getType_service();

        cl_agreement_name.setText(agreement);
        cl_partner_name.setText(partner);
        cl_partners_street.setText(partnerstreet);
        s_partners_city.setText(agreementdetail);
        cl_agreement_type.setText(agreementtype);


        // Return the completed view to render on screen
        return convertView;
    }
}