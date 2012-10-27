package org.francho.apps.unutopia_android.widget;

import java.util.List;
import java.util.Map;

import org.francho.apps.unutopia_android.R;
import org.francho.apps.unutopia_android.data.FeedContract;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FeedsAdapter extends SimpleAdapter  {
	private static final String[] FROM = new String[] { FeedContract.TITLE, FeedContract.DATE };
	private static final int[] TO = new int[] { R.id.feed_listitem_title,
			R.id.feed_listitem_date };
	private Context context;
	

	public FeedsAdapter(Context context, List<? extends Map<String, ?>> data) {
		super(context, data, R.layout.feed_listitem, FROM, TO);
		
		this.context = context;
	}


	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		super.setViewText(v, text);
	}

	private boolean isDateView(TextView v) {
		return v.getId() == R.id.feed_listitem_date;
	}
	
	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);
		
		return (String)DateUtils.getRelativeTimeSpanString(context, millis);
	}

}
