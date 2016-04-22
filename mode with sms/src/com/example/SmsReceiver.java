package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SmsReceiver extends BroadcastReceiver {

	AudioManager am;
	SharedPreferences pref;
	@Override
	 public void onReceive(Context context, Intent intent) 
	   {
	    //---get the SMS message passed in---
	    Bundle bundle = intent.getExtras();        
	    SmsMessage[] msgs = null;
	    String str = "",add="";            
	    if (bundle != null)
	    {
	        //---retrieve the SMS message received---
	        Object[] pdus = (Object[]) bundle.get("pdus");
	        msgs = new SmsMessage[pdus.length];            
	        for (int i=0; i<msgs.length; i++){
	        	msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
	            str += msgs[i].getMessageBody().toString();
	            add= msgs[i].getOriginatingAddress();  
	            
	           
	            
	            //for way to sms messages
	            if(add.equalsIgnoreCase("LM-WAYSMS"))
	            {
	            StringBuffer sb=new StringBuffer(str);
	            int ip;
	            for(ip=0;ip<sb.length();ip++)
	            {
	            	if(sb.charAt(ip)==':')
	            	{
	            		break;
	            	}
	            }
	             str="";
	             ip=ip+2;
	            	str+=sb.substring(ip);
	            } //end of way to sms
	            
	            
	            
	            pref = PreferenceManager.getDefaultSharedPreferences(context);
	            if (str.equalsIgnoreCase(pref.getString("normal","##nor*")) || 
	            	str.equalsIgnoreCase(pref.getString("vibrate","##vib*"))||
	            	str.equalsIgnoreCase(pref.getString("silent","##sil*"))||
	            	str.equalsIgnoreCase(pref.getString("song","##son*")))
	            {
	            Intent myintent=new Intent(context,WhatToDo.class);
	            myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            myintent.putExtra("mode",str);
	            context.startActivity(myintent);
	            
	            abortBroadcast();
	            }
	            else
	            {
	            	//Toast.makeText(context,"went into else part", Toast.LENGTH_LONG).show();
	            }
	        }
	    }                         
	 }





}
