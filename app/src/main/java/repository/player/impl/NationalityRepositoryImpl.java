package repository.player.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import conf.databases.DBConstants;
import domain.player.Nationality;
import repository.player.NationalityRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class NationalityRepositoryImpl extends SQLiteOpenHelper implements NationalityRepository{

    public static final String TABLE_NAME = " nationailty ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_CONTINENT = " continent ";
    public static final String COLUMN_COUNTRY = " country ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_CONTINENT + " TEXT UNIQUE NOT NULL "
            + COLUMN_COUNTRY + " TEXT UNIQUE NOT NULL ); ";

    public NationalityRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()
    {
        db = this.getWritableDatabase();
    }

    public void close()
    {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                " Updating from old version " + oldVersion + " to new version " + newVersion + " which will destroy all data ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Nationality findByID(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_CONTINENT,
                COLUMN_COUNTRY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final Nationality nationality = new Nationality.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Continent(cursor.getString(cursor.getColumnIndex(COLUMN_CONTINENT)))
                    .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .build();
            return nationality;
        }else {
            return null;
        }
    }

    @Override
    public Nationality save(Nationality entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_CONTINENT,entity.getContinent());
        values.put(COLUMN_COUNTRY,entity.getCountry());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        Nationality nationality = new Nationality.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();

        return nationality;
    }

    @Override
    public Nationality update(Nationality entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_CONTINENT,entity.getContinent());
        values.put(COLUMN_COUNTRY,entity.getCountry());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Nationality delete(Nationality entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Nationality> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Nationality> nationalitySet = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            do {
                Nationality nationality = new Nationality.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Continent(cursor.getString(cursor.getColumnIndex(COLUMN_CONTINENT)))
                        .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .build();
                nationalitySet.add(nationality);

            }while(cursor.moveToNext());
        }
        return nationalitySet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
