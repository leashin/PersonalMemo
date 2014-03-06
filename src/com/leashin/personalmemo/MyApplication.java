package com.leashin.personalmemo;

import com.leashin.personalmemo.util.Logs;

import android.app.Application;

public class MyApplication extends Application {

	private static final String TAG = MyApplication.class.getSimpleName();

	private static MyApplication INSTANCE = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Logs.d(TAG, "onCreate");

		INSTANCE = this;
	}

	public static MyApplication getInstance() {
		return INSTANCE;
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Logs.d(TAG, "onLowMemory");
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		Logs.d(TAG, "onTerminate");
	}

}
