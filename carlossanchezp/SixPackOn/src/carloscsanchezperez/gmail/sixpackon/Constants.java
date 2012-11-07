package carloscsanchezperez.gmail.sixpackon;


import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;
import android.net.Uri;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	   
	   
	   public static final String AUTHORITY = "carlossanchezp.wordpress.com";
	   public static final Uri CONTENT_URI = Uri.parse("content://"
	         + AUTHORITY + "/" + FeedsTable.TABLE_NAME);
	   

	   
	}
