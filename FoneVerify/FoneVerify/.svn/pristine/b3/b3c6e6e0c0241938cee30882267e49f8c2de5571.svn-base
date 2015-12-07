package com.u2opia.foneverify;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.u2opia.foneverify.utility.Constant;
import com.u2opia.foneverify.utility.Preferances;

public class CallScreen extends Activity {
	
	
	
	private String mStrDidNumber;
	private Preferances pref;
	private static final String TAG = "CallScreen";
	private ProgressBar mProgressBar;
	private TextView mTimeTextView;
	private long mLongTimeout = 90;
	private long startTime;
	private MyCountDownTimer countDownTimer;
	private final long interval = 1 * 1000;
	private boolean isElapseTime = false;
	private long lElapseTime;
	//private boolean isVerified = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		pref = new Preferances();
		mStrDidNumber = pref.getDidNumber(this);
		setContentView(R.layout.call_verify_layout);	
		Log.e(TAG, "Times called");
		
		mProgressBar = (ProgressBar)findViewById(R.id.progressbar_callverify);
		mTimeTextView = (TextView) findViewById(R.id.time_textview_callverify);		
		startTime = mLongTimeout * 1000;// 30 * 1000;
		countDownTimer = new MyCountDownTimer(startTime, interval);
		mTimeTextView.setText(mTimeTextView.getText() + String.valueOf(startTime / 1000));
		countDownTimer.start();
		mProgressBar.setVisibility(View.VISIBLE);
		
		Thread logoTimer = new Thread(){
			public void run(){
				try{
					int logoTimer = 0;
					while (logoTimer<3000){
						sleep(100);
						logoTimer=logoTimer+100;
					}					
					didNumber();
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
				/*finally{
					finish();
				}*/
			}
		};
		logoTimer.start();
		
		
	}
 
	 private void didNumber(){
		  RequestQueue queue = Volley.newRequestQueue(this);
		  String url = Constant.OTPVERIFY_URL_NEW;  
		  String strParameter = "?verificationId="
		  + pref.getVerificationCode(CallScreen.this) + "&customerId="
		  + Constant.CUSTOMER_ID_NEW + "&appKey=" + Constant.APP_SECRET +"&did=" + pref.getDidNumber(CallScreen.this);
		  String finalUrl = url + strParameter;
		  Log.e(TAG, "didNumber finalUrl:" + finalUrl); 
		  
		  StringRequest postRequest = new StringRequest(Method.GET, finalUrl, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
			// response
			Log.e(TAG, "didNumber Response:" + response);
			
			//if(isVerified){
			
			try {
				JSONObject jsonObjectVerifyOtp = new JSONObject(response);
				String responseCode = jsonObjectVerifyOtp.getString("responseCode");
				 if (responseCode.equals("200")) {					
					 Intent intentsuccessScreen = new Intent();
					 intentsuccessScreen.setClass(CallScreen.this, SuccessScreen.class);
					 startActivity(intentsuccessScreen);
					// isVerified = true;
					 CallScreen.this.finish();				 
					} 
				 else {
					  errorScreen(responseCode);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					
				}
			//}
			}
			 }, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
				Log.e("ERROR", "didNumber error => " + error.toString());		
				}
				}) ;
				queue.add(postRequest);
			
	 }
	 
	 private void errorScreen(String msgCode){
		 if(msgCode.equals("709") || msgCode.equals("704") || msgCode.equals("507") || msgCode.equals("505") || msgCode.equals("500")
				 || msgCode.equals("501") || msgCode.equals("502") || msgCode.equals("700")){
			 Intent intentError = new Intent();
			 intentError.setClass(this, FailScreen.class);
			 startActivity(intentError);
			 countDownTimer.cancel();
			 CallScreen.this.finish();
		 }
		
		 else if(msgCode.equals("703")){
			 Intent intentSuccess = new Intent();
			 intentSuccess.setClass(this, SuccessScreen.class);
			 startActivity(intentSuccess);
			 countDownTimer.cancel();
			 CallScreen.this.finish();;
		 }
		 else if(msgCode.equals("710")){
			 /*Intent intentSuccess = new Intent();
			 intentSuccess.setClass(this, SuccessScreen.class);
			 startActivity(intentSuccess);
			 countDownTimer.cancel();
			 CallScreen.this.finish();*/
			 Log.e(TAG, "710:::::::::::::::::");
		 }
	 }

 public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			mTimeTextView.setText("Time's up!");
			mProgressBar.setVisibility(View.INVISIBLE);
			
			Thread logoTimer = new Thread(){
				public void run(){
					try{
						int logoTimer = 0;
						while (logoTimer<5000){
							sleep(100);
							logoTimer=logoTimer+100;
						}					
						didNumber();
					} catch (InterruptedException e) {					
						e.printStackTrace();
					}
					/*finally{
						finish();
					}*/
				}
			};
			logoTimer.start();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			mTimeTextView.setText("Seconds remaining: " + millisUntilFinished / 1000);			
			if(millisUntilFinished /1000 == 60 || millisUntilFinished /1000 == 30){
			didNumber();
			}
		}
	}
 
}
	 

