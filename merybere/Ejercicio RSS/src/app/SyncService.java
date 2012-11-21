package app;

import data.ArticlesDbHelper;
import data.ArticlesFeed;
import data.RSSInterface;
import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.ResultReceiver;
import android.util.Log;

public class SyncService extends IntentService {

	private static final String TAG = "SyncService";
	private ResultReceiver resultReceiver = new ResultReceiver(null);
	public ArticlesDbHelper articlesDbHelper = new ArticlesDbHelper(this);
	
	public SyncService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");

		resultReceiver = (ResultReceiver) intent.getParcelableExtra(RSSInterface.INTENT_RESULTRECEIVER);
		
		// Comprobar que nos han pasado el resultReceiver, para evitar null pointer exception
		if(resultReceiver != null) {
			// Al arrancar, mandar un result code 1
			resultReceiver.send(RSSInterface.CODE_START_TASK, null);
		}
		
		ArticlesFeed articlesTask = new ArticlesFeed();
		
		final SQLiteDatabase db = articlesDbHelper.getWritableDatabase();
		articlesTask.loadNewArticles(db);
		
		if(resultReceiver != null) {
			// Al finalizar, mandar un result code 0
			resultReceiver.send(RSSInterface.CODE_END_TASK, null);
		}		
	}

}
