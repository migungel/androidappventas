package com.dvnet.agreement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.User;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private PartnerDbHelper mPartnerDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnSincro = (Button) findViewById(R.id.btnSincron);

        mPartnerDbHelper = new PartnerDbHelper(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
                    }, 1000);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText edtUserName = (TextInputEditText) findViewById(R.id.edtUserName);
                TextInputEditText edtPassword = (TextInputEditText)findViewById(R.id.edtPassword);
                login(
                        edtUserName.getText().toString(),
                        edtPassword.getText().toString()
                );
//                login("admin", "DvtvCanal9");
            }
        });

        btnSincro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DbMainActivity.class));
            }
        });

        createFolder();
    }

    public void login(String userName, String password) {
        if (userName == null || userName.isEmpty()){
            Toast.makeText(this, "Login Failed! \n Debe ingresar Usuario " , Toast.LENGTH_SHORT).show();
            return;
        }

        if (password == null || password.isEmpty()){
            Toast.makeText(this, "Login Failed! \n Debe ingresar contraseña " , Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Cursor c = mPartnerDbHelper.getUserByLogin(userName.trim());

            if(c.getCount()<=0){
                Toast.makeText(this, "Login Fail!\n Usuario no registrado en App por favor vaya Sincronizar -> Recibir Informacion\n utilice su usuario para sincronizar", Toast.LENGTH_SHORT).show();
                return;
            }

            c.moveToFirst();
            User u = new User(c);
            c.close();

            if(!hasBytes(u.getSing())){
                Toast.makeText(this, "Debe registrar una firma para tu usuario. Por Opciones Sincronización -> Información Usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            if (u.getUser().equals(userName) && u.getPass().equals(password)) {
                GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
                globalUser.setData(u.getId());

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Login Failed! \n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
    }

    private boolean hasBytes(final byte[] data) {
        if (data != null && data.length > 0) {
            return true;
        }
        return false;
    }

    public void createFolder(){
        String nameFolder = "DVNET";
        boolean export = false;
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), nameFolder);
        if (dir.exists()) {
            export = true;
//            Toast.makeText(getApplicationContext(), "Directory is already exits", Toast.LENGTH_SHORT).show();
        } else {
            dir.mkdir();
            if ( dir.isDirectory() ) {
                export = true;
//                Toast.makeText(getApplicationContext(), "Directory is created", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String msg = "Message: failed to create directory\n"+
                        "Path: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + nameFolder + "\n" +
                        "mkdir: " + dir.mkdir();
                builder.setMessage(msg);
                builder.show();
            }
        }

        if (export){
            logcat(dir);
        }
    }

    private void logcat(File dir) {
        try {
            String filename = "logcat.txt";
            String logPath  = "/" + filename;
            File output = new File(dir, logPath);
            if (output.exists()){
                if ( output.delete() ) { output = new File(dir, logPath); }
            }
            //"logcat -d *:E -r 160 -f "
            Runtime.getRuntime().exec("logcat -f " + output.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DVAgreement", "initial: ", e );
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
    }

}