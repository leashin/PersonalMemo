package com.leashin.quanzi.ui.base;

import com.leashin.quanzi.utils.Logs;

import android.app.Activity;
import android.os.Bundle;

public abstract class AbsActivity extends Activity {
	private static final String LIFECYCLE = "ActivityLifecycle";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Logs.d(LIFECYCLE, this + ": onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Logs.d(LIFECYCLE, this + ": onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Logs.d(LIFECYCLE, this + ": onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Logs.d(LIFECYCLE, this + ": onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Logs.d(LIFECYCLE, this + ": onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Logs.d(LIFECYCLE, this + ": onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Logs.d(LIFECYCLE, this + ": onDestroy");
	}

	/**
	 * �ڵ���ʱ��ʼ��view�ͼ�����
	 */
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initViews();
		setListeners();
	}

	/**
	 * ��ʼ��view, ����Ҫ�Լ�����
	 */
	protected abstract void initViews();

	/**
	 * ���ü�������ʵ�ָ÷������ɣ�����Ҫ�Լ��{��
	 */
	protected abstract void setListeners();
}
