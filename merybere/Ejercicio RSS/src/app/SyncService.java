package app;

import data.ArticlesFeed;
import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;

public class SyncService extends IntentService {

	private static final String TAG = "SyncService";
	private ResultReceiver resultReceiver = new ResultReceiver(null);
	
	public SyncService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");

		resultReceiver = (ResultReceiver) intent.getParcelableExtra("com.merybere.apps.EXTRA_MYRESULTRECEIVER");
		
		// Comprobar que nos han pasado el resultReceiver, para evitar null pointer exception
		if(resultReceiver != null) {
			// Al arrancar, mandar un result code 1
			resultReceiver.send(1, null);
		}
		
		ArticlesFeed articlesTask = new ArticlesFeed();
		articlesTask.loadNewArticles();
		
		if(resultReceiver != null) {
			// Al finalizar, mandar un result code 0
			resultReceiver.send(0, null);
		}		
	}

}
