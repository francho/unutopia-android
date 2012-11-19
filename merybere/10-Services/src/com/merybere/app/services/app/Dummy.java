package com.merybere.app.services.app;

import android.util.Log;

public class Dummy {

	private static final String TAG = "Dummy";

	// Bucle que espere 5 segundos
	public void hardWork() {
		Log.d(TAG, "hardwork start");
		long endTime = System.currentTimeMillis() + 5 * 1000;
		
		while(System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
					Log.d(TAG, "hardwork stop");
				}
			}
		}
		
		Log.d(TAG, "hardwork stop");
	}
}
