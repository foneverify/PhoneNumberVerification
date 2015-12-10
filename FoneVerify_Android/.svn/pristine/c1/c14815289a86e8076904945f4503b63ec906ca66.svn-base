package com.u2opia.foneverify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class SuccessScreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success_screen);		
		
		TextView txtView = (TextView)findViewById(R.id.screen_text);
		txtView.setText(R.string.successscreen_text);
		
		}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		this.finish();		
		System.exit(0);
	}
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event){  
     if(keyCode==KeyEvent.KEYCODE_BACK)  
      {  
      
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);
    	this.finish();
    	System.exit(0);
        }  
     return true;  
   }
}
