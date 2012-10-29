package com.webo.FeedReader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ArticleList extends BaseAdapter{

	private Context context;
	private List<ArticleItem> items;
	
	public ArticleList(Context context){
		this.context = context;
		this.items = getItems();
	}
	
	private List<ArticleItem> getItems() {		
		
		return null;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
