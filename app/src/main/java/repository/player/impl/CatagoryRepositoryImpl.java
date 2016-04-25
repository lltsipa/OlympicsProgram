package repository.player.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import conf.databases.DBConstants;
import domain.player.Catagory;
import repository.player.CatagoryRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class CatagoryRepositoryImpl extends SQLiteOpenHelper implements CatagoryRepository {

    public static final String TABLE_NAME = " catagory ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_MALE = " male ";
    public static final String COLUMN_FEMALE = " female ";

    public static final String DATABSE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_MALE + " TEXT "
            + COLUMN_FEMALE + " TEXT); ";

    public CatagoryRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() throws SQLException {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABSE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                " Updateing from old version " + oldVersion + " to new " + newVersion + " which will destroy all data ");
        db.execSQL(" DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }


    @Override
    public Catagory findByID(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                    COLUMN_ID,
                    COLUMN_MALE,
                    COLUMN_FEMALE},
                    COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if(cursor.moveToFirst())
        {
            final Catagory catagory = new Catagory.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Men(cursor.getString(cursor.getColumnIndex(COLUMN_MALE)))
                    .Women(cursor.getString(cursor.getColumnIndex(COLUMN_FEMALE)))
                    .build();
            return catagory;
        }else {
            return null;
        }
    }

    @Override
    public Catagory save(Catagory entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_MALE,entity.getMen());
        values.put(COLUMN_FEMALE,entity.getWomen());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        Catagory insertedCatagory = new Catagory.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return insertedCatagory;
    }

    @Override
    public Catagory update(Catagory entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_MALE,entity.getMen());
        values.put(COLUMN_FEMALE,entity.getWomen());

        db.update(TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Catagory delete(Catagory entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Catagory> findAll() {
        SQLiteDatabase db  = this.getReadableDatabase();
        Set<Catagory> catagorySet = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do{
                final Catagory catagory = new Catagory.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Men(cursor.getString(cursor.getColumnIndex(COLUMN_MALE)))
                        .Women(cursor.getString(cursor.getColumnIndex(COLUMN_FEMALE)))
                        .build();
                catagorySet.add(catagory);
            }while (cursor.moveToNext());
        }
        return catagorySet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
