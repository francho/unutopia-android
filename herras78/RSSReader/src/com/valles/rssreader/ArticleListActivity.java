package com.valles.rssreader;

import java.util.ArrayList;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ArticleListActivity extends Activity {
	 
	private static ArrayList<FeedItem> ItemArray =  new ArrayList<FeedItem>();
	private FeedAdapter adaptador = null;
	private TextView TxtFuentes;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);
        
        TxtFuentes = (TextView) findViewById(R.id.contfuentes);
        CargaDatos();
		
		ListView FeedList = (ListView)findViewById(R.id.feedlist);
        adaptador = new FeedAdapter(this);
        FeedList.setAdapter(adaptador);
        
        final TextView TxtTitulo = (TextView) findViewById(R.id.lbltitulo);
        final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        TxtTitulo.setTypeface(font1);
        
        
        final ImageView acercade = (ImageView) findViewById(R.id.acercade);
        acercade.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	ToAbout();            	
            }
        });
        
        FeedList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		       		
        		Toast.makeText(ArticleListActivity.this,"Comenzando : "+ ItemArray.get(position).getTitulo(),Toast.LENGTH_SHORT).show();
        		Intent intent = new Intent(ArticleListActivity.this, ItemReader.class);
        		
        		Bundle bundle = new Bundle();
        		bundle.putString("TITULO",ItemArray.get(position).getTitulo().toString());
        		bundle.putString("FECHA",DateGen(position).toString());
        		bundle.putInt("IMAGEN",ItemArray.get(position).getImagen());
        		
        		intent.putExtras(bundle);
                
        		startActivity(intent);
        	}
        });
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {  
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu_article_list, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_about:
	        	ToAbout();
	            return true;
	        case R.id.menu_buscar:
	           
	            return true;
	        case R.id.menu_mas:
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void ToAbout(){
		Intent intent = new Intent(ArticleListActivity.this, AboutActivity.class);     
        startActivity(intent); 
	}

	public void CargaDatos(){
		ItemArray.clear();
		long millis = new java.util.Date().getTime();
		int i = 1;
			do{
				FeedItem item = new FeedItem();
				item.FillItem("Titulo Nº"+i,millis,R.drawable.feed_imgen);
				i++;
				millis = millis - ((long)Math.ceil(Math.random() * 86400000));
				ItemArray.add(item);
			}while(ItemArray.size()<15);
		TxtFuentes.setText("Fuentes:"+ItemArray.size()+"/"+ItemArray.size());
	}
	
	private static class FeedAdapter extends BaseAdapter{
		
		private Context context;
		
		public FeedAdapter(Context c){
			
			context = c;	
		}

		public int getCount() {
			
			return ItemArray.size();
		}

		public Object getItem(int position) {
			
			return ItemArray.get(position);
		}

		public long getItemId(int position) {
			
			return 0;
		}

		public View getView(int position,View convertView,ViewGroup parent) {
			
			View view = null;
			
			if(convertView == null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.feed_item, null);
			}else{
				view = convertView;				
			}	
			
			ImageView ImgItem = (ImageView) view.findViewById(R.id.img_item);
			ImgItem.setImageDrawable(context.getResources().getDrawable(ItemArray.get(position).getImagen()));
			
			TextView tituloEntre = (TextView) view.findViewById(R.id.tit_item);
			tituloEntre.setText(ItemArray.get(position).getTitulo());			

			TextView tiempo = (TextView) view.findViewById(R.id.date_item);
			tiempo.setText(DateGen(position));
			
		return view;
		}		
	}
	
	public static String DateGen(int position){
		
		long DateIn = ItemArray.get(position).getDate();
		long ElapsedTime = (new java.util.Date().getTime()) - DateIn;
		long dia = 86400000;
		long hora = 3600000;
		long minuto = 60000;
		String StrElapsedtime = new java.sql.Date(DateIn)+"";
		
		if((ElapsedTime/dia)>=1){				
			StrElapsedtime += "("+(ElapsedTime/dia)+"d)";
		}else if((ElapsedTime/hora)>=1){
			StrElapsedtime += "("+(ElapsedTime/hora)+"h)";
		}else if((ElapsedTime/minuto)>=1){
			StrElapsedtime += "("+(ElapsedTime/minuto)+"m)";
		}

		return StrElapsedtime;
	}
}
	

