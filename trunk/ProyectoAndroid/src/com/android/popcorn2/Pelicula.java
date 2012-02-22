package com.android.popcorn2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pelicula extends Activity {
	
	private static final int ACTIVITY_CREATE=0;
	
	
	    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    //Creamos un boton que nos va a llevar a la actividad de ListarPeliculas
	    Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), ListarPeliculas.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        //Creamos otro boton que nos lleva a crear una pelicula, 
        //es la misma clase que editar pelicula
        Button siguiente = (Button) findViewById(R.id.Button03);
        siguiente.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view2) {
				Intent i = new Intent(view2.getContext(), PeliculaEdit.class);
		        startActivityForResult(i, ACTIVITY_CREATE);
			}
		});
	}
}

