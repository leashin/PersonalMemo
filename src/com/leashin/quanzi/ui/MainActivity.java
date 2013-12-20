package com.leashin.quanzi.ui;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.leashin.quanzi.App;
import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsSlidingFragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class MainActivity extends AbsSlidingFragmentActivity {

	private SlidingMenu mSlidingMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initViews() {
	}

	@Override
	protected void setListeners() {
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

		mSlidingMenu.setSecondaryMenu(R.layout.behind_two);
		mSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadow_right);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow_left);

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

		boolean b = sp.getBoolean(App.key.SP_RUN_ON_BACKGROUND, false);

		return b;
	}

}
