package cput.ac.za.infoshare.repository.content.Impl;

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
import cput.ac.za.infoshare.domain.content.Media;
import cput.ac.za.infoshare.repository.content.MediaRepository;


/**
 * Created by user9 on 2016/04/21.
 */
public class MediaRepositoryImpl  extends SQLiteOpenHelper implements MediaRepository {
    public static final String TABLE_NAME = "Media";
    private SQLiteDatabase db;

    public static final String COLUMN_contentId = "contentId";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_description = "name";
    public static final String COLUMN_url = "url";
    public static final String COLUMN_mime = "mime";
    public static final String COLUMN_state = "state";
    public static final String COLUMN_date = "date";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_contentId + " TEXT  NOT NULL , "
            + COLUMN_description + " TEXT  NOT NULL ,"
            + COLUMN_url + " TEXT  NOT NULL ,"
            + COLUMN_mime + " TEXT  NOT NULL ,"
            + COLUMN_state + " TEXT  NOT NULL ,"
            + COLUMN_date + " TEXT  NOT NULL ;";

    public MediaRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
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

    @Override
    public Media findById(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_contentId,
                        COLUMN_description,
                        COLUMN_url,
                        COLUMN_mime,
                        COLUMN_state,
                        COLUMN_date},
                COLUMN_ID + " = ? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Media media = null;
            try {
                media = new Media.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .contentid(cursor.getString(cursor.getColumnIndex(COLUMN_contentId)))
                        .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                        .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_description)))
                        .date(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return media;
        } else {
            return null;
        }
    }

    @Override
    public Media save(Media entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_contentId, entity.getContentId());
            values.put(COLUMN_description, entity.getDescription());
            values.put(COLUMN_url, entity.getUrl());
            values.put(COLUMN_mime, entity.getMime());
            values.put(COLUMN_state, entity.getState());
            values.put(COLUMN_date, entity.getDate().toString());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Media update(Media entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_contentId, entity.getContentId());
        values.put(COLUMN_description, entity.getDescription());
        values.put(COLUMN_url, entity.getUrl());
        values.put(COLUMN_mime, entity.getMime());
        values.put(COLUMN_state, entity.getState());
        values.put(COLUMN_date, entity.getDate().toString());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Media delete(Media entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Media> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Set<Media> medias = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Media media;
                try {
                    media = new Media.Builder()
                            .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                            .contentid(cursor.getString(cursor.getColumnIndex(COLUMN_contentId)))
                            .url(cursor.getString(cursor.getColumnIndex(COLUMN_url)))
                            .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                            .mime(cursor.getString(cursor.getColumnIndex(COLUMN_mime)))
                            .description(cursor.getString(cursor.getColumnIndex(COLUMN_description)))
                            .date(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_date))))
                            .build();
                    medias.add(media);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while (cursor.moveToNext());
        }
        return medias;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
