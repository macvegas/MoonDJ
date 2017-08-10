package com.example.simonbeaulieu.moondj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.simonbeaulieu.moondj.HeritageActivity.echo;

/**
 * Created by simon.beaulieu on 02/08/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="MusicStash";
    private static final String TABLE_MUSICS ="musiques";
    private static final String KEY_ID="id";
    private static final String KEY_TITRE="titre";
    private static final String KEY_ARTISTE="artiste";
    private static final String KEY_PATH="path";
    private static final String KEY_NOTATION="note";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MUSICS + "("
        + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +KEY_PATH + " TEXT, " + KEY_TITRE + " TEXT,"
        + KEY_ARTISTE + " TEXT," + KEY_NOTATION + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
//        close();
            // TODO: 10/08/2017 rajouter une colonne temp pour revenir là ou on a laissé l'application (a la sortie de l'app)
        }

        catch (SQLiteException e){
            Log.e("DBHhandler's onCreate","Table Already Exists");
            close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSICS);
        onCreate(sqLiteDatabase);
    }

    public void dropTableSong(){
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS "+ TABLE_MUSICS);
    }

    public void addSong(Song song){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_PATH,song.getPath());
        values.put(KEY_TITRE,song.getTitle());
        values.put(KEY_ARTISTE,song.getArtist());
        values.put(KEY_NOTATION,song.getNotation());
        database.insert(TABLE_MUSICS,null,values);
        database.close();
    }

    public Song getSong(String titre){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MUSICS, new String[] {KEY_ID, KEY_PATH,
                        KEY_TITRE, KEY_ARTISTE, KEY_NOTATION }, KEY_TITRE + "=?",
                new String[] { titre }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Song song = new Song(cursor.getInt(0),cursor.getString(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        cursor.close();
// return shop
        return song;
    }

    public int updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PATH,song.getPath());
        values.put(KEY_TITRE,song.getTitle());
        values.put(KEY_ARTISTE,song.getArtist());
        values.put(KEY_NOTATION,song.getNotation());
// updating row
        return db.update(TABLE_MUSICS, values, KEY_TITRE + " = ?",
                new String[]{song.getTitle()});
    }

    public void deleteSong(String titre) {
//        Song song=getSong(titre);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MUSICS, KEY_TITRE + " = ?",
                new String[] { titre });
        db.close();
    }

    public int getSongCount() {
        String countQuery = "SELECT * FROM " + TABLE_MUSICS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int nb= cursor.getCount();
        cursor.close();
    // return count
        return nb;
    }

    public List<Song> getAllSongs() {
        List<Song> songList = new ArrayList<Song>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MUSICS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Song song = new Song();
                song.setId(cursor.getInt(0));
                song.setPath(cursor.getString(0));
                song.setTitle(cursor.getString(1));
                song.setArtist(cursor.getString(2));
                song.setNotation(cursor.getString(3));
// Adding contact to list
                songList.add(song);
            } while (cursor.moveToNext());
        }
// return contact list
        cursor.close();
        return songList;
    }
}
