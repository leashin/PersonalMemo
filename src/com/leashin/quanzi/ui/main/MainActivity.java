package com.leashin.quanzi.ui.main;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.leashin.quanzi.App;
import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.adapter.ChecklistAdapter;
import com.leashin.quanzi.ui.base.AbsSlidingFragmentActivity;
import com.leashin.quanzi.ui.main.TestFragment.OnFragmentDetachListener;
import com.leashin.quanzi.utils.PixelUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AbsSlidingFragmentActivity implements
		OnFragmentDetachListener {

	private SlidingMenu mSlidingMenu;
	private ActionBar mActionBar;

	private ListView mChecklistLv;

	private TestFragment mFragment;
	private OtherFragment mFragment2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.slidingmenu_first);
		setContentView(R.layout.activity_main);
		setTitle(R.string.app_name);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_test, TestFragment.newInstance("haha"));
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.config:
			mSlidingMenu.showSecondaryMenu();
			break;
		case android.R.id.home:
			mSlidingMenu.showMenu();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initActionBar() {
		super.initActionBar();
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
	}

	@Override
	protected void initSlidingMenu() {
		super.initSlidingMenu();

		mSlidingMenu = getSlidingMenu();

		// 设置基础属性
		mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

		// 设置左边的menu属性
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow_left);

		// 设置右边的menu属性
		mSlidingMenu.setSecondaryMenu(R.layout.slidingmenu_secondary);
		mSlidingMenu.setSecondaryBehindWidth(PixelUtils.dp2px(160));
		mSlidingMenu
				.setSecondaryShadowDrawable(R.drawable.slidingmenu_shadow_right);

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
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, position + "被点击",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private long mFirstBackPressedTime = 0;

	@Override
	public void onBackPressed() {
		long msec = System.currentTimeMillis();

		if (msec - mFirstBackPressedTime < App.config.BACK_PRESSED_TIMEOUT) {
			if (isBackgroundEnabled()) {
				moveTaskToBack(false);
			} else {
				super.onBackPressed();
			}
		} else {
			mFirstBackPressedTime = msec;

			if (isBackgroundEnabled()) {
				Toast.makeText(getBaseContext(),
						R.string.back_pressed_to_background, Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(getBaseContext(), R.string.back_pressed_to_exit,
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * 是否允许程序后台运行
	 * 
	 * @return true允许 otherwise不允许
	 */
	private boolean isBackgroundEnabled() {
		SharedPreferences sp = getSharedPreferences(App.config.SP_CONFIG,
				Context.MODE_PRIVATE);

		boolean b = sp.getBoolean(App.key.SP_BACKGROUND_ENABLED, false);

		return b;
	}

	@Override
	public void onFragmentDetach(int tag) {
	}

}
