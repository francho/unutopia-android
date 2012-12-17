package cat.foixench.apps.lectorss.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import org.xml.sax.SAXException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;
import cat.foixench.apps.lectorss.R;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;

public class FeedsLoader {
	
	private final static int DEFAULT_TOAST_DURATION = 2500;
	
	private Context context;

	public FeedsLoader (Context context) {
		this.context = context;
	}
	
	/**
	 * Carga las entradas en el feed indicado como par‡metro. Para la carga usamos la libreria Android-RSS-Reader-Library
	 * @param feedName URL del feed a cargar.
	 * @see https://github.com/matshofman/Android-RSS-Reader-Library
	 */
	public void loadFeeds (String feedName) {

		// definimos una URL con la direccion recivida como string
		
		try {
			URL urlFeed = new URL (feedName);
			
			// carga del feed
			RssFeed feed = RssReader.read(urlFeed);
			
			// procesamos los registros recuperados en el feed
			
			ArrayList <RssItem> rssItems = feed.getRssItems();
			
			this.copyRssIntoDB (rssItems);
			
			
			
		} catch (MalformedURLException mue) {
			Toast t = Toast.makeText(context, R.string.feedsloader_error_malformed, DEFAULT_TOAST_DURATION);
			t.show ();
		} catch (SAXException e) {
			Toast t = Toast.makeText(context, R.string.feedsloader_error_sax, DEFAULT_TOAST_DURATION);
			t.show ();
		} catch (IOException e) {
			Toast t = Toast.makeText(context, R.string.feedsloader_error_io, DEFAULT_TOAST_DURATION);
			t.show ();
		}
	}
	
	// copia los items cargados en la bbdd en la tabla de feeds
	public void copyRssIntoDB (ArrayList <RssItem> rssItems) {
		for (RssItem rssOneItem : rssItems) {
			
			String itemLink = rssOneItem.getLink();
			// verificamos que no exista el item en la base de datos
			Uri uri = FeedsTable.getUri ();
			String [] projection = {FeedsTable._ID};
			
			String selection = FeedsTable.COLUMN_LINK + " = ?";
			String[] selectionArgs = new String [] {itemLink};
			
			Cursor result = context.getContentResolver ().query(uri, projection, selection, selectionArgs, null);
			// si no hay resultado es un item nuevo
			if (result.getCount() == 0) {
				ContentValues values = new ContentValues();
				
				values.put (FeedsTable.COLUMN_TITLE, rssOneItem.getTitle());
				values.put (FeedsTable.COLUMN_AUTHOR, "");
				values.put (FeedsTable.COLUMN_DESCRIPTION, rssOneItem.getDescription());
				values.put (FeedsTable.COLUMN_LINK, rssOneItem.getLink ());
				values.put (FeedsTable.COLUMN_PUB_DATE, rssOneItem.getPubDate().getTime() );

				context.getContentResolver ().insert(uri, values );
			}
			
			result.close();
			
		}
	}

}
