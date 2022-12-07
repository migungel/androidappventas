package com.dvnet.agreement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserActivity extends AppCompatActivity {

    private Button mGetuser;
    private Button mFirmar;
    private Button mSave;

    private TextInputLayout mIdentificacionField;
    private TextInputLayout mUsernameField;

    private TextInputEditText mUser;
    private TextInputEditText mIdentificacion;
    private TextInputEditText mNombre;

    PartnerDbHelper mUserDbHelper;

    private byte[] mFirma;
    private String mUserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mGetuser = (Button) findViewById(R.id.btn_getuser);
        mFirmar = (Button) findViewById(R.id.btn_usersing);
        mSave = (Button) findViewById(R.id.btn_usersave);

        mIdentificacionField = (TextInputLayout) findViewById(R.id.til_identification);
        mUsernameField = (TextInputLayout) findViewById(R.id.til_username);
        mUser = (TextInputEditText) findViewById(R.id.s_user);
        mIdentificacion = (TextInputEditText) findViewById(R.id.s_identificacion);
        mNombre = (TextInputEditText) findViewById(R.id.s_username);

        mUserDbHelper = new PartnerDbHelper(this);

        mIdentificacionField.setVisibility(View.GONE);
        mUsernameField.setVisibility(View.GONE);
        mFirmar.setVisibility(View.GONE);
        mSave.setVisibility(View.GONE);

        mGetuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserv = mUser.getText().toString();
                if(!mUserv.isEmpty()){
                    GetUser();
                } else {
                    Toast.makeText(UserActivity.this, "Debe ingresar user...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mFirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAgreementSing();
                mSave.setVisibility(View.VISIBLE);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUser();
            }
        });
    }

    private void GetUser(){
        String user = mUser.getText().toString();
        Cursor cuser = mUserDbHelper.getUserByLogin(user);

        if(cuser.getCount() <= 0) {
            mIdentificacionField.setVisibility(View.GONE);
            mUsernameField.setVisibility(View.GONE);
            mFirmar.setVisibility(View.GONE);
            Toast.makeText(UserActivity.this, "Usuario registrado no existe en el dispositivo...", Toast.LENGTH_SHORT).show();
            return;
        }

        mIdentificacionField.setVisibility(View.VISIBLE);
        mUsernameField.setVisibility(View.VISIBLE);
        mFirmar.setVisibility(View.VISIBLE);

        cuser.moveToFirst();
        User u = new User(cuser);

        mIdentificacion.setText(u.getIdentificacion());
        mNombre.setText(u.getName());
        mFirma = u.getSing();

    }

    private void showAgreementSing() {
        Intent intent = new Intent(this, SingActivity.class);
        //intent.putExtra("mAgreementId", agreementid);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                mFirma = data.getByteArrayExtra("signature");
            }
        }
    }

    private void SaveUser(){
        String user = mUser.getText().toString();
        Cursor cuser = mUserDbHelper.getUserByLogin(user);

        if(cuser.getCount() <= 0) {
            Toast.makeText(UserActivity.this, "Usuario registrado no existe en el dispositivo...", Toast.LENGTH_SHORT).show();
            return;
        }

        cuser.moveToFirst();
        User u = new User(cuser);

        String identificacion = mIdentificacion.getText().toString();
        String nombre = mNombre.getText().toString();

        u.setIdentificacion(identificacion);
        u.setName(nombre);
        u.setSing(mFirma);

        if(!hasBytes(u.getSing())){
            Toast.makeText(UserActivity.this, "Debe registrar la firma...", Toast.LENGTH_SHORT).show();
            return;
        }

        mUserDbHelper.updateUser(u, u.getId());

        Toast.makeText(UserActivity.this, "Usuario actualizado exitosamente en el dispositivo...", Toast.LENGTH_SHORT).show();
    }

    private static boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

}
