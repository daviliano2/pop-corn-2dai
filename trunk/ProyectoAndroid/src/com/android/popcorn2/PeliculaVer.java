package com.android.popcorn2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class PeliculaVer extends Activity {

	private TextView mTitleText;
    private TextView mSinopsisText;
    private TextView mDirectorText;
    private TextView mActoresText;
    private Long mRowId;
    private PeliculaDbAdapter mDbHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new PeliculaDbAdapter(this);
        mDbHelper.open();
        
        setContentView(R.layout.pelicula_ver);
        setTitle(R.string.ver_pelicula);
        
        mTitleText = (TextView) findViewById(R.id.vertitle);
        mSinopsisText = (TextView) findViewById(R.id.versinopsis);
        mDirectorText = (TextView) findViewById(R.id.verdirector);
        mActoresText = (TextView) findViewById(R.id.veractores);
        
        mRowId = (savedInstanceState == null) ? null :
        	(Long) savedInstanceState.getSerializable(PeliculaDbAdapter.KEY_ROWID);
        if (mRowId == null) {
        	Bundle extras = getIntent().getExtras();
        	mRowId = extras != null ? extras.getLong(PeliculaDbAdapter.KEY_ROWID)
        							: null;
        }
        
        populateFields();
      
    }
    
    private void populateFields() {
        if (mRowId != null) {
            Cursor pelicula = mDbHelper.fetchPelicula(mRowId);
            startManagingCursor(pelicula);
            mTitleText.setText(pelicula.getString(
                    pelicula.getColumnIndexOrThrow(PeliculaDbAdapter.KEY_TITLE)));
            mSinopsisText.setText(pelicula.getString(
                    pelicula.getColumnIndexOrThrow(PeliculaDbAdapter.KEY_SINOPSIS)));
            mDirectorText.setText(pelicula.getString(
            		pelicula.getColumnIndexOrThrow(PeliculaDbAdapter.KEY_DIRECTOR)));
            mActoresText.setText(pelicula.getString(
            		pelicula.getColumnIndexOrThrow(PeliculaDbAdapter.KEY_ACTORES)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(PeliculaDbAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String title = mTitleText.getText().toString();
        String sinopsis = mSinopsisText.getText().toString();
        String director = mDirectorText.getText().toString();
        String actores = mActoresText.getText().toString();

        if (mRowId == null) {
            long id = mDbHelper.createPelicula(title, sinopsis, director, actores);
            if (id > 0) {
                mRowId = id;
            }
        } else {
        	mDbHelper.updatePelicula(mRowId, title, sinopsis, director, actores);
        }
    }
}
