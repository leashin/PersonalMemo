package com.leashin.quanzi.ui;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.leashin.quanzi.App;
import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsSlidingFragmentActivity;
import com.leashin.quanzi.utils.PixelUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AbsSlidingFragmentActivity {

	private SlidingMenu mSlidingMenu;
	private ActionBar mActionBar;
	private Button mLeftBtn;
	private Button mRightBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.slidingmenu_first);
		setContentView(R.layout.activity_main);
		setTitle("备忘录");
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
		mLeftBtn = (Button) findViewById(R.id.btn_open_left);
		mRightBtn = (Button) findViewById(R.id.btn_open_right);
	}

	@Override
	protected void setListeners() {
		mSlidingMenu.setOnOpenListener(new OnOpenListener() {
			@Override
			public void onOpen() {
				Toast.makeText(getBaseContext(), "打开左边", Toast.LENGTH_SHORT)
						.show();
			}
		});

		mSlidingMenu.setSecondaryOnOpenListner(new OnOpenListener() {
			@Override
			public void onOpen() {
				Toast.makeText(getBaseContext(), "打开右边", Toast.LENGTH_SHORT)
						.show();
			}
		});

		mLeftBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSlidingMenu.showMenu();
			}
		});

		mRightBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSlidingMenu.showSecondaryMenu();
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

	private boolean isBackgroundEnabled() {
		SharedPreferences sp = getSharedPreferences(App.config.SP_CONFIG,
				Context.MODE_PRIVATE);

		boolean b = sp.getBoolean(App.key.SP_RUN_ON_BACKGROUND, true);

		return b;
	}

}
