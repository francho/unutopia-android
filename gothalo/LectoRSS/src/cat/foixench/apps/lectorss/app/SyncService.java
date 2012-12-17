package cat.foixench.apps.lectorss.app;

import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;

public class SyncService extends IntentService 
						 implements LectoRSSInterface {
	
	private ResultReceiver resultReceiver;
	
	private static final String TEST_FEED = "http://www.gothalo.net/index.php/blog?format=feed&type=rss";
	
	public SyncService () {
		super (SERVICE_TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// lanzamos la tarea de carga del feed rss.
		resultReceiver = (ResultReceiver) intent.getParcelableExtra (EXTRA_FEED_RECEIVER);

		if (resultReceiver != null) {
			resultReceiver.send(LOAD_STARTED, null);
		}
		
		FeedsLoader task = new FeedsLoader (this);

		task.loadFeeds (TEST_FEED);
		
		if (resultReceiver != null) {
			resultReceiver.send (LOAD_ENDED, null);
		}
			
		
	}

}
