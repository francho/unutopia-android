package org.francho.apps.unutopia_android.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.francho.apps.unutopia_android.data.FeedContract;
import org.francho.apps.unutopia_android.data.FeedContract.Articles;
import org.xml.sax.SAXException;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.RemoteException;

public class RssSyncService extends IntentService {

	private static final String TAG = "RssSyncService";
	private static final String FEED_URL = "http://francho.org/tag/curso-unutopia-android/feed";

	public RssSyncService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final RssFeed feed = downloadFeed();
		if(feed == null) {
			return;
		}
		
		final ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		final ArrayList<RssItem> rssItems = feed.getRssItems();
		
		for(RssItem rssItem : rssItems) {
			final Builder operation = ContentProviderOperation.newInsert(Articles.getUri());
			operation.withValue(Articles.LINK, rssItem.getLink().trim());
			operation.withValue(Articles.CONTENT, rssItem.getContent().trim());
			operation.withValue(Articles.DESCRIPTION, rssItem.getDescription().trim());
			operation.withValue(Articles.TITLE, normalizeTitle(rssItem.getTitle()));
			operation.withValue(Articles.PUB_DATE, rssItem.getPubDate().getTime());
			
			operations.add(operation.build());
		} 
		
		if(operations.size()>0) {
			try {
				getContentResolver().applyBatch(FeedContract.AUTHORITY, operations);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (OperationApplicationException e) {
				e.printStackTrace();
			}	
		}
	}

	private String normalizeTitle(String title) {
		return title.replaceAll(".*Curso online de programaci—n Android - (.*)", "\1").trim();
	}

	private RssFeed downloadFeed() {
		try {
			final URL url = new URL(FEED_URL);
			return RssReader.read(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
