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
import cput.ac.za.infoshare.domain.content.RawContent;
import cput.ac.za.infoshare.repository.content.RawContentRepository;


/**
 * Created by user9 on 2016/04/21.
 */
public class RawContentRepositoryImpl  extends SQLiteOpenHelper implements RawContentRepository {
    public static final String TABLE_NAME = "RawContent";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_dateCreated = "dateCreated";
    public static final String COLUMN_creator = "creator";
    public static final String COLUMN_source = "source";
    public static final String COLUMN_category = "category";
    public static final String COLUMN_title = "title";
    public static final String COLUMN_content = "content";
    public static final String COLUMN_contentType = "contentType";
    public static final String COLUMN_status = "status";
    public static final String COLUMN_state = "state";
    public static final String COLUMN_org = "org";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_dateCreated + " TEXT  NOT NULL , "
            + COLUMN_creator + " TEXT NOT NULL , "
            + COLUMN_source + " TEXT NOT NULL , "
            + COLUMN_category + " TEXT NOT NULL , "
            + COLUMN_title + " TEXT NOT NULL , "
            + COLUMN_content + " TEXT NOT NULL , "
            + COLUMN_contentType + " TEXT NOT NULL , "
            + COLUMN_status + " TEXT NOT NULL , "
            + COLUMN_state + " TEXT NOT NULL , "
            + COLUMN_org + " TEXT UNIQUE NOT NULL ;";


    public RawContentRepositoryImpl(Context context) {
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
    public RawContent findById(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_dateCreated,
                        COLUMN_creator,
                        COLUMN_source,
                        COLUMN_category,
                        COLUMN_title,
                        COLUMN_content,
                        COLUMN_contentType,
                        COLUMN_status,
                        COLUMN_state,
                        COLUMN_org},
                COLUMN_ID + " = ? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
            RawContent rawContent;
            try {
                rawContent = new RawContent.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .dateCreated(dateFormat.parse(cursor.getString(cursor
                                .getColumnIndex(COLUMN_dateCreated))))
                        .creator(cursor.getString(cursor.getColumnIndex(COLUMN_creator)))
                        .source(cursor.getString(cursor.getColumnIndex(COLUMN_source)))
                        .category(cursor.getString(cursor.getColumnIndex(COLUMN_category)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_title)))
                        .content(cursor.getString(cursor.getColumnIndex(COLUMN_content)))
                        .contentType(cursor.getString(cursor.getColumnIndex(COLUMN_contentType)))
                        .status(cursor.getString(cursor.getColumnIndex(COLUMN_status)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                        .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                        .build();
                return rawContent;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }


    @Override
    public RawContent save(RawContent entity) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_dateCreated, entity.getDateCreated().toString());
            values.put(COLUMN_creator, entity.getCreator());
            values.put(COLUMN_source, entity.getSource());
            values.put(COLUMN_category, entity.getCategory());
            values.put(COLUMN_title, entity.getTitle());
            values.put(COLUMN_content, entity.getContent());
            values.put(COLUMN_contentType, entity.getContentType());
            values.put(COLUMN_status, entity.getStatus());
            values.put(COLUMN_state, entity.getState());
            values.put(COLUMN_org, entity.getOrg());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public RawContent update(RawContent entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_dateCreated, entity.getDateCreated().toString());
        values.put(COLUMN_creator, entity.getCreator());
        values.put(COLUMN_source, entity.getSource());
        values.put(COLUMN_category, entity.getCategory());
        values.put(COLUMN_title, entity.getTitle());
        values.put(COLUMN_content, entity.getContent());
        values.put(COLUMN_contentType, entity.getContentType());
        values.put(COLUMN_status, entity.getStatus());
        values.put(COLUMN_state, entity.getState());
        values.put(COLUMN_org, entity.getOrg());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public RawContent delete(RawContent entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<RawContent> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<RawContent> rawContents = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat dateFormat = new SimpleDateFormat(  "MM/dd/yyyy HH:mm:ss");
                RawContent rawContent = null;
                try {
                    rawContent = new RawContent.Builder()
                            .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                            .dateCreated(dateFormat.parse(cursor.getString(cursor
                                    .getColumnIndex(COLUMN_dateCreated))))
                            .creator(cursor.getString(cursor.getColumnIndex(COLUMN_creator)))
                            .source(cursor.getString(cursor.getColumnIndex(COLUMN_source)))
                            .category(cursor.getString(cursor.getColumnIndex(COLUMN_category)))
                            .title(cursor.getString(cursor.getColumnIndex(COLUMN_title)))
                            .content(cursor.getString(cursor.getColumnIndex(COLUMN_content)))
                            .contentType(cursor.getString(cursor.getColumnIndex(COLUMN_contentType)))
                            .status(cursor.getString(cursor.getColumnIndex(COLUMN_status)))
                            .state(cursor.getString(cursor.getColumnIndex(COLUMN_state)))
                            .org(cursor.getString(cursor.getColumnIndex(COLUMN_org)))
                            .build();
                    rawContents.add(rawContent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        return rawContents;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
