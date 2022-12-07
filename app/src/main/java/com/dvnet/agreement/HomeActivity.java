package com.dvnet.agreement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.dvnet.agreement.agreement.AgreementActivity;
import com.dvnet.agreement.partner.BarridoActivity;
import com.dvnet.agreement.partner.PartnerActivity;
import com.dvnet.agreement.partner.PartnerCursorAdapter;
import com.dvnet.agreement.partner.ReferActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private boolean closed = false;
    private PartnerCursorAdapter mPartnerAdapter;

    FloatingActionButton fabmain = null;
    FloatingActionButton fabpartner = null;
    FloatingActionButton fabcontrato = null;
    FloatingActionButton fabrefer = null;
    FloatingActionButton fabbarrido = null;
    FloatingActionButton fabconsulta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fabmain = (FloatingActionButton)findViewById(R.id.fabmain);
        fabpartner = (FloatingActionButton)findViewById(R.id.fabpartner);
        fabcontrato = (FloatingActionButton)findViewById(R.id.fabcontrato);
        fabrefer = (FloatingActionButton)findViewById(R.id.fabrefer);
        fabbarrido = (FloatingActionButton)findViewById(R.id.fabbarrido);
        fabconsulta = (FloatingActionButton)findViewById(R.id.fabsearch);

        fabmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnAddButtonClick();
            }
        });

        mPartnerAdapter = new PartnerCursorAdapter(this, null);

        fabpartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarpartner();
            }
        });

        fabcontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarcontrato();
            }
        });
        fabrefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarreferido();
            }
        });
        fabbarrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarbarridos();
            }
        });
        fabconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarconsulta();
            }
        });

    }


    private void lanzarpartner(){
        startActivity(new Intent(this, PartnerActivity.class));
        Toast.makeText(this,"Personas",Toast.LENGTH_SHORT).show();
    }

    private void lanzarcontrato(){
        startActivity(new Intent(this, AgreementActivity.class));
        Toast.makeText(this,"Contratos",Toast.LENGTH_SHORT).show();
    }

    private void lanzarreferido(){
        startActivity(new Intent(this, ReferActivity.class));
        Toast.makeText(this,"Referidos",Toast.LENGTH_SHORT).show();
    }

    private void lanzarbarridos(){
        startActivity(new Intent(this, BarridoActivity.class));
        Toast.makeText(this,"Barridos",Toast.LENGTH_SHORT).show();
    }

    private void lanzarconsulta(){
        startActivity(new Intent(this, ConsultaActivity.class));
        Toast.makeText(this,"Consultar",Toast.LENGTH_SHORT).show();
    }

    private void OnAddButtonClick() {
        setVisibility(closed);
        setAnimation(closed);
        closed = !closed;
    }

    // A Function used to set the Animation effect
    private void setAnimation(Boolean closed) {
        Animation rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_anim);
        Animation rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_anim);
        Animation fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        Animation toBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);

        if(!closed){
            fabconsulta.startAnimation(fromBottom);
            fabpartner.startAnimation(fromBottom);
            fabcontrato.startAnimation(fromBottom);
            fabrefer.startAnimation(fromBottom);
            fabbarrido.startAnimation(fromBottom);
            fabmain.startAnimation(rotateOpen);
        }else{
            fabconsulta.startAnimation(toBottom);
            fabpartner.startAnimation(toBottom);
            fabcontrato.startAnimation(toBottom);
            fabrefer.startAnimation(toBottom);
            fabbarrido.startAnimation(toBottom);
            fabmain.startAnimation(rotateClose);
        }
    }
    // used to set visibility to VISIBLE / INVISIBLE
    private void setVisibility(Boolean closed)
    {
        if(!closed)
        {
            fabconsulta.setVisibility(View.VISIBLE);
            fabpartner.setVisibility(View.VISIBLE);
            fabcontrato.setVisibility(View.VISIBLE);
            fabrefer.setVisibility(View.VISIBLE);
            fabbarrido.setVisibility(View.VISIBLE);
        }
        else
            {
                fabconsulta.setVisibility(View.INVISIBLE);
                fabrefer.setVisibility(View.INVISIBLE);
                fabbarrido.setVisibility(View.INVISIBLE);
                fabpartner.setVisibility(View.INVISIBLE);
                fabcontrato.setVisibility(View.INVISIBLE);
            }
    }


}
