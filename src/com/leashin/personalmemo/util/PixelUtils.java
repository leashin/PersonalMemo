package com.leashin.personalmemo.util;

import android.content.Context;

import com.leashin.personalmemo.MyApplication;

public class PixelUtils {

	public static int dp2px(float dpValue) {
		final Context context = MyApplication.getInstance();
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dp(float pxValue) {
		final Context context = MyApplication.getInstance();
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
