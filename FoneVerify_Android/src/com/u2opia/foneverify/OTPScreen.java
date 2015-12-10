package com.u2opia.foneverify;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.u2opia.foneverify.utility.ConnectivityUtils;
import com.u2opia.foneverify.utility.Constant;
import com.u2opia.foneverify.utility.ErrorCode;
import com.u2opia.foneverify.utility.OTPSMS;
import com.u2opia.foneverify.utility.Preferances;

public class OTPScreen extends Activity implements OnClickListener {
	private EditText mPinOneEditText;
	private TextView mTimeTextView;
	private Button mVerifyBtn;
	private String mStrErrorMessage;	
	private static final String TAG = "OTPScreen";
	private String mStrVerificationId, mStrResponseMobileNumber;//mStrSmsCLI
	//default timeout for each customer is set to 90 seconds
	private long mLongTimeout = 90;
	private String mStrPinOne;
	private Preferances pref;
	private long startTime;
	private final long interval = 1 * 1000;
	private MyCountDownTimer countDownTimer;
	private JSONObject jsonObjectGetOtp;
	private String responseCode;
	private ProgressBar mProgressBar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otp_screen);

		pref = new Preferances();
		mVerifyBtn = (Button) findViewById(R.id.button_verify);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_otpscreen);
		mTimeTextView = (TextView) findViewById(R.id.time_textview);
		mPinOneEditText = (EditText) findViewById(R.id.otp_edittext);		
		mVerifyBtn.setOnClickListener(this);
		
		if (ConnectivityUtils.isConnectedMobile(this) || ConnectivityUtils.isConnectedWifi(this)) {
			getOtp();
			
		} else {
			Toast.makeText(OTPScreen.this, "Please check your network connection", Toast.LENGTH_LONG).show();
		}			
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_verify:
			mStrPinOne = mPinOneEditText.getText().toString().trim();			
			String strOTP = mStrPinOne;
			otpVerification(strOTP.trim());
			break;
		default:
			break;
		}
	}

 public void getOtp() {
  RequestQueue queue = Volley.newRequestQueue(this);
  String url = Constant.OTP_URL_NEW;  
  StringRequest postRequest = new StringRequest(Method.POST, url,
  new Response.Listener<String>() {
  @Override
  public void onResponse(String response) {
  // response
  Log.e(TAG, "Response From server:" + response);
   try {
		jsonObjectGetOtp = new JSONObject(response);
		responseCode = jsonObjectGetOtp.getString("responseCode");		
		mStrVerificationId = jsonObjectGetOtp.getString("verificationId");		
		pref.setVerificationCode(OTPScreen.this, mStrVerificationId);		
		if (responseCode.equals("200")) {
		 mProgressBar.setVisibility(View.VISIBLE);
		 mStrResponseMobileNumber = jsonObjectGetOtp.getString("mobileNumber");
		// Timer countdown			
		startTime = mLongTimeout * 1000;// 30 * 1000;
		countDownTimer = new MyCountDownTimer(startTime, interval);
		mTimeTextView.setText(mTimeTextView.getText() + String.valueOf(startTime / 1000));
		countDownTimer.start();
		}			
		else {
		startTime = mLongTimeout * 1000;
		countDownTimer = new MyCountDownTimer(startTime, interval);
		mTimeTextView.setText(mTimeTextView.getText()+ String.valueOf(startTime / 1000));
		countDownTimer.start();
		//mStrErrorMessage = ErrorCode.errorMessage(responseCode);
		//Toast.makeText(OTPScreen.this, mStrErrorMessage, Toast.LENGTH_LONG).show();
		}		
	 } catch (JSONException e) {
		e.printStackTrace();
		mStrErrorMessage = ErrorCode.errorMessage(responseCode);
		//Toast.makeText(OTPScreen.this, mStrErrorMessage, Toast.LENGTH_LONG).show();
		//mVerifyBtn.setEnabled(false);
		}		
     }
	  }, new Response.ErrorListener() {
		 @Override
		 public void onErrorResponse(VolleyError error) {
		 Log.e("ERROR", "error => " + error);
		 //mVerifyBtn.setEnabled(false);
		}
		}) {
			@Override
			public byte[] getBody() throws AuthFailureError {
				String httpPostBody = "msisdn="
						+ pref.getMsisdn(OTPScreen.this) + "&customerId="
						+ Constant.CUSTOMER_ID_NEW
						+ "&isoCountryCode="
						+ pref.getCountryCode(OTPScreen.this) + "&appKey="
						+ Constant.APP_SECRET;
				try {
					httpPostBody = httpPostBody
							+ "&randomFieldFilledWithAwkwardCharacters="
							+ URLEncoder.encode("{{%stuffToBe Escaped/",
									"UTF-8");

				} catch (UnsupportedEncodingException exception) {
					Log.e("ERROR", "exception", exception);
					// return null and don't pass any POST string if you
					// encounter encoding error
					return null;
				}
				return httpPostBody.getBytes();
			}
		};
		queue.add(postRequest);
	}

 private void otpVerification(final String strOtp) {

  RequestQueue queue = Volley.newRequestQueue(this);
  String url = Constant.OTPVERIFY_URL_NEW;  
  String strParameter = "?verificationId="
  + pref.getVerificationCode(OTPScreen.this) + "&customerId="
  + Constant.CUSTOMER_ID_NEW + "&code=" + strOtp
  + "&appKey=" + Constant.APP_SECRET;
  String finalUrl = url + strParameter;
  Log.e(TAG, "finalUrl:" + finalUrl); 
  
  StringRequest postRequest = new StringRequest(Method.GET, finalUrl, new Response.Listener<String>() {
	@Override
	public void onResponse(String response) {
	// response
	Log.e(TAG, "Response:" + response);
	try {
		JSONObject jsonObjectVerifyOtp = new JSONObject(response);
		String responseCode = jsonObjectVerifyOtp.getString("responseCode");
		 if (responseCode.equals("200")) {
			countDownTimer.cancel();
			mProgressBar.setVisibility(View.INVISIBLE);
			Intent successScreen = new Intent();
			successScreen.setClass(OTPScreen.this, SuccessScreen.class);
			startActivity(successScreen);
			finish();
			} 
		 else if (responseCode.equals("701")) {
			 countDownTimer.cancel();
			 mProgressBar.setVisibility(View.INVISIBLE);			
			 String strDidNumber = jsonObjectVerifyOtp.getString("didAssigned");
			 pref.setDidNumber(OTPScreen.this, strDidNumber);
			 Intent callIntent = new Intent(Intent.ACTION_CALL);
			 callIntent.setData(Uri.parse("tel:"+strDidNumber));
			 startActivity(callIntent);				
			 callState(strDidNumber);			 				 
			} 
		else {
			  errorScreen(responseCode);			
			}
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}
	 }, new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError error) {
		Log.e("ERROR", "error => " + error.toString());		
		}
		}) /*{

		}*/;
		queue.add(postRequest);
	}
 
 
 private void errorScreen(String msgCode){
	 if(msgCode.equals("709") || msgCode.equals("704") || msgCode.equals("507") || msgCode.equals("505") || msgCode.equals("500")
			 || msgCode.equals("501") || msgCode.equals("502") || msgCode.equals("700")){
		 countDownTimer.cancel();
		 mProgressBar.setVisibility(View.INVISIBLE);
		 Intent intentError = new Intent();
		 intentError.setClass(OTPScreen.this, FailScreen.class);
		 startActivity(intentError);
		 finish();
	 }
	 
	 else if(msgCode.equals("703")){
		 countDownTimer.cancel();
		 mProgressBar.setVisibility(View.INVISIBLE);
		 Intent intentSuccess = new Intent();
		 intentSuccess.setClass(this, SuccessScreen.class);
		 startActivity(intentSuccess);
		 OTPScreen.this.finish();;
	 }
	 
	 else if(msgCode.equals("700")){
		 countDownTimer.cancel();
		 mProgressBar.setVisibility(View.INVISIBLE);
		 Intent intentFailed = new Intent();
		 intentFailed.setClass(OTPScreen.this, FailScreen.class);
		 startActivity(intentFailed);
		 finish();
	 }
	 
	 
	 else if(msgCode.equals("702") || msgCode.equals("707") || msgCode.equals("708") || msgCode.equals("711")){
		  //mStrErrorMessage = ErrorCode.errorMessage(msgCode);
		 // Toast.makeText(OTPScreen.this, mStrErrorMessage, Toast.LENGTH_LONG).show();
	 }
	 
	 
	 else if(msgCode.equals("503") || msgCode.equals("504")){
		 countDownTimer.cancel();
		 mProgressBar.setVisibility(View.INVISIBLE);
		 Intent intentMain = new Intent();
		 intentMain.setClass(OTPScreen.this, MainActivity.class);
		 startActivity(intentMain);
		 finish();  
	 }
  else if(msgCode.equals("506")  || msgCode.equals("705") || msgCode.equals("706")){
		 String strOTP = null ;
		 try{
		 mStrPinOne = mPinOneEditText.getText().toString().trim();			
		 strOTP = mStrPinOne;
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 otpVerification(strOTP.trim());
	 }
	 
	 /*else{
		 String mStrErrorMessage = ErrorCode.errorMessage(msgCode);
		 Toast.makeText(OTPScreen.this, mStrErrorMessage, Toast.LENGTH_LONG).show();
	 }*/
 }
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			mTimeTextView.setText("Time's up!");			
			Thread logoTimer = new Thread(){
				public void run(){
					try{
						int logoTimer = 0;
						while (logoTimer<5000){
							sleep(100);
							logoTimer=logoTimer+100;
						}					
						String strOTP = null ;
						 try{
						 mStrPinOne = mPinOneEditText.getText().toString().trim();			
						 strOTP = mStrPinOne;
						 }
						 catch(Exception e){
							 e.printStackTrace();
						 }
						 otpVerification(strOTP.trim());
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
			Log.e(TAG,"MyCountDownTimer:"+millisUntilFinished);
			mTimeTextView.setText("Seconds remaining: " + millisUntilFinished
					/ 1000);
			if(millisUntilFinished /1000 == 75 || millisUntilFinished /1000 == 60 || millisUntilFinished /1000 == 30){
				String str = null;
				try {
					str = OTPSMS.getOtp().trim();
				}
				catch(NullPointerException ne){
					ne.printStackTrace();
				}				
				mPinOneEditText.setText(str.trim());									
				String strOTP = null ;
				 try{
				 mStrPinOne = mPinOneEditText.getText().toString().trim();			
				 strOTP = mStrPinOne;
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
				 otpVerification(strOTP.trim());
			}
			
		}
	}
	private void callState(String didNumber ){
		  TelephonyManager telephonyManager = (TelephonyManager)getSystemService(this.TELEPHONY_SERVICE); 
		  PhoneStateListener callStateListener = new PhoneStateListener() {  
		  public void onCallStateChanged(int state, String incomingNumber)   
		   {
			  if(state==TelephonyManager.CALL_STATE_IDLE){  
				 Intent intentCallScreen = new Intent();
				 intentCallScreen.setClass(OTPScreen.this, CallScreen.class);
				 startActivity(intentCallScreen);
				 finish();
				} 		
		   }  
		 };  
		 telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);  
		  
	}
}
