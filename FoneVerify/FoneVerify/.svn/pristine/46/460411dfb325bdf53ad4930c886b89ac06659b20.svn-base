package com.u2opia.foneverify;

import com.u2opia.foneverify.utility.OTPSMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

public class IncomingSms extends BroadcastReceiver {    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();     
    public void onReceive(Context context, Intent intent) {     
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras(); 
        try {             
            if (bundle != null) {                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");                 
                for (int i = 0; i < pdusObj.length; i++) {                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();                     
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody(); 
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                    String [] str1 = message.split("Please enter your One-Time Password ");
    	    	    String[] str2 = str1[1].split(" to");
    	    	    String strFinalOtp = str2[0];  
    	    	    OTPSMS.setOtp(strFinalOtp);
                    //Show Alert
                    //int duration = Toast.LENGTH_LONG;
                    //Toast toast = Toast.makeText(context,"senderNum: "+ senderNum + ", message: " + message, duration);
                    //toast.show();                     
                } // end for loop
              } // bundle is null
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    
}
