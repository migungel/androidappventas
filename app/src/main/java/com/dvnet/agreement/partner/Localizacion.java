package com.dvnet.agreement.partner;



import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Localizacion implements LocationListener {

    AddEditPartnerFragment addEditPartnerFragment;

    public String latitud;
    public String longitud;

    public AddEditPartnerFragment getAddEditPartnerFragment() {
        return addEditPartnerFragment;
    }

    public void setAddEditPartnerFragment(AddEditPartnerFragment addEditPartnerFragment) {
        this.addEditPartnerFragment = addEditPartnerFragment;
    }


    @Override
    public void onLocationChanged(Location loc) {
        // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
        loc.getLatitude();
        loc.getLongitude();
        String sLatitud = String.valueOf(loc.getLatitude());
        String sLongitud = String.valueOf(loc.getLongitude());

        latitud = sLatitud;
        longitud = sLongitud;
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es desactivado
        Toast.makeText(this.addEditPartnerFragment.getActivity(),
                "GPS Desactivado",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es activado
        Toast.makeText(this.addEditPartnerFragment.getActivity(),
                "GPS Activado",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }
}