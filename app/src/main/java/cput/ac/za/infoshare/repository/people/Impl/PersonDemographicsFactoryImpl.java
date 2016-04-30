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
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.person.PersonDemographics;
import cput.ac.za.infoshare.repository.people.PersonDemographicsFactory;

/**
 * Created by user9 on 2016/04/26.
 */
public class PersonDemographicsFactoryImpl extends SQLiteOpenHelper implements PersonDemographicsFactory {
    /***
     private String id;
     private String personId;
     private String genderId;
     private Date dateOfBirth;
     private Date date;
     private String state;
     private String maritalStatusId;
     private int numberOfDependencies;
     private String personRaceId;
     *
     */
    public static final String TABLE_NAME = " PersonDemographics";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_personId = "personId";
    public static final String COLUMN_genderId = "genderId";
    public static final String COLUMN_dateOfBirth = "dateOfBirth";
    public static final String COLUMN_date = "date";
    public static final String COLUMN_state = "state";
    public static final String COLUMN_maritalStatusId = "maritalStatusId";
    public static final String COLUMN_numberOfDependencies = "numberOfDependencies";
    public static final String COLUMN_personRaceId = "personRaceId";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_personId + " TEXT  NOT NULL , "
            + COLUMN_genderId + " TEXT NOT NULL , "
            + COLUMN_dateOfBirth + " TEXT NOT NULL , "
            + COLUMN_date + " TEXT NOT NULL , "
            + COLUMN_maritalStatusId + " TEXT NOT NULL , "
            + COLUMN_state + " TEXT NOT NULL , "
            + COLUMN_personRaceId + " TEXT NOT NULL , "
            + COLUMN_numberOfDependencies + " INTEGER  NOT NULL );";


    public PersonDemographicsFactoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public  PersonDemographics findById(String s) throws ParseException {
        open();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_personId,
                        COLUMN_genderId,
                        COLUMN_dateOfBirth,
                        COLUMN_date,
                        COLUMN_maritalStatusId,
                        COLUMN_state,
                        COLUMN_personRaceId,
                        COLUMN_numberOfDependencies},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
            return new  PersonDemographics.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .personId(cursor.getString(cursor.getColumnIndex(COLUMN_personId)))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                    .genderId(cursor.getString(cursor.getColumnIndex(COLUMN_genderId)))
                    .dateOfBirth(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_dateOfBirth))))
                    .date(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
                    .maritalstatusid(cursor.getString(cursor.getColumnIndex(COLUMN_maritalStatusId)))
                    .personraceid(cursor.getString(cursor.getColumnIndex(COLUMN_personRaceId)))
                    .numberofdependencies(cursor.getInt(cursor.getColumnIndex(COLUMN_numberOfDependencies)))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public  PersonDemographics save(PersonDemographics entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_personId, entity.getPersonId());
            values.put(COLUMN_state, entity.getState());
            values.put(COLUMN_genderId, entity.getGenderId());
            values.put(COLUMN_dateOfBirth, entity.getDateOfBirth().toString());
            values.put(COLUMN_date, entity.getDate().toString());
            values.put(COLUMN_maritalStatusId, entity.getMaritalStatusId());
            values.put(COLUMN_personRaceId, entity.getPersonRaceId());
            values.put(COLUMN_numberOfDependencies, entity.getNumberOfDependencies());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public  PersonDemographics update(PersonDemographics entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_personId, entity.getPersonId());
            values.put(COLUMN_state, entity.getState());
            values.put(COLUMN_genderId, entity.getGenderId());
            values.put(COLUMN_dateOfBirth, entity.getDateOfBirth().toString());
            values.put(COLUMN_date, entity.getDate().toString());
            values.put(COLUMN_maritalStatusId, entity.getMaritalStatusId());
            values.put(COLUMN_personRaceId, entity.getPersonRaceId());
            values.put(COLUMN_numberOfDependencies, entity.getNumberOfDependencies());
            db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getId())});
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public  PersonDemographics delete(PersonDemographics entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set< PersonDemographics> findAll() throws ParseException {

        SQLiteDatabase db = this.getReadableDatabase();
        Set< PersonDemographics> personDemographics = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                 SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
                PersonDemographics demographics = new  PersonDemographics.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .personId(cursor.getString(cursor.getColumnIndex(COLUMN_personId)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                        .genderId(cursor.getString(cursor.getColumnIndex(COLUMN_genderId)))
                        .dateOfBirth(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_dateOfBirth))))
                        .date(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
                        .maritalstatusid(cursor.getString(cursor.getColumnIndex(COLUMN_maritalStatusId)))
                        .personraceid(cursor.getString(cursor.getColumnIndex(COLUMN_personRaceId)))
                        .numberofdependencies(cursor.getInt(cursor.getColumnIndex(COLUMN_numberOfDependencies)))
                        .build();
                personDemographics.add(demographics);
            } while (cursor.moveToNext());
        }
        return personDemographics;
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
