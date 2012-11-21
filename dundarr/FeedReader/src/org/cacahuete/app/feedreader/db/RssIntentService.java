package org.cacahuete.app.feedreader.db;


import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssItem;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;


public class RssIntentService extends IntentService {



	private static final String TAG = "RssIntentService";
	private ResultReceiver resultReceiver;

	public RssIntentService() {
		super(TAG);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "iniciando IntentService");
		
		resultReceiver = (ResultReceiver) intent.getParcelableExtra("EXTRA_MYRESULTRECEIVER");
		
		
		RssReload reloadTask = new RssReload();
		reloadTask.borrarArticulos(getContentResolver());
		
		reloadTask.loadFeeds(getContentResolver());
		if(resultReceiver != null) {
			resultReceiver.send(10, null);
		}
		Float porcentaje_por_feed=(float) (90/reloadTask.getNumberOfFeeds());
		Float porcentaje_acumulado=Float.valueOf(10);
		Log.d(TAG,"Porcentaje por feed es "+porcentaje_por_feed.toString());
		Log.d(TAG,"Progreso es "+porcentaje_acumulado.toString()+".Hay "+reloadTask.getNumberOfFeeds()+" feeds");
		for(URL url : reloadTask.getFeedList()) {
			
			ArrayList<RssItem> lista_articulos=reloadTask.reloadArticlesForFeed(url);
	//		Log.d(TAG,"Progreso es "+porcentaje_acumulado.toString()+".Hay "+lista_articulos.size()+" articulos en feed");
			Float porcentaje_por_articulo=porcentaje_por_feed/lista_articulos.size();
			Log.d(TAG,"El porcentaje por articulo es "+porcentaje_por_articulo.toString());
			for(RssItem rssItem : lista_articulos) {
				reloadTask.insertArticle(getContentResolver(), rssItem);
				porcentaje_acumulado=porcentaje_acumulado+porcentaje_por_articulo;
				Log.d(TAG,"Progreso es "+porcentaje_acumulado.toString()+", insertando articulo");
				Integer progreso=Math.round(porcentaje_acumulado);
				if(resultReceiver != null) {
					resultReceiver.send(progreso, null);
				}
			}
		}
		

	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	
}
