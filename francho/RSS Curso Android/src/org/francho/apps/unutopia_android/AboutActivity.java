package org.francho.apps.unutopia_android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AboutActivity extends Activity {

	private CountDownTimer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about);

		setVersionNumber();
		enableLinks();
	}

	private void setVersionNumber() {
		final TextView versionView = (TextView) findViewById(R.id.about_version);
		if (null == versionView) {
			return;
		}

		try {
			final PackageInfo pkgInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			final String format = getString(R.string.about_version);
			final String versionTxt = String.format(format,
					pkgInfo.versionName, pkgInfo.versionCode);
			versionView.setText(versionTxt);
		} catch (NameNotFoundException e) {
			versionView.setText(R.string.about_unknown_version);
		}
	}

	private void enableLinks() {
		setAboutLink();

		enableLinksInText(R.id.about_intro, getString(R.string.about_text));
		enableLinksInText(R.id.about_legal, getString(R.string.about_license));
	}

	private void setAboutLink() {
		final TextView text = (TextView) findViewById(R.id.about_subtitle);
		if (null == text) {
			return;
		}

		text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://francho.org/about"));
				startActivity(intent);
			}
		});

	}

	private void enableLinksInText(int resId, String source) {
		final TextView view = (TextView) findViewById(resId);
		if (null == view) {
			return;
		}
		
		view.setFocusable(true);
		view.setLinksClickable(true);
		Linkify.addLinks(view, Linkify.ALL);
	}
}
