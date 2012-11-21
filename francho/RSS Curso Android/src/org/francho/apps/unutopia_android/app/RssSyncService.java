package org.francho.apps.unutopia_android.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import org.francho.apps.unutopia_android.data.FeedContract;
import org.francho.apps.unutopia_android.data.FeedContract.Articles;
import org.xml.sax.SAXException;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.RemoteException;

public class RssSyncService extends IntentService {

	private static final String TAG = "RssSyncService";
	private static final String FEED_URL = "http://francho.org/tag/curso-unutopia-android/feed";
	private Pattern youtubePattern;

	public RssSyncService() {
		super(TAG);
		
		String regex = "<span class='embed-youtube'[^>]+>.*<iframe.+src='http://www.youtube.com/embed/([A-Za-z0-9_-]+)[^>]+>.*</iframe></span>";
		
		youtubePattern = Pattern.compile(regex, Pattern.MULTILINE);
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
			operation.withValue(Articles.CONTENT, normalizeContent(rssItem.getContent()));
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
		return title.replace("Curso online de programaci—n Android Ð ", "").trim();
	}
	
	private String normalizeContent(String html) {
		String result = html;
		
		Matcher matcher = youtubePattern.matcher(html);
		if(matcher.find()) {
			String youtubeId = matcher.group(1);
			String youtube = String.format(
					"<span style='text-align:center; display: block;'><a href='http://youtube.com/watch?v=%s'><img src='http://i.ytimg.com/vi/%s/0.jpg' /><br/>ver hangout</a></span>"
					, youtubeId
					, youtubeId);
			result = result.replace(matcher.group(0), youtube);
		}
		
		return result.trim();
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
