package com.leashin.quanzi.ui.main;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnMenuSlideListener;
import com.leashin.quanzi.Const;
import com.leashin.quanzi.R;
import com.leashin.quanzi.app.ActionBarDrawerToggle;
import com.leashin.quanzi.ui.adapter.ChecklistAdapter;
import com.leashin.quanzi.ui.base.AbsSlidingFragmentActivity;
import com.leashin.quanzi.ui.base.FragmentIntent;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AbsSlidingFragmentActivity {

	private SlidingMenu mSlidingMenu;
	private ActionBar mActionBar;
	private ActionBarDrawerToggle mActionBarDrawerToggle;

	private ListView mChecklistLv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.slidingmenu_first);
		setContentView(R.layout.activity_main);


		setSlidingActionBarEnabled(false);
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

	@Override
	protected void initSlidingMenu() {
		super.initSlidingMenu();

		mSlidingMenu = getSlidingMenu();

		// 设置基础属性
		mSlidingMenu.setMode(SlidingMenu.LEFT);

		// 设置左边的menu属性
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow_left);

		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mSlidingMenu,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name);

		mSlidingMenu.setOnMenuSlideListener(new OnMenuSlideListener() {

			@Override
			public void onMenuSlide(View view, float slidePercent) {
				mActionBarDrawerToggle.onDrawerSlide(view, slidePercent);
				Log.d("slidePercent", "slideOffset = " + slidePercent);
			}
		});
		// // 设置右边的menu属性
		// mSlidingMenu.setSecondaryMenu(R.layout.slidingmenu_secondary);
		// mSlidingMenu.setSecondaryBehindWidth(PixelUtils.dp2px(160));
		// mSlidingMenu
		// .setSecondaryShadowDrawable(R.drawable.slidingmenu_shadow_right);
	}

	@Override
	protected void initViews() {
		mChecklistLv = (ListView) findViewById(R.id.lv_checklist);
		mChecklistLv.setAdapter(new ChecklistAdapter(this));
	}

	@Override
	protected void setListeners() {
		mChecklistLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, position + "被点击",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private long mFirstBackPressedTime = 0;

	@Override
	public void onBackPressed() {
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
	public void startFragment(FragmentIntent fi) {
		startFragment(fi, false);
	}

	@Override
	public void startFragment(FragmentIntent fi, boolean backToStack) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		Fragment newFragment = Fragment.instantiate(this, fi.getFragmentClass()
				.getName(), fi.getExtras());

		ft.replace(R.id.fragment_container, newFragment);
		if (backToStack) {
			ft.addToBackStack(null);
		}

		ft.commit();
	}
}
