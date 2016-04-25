package repository.team.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import conf.databases.DBConstants;
import domain.team.TeamDetails;
import repository.team.TeamDetailsRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class TeamDetailsRepositoryImpl extends SQLiteOpenHelper implements TeamDetailsRepository{
    public static final String TABLE_NAME = " teamDetails ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_ESTABLISHED = " established ";
    public static final String COLUMN_APPEARANCES = " appearances ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_ESTABLISHED + " TEXT NOT NULL "
            + COLUMN_APPEARANCES + " TEXT NOT NULL); ";

    public TeamDetailsRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null, DBConstants.DATABASE_VERSION);
    }

    public void open()
    {
        db = this.getReadableDatabase();
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
    public TeamDetails findByID(Long aLong) {
        open();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_ESTABLISHED,
                COLUMN_APPEARANCES},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final TeamDetails teamDetails = new TeamDetails.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .established(cursor.getString(cursor.getColumnIndex(COLUMN_ESTABLISHED)))
                    .appearances(cursor.getString(cursor.getColumnIndex(COLUMN_APPEARANCES)))
                    .build();
            return teamDetails;
        }else {
            return null;
        }
    }

    @Override
    public TeamDetails save(TeamDetails entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ESTABLISHED,entity.getEstablished());
        values.put(COLUMN_APPEARANCES,entity.getAppearances());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        TeamDetails teamDetails = new TeamDetails.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();

        return teamDetails;
    }

    @Override
    public TeamDetails update(TeamDetails entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ESTABLISHED,entity.getEstablished());
        values.put(COLUMN_APPEARANCES,entity.getAppearances());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public TeamDetails delete(TeamDetails entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<TeamDetails> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<TeamDetails> teamDetailsSet = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {
                TeamDetails teamDetails = new TeamDetails.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .established(cursor.getString(cursor.getColumnIndex(COLUMN_ESTABLISHED)))
                        .appearances(cursor.getString(cursor.getColumnIndex(COLUMN_APPEARANCES)))
                        .build();
                teamDetailsSet.add(teamDetails);
            }while (cursor.moveToNext());
        }
        return teamDetailsSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
