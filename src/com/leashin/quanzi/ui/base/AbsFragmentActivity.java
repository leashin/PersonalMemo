package com.leashin.quanzi.ui.base;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.leashin.quanzi.utils.Logs;

import android.os.Bundle;

public class AbsFragmentActivity extends SherlockFragmentActivity implements
		FragmentCallback {
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Logs.d(LIFECYCLE, this + ": onSaveInstanceState");
	}

	/**
	 * 在调用时初始化view和监听器
	 */
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initViews();
		setListeners();
	}

	public AbsFragmentActivity getBaseActivity() {
		return this;
	}

	/**
	 * 初始化view, 不需要自己调用
	 */
	protected void initViews() {
	}

	/**
	 * 设置监听器，实现该方法即可，不需要自己調用
	 */
	protected void setListeners() {
	}

	/**
	 * 在fragment容器启动一个新的fragment
	 * 
	 * @param fi
	 *            startFragment (fi, false)
	 */
	@Override
	public void startFragment(FragmentIntent fi) {
		startFragment(fi, false);
	}

	/**
	 * 
	 * @param fi
	 *            类似于Intent
	 * @param backToStack
	 *            是否可以加入到fragment栈中
	 */
	@Override
	public void startFragment(FragmentIntent fi, boolean backToStack) {
	}
}
