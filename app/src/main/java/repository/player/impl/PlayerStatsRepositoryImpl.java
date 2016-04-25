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
import domain.player.PlayerStats;
import repository.player.PlayerStatsRepository;

/**
 * Created by lodz on 2016/04/25.
 */
public class PlayerStatsRepositoryImpl extends SQLiteOpenHelper implements PlayerStatsRepository{
    public static final String TABLE_NAME = " playerStats ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_CAPS = " caps ";
    public static final String COLUMN_GAMESWON = " gamesWon ";
    public static final String COLUMN_GAMESLOST = " gamesLost ";
    public static final String COLUMN_GAMESTARTED = " gamesStarted ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL"
            + COLUMN_CAPS + " TEXT "
            + COLUMN_GAMESTARTED + " TEXT NOT NULL"
            + COLUMN_GAMESWON + " TEXT NOT NULL "
            + COLUMN_GAMESLOST + " TEXT NOT NULL); ";

    public PlayerStatsRepositoryImpl(Context context)
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
    public PlayerStats findByID(Long aLong) {
        open();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_CAPS,
                COLUMN_GAMESTARTED,
                COLUMN_GAMESWON,
                COLUMN_GAMESLOST},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,null,null);

        if(cursor.moveToFirst())
        {
            final PlayerStats playerStats = new PlayerStats.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Caps(cursor.getString(cursor.getColumnIndex(COLUMN_CAPS)))
                    .GamesStarted(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESTARTED)))
                    .GamesWon(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESWON)))
                    .GamesLost(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESLOST)))
                    .build();
            return playerStats;
        }else {
            return null;
        }
    }

    @Override
    public PlayerStats save(PlayerStats entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CAPS, entity.getCaps());
        values.put(COLUMN_GAMESTARTED, entity.getGamesStarted());
        values.put(COLUMN_GAMESLOST, entity.getGamesLost());
        values.put(COLUMN_GAMESWON, entity.getGamesWon());

        long id = db.insertOrThrow(TABLE_NAME,null,values);

        PlayerStats playerStats = new PlayerStats.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return playerStats;
    }

    @Override
    public PlayerStats update(PlayerStats entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CAPS, entity.getCaps());
        values.put(COLUMN_GAMESTARTED, entity.getGamesStarted());
        values.put(COLUMN_GAMESLOST, entity.getGamesLost());
        values.put(COLUMN_GAMESWON, entity.getGamesWon());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public PlayerStats delete(PlayerStats entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<PlayerStats> findAll() {
        Set<PlayerStats> playerStatsSet = new HashSet<>();
        SQLiteDatabase db = this.getReadableDatabase();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {
                PlayerStats playerStats = new PlayerStats.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Caps(cursor.getString(cursor.getColumnIndex(COLUMN_CAPS)))
                        .GamesStarted(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESTARTED)))
                        .GamesWon(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESWON)))
                        .GamesLost(cursor.getString(cursor.getColumnIndex(COLUMN_GAMESLOST)))
                        .build();
                playerStatsSet.add(playerStats);
            }while (cursor.moveToNext());
        }

        return playerStatsSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
