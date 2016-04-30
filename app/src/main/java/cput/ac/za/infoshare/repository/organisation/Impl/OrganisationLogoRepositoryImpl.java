package cput.ac.za.infoshare.repository.organisation.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.organisation.OrganisationLogo;
import cput.ac.za.infoshare.repository.organisation.OrganisationLogoRepository;


/**
 * Created by user9 on 2016/04/21.
 */
public class OrganisationLogoRepositoryImpl  extends SQLiteOpenHelper implements OrganisationLogoRepository {

    public static final String TABLE_NAME = "OrganisationLogo";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_org = "org";
    public static final String COLUMN_url = "url";
    public static final String COLUMN_size = "size";
    public static final String COLUMN_mime = "mime";
    public static final String COLUMN_date = "date";
    public static final String COLUMN_DESCRIPTION = "description";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_org + " TEXT  NOT NULL , "
            + COLUMN_url + " TEXT NOT NULL , "
            + COLUMN_mime + " TEXT NOT NULL , "
            + COLUMN_size + " TEXT NOT NULL , "
            + COLUMN_date + " TEXT NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL );";


    public OrganisationLogoRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public OrganisationLogo findById(String s) throws ParseException {
        open();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_org,
                        COLUMN_url,
                        COLUMN_mime,
                        COLUMN_size,
                        COLUMN_date,
                        COLUMN_DESCRIPTION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
            final OrganisationLogo organisationLogo = new OrganisationLogo.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                    .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                    .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                    .size(cursor.getString(cursor.getColumnIndex(COLUMN_size)))
                    .date(dateFormat.parse(cursor.getString(cursor
                            .getColumnIndex(COLUMN_date))))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .build();
            return organisationLogo;
        } else {
            return null;
        }
    }

    @Override
    public OrganisationLogo save(OrganisationLogo entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_org, entity.getOrg());
            values.put(COLUMN_url, entity.getUrl());
            values.put(COLUMN_mime, entity.getMime());
            values.put(COLUMN_size, entity.getSize());
            values.put(COLUMN_date, entity.getDate().toString());
            values.put(COLUMN_DESCRIPTION, entity.getDescription());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public OrganisationLogo update(OrganisationLogo entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_org, entity.getOrg());
        values.put(COLUMN_url, entity.getUrl());
        values.put(COLUMN_mime, entity.getMime());
        values.put(COLUMN_size, entity.getSize());
        values.put(COLUMN_date, entity.getDate().toString());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public OrganisationLogo delete(OrganisationLogo entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<OrganisationLogo> findAll() throws ParseException {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<OrganisationLogo> organisationLogos = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
                final OrganisationLogo organisationLogo = new OrganisationLogo.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                        .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                        .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                        .size(cursor.getString(cursor.getColumnIndex(COLUMN_size)))
                        .date(dateFormat.parse(cursor.getString(cursor
                                .getColumnIndex(COLUMN_date))))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .build();
                organisationLogos.add(organisationLogo);
            } while (cursor.moveToNext());
        }
        return organisationLogos;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading db from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
