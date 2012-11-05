package carloscsanchezperez.gmail.sixpackon;


import android.net.Uri;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	   public static final String TABLE_NAME = "feed";
	   
	   public static final String AUTHORITY = "carlossanchezp.wordpress.com";
	   public static final Uri CONTENT_URI = Uri.parse("content://"
	         + AUTHORITY + "/" + TABLE_NAME);
	   

	   
	}
