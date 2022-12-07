package com.dvnet.agreement.data.agreement;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dvnet.agreement.data.Company;
import com.dvnet.agreement.data.Tecnologia;
import com.dvnet.agreement.data.TipoServicio;
import com.dvnet.agreement.data.agreement.AgreementContract;
import com.dvnet.agreement.data.partner.Partner;
import com.dvnet.agreement.data.partner.PartnerContract;

public class AgreementDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DVNet.db";
    public static final int DATABASE_VERSION = 1;

    public AgreementDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + AgreementContract.AgreementEntry.TABLE_NAME + " ("
                    + AgreementContract.AgreementEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + AgreementContract.AgreementEntry.ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.PARTNER_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.PARTNER + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.TIPOSERVICIO_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.TIPOSERVICIO + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.PLAN_ID + " TEXT,"
                    + AgreementContract.AgreementEntry.PLAN + " TEXT,"
                    + AgreementContract.AgreementEntry.SECTOR_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.SECTOR + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.CANTON_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.CANTON + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.TIPOVIVIENDA_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.TIPOVIVIENDA + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.POSTE_ID + " TEXT,"
                    + AgreementContract.AgreementEntry.POSTE + " TEXT,"
                    + AgreementContract.AgreementEntry.NAP_ID + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.NAP + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.STATE + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.FOTOCEDULA + " BLOB,"
                    + AgreementContract.AgreementEntry.NOMBRECEDULA + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.FOTOCEDULAPOST + " BLOB,"
                    + AgreementContract.AgreementEntry.NOMBRECEDULAPOST + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.FOTOPLANILLA + " BLOB,"
                    + AgreementContract.AgreementEntry.NOMBREPLANILLA + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.FOTOCASA + " BLOB,"
                    + AgreementContract.AgreementEntry.NOMBRECASA + " TEXT NOT NULL,"
                    + AgreementContract.AgreementEntry.CONTRATOPDF + " BLOB,"
                    + AgreementContract.AgreementEntry.NOMBREPDF + " TEXT NOT NULL"
                    + " )");

            Log.i(TAG, "onCreate: successfully created table account_analytic_account agreementhelper");

            db.execSQL(CreateTableTipoServicio());
            Log.i(TAG, "onCreate: successfully created table TipoServicio");
            db.execSQL(CreateTableTecnologia());
            Log.i(TAG, "onCreate: successfully created table Tecnologia");
            db.execSQL(CreateTableSector());
            Log.i(TAG, "onCreate: successfully created table Sector");
            db.execSQL(CreateTableCanton());
            Log.i(TAG, "onCreate: successfully created table Canton");
            db.execSQL(CreateTableTipoVivienda());
            Log.i(TAG, "onCreate: successfully created table TipoVivienda");
            db.execSQL(CreateTableAutorizacion());
            Log.i(TAG, "onCreate: successfully created table Autorizacion");
            db.execSQL(CreateTablePoste());
            Log.i(TAG, "onCreate: successfully created table Poste");
            db.execSQL(CreateTableCompany());
            Log.i(TAG, "onCreate: successfully created table Company");

            CreateData(db);

        }
        catch (Exception e)
        {
            Log.w(TAG, "onCreate: ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveAgremeent(@NonNull Agremeent agremeent) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Log.d(TAG, "saveAgremeent() returned: " + agremeent.toString());

        return sqLiteDatabase.insert(
                AgreementContract.AgreementEntry.TABLE_NAME,
                null,
                agremeent.toContentValues());

    }

    public Cursor getAllAgremeent() {
        return getReadableDatabase()
                .query(
                        AgreementContract.AgreementEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getAgremeentById(String AgremeentId) {
        Cursor c = getReadableDatabase().query(
                AgreementContract.AgreementEntry.TABLE_NAME,
                null,
                AgreementContract.AgreementEntry.ID + " LIKE ?",
                new String[]{AgremeentId},
                null,
                null,
                null);
        return c;
    }

    public int deleteAgremeent(String AgremeentId) {
        return getWritableDatabase().delete(
                AgreementContract.AgreementEntry.TABLE_NAME,
                AgreementContract.AgreementEntry.ID + " LIKE ?",
                new String[]{AgremeentId});
    }

    public int updateAgremeent(Agremeent agremeent, String AgremeentId) {
        return getWritableDatabase().update(
                AgreementContract.AgreementEntry.TABLE_NAME,
                agremeent.toContentValues(),
                AgreementContract.AgreementEntry.ID + " LIKE ?",
                new String[]{AgremeentId}
        );
    }

    private String CreateTableTipoServicio()
    {
        String create_tservicio =
        "CREATE TABLE tb_gen_tiposervicio (" +
                "id TEXT NOT NULL, " +
                "nombre TEXT NOT NULL, )";

        return create_tservicio;
    }

    private String CreateTableTecnologia()
    {
        String create_tecnologia =
                "CREATE TABLE tb_gen_tecnologia (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_tecnologia;
    }

    private String CreateTableSector()
    {
        String create_sector =
                "CREATE TABLE tb_gen_sector (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_sector;
    }

    private String CreateTableCanton()
    {
        String create_canton =
                "CREATE TABLE tb_gen_canton (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_canton;
    }

    private String CreateTableTipoVivienda()
    {
        String create_tipovivienda =
                "CREATE TABLE tb_gen_tipovivienda (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_tipovivienda;
    }

    private String CreateTableAutorizacion()
    {
        String create_Autorizacion =
                "CREATE TABLE tb_gen_autorizacion (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_Autorizacion;
    }

    private String CreateTablePoste()
    {
        String create_Poste =
                "CREATE TABLE tb_gen_poste (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_Poste;
    }

    private String CreateTableCompany()
    {
        String create_company =
                "CREATE TABLE tb_gen_company (" +
                        "id TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, )";

        return create_company;
    }

    private void CreateData(SQLiteDatabase db) {

        db.insert("tb_gen_tiposervicio", null, new TipoServicio("tva", "TV Cable Analogico").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("tvd", "Tv Cable Digital").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("int", "Internet").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("dpa", "Duopack Analogico (TV + Internet)").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("dpd", "DuoPack Digital (TV + Internet)").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("tel", "Telefonia").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("dpat", "Duopack Analogico (TV + Telefonia)").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("dpdt", "DuoPack Digital (TV + Telefonia)").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("dpit", "Duopack Analogico (Internet + Telefonia)").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("tpa", "TriPack Analogico").toContentValues());
        db.insert("tb_gen_tiposervicio", null, new TipoServicio("tpd", "TriPack Digital").toContentValues());

        db.insert("tb_gen_company", null, new Company("1", "DV TELEVISION DVTV S.A").toContentValues());
        db.insert("tb_gen_company", null, new Company("3", "TRANSCORPORACION S. A.").toContentValues());
        db.insert("tb_gen_company", null, new Company("4", "PARROQUIAS").toContentValues());

        /*
        db.insert("tb_gen_tecnologia", null, new Tecnologia("coax", "Coaxial").toContentValues());
        db.insert("tb_gen_tecnologia", null, new Tecnologia("fo", "Fibra Optica").toContentValues());
        db.insert("tb_gen_tecnologia", null, new Tecnologia("ina", "Inalambrico").toContentValues());
        db.insert("tb_gen_tecnologia", null, new Tecnologia("mix", "Red Mixta FO + Coaxial").toContentValues());
         */



    }

}
