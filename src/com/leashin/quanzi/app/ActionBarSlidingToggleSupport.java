/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leashin.quanzi.app;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Method;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * This class encapsulates some awful hacks.
 *
 * Before JB-MR2 (API 18) it was not possible to change the home-as-up indicator glyph
 * in an action bar without some really gross hacks. Since the MR2 SDK is not published as of
 * this writing, the new API is accessed via reflection here if available.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ActionBarSlidingToggleSupport {
	private static final String TAG = "ActionBarDrawerToggleHoneycomb";

	private static final int[] THEME_ATTRS = new int[] { android.R.attr.homeAsUpIndicator };
	private static final int[] SUPPORT_THEME_ATTRS = new int[] { com.actionbarsherlock.R.attr.homeAsUpIndicator };

	public static Object setActionBarUpIndicator(Object info,
			SherlockFragmentActivity activity, Drawable drawable,
			int contentDescRes) {
		if (info == null) {
			info = new SetIndicatorInfo(activity);
		}
		final SetIndicatorInfo sii = (SetIndicatorInfo) info;
		if (sii.setHomeAsUpIndicator != null) {
			try {
				final ActionBar actionBar = activity.getSupportActionBar();
				sii.setHomeAsUpIndicator.invoke(actionBar, drawable);
				sii.setHomeActionContentDescription.invoke(actionBar,
						contentDescRes);
			} catch (Exception e) {
				Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API",
						e);
			}
		} else if (sii.upIndicatorView != null) {
			sii.upIndicatorView.setImageDrawable(drawable);
		} else {
			Log.w(TAG, "Couldn't set home-as-up indicator");
		}
		return info;
	}

	public static Object setActionBarDescription(Object info,
			SherlockFragmentActivity activity, int contentDescRes) {
		if (info == null) {
			info = new SetIndicatorInfo(activity);
		}
		final SetIndicatorInfo sii = (SetIndicatorInfo) info;
		if (sii.setHomeAsUpIndicator != null) {
			try {
				final ActionBar actionBar = activity.getSupportActionBar();
				sii.setHomeActionContentDescription.invoke(actionBar,
						contentDescRes);
			} catch (Exception e) {
				Log.w(TAG, "Couldn't set content description via JB-MR2 API", e);
			}
		}
		return info;
	}

	public static Drawable getThemeUpIndicator(SherlockFragmentActivity activity) {
		final TypedArray a;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			a = activity.obtainStyledAttributes(THEME_ATTRS);
		} else {
			a = activity.obtainStyledAttributes(SUPPORT_THEME_ATTRS);
		}
		final Drawable result = a.getDrawable(0);
		a.recycle();
		return result;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static class SetIndicatorInfo {
		public Method setHomeAsUpIndicator;
		public Method setHomeActionContentDescription;
		public ImageView upIndicatorView;

		SetIndicatorInfo(SherlockFragmentActivity activity) {
			try {
				setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod(
						"setHomeAsUpIndicator", Drawable.class);
				setHomeActionContentDescription = ActionBar.class
						.getDeclaredMethod("setHomeActionContentDescription",
								Integer.TYPE);

				// If we got the method we won't need the stuff below.
				return;
			} catch (NoSuchMethodException e) {
				// Oh well. We'll use the other mechanism below instead.
			}

			final View home;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				home = activity.findViewById(android.R.id.home);
			} else {
				home = activity
						.findViewById(com.actionbarsherlock.R.id.abs__home);
			}

			if (home == null) {
				// Action bar doesn't have a known configuration, an OEM messed
				// with things.
				return;
			}

			final ViewGroup parent = (ViewGroup) home.getParent();
			final int childCount = parent.getChildCount();
			if (childCount != 2) {
				// No idea which one will be the right one, an OEM messed with
				// things.
				return;
			}

			final View first = parent.getChildAt(0);
			final View second = parent.getChildAt(1);
			final View up = first.getId() == android.R.id.home ? second : first;

			if (up instanceof ImageView) {
				// Jackpot! (Probably...)
				upIndicatorView = (ImageView) up;
			}
		}
	}
}
