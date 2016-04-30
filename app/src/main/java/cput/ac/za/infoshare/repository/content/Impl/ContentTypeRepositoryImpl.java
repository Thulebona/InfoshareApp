package cput.ac.za.infoshare.repository.content.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.content.ContentType;
import cput.ac.za.infoshare.repository.content.ContentTypeRepository;


/**
 * Created by user9 on 2016/04/21.
 */
public class ContentTypeRepositoryImpl  extends SQLiteOpenHelper implements ContentTypeRepository {
    public static final String TABLE_NAME = "ContentType";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_NAME + " TEXT UNIQUE NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL );";

    public ContentTypeRepositoryImpl(Context context) {
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
    public ContentType findById(String s) {
        open();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_DESCRIPTION},
                COLUMN_ID + " = ? ",
                new String[]{String.valueOf(s)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ContentType contentType = new ContentType.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .build();
            return contentType;
        } else {
            return null;
        }
    }

    @Override
    public ContentType save(ContentType entity) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_NAME, entity.getName());
            values.put(COLUMN_DESCRIPTION, entity.getDescription());
            db.insert(TABLE_NAME,null,values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public ContentType update(ContentType entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        db.update(TABLE_NAME, values, COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public ContentType delete(ContentType entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<ContentType> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<ContentType> contentTypes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final ContentType contentType = new ContentType.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .build();
                contentTypes.add(contentType);
            } while (cursor.moveToNext());
        }
        return contentTypes;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
