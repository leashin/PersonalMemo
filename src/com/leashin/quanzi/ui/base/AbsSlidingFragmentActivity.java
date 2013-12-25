package com.leashin.quanzi.ui.base;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
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

	public void setCenterTitle(int resId) {
		setCenterTitle(getString(resId));
	}

	public void setCenterTitle(CharSequence text) {
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER;

		TextView titleTv = new TextView(this);

		titleTv.setText(text);
		titleTv.setLayoutParams(lp);
		titleTv.setTextAppearance(this, R.style.MY_Widget_ActionBar_Title);

		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setCustomView(titleTv);
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
		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayUseLogoEnabled(false);
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
