package cput.ac.za.infoshare.repository.people.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.person.Person;
import cput.ac.za.infoshare.domain.person.PersonImages;
import cput.ac.za.infoshare.repository.people.PersonImagesFactory;

/**
 * Created by user9 on 2016/04/25.
 */
public class PersonImagesFactoryImpl extends SQLiteOpenHelper implements PersonImagesFactory {
    /***
     private String org;
     private String personId;
     private String id;
     private String url;
     private String description;
     private String mime;
     private String size;
     private Date date;
     *
     */
    public static final String TABLE_NAME = "PersonImages";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_org = "org";
    public static final String COLUMN_firstName = "firstName";
    public static final String COLUMN_emailAddress = "emailAddress";
    public static final String COLUMN_lastName = "lastName";
    public static final String COLUMN_authvalue = "authvalue";
    public static final String COLUMN_enabled = "enabled";
    public static final String COLUMN_accountNonExpired = "accountNonExpired";
    public static final String COLUMN_credentialsNonExpired = "credentialsNonExpired";
    public static final String COLUMN_accountNonLocked = "accountNonLocked";
    public static final String COLUMN_state = "state";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_org + " TEXT  NOT NULL , "
            + COLUMN_firstName + " TEXT NOT NULL , "
            + COLUMN_emailAddress + " TEXT NOT NULL , "
            + COLUMN_lastName + " TEXT NOT NULL , "
            + COLUMN_authvalue + " TEXT NOT NULL , "
            + COLUMN_enabled + " INTEGER NOT NULL , "
            + COLUMN_accountNonExpired + " INTEGER NOT NULL , "
            + COLUMN_credentialsNonExpired + " INTEGER NOT NULL , "
            + COLUMN_accountNonLocked + " INTEGER NOT NULL , "
            + COLUMN_state + " INTEGER  NOT NULL ;";


    public PersonImagesFactoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public PersonImages findById(String s) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_org,
                        COLUMN_firstName,
                        COLUMN_emailAddress,
                        COLUMN_lastName,
                        COLUMN_authvalue,
                        COLUMN_enabled,
                        COLUMN_accountNonExpired,
                        COLUMN_credentialsNonExpired,
                        COLUMN_accountNonLocked,
                        COLUMN_state},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            return new PersonImages.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                    .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_firstName)))
                    .emailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_emailAddress)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_lastName)))
                    .authvalue(cursor.getString(cursor.getColumnIndex(COLUMN_authvalue)))
                    .enabled(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_enabled))))
                    .accountNonExpired(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_accountNonExpired))))
                    .credentialsNonExpired(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_credentialsNonExpired))))
                    .accountNonLocked(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_accountNonLocked))))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public PersonImages save(Person entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_org, entity.getOrg());
            values.put(COLUMN_firstName, entity.getFirstName());
            values.put(COLUMN_emailAddress, entity.getEmailAddress());
            values.put(COLUMN_lastName, entity.getLastName());
            values.put(COLUMN_authvalue, entity.getAuthvalue());
            values.put(COLUMN_enabled, entity.getEnabled());
            values.put(COLUMN_accountNonExpired, entity.getAccountNonExpired());
            values.put(COLUMN_credentialsNonExpired, entity.getCredentialsNonExpired());
            values.put(COLUMN_accountNonLocked, entity.getAccountNonLocked());
            values.put(COLUMN_state, entity.getState());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public PersonImages update(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_org, entity.getOrg());
        values.put(COLUMN_firstName, entity.getFirstName());
        values.put(COLUMN_emailAddress, entity.getEmailAddress());
        values.put(COLUMN_lastName, entity.getLastName());
        values.put(COLUMN_authvalue, entity.getAuthvalue());
        values.put(COLUMN_enabled, entity.getEnabled());
        values.put(COLUMN_accountNonExpired, entity.getAccountNonExpired());
        values.put(COLUMN_credentialsNonExpired, entity.getCredentialsNonExpired());
        values.put(COLUMN_accountNonLocked, entity.getAccountNonLocked());
        values.put(COLUMN_state, entity.getState());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PersonImages delete(Person entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PersonImages> findAll() throws ParseException {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonImages> images = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                PersonImages personImages = new PersonImages.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_firstName)))
                        .emailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_emailAddress)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_lastName)))
                        .authvalue(cursor.getString(cursor.getColumnIndex(COLUMN_authvalue)))
                        .enabled(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_enabled))))
                        .accountNonExpired(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_accountNonExpired))))
                        .credentialsNonExpired(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_credentialsNonExpired))))
                        .accountNonLocked(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_accountNonLocked))))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                        .build();
                images.add(personImages);
            } while (cursor.moveToNext());
        }
        return images;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(DATABASE_CREATE);
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
