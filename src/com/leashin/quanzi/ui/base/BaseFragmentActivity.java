package com.leashin.quanzi.ui.base;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.leashin.quanzi.utils.Logs;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragmentActivity extends SherlockFragmentActivity {
	private static final String LIFECYCLE = "ActivityLifecycle";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d(LIFECYCLE, this + ": onCreate");
		initActionBar();
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Logs.d(LIFECYCLE, this + ": onSaveInstanceState");
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
	 * ��ʼ��actionbarͨ������, ����Ҫ�Լ�����
	 */
	protected void initActionBar() {
		// ActionBar ab = getSupportActionBar();
		// ab.setDisplayShowTitleEnabled(true);
		// ab.setDisplayUseLogoEnabled(true);
	}

	public BaseFragmentActivity getBaseActivity() {
		return this;
	}

	/**
	 * ��ʼ��view, ����Ҫ�Լ�����
	 */
	protected void initViews() {
	}

	/**
	 * ���ü�������ʵ�ָ÷������ɣ�����Ҫ�Լ��{��
	 */
	protected void setListeners() {
	}

	/**
	 * ��fragment��������һ���µ�fragment
	 * ��ϸ��replaceFragment(int containerId, Fragment f, boolean backToStack)
	 */
	public void replaceFragment(int containerId, Fragment f) {
		replaceFragment(containerId, f, false);
	}

	/**
	 * 
	 * @param containerId Ҫ�󶨵�����Id
	 * @param f 
	 * @param backToStack �Ƿ����ѹջ
	 */
	public void replaceFragment(int containerId, Fragment f,
			boolean backToStack) {
	}
}
