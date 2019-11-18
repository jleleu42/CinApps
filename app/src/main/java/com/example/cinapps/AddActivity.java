package com.example.cinapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    private SQLiteDatabase mDb;
    private FilmListAdapter mAdapter;

    private EditText et_title;
    private EditText et_date;
    private EditText et_scenario;
    private EditText et_realisation;
    private EditText et_musique;
    private EditText et_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //recup des data
        et_title = (EditText) this.findViewById(R.id.et_edit_title);
        et_date = (EditText) this.findViewById(R.id.et_edit_date);
        et_scenario = (EditText) this.findViewById(R.id.et_edit_scenario);
        et_realisation = (EditText) this.findViewById(R.id.et_edit_realisation);
        et_musique= (EditText) this.findViewById(R.id.et_edit_musique);
        et_desc = (EditText) this.findViewById(R.id.et_edit_desc);

        //ouverture connexion db
        FilmDbHelper dbHelper = new FilmDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllFilms();
        mAdapter = new FilmListAdapter(this, cursor);

    }


    public void addFilmList(View view) {
        //TODO save data on sqllite and IF it's okay finish ELSE popup error and finish

        if (et_title.getText().length() == 0){
            return;
        }
        int scenario = 0;
        int realisation = 0;
        int musique = 0;

        try {
            // on convertit en nombre
            scenario = Integer.parseInt(et_scenario.getText().toString());
            realisation = Integer.parseInt(et_realisation.getText().toString());
            musique = Integer.parseInt(et_musique.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Failed to parse text to number: " + ex.getMessage());
        }

        // Add film to db
        addNewFilm(et_title.getText().toString(), et_date.getText().toString(), scenario, realisation ,musique, et_desc.getText().toString());

        // Update the cursor
        mAdapter.swapCursor(getAllFilms());

        //fin et retour au main
        finish();
    }




    private long addNewFilm(String title, String date, int scenario, int realisation ,int musique, String desc) {
        ContentValues cv = new ContentValues();
        cv.put(FilmDesc.FilmDescEntry.COLUMN_TITLE, title);
        cv.put(FilmDesc.FilmDescEntry.COLUMN_DATE, date);
        cv.put(FilmDesc.FilmDescEntry.COLUMN_SCENARIO, scenario);
        cv.put(FilmDesc.FilmDescEntry.COLUMN_REALISATION, realisation);
        cv.put(FilmDesc.FilmDescEntry.COLUMN_MUSIQUE, musique);
        cv.put(FilmDesc.FilmDescEntry.COLUMN_DESCRIPTION, desc);

        return mDb.insert(FilmDesc.FilmDescEntry.TABLE_NAME, null, cv);
    }


    public Cursor getAllFilms() {
        return mDb.query(
                FilmDesc.FilmDescEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                FilmDesc.FilmDescEntry.COLUMN_TITLE
        );
    }

}
