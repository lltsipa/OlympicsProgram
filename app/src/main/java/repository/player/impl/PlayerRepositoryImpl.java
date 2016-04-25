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
import domain.player.Player;
import repository.player.PlayerRepository;
import repository.player.PlayerStatsRepository;


/**
 * Created by lodz on 2016/04/25.
 */
public class PlayerRepositoryImpl extends SQLiteOpenHelper implements PlayerRepository {

    public static final String TABLE_NAME = " player ";
    SQLiteDatabase db;

    public static final String COLUMN_ID = " id ";
    public static final String COLUMN_NAME = " name ";
    public static final String COLUMN_SURNAME = " surname ";
    public static final String COLUMN_AGE = " age ";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ( "
            + COLUMN_ID + " TEXT UNIQUE NOT NULL "
            + COLUMN_NAME + " TEXT UNIQUE NOT NULL "
            + COLUMN_SURNAME + " TEXT UNIQUE NOT NULL "
            + COLUMN_AGE + " TEXT UNIQUE NOT NULL); ";

    public PlayerRepositoryImpl(Context context)
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
    public Player findByID(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID,
                COLUMN_NAME,
                COLUMN_SURNAME,
                COLUMN_AGE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if(cursor.moveToFirst())
        {
            final Player player = new Player.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .PlayerName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .PlayerSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .PlayerAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                    .build();
            return player;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Player save(Player entity) {

        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getPlayerName());
        values.put(COLUMN_SURNAME, entity.getPlayerSurname());
        values.put(COLUMN_AGE, entity.getPlayerAge());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Player player = new Player.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return player;

    }

    @Override
    public Player update(Player entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getPlayerName());
        values.put(COLUMN_SURNAME, entity.getPlayerSurname());
        values.put(COLUMN_AGE, entity.getPlayerAge());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Player delete(Player entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Player> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Player> playerSet = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        do{
            Player player = new Player.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .PlayerName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .PlayerSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .PlayerAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                    .build();
            playerSet.add(player);
        }while(cursor.moveToNext());

        return playerSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
