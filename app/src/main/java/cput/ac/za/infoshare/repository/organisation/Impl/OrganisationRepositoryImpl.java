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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.organisation.Organisation;
import cput.ac.za.infoshare.repository.organisation.OrganisationRepository;


/**
 * Created by user9 on 2016/04/21.
 */
public class OrganisationRepositoryImpl extends SQLiteOpenHelper implements OrganisationRepository {

    public static final String TABLE_NAME = "Organisation";
    private SQLiteDatabase db;
/**
*
*
        */
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_name = "name";
    public static final String COLUMN_address = "address";
    public static final String COLUMN_contactphone = "contactPhone";
    public static final String COLUMN_email = "email";
    public static final String COLUMN_adminattached = "address";
    public static final String COLUMN_date = "date";
    public static final String COLUMN_state = "state";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_name + " TEXT  NOT NULL , "
            + COLUMN_address + " TEXT NOT NULL , "
            + COLUMN_contactphone + " TEXT NOT NULL , "
            + COLUMN_email + " TEXT NOT NULL , "
            + COLUMN_date + " TEXT NOT NULL , "
            + COLUMN_adminattached + " TEXT NOT NULL , "
            + COLUMN_state + " TEXT  NOT NULL );";


    public OrganisationRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Organisation findById(String s) throws ParseException {
        open();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_name,
                        COLUMN_address,
                        COLUMN_contactphone,
                        COLUMN_email,
                        COLUMN_date,
                        COLUMN_adminattached,
                        COLUMN_state},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
            Map<String, String> details = new HashMap<>();
            details.put("address",cursor.getString(cursor.getColumnIndex(COLUMN_address)));
            details.put("contactphone", cursor.getString(cursor.getColumnIndex(COLUMN_contactphone)));
            details.put("email", cursor.getString(cursor.getColumnIndex(COLUMN_email)));

            final Organisation organisation = new Organisation.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_name)))
                    .details(details)
                    .adminattached(cursor.getString(cursor.getColumnIndex(COLUMN_adminattached)))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                    .date(dateFormat.parse(cursor.getString(cursor
                            .getColumnIndex(COLUMN_date))))
                    .build();
            return organisation;
        } else {
            return null;
        }
    }

    @Override
    public Organisation save(Organisation entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_name, entity.getName());
            values.put(COLUMN_address, entity.getDetails().get("address"));
            values.put(COLUMN_contactphone, entity.getDetails().get("contactphone"));
            values.put(COLUMN_email, entity.getDetails().get("email"));
            values.put(COLUMN_adminattached, entity.getAdminattached());
            values.put(COLUMN_state, entity.getState());
            values.put(COLUMN_date, entity.getDate().toString());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Organisation update(Organisation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_name, entity.getName());
        values.put(COLUMN_address, entity.getDetails().get("address"));
        values.put(COLUMN_contactphone, entity.getDetails().get("contactphone"));
        values.put(COLUMN_email, entity.getDetails().get("email"));
        values.put(COLUMN_adminattached, entity.getAdminattached());
        values.put(COLUMN_state, entity.getState());
        values.put(COLUMN_date, entity.getDate().toString());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Organisation delete(Organisation entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Organisation> findAll() throws ParseException {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<Organisation> organisations = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
                Map<String, String> details = new HashMap<>();
                details.put("address",cursor.getString(cursor.getColumnIndex(COLUMN_address)));
                details.put("contactphone", cursor.getString(cursor.getColumnIndex(COLUMN_contactphone)));
                details.put("email", cursor.getString(cursor.getColumnIndex(COLUMN_email)));

                final Organisation organisation = new Organisation.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_name)))
                        .details(details)
                        .adminattached(cursor.getString(cursor.getColumnIndex(COLUMN_adminattached)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                        .date(dateFormat.parse(cursor.getString(cursor
                                .getColumnIndex(COLUMN_date))))
                        .build();
                organisations.add(organisation);
            } while (cursor.moveToNext());
        }
        return organisations;
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
