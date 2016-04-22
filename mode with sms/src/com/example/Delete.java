package com.example;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class Delete extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        deleteMsg();
        finish();

	}
	
 	public void deleteMsg()
 	{
 	try{	
 		
 	Uri uri = Uri.parse("content://sms/inbox");
 	Cursor c = getApplicationContext().getContentResolver().query(uri, null, null, null, null); 

 	if(c.moveToFirst()) 
 	 {
 		  long id = c.getLong(0);
 		  int ids=uri.getPort();
          this.getContentResolver().delete(
          Uri.parse("content://sms/" + id), null, null);
          NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
   		notificationManager.cancel(ids);

 	 }
 	c.close(); 
 	}
 	catch (Exception e) {
		Toast.makeText(getApplicationContext(),""+e.getMessage(), 10000).show();
 	}

 	finish();
 	}

}
