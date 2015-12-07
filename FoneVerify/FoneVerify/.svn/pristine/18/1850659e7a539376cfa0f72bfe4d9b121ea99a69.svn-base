package com.u2opia.foneverify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FailScreen extends Activity implements OnClickListener {
	
	private Button btnTryAgain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.failure_screen);
		
		btnTryAgain = (Button)findViewById(R.id.button_tryagain);
		btnTryAgain.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intentMainActivity = new Intent();
		intentMainActivity.setClass(this, MainActivity.class);
		startActivity(intentMainActivity);
		finish();
		
	}
 }