package com.leashin.quanzi.ui.main;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnMenuSlideListener;
import com.leashin.quanzi.Const;
import com.leashin.quanzi.R;
import com.leashin.quanzi.app.ActionBarSlidingToggle;
import com.leashin.quanzi.ui.base.BaseFragmentActivity;
import com.leashin.quanzi.utils.Logs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity {
	private static final String TAG = "MainActivity";
	private static final boolean DEBUG = true;

	private SlidingMenu mSlidingMenu;
	private ActionBar mActionBar;
	private ActionBarSlidingToggle mActionBarDrawerToggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Fragment f = new OptionListFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.slidingmenu_options_container, f).commit();
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActionBarDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initActionBar() {
		super.initActionBar();
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
	}

	protected void initSlidingMenu() {
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.slidingmenu_options);
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow_left);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindScrollScale(0.0f);
		mSlidingMenu.setBackgroundColor(Color.WHITE);
		mSlidingMenu.setFadeEnabled(true);
		mSlidingMenu.setFadeDegree(0.35f);

		// 滑动slidemenu时的动画
		mSlidingMenu.setBehindCanvasTransformer(new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		});
	}

	@Override
	protected void initViews() {
		mSlidingMenu = new SlidingMenu(this);
		initSlidingMenu();
		mActionBarDrawerToggle = new ActionBarSlidingToggle(this, mSlidingMenu,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name);

	}

	@Override
	protected void setListeners() {
		mSlidingMenu.setOnMenuSlideListener(new OnMenuSlideListener() {
			@Override
			public void onMenuSlide(View view, float slidePercent) {
				mActionBarDrawerToggle.onDrawerSlide(view, slidePercent);
				Logs.d(TAG, "slideOffset = " + slidePercent, DEBUG);
			}
		});
	}

	private long mFirstBackPressedTime = 0;

	@Override
	public void onBackPressed() {
		if (mSlidingMenu.isMenuShowing()) {
			mSlidingMenu.toggle();
			return;
		}

		FragmentManager fm = getSupportFragmentManager();

		if (fm.getBackStackEntryCount() > 0) {
			super.onBackPressed();
		} else {
			long msec = System.currentTimeMillis();

			if (msec - mFirstBackPressedTime < Const.config.BACK_PRESSED_TIMEOUT) {
				if (isBackgroundEnabled()) {
					moveTaskToBack(false);
				} else {
					super.onBackPressed();
				}
			} else {
				mFirstBackPressedTime = msec;

				if (isBackgroundEnabled()) {
					Toast.makeText(getBaseContext(),
							R.string.back_pressed_to_background,
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(),
							R.string.back_pressed_to_exit, Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}

	/**
	 * 是否允许程序后台运行
	 * 
	 * @return true允许 otherwise不允许
	 */
	private boolean isBackgroundEnabled() {
		SharedPreferences sp = getSharedPreferences(Const.config.SP_CONFIG,
				Context.MODE_PRIVATE);

		boolean b = sp.getBoolean(Const.key.SP_BACKGROUND_ENABLED, false);

		return b;
	}

	@Override
	public void replaceFragment(int containerId, Fragment f, boolean backToStack) {
		super.replaceFragment(containerId, f, backToStack);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_container, f);

		if (backToStack) {
			ft.addToBackStack(null);
		}

		ft.commit();

		mSlidingMenu.showContent();
	}
}
