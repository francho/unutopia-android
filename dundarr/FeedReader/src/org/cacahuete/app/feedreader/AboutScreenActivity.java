package org.cacahuete.app.feedreader;

import org.cacahuete.app.feedreader.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;




/**
* About screen activity
*
*/
public class AboutScreenActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.about);
		
		 final Button boton = (Button) findViewById(R.id.aboutscreen_button1);
         boton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }
         });
	}


}
