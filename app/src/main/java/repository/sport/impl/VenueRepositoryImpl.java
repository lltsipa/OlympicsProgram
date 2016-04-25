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
import domain.sport.Venue;
import repository.sport.VenueRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class VenueRepositoryImpl extends SQLiteOpenHelper implements VenueRepository{
    public static final String TABLE_NAME = " venue ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_VENUE = " venue ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_VENUE + "TEXT NOT NULL); ";

    public VenueRepositoryImpl(Context context)
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
    public Venue findByID(Long aLong) {
        open();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                        COLUMN_VENUE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,null,null);

        if(cursor.moveToFirst())
        {
            final Venue venue = new Venue.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setVenue(cursor.getString(cursor.getColumnIndex(COLUMN_VENUE)))
                    .build();
            return venue;
        }else {
            return null;
        }
    }

    @Override
    public Venue save(Venue entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_VENUE,entity.getVenue());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        Venue venue = new Venue.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return venue;
    }

    @Override
    public Venue update(Venue entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_VENUE,entity.getVenue());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Venue delete(Venue entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Venue> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Venue> venueSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {
                Venue venue = new Venue.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setVenue(cursor.getString(cursor.getColumnIndex(COLUMN_VENUE)))
                        .build();
                venueSet.add(venue);
            }while (cursor.moveToNext());
        }

        return venueSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
