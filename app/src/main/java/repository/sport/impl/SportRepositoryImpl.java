package repository.sport.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import conf.databases.DBConstants;
import domain.sport.Sport;
import repository.sport.SportRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class SportRepositoryImpl extends SQLiteOpenHelper implements SportRepository{
    public static final String TABLE_NAME = " sport ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_SPORT = " sport ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + COLUMN_ID +" TEXT UNIQUE NOT NULL "
            + COLUMN_SPORT +" TEXT NOT NULL); ";

    public SportRepositoryImpl(Context context)
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
    public Sport findByID(Long aLong) {
        open();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_SPORT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,null,null);

        if(cursor.moveToFirst())
        {
            final Sport sport = new Sport.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Sport(cursor.getString(cursor.getColumnIndex(COLUMN_SPORT)))
                    .build();
            return sport;
        }else {
            return null;
        }
    }

    @Override
    public Sport save(Sport entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_SPORT,entity.getSport());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        Sport sport = new Sport.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return sport;
    }

    @Override
    public Sport update(Sport entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_SPORT,entity.getSport());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Sport delete(Sport entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Sport> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Sport> sportSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {
                Sport sport = new Sport.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Sport(cursor.getString(cursor.getColumnIndex(COLUMN_SPORT)))
                        .build();
                sportSet.add(sport);
            }while (cursor.moveToNext());
        }

        return sportSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
