package com.leashin.quanzi.ui.base;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.leashin.quanzi.R;
import com.leashin.quanzi.utils.Logs;

public class AbsSlidingFragmentActivity extends SlidingFragmentActivity
		implements FragmentCallback {
	private static final String LIFECYCLE = "SlidingFragmentActivityLifecycle";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.d(LIFECYCLE, this + ": onCreate");

		initSlidingMenu();
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
		super.onSaveInstanceState(outState);
		Logs.d(LIFECYCLE, this + ": onSaveInstanceState");
	}

	@Override
	public void setContentView(int id) {
		super.setContentView(id);
		initViews();
		setListeners();
	}

	/**
	 * 初始化actionbar通用设置, 不需要自己调用
	 */
	protected void initActionBar() {
//		ActionBar ab = getSupportActionBar();
//		ab.setDisplayShowTitleEnabled(true);
//		ab.setDisplayUseLogoEnabled(true);
	}

	/**
	 * 初始化slidingmenu通用设置, 不需要自己调用
	 */
	protected void initSlidingMenu() {
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setFadeDegree(0.5f);
	}

	public AbsSlidingFragmentActivity getBaseActivity() {
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
	 *            startFragment(fi, false)
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
