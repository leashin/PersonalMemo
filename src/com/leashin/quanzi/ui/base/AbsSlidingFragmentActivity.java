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
	 * ��ʼ��actionbarͨ������, ����Ҫ�Լ�����
	 */
	protected void initActionBar() {
//		ActionBar ab = getSupportActionBar();
//		ab.setDisplayShowTitleEnabled(true);
//		ab.setDisplayUseLogoEnabled(true);
	}

	/**
	 * ��ʼ��slidingmenuͨ������, ����Ҫ�Լ�����
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
	 *            ������Intent
	 * @param backToStack
	 *            �Ƿ���Լ��뵽fragmentջ��
	 */
	@Override
	public void startFragment(FragmentIntent fi, boolean backToStack) {
	}
}
