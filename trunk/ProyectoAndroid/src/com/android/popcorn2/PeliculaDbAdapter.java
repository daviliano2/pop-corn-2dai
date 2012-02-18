package com.android.popcorn2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PeliculaDbAdapter {
	public static final String KEY_TITLE = "title";
    public static final String KEY_SINOPSIS = "sinopsis";
    public static final String KEY_DIRECTOR = "director";
    public static final String KEY_ACTORES = "actors";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "PeliculaDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    //Creacion de la base de datos
    
    private static final String DATABASE_CREATE =
            "create table peliculas (_id integer primary key autoincrement, "
            + "title text not null, sinopsis text, director text, actors text);";
    
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "peliculas";
    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
    
    public  PeliculaDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }
    
    public PeliculaDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    
    //Crear una nueva pelicula
    
    public long createPelicula(String title, String sinopsis, 
    		String director, String actores) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_SINOPSIS, sinopsis);
        initialValues.put(KEY_DIRECTOR, director);
        initialValues.put(KEY_ACTORES, actores);
        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    //Borrar pelicula
    
    public boolean deletePelicula(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    //Cursor que devuelve la lista de peliculas de la base de datos
    
    public Cursor fetchAllPeliculas() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
                KEY_SINOPSIS,KEY_DIRECTOR,KEY_ACTORES}, null, null, null, null, null);
    }
    
    //Cursor que se coloca en la pelicula que coincide con el rowId pasado
    
    public Cursor fetchPelicula(long rowId) throws SQLException {

        Cursor mCursor =
        		mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_TITLE, KEY_SINOPSIS, KEY_DIRECTOR, KEY_ACTORES}, 
                    KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    //Actualizar pelicula con los parametros pasados en la llamada
    
    public boolean updatePelicula(long rowId, String title, String sinopsis, String director, String actores) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_SINOPSIS, sinopsis);
        args.put(KEY_DIRECTOR, director);
        args.put(KEY_ACTORES, actores);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
