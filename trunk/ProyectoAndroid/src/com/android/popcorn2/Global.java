package com.android.popcorn2;

import android.app.Application;

public class Global extends Application {
	
	private PeliculaDbAdapter DbHelper;
	
	@Override
	public void onCreate() {
		
		super.onCreate();
		open();
	}

	public PeliculaDbAdapter open() {
		return DbHelper.open();
	}
	
	public PeliculaDbAdapter getDbHelper() {
		return DbHelper;
	}
}
