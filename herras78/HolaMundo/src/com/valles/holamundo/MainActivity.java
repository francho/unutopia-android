package com.valles.holamundo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView TextV1;
	private TextView TextV2;
	private TextView TextV3;
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextV1 = (TextView)findViewById(R.id.txt1);
        TextV2 = (TextView)findViewById(R.id.txt2);
        TextV3 = (TextView)findViewById(R.id.txt3);
        
        Typeface font1 = Typeface.createFromAsset(getAssets(),"aajax.ttf");
        //Typeface font2 = Typeface.createFromAsset(getAssets(),"acidh.ttf");
        //Typeface font3 = Typeface.createFromAsset(getAssets(),"acquaint.ttf");
        
        TextV1.setTypeface(font1);
        TextV2.setTypeface(font1);
        TextV3.setTypeface(font1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
