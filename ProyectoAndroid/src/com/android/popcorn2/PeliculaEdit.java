package com.android.popcorn2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PeliculaEdit extends Activity {

	private EditText mTitleText;
    private EditText mSinopsisText;
    private EditText mDirectorText;
    private EditText mActoresText;
    private Long mRowId;
    private static final int NOTIF_ALERTA_ID = 1;
    private PeliculaDbAdapter mDbHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new PeliculaDbAdapter(this);
        mDbHelper.open();
        
        setContentView(R.layout.pelicula_crear);
        setTitle(R.string.editar_pelicula);
        
        mTitleText = (EditText) findViewById(R.id.title);
        mSinopsisText = (EditText) findViewById(R.id.sinopsis);
        mDirectorText = (EditText) findViewById(R.id.director);
        mActoresText = (EditText) findViewById(R.id.actores);

        Button confirmButton = (Button) findViewById(R.id.confirm);

        mRowId = (savedInstanceState == null) ? null :
        	(Long) savedInstanceState.getSerializable(PeliculaDbAdapter.KEY_ROWID);
        if (mRowId == null) {
        	Bundle extras = getIntent().getExtras();
        	mRowId = extras != null ? extras.getLong(PeliculaDbAdapter.KEY_ROWID)
        							: null;
        }
        
        populateFields();
        	
        confirmButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Obtenemos una referencia al servicio de notificaciones
				String ns = Context.NOTIFICATION_SERVICE;
				NotificationManager notManager = 
					(NotificationManager) getSystemService(ns);
				
				//Configuramos la notificaci�n
				int icono = android.R.drawable.stat_sys_warning;
				CharSequence textoEstado = "Alerta!";
				long hora = System.currentTimeMillis();

				Notification notif = 
					new Notification(icono, textoEstado, hora);
				
				//Configuramos el Intent
				Context contexto = getApplicationContext();
				CharSequence titulo = "Notificacion";
				CharSequence descripcion = "Notificacion enviada correctamente";
				
				Intent notIntent = new Intent(contexto, 
						PeliculaEdit.class);
				
				PendingIntent contIntent = PendingIntent.getActivity(
						contexto, 0, notIntent, 0);

				notif.setLatestEventInfo(
						contexto, titulo, descripcion, contIntent);
				
				//AutoCancel: cuando se pulsa la notificai�n �sta desaparece
				notif.flags |= Notification.FLAG_AUTO_CANCEL;
				
				//A�adir sonido, vibraci�n y luces
				//notif.defaults |= Notification.DEFAULT_SOUND;
				//notif.defaults |= Notification.DEFAULT_VIBRATE;
				//notif.defaults |= Notification.DEFAULT_LIGHTS;
				
				//Enviar notificaci�n
				notManager.notify(NOTIF_ALERTA_ID, notif);
				
				setResult(RESULT_OK);
                finish();
			}
		});
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
