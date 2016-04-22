package com.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class WhatToDo extends Activity{
	String mode;
	AudioManager am;	
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        Context ctx = getApplicationContext();
	  	   	 pref = PreferenceManager.getDefaultSharedPreferences(ctx);
	        editor=pref.edit();
	        Intent i=getIntent();
	        String str=i.getStringExtra("mode");
	        am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);

			if (str.equalsIgnoreCase(pref.getString("normal","##nor*")))
			{
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
			else if(str.equalsIgnoreCase(pref.getString("vibrate","##vib*")))
			{
				Toast.makeText(getApplicationContext(), "vibrate selected", Toast.LENGTH_LONG).show();
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}
			else if (str.equalsIgnoreCase(pref.getString("silent","##sil*"))) 
			{
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
			else
			{
				//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
			}
			
			onBackPressed();
	    }
	 
	 	@Override
	 	public void onBackPressed() {
	 		// TODO Auto-generated method stub
	 		super.onBackPressed();
	 		Intent it=new Intent(getApplicationContext(),Delete.class);
			startActivity(it);
	 		finish();
	 	}
	 	
	 
	 }
