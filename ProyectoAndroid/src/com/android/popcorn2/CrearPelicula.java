package com.android.popcorn2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class CrearPelicula extends ListActivity {
	
	private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
	private PeliculaDbAdapter mDbHelper;
	
	//Este metodo se llama cuando la actividad es creada la primera vez
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelicula_lista);
        mDbHelper = new PeliculaDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
                createPelicula();
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
	
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }
	
	@Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deletePelicula(info.id);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }
	
	private void createPelicula() {
		Intent i = new Intent(this, PeliculaEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
	
	private void fillData() {
		Cursor notesCursor = mDbHelper.fetchAllPeliculas();
        startManagingCursor(notesCursor);

        // Creamos un array que contiene los campos que se van a visualizar en la lista (de momento solo el titulo)
        String[] from = new String[]{PeliculaDbAdapter.KEY_TITLE,PeliculaDbAdapter.KEY_DIRECTOR};

        // Creamos otro array con los campos que queremos asociar al array de arriba
        int[] to = new int[]{R.id.titulo,R.id.autor};

        // Ahora creamos un simple cursor adapter y lo fijamos
        SimpleCursorAdapter peliculas = 
            new SimpleCursorAdapter(this, R.layout.pelicula_item, notesCursor, from, to);
        setListAdapter(peliculas);
    }
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, PeliculaEdit.class);
        i.putExtra(PeliculaDbAdapter.KEY_ROWID, id);        
        startActivityForResult(i, ACTIVITY_EDIT);
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}