package carloscsanchezperez.gmail.sixpackon;

import static android.provider.BaseColumns._ID;
import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;

//title + link + pubDate + description + content
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsData extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "feeds.db";
   private static final int DATABASE_VERSION = 1;

   /** Create a helper object for the Events database */
   public EventsData(Context ctx) {
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
	 //title + link + pubDate + description + content
      db.execSQL("CREATE TABLE " + FeedsTable.TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
            FeedsTable.TITLE + " TEXT NOT NULL, " + 
            FeedsTable.LINK + " TEXT NOT NULL, " + 
            FeedsTable.PUBDATE + " TEXT NOT NULL, " + 
            FeedsTable.DESCRIPTION + " TEXT NOT NULL, "+ 
            FeedsTable.CONTENT + " TEXT NOT NULL);"
    		    
    		  );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion,
         int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + FeedsTable.TABLE_NAME);
      onCreate(db);
   }

}