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
import domain.player.MedalRecieved;
import repository.player.MedalRecievedRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class MedalRecievedRepositoryImpl extends SQLiteOpenHelper implements MedalRecievedRepository{

    public static final String TABLE_NAME = " medalRecieved ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_MEDAL = " medal ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_MEDAL + " TEXT NOT NULL ";

    public MedalRecievedRepositoryImpl(Context context)
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
    public MedalRecieved findByID(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_MEDAL},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final MedalRecieved medalRecieved = new MedalRecieved.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setNextPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDAL)))
                    .build();
            return medalRecieved;

        }else
        {
            return null;
        }
    }

    @Override
    public MedalRecieved save(MedalRecieved entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_MEDAL,entity.getMedal());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        MedalRecieved medalRecieved = new MedalRecieved.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();

        return medalRecieved;
    }

    @Override
    public MedalRecieved update(MedalRecieved entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_MEDAL,entity.getMedal());

        db.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public MedalRecieved delete(MedalRecieved entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<MedalRecieved> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<MedalRecieved> medalset = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst()) {
            do {
                MedalRecieved medalRecieved = new MedalRecieved.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setNextPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDAL)))
                        .build();
                medalset.add(medalRecieved);
            }while (cursor.moveToNext());
        }

            return medalset;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
