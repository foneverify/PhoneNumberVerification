package com.u2opia.foneverify;

import java.util.Random;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();
	private static Context context;
	private RequestQueue mRequestQueue = null;
	private final int MAX_ATTEMPTS = 5;
	private final int BACKOFF_MILLI_SECONDS = 2000;
	private final Random random = new Random();
	private PowerManager.WakeLock wakeLock;
	private static AppController mInstance;
	public Activity activityObj;

	public void setActivityObject(Activity atvyObj) {
		activityObj = atvyObj;
	}

	public enum TrackerName {
		APP_TRACKER, // Tracker used only in this app.
		GLOBAL_TRACKER, // NOT IN USE, Tracker used by all the apps from a
		ECOMMERCE_TRACKER, // NOT IN USE, Tracker used by all ecommerce
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		AppController.context = getApplicationContext();
	}

	public static Context getAppContext() {
		return AppController.context;
	}

	@Override
	public void onLowMemory() {

	}

	public static synchronized AppController getInstance() {
		if (mInstance == null)
			mInstance = new AppController();
		return mInstance;
	}

	// public RequestQueue getRequestQueue(Activity activityObj) {
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	/*public <T> void addToRequestQueue(Request<T> req, String tag,
			Activity activityObj) {
		req.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 5,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}*/
	
	public <T> void addToRequestQueue(Request<T> req, Activity activityObj) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	public void acquireWakeLock(Context context) {
		if (wakeLock != null)
			wakeLock.release();
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, "WakeLock");
		wakeLock.acquire();
	}

	public void releaseWakeLock() {
		wakeLock.release();
	}

}
