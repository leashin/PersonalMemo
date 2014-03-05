package com.leashin.personalmemo.ui;

import com.leashin.personalmemo.R;
import com.leashin.personalmemo.ui.base.BaseActivity;
import com.leashin.personalmemo.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends BaseActivity {

	private static final boolean DEBUG = true;

	private ImageView mSplashImgv;

	private Animation mSplashAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();

		setContentView(R.layout.activity_splash);

		if (DEBUG) {
			Intent intent = new Intent();
			intent.setClass(getBaseContext(), MainActivity.class);
			startActivity(intent);
			finish();
		}

		initAnimation();

		mSplashImgv.startAnimation(mSplashAnimation);
	}

	@Override
	protected void initViews() {
		mSplashImgv = (ImageView) findViewById(R.id.imgv_splash);
	}

	@Override
	protected void setListeners() {
	}

	private void initAnimation() {
		mSplashAnimation = AnimationUtils.loadAnimation(this,
				R.anim.splash_fade_in);
		mSplashAnimation.setAnimationListener(mSplashAnimListener);
	}

	private AnimationListener mSplashAnimListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Intent intent = new Intent();
			intent.setClass(getBaseContext(), MainActivity.class);
			startActivity(intent);
			finish();
		}
	};

}
