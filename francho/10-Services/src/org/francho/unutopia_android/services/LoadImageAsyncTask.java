package org.francho.unutopia_android.services;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

	private ImageView imageView;

	public LoadImageAsyncTask(ImageView imageView) {
		this.imageView = imageView;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		String urlString = params[0];

		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream input = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(input);
			connection.disconnect();
		} catch (Exception e) {
			bitmap = null;
			e.printStackTrace();
		}
		
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		
		imageView.setImageBitmap(result);
	}

	@Override
	protected void onPreExecute() {
		imageView.setImageBitmap(null);
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	
	
}
