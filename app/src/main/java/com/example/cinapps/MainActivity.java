package com.example.cinapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity  {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private SQLiteDatabase mDb;

    private FilmListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        RecyclerView FilmListRecyclerView;
        FilmListRecyclerView = (RecyclerView) this.findViewById(R.id.rv_film);
        FilmListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FilmDbHelper dbHelper = new FilmDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllFilms();
        mAdapter = new FilmListAdapter(this, cursor);
        FilmListRecyclerView.setAdapter(mAdapter);

        //Intégration Firebase
        List<Film> dataList = new ArrayList<Film>() ;
        Cursor c = mDb.rawQuery("select * from " + FilmDesc.FilmDescEntry.TABLE_NAME,null );
        try {
            while (c.moveToNext()) {
                Film f = new Film();
                f.setTitle(c.getString(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_TITLE)));
                f.setDate(c.getString(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_DATE)));
                f.setScenario(c.getInt(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_SCENARIO)));
                f.setRealisation(c.getInt(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_REALISATION)));
                f.setMusique(c.getInt(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_MUSIQUE)));
                f.setDescription(c.getString(c.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_DESCRIPTION)));
                dataList.add(f);
                Log.e(LOG_TAG, "Title : " + f.getTitle() );
                Log.e(LOG_TAG, "Title : " + f.getScenario() );

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("film").child(f.getTitle()).setValue(f);

            }
        }  finally {
            c.close();
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            // on va sur la nouvelle intent
            Class<AddActivity> destinationActivity = AddActivity.class;
            Context context = MainActivity.this;
            Intent i = new Intent(context,destinationActivity);
            startActivity(i);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    private Cursor getAllFilms() {
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
