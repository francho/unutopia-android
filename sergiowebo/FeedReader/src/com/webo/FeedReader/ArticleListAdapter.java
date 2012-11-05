package com.webo.FeedReader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArticleListAdapter extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<ArticleItem> listItems;
	
	public ArticleListAdapter(Activity activity, ArrayList<ArticleItem> listItems) {
		this.activity = activity;
		this.listItems = listItems;
	}
	
	public int getCount() {
		return listItems.size();
	}
	public Object getItem(int arg0) {
		return listItems.get(arg0);
	}
	public long getItemId(int arg0) {
		return listItems.get(arg0).getId();
	}
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		View view = arg1;
		
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.article_list_row, null);
		} 
		
		ArticleItem item = listItems.get(arg0);
		
		TextView title = (TextView) view.findViewById(R.id.title);
	    title.setText(item.getTitle());
	    
	    TextView author = (TextView) view.findViewById(R.id.author);
	    author.setText(item.getAuthor());
		
	    TextView date = (TextView) view.findViewById(R.id.date);
	    date.setText(item.getDate());
	    
		return view;
	}
	

	
}
