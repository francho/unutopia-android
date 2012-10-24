package hastalas4.es.feedreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Button btnArticles = (Button)findViewById(R.id.btnArticles);
		btnArticles.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnArticles:
			Context context = this;
			Intent intent = new Intent(context, ArticleListActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	

}
