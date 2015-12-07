package com.u2opia.foneverify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ErrorScreen extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success_screen);		
		TextView txtView = (TextView)findViewById(R.id.screen_text);
		txtView.setText(R.string.fail_text);
		TextView txtViewEmail = (TextView)findViewById(R.id.email_text);
		txtViewEmail.setVisibility(View.VISIBLE);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

}
