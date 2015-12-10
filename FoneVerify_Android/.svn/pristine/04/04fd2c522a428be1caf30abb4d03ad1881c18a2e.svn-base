package com.u2opia.foneverify;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.u2opia.foneverify.utility.ConnectivityUtils;
import com.u2opia.foneverify.utility.Constant;
import com.u2opia.foneverify.utility.Preferances;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText mMobileEditText, mCountryCodeEditText;		
	private Button mSubmitBtn;
    private String mStrMobileNumber, mStrCountryCode ;    
    private String mStrOtpUrl, mStrOtpVerifyUrl;
    private static final String TAG = "MainActivity";
    private Preferances pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		mMobileEditText = (EditText)findViewById(R.id.mobile_edittext);		
		mCountryCodeEditText = (EditText)findViewById(R.id.countrycode_edittext);
		
		pref = new Preferances();
		try{			
			mMobileEditText.setText(pref.getMsisdn(this));
			mCountryCodeEditText.setText(pref.getCountryCode(this));
		}catch(Exception e){
			e.printStackTrace();
		}		
		mSubmitBtn = (Button)findViewById(R.id.button_submit);
		mSubmitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_submit:			
			mStrMobileNumber = mMobileEditText.getText().toString().trim();			
			mStrCountryCode = mCountryCodeEditText.getText().toString().trim();
			try{
				pref.setMsisdn(MainActivity.this, mStrMobileNumber);
				pref.setCountryCode(MainActivity.this, mStrCountryCode);			
					
			}catch (Exception e) {
				e.printStackTrace();
			}
			if (mStrMobileNumber.equals("")	|| mStrCountryCode.equals("")) {
				new AlertDialog.Builder(this)
			    .setTitle("Please enter mobile number & country code")
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	mMobileEditText.setFocusable(true);
			        	dialog.cancel();
			        }
			     })			    
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
			}
			else{				
			if (ConnectivityUtils.isConnectedMobile(this) || ConnectivityUtils.isConnectedWifi(this)) {	
				Intent intentOtp = new Intent();
				intentOtp.setClass(this, OTPScreen.class);							
				startActivity(intentOtp);
			}
			else{
				Toast.makeText(MainActivity.this, "Please check your network connection", Toast.LENGTH_LONG).show();
			}			
			}
			break;

		default:
			break;
		}		
	}
}
