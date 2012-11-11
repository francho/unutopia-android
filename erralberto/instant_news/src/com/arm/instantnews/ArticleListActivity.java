package com.arm.instantnews;

import com.arm.instantnews.app.AppIntent;
import com.arm.instantnews.widget.FeedsAdapter;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;


public class ArticleListActivity extends ListActivity {

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       final FeedsAdapter adapter = new FeedsAdapter(this);
	   setListAdapter(adapter);         
   }
   
   @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = AppIntent.getArticleIntent(id);		
		startActivity(intent);
	}

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.activity_about, menu);
       getMenuInflater().inflate(R.menu.activity_exit, menu);
       return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       // Handle item selection
	   switch(item.getItemId()) { 
       case R.id.menu_about: 
             showAbout();
             return true;
       case R.id.menu_exit:	        
	    	exit();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);

	   }
   }
   
  /* @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			startActivity(AppIntent.getAboutIntent());
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}*/

   
   void showAbout() {
   	
   	WebView webView = new WebView(this);
	    //<font color=#ec03bd>
		String html = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>About</title></head><body ><center>" +
						"<font color=white>"+
						"<img src=\"logo.9.png\" width=\"100%\">" +
						"<pre>        " + getVersion(this) + "</pre>" +
						"<p Align=left>Instant<span style=\"color:#ec03bd;\"> News</span> por <span xmlns:cc=\"http://creativecommons.org/ns#\" property=\"cc:attributionName\">Alberto Rodríguez Montiel</span> se encuentra bajo una:</p><p Align=center> <a rel=\"license\" href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/deed.es\">Licencia Creative Commons Atribución-NoComercial-CompartirIgual 3.0 Unported</a></span></p> "+												
						"<img src=\"ccc.png\" width=\"40%\">&nbsp;"+
						"&nbsp; <img src=\"cc_letra.png\" width=\"40%\">"+
						"<p Align=left><br>El código fuente de esta aplicación puedes encontrarlo en el repositorio del Curso Android by Francho-Joven:</p> "+
						"<p><a href=\"https://github.com/francho/unutopia-android.git\">github unutopía-android 2012</a></p>"+
						"<p>&copy; Copyright 2012 AndroTech Ltd.</p>" +	
						"</center></body></html>";
	   webView.setBackgroundColor(Color.BLACK);
       webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);
       new AlertDialog.Builder(this).setIcon(R.drawable.pirate1).setTitle(R.string.title_activity_about).setView(webView).setCancelable(true).setPositiveButton(getString(R.string.splash_botton), new DialogInterface.OnClickListener() {			
			//@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		}).show();
       
   }
   
   public String getVersion(Context context) {		
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			String format = getString(R.string.about_version);
			String version = String.format(format,packageInfo.versionName, packageInfo.versionCode);
			return version;
		} catch (NameNotFoundException e) {
		}
		return "unknown";
	}
   
   public void exit(){
	   
   }
   
}