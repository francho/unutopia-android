/**
 * 
 */
package carloscsanchezperez.gmail.sixpackon;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleDetailActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView (R.layout.articledetail);
		
		// recuperamos los parametros via Intent
		Intent intent = this.getIntent ();
		String text = intent.getStringExtra("SixPackOn RSS");
		
		TextView detailTitle = (TextView) findViewById (R.id.detail_title);

		detailTitle.setText (text);
		
	}
	
	

}
