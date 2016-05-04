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

import cput.ac.za.infoshare.AppConf.Util.AppUtil;
import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
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
    public static final String COLUMN_personId = "personId";
    public static final String COLUMN_url = "url";
    public static final String COLUMN_description = "description";
    public static final String COLUMN_mime = "mime";
    public static final String COLUMN_size = "size";
    public static final String COLUMN_date = "date";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_org + " TEXT  NOT NULL , "
            + COLUMN_personId + " TEXT NOT NULL , "
            + COLUMN_url + " TEXT NOT NULL , "
            + COLUMN_description + " TEXT NOT NULL , "
            + COLUMN_mime + " TEXT NOT NULL , "
            + COLUMN_size + " TEXT NOT NULL , "
            + COLUMN_date + " TEXT NOT NULL );";


    public PersonImagesFactoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public PersonImages findById(String s) throws ParseException {
        open();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_org,
                        COLUMN_personId,
                        COLUMN_url,
                        COLUMN_description,
                        COLUMN_mime,
                        COLUMN_size,
                        COLUMN_date},
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
                    .personId(cursor.getString(cursor.getColumnIndex(COLUMN_personId)))
                    .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_description)))
                    .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                    .size(cursor.getString(cursor.getColumnIndex(COLUMN_size)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public PersonImages save(PersonImages entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_org, entity.getOrg());
            values.put(COLUMN_personId, entity.getPersonId());
            values.put(COLUMN_url, entity.getUrl());
            values.put(COLUMN_description, entity.getDescription());
            values.put(COLUMN_mime, entity.getMime());
            values.put(COLUMN_size, entity.getSize());
            values.put(COLUMN_date, entity.getDate().toString());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public PersonImages update(PersonImages entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_org, entity.getOrg());
        values.put(COLUMN_personId, entity.getPersonId());
        values.put(COLUMN_url, entity.getUrl());
        values.put(COLUMN_description, entity.getDescription());
        values.put(COLUMN_mime, entity.getMime());
        values.put(COLUMN_size, entity.getSize());
        values.put(COLUMN_date, entity.getDate().toString());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PersonImages delete(PersonImages entity) {
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
                SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
                PersonImages personImages = new PersonImages.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                        .personId(cursor.getString(cursor.getColumnIndex(COLUMN_personId)))
                        .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_description)))
                        .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                        .size(cursor.getString(cursor.getColumnIndex(COLUMN_size)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
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
