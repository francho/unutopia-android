package org.francho.apps.unutopia_android;

import org.francho.apps.unutopia_android.app.AppIntent;
import org.francho.apps.unutopia_android.data.FeedContract.Articles;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * A fragment representing a single number detail screen. This fragment is
 * either contained in a {@link numberListActivity} in two-pane mode (on
 * tablets) or a {@link numberDetailActivity} on handsets.
 */
public class ArticleDetailFragment extends Fragment {

	private Uri mUri;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ArticleDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(AppIntent.EXTRA_ARTICLE_ID)) {
			long id = getArguments().getLong(AppIntent.EXTRA_ARTICLE_ID);
			mUri = Articles.getUri(id);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_article_detail,
				container, false);

		final TextView titleView = (TextView) rootView
				.findViewById(R.id.article_title);
		final WebView descView = (WebView) rootView
				.findViewById(R.id.article_detail);
		setupWebView(descView);
		final TextView dateView = (TextView) rootView
				.findViewById(R.id.article_date);

		// Show the dummy content as text in a TextView.
		if (mUri != null) {

			String[] projection = new String[] { Articles.TITLE, // 0
					Articles.CONTENT, // 1
					Articles.PUB_DATE, // 2
					Articles.LINK // 3
			};

			Cursor cursor = getActivity().getContentResolver().query(mUri,
					projection, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				String title = cursor.getString(0);
				titleView.setText(title);

				String html = cursor.getString(1);
				descView.loadDataWithBaseURL(null, html, "text/html", "utf-8",
						null);

				String date = (String) DateUtils.getRelativeTimeSpanString(
						getActivity(), cursor.getLong(2));
				dateView.setText(date);
			}
			cursor.close();
		}

		return rootView;
	}

	private void setupWebView(WebView webView) {
		webView.getSettings().setJavaScriptEnabled(true);

		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("WebView", url);
				Uri uri = Uri.parse(url);
				Context context = getActivity();
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				context.startActivity(intent);

				return (true);
			}
		});
	}
}
