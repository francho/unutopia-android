package com.arm.instantnews;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class ArticleListActivity extends Activity {

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       

         
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.activity_main, menu);
       getMenuInflater().inflate(R.menu.activity_main, menu);
       return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       // Handle item selection
	   switch(item.getItemId()) { 
       case R.id.menu_settings: 
             showAbout();  
	   }
	return false;
   }

   
   void showAbout() {
   	
   	WebView webView = new WebView(this);
	    //<font color=#ec03bd>
		String html = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>About</title></head><body ><center>" +
						"<font color=white>"+
						"<img src=\"logo.9.png\" width=\"100%\">" +
						"<p>Version " + getVersion(this) + ".</p>" +
						"<b>MetaWatch Community Team</b><br>" +
						"Joakim Andersson<br>Chris Boyle<br>Garth Bushell<br>Prash D<br>Matthias Gruenewald<br>"+
						"Richard Munn<br>Craig Oliver<br>Didi Pfeifle<br>Thierry Schork<br>Kyle Schroeder<br>"+
						"Chris Sewell<br>Geoff Willingham<br>Dobie Wollert<p>"+
						"<b>Translation Team</b><br>"+
						"Miguel Branco<br>Didi Pfeifle<br>Geurt Pieter Maassen van den Brink<br>Thierry Schork<br>"+
						"Kamosan Software<p>"+
						"<p>&copy; Copyright 2011-2012 Meta Watch Ltd.</p>" +
						"</center></body></html>";
	   webView.setBackgroundColor(Color.BLACK);
	   
	   
       webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);
       new AlertDialog.Builder(this).setView(webView).setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {			
			//@Override
			public void onClick(DialogInterface dialog, int which) {
				
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