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
import domain.team.TeamStats;
import repository.team.TeamStatsRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class TeamStatsRepositoryImpl extends SQLiteOpenHelper implements TeamStatsRepository{
    public static final String TABLE_NAME = " teamStats ";

    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_PLAYED = " played ";
    public static final String COLUMN_WON = " won ";
    public static final String COLUMN_LOST = " lost ";

    public static final String DATABSE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_PLAYED + " TEXT NOT NULL "
            + COLUMN_WON + " TEXT NOT NULL "
            + COLUMN_LOST + " TEXT NOT NULL); ";

    public TeamStatsRepositoryImpl(Context context)
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
        db.execSQL(DATABSE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                " Updating from old version " + oldVersion + " to new version " + newVersion + " which will destroy all data ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    @Override
    public TeamStats findByID(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                COLUMN_ID,
                COLUMN_PLAYED,
                COLUMN_WON,
                COLUMN_LOST},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null, null, null);

        if(cursor.moveToFirst())
        {
            final TeamStats teamStats = new TeamStats.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Played(cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYED)))
                    .Wins(cursor.getInt(cursor.getColumnIndex(COLUMN_WON)))
                    .Loses(cursor.getInt(cursor.getColumnIndex(COLUMN_LOST)))
                    .build();
            return teamStats;
        }else {
            return null;
        }
    }

    @Override
    public TeamStats save(TeamStats entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_PLAYED,entity.getPlayed());
        values.put(COLUMN_WON,entity.getWins());
        values.put(COLUMN_LOST,entity.getLoses());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        TeamStats teamStats = new TeamStats.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return teamStats;
    }

    @Override
    public TeamStats update(TeamStats entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_PLAYED,entity.getPlayed());
        values.put(COLUMN_WON,entity.getWins());
        values.put(COLUMN_LOST,entity.getLoses());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public TeamStats delete(TeamStats entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<TeamStats> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<TeamStats> teamStatsSet = new HashSet<>();
        open();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {
                TeamStats teamStats = new TeamStats.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Played(cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYED)))
                        .Wins(cursor.getInt(cursor.getColumnIndex(COLUMN_WON)))
                        .Loses(cursor.getInt(cursor.getColumnIndex(COLUMN_LOST)))
                        .build();
                teamStatsSet.add(teamStats);
            }while (cursor.moveToNext());
        }

        return teamStatsSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
