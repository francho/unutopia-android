package org.francho.unutopia.resources;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView view;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        view = (TextView)findViewById(R.id.hello_text);
        view.setText(R.string.hello_world);
    }

    
}
