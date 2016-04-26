package cput.ac.za.infoshare.repository.people.Impl;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.util.Set;

import cput.ac.za.infoshare.AppConf.databasese.DBConstants;
import cput.ac.za.infoshare.domain.person.PersonContact;
import cput.ac.za.infoshare.repository.people.PersonContactFactory;

/**
 * Created by user9 on 2016/04/26.
 */
public class PersonContactFactoryImpl extends SQLiteOpenHelper implements PersonContactFactory {
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
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL ;";


    public PersonFactoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
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

    @Override
    public PersonContact findById(String s) throws ParseException {
        return null;
    }

    @Override
    public PersonContact save(PersonContact entity) {
        return null;
    }

    @Override
    public PersonContact update(PersonContact entity) {
        return null;
    }

    @Override
    public PersonContact delete(PersonContact entity) {
        return null;
    }

    @Override
    public Set<PersonContact> findAll() throws ParseException {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
