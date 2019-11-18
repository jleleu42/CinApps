package com.example.cinapps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "film.db";

    private static final int DATABASE_VERSION = 1;

    // Constructor
    public FilmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FILM_TABLE = "CREATE TABLE " + FilmDesc.FilmDescEntry.TABLE_NAME + " (" +
                FilmDesc.FilmDescEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FilmDesc.FilmDescEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FilmDesc.FilmDescEntry.COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                FilmDesc.FilmDescEntry.COLUMN_SCENARIO + " INTEGER NOT NULL," +
                FilmDesc.FilmDescEntry.COLUMN_REALISATION + " INTEGER NOT NULL, " +
                FilmDesc.FilmDescEntry.COLUMN_MUSIQUE + " INTEGER NOT NULL, " +
                FilmDesc.FilmDescEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FILM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FilmDesc.FilmDescEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
