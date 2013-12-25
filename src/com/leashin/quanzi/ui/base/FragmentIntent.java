package com.leashin.quanzi.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class FragmentIntent {

	/**
	 * ��Ҫ��ת����fragment
	 */
	private Class<? extends Fragment> mClass;
	/**
	 * ��ǩ
	 */
	private String mTag;
	/**
	 * ����������
	 */
	private Bundle mArgs;

	public FragmentIntent() {
		this(null);
	}

	public FragmentIntent(Class<? extends Fragment> clz) {
		mClass = clz;
		mTag = "";
		mArgs = new Bundle();
	}

	public void setClass(Class<? extends Fragment> clz) {
		mClass = clz;
	}

	public Class<? extends Fragment> getFragmentClass() {
		return mClass;
	}

	public void putExtras(Bundle args) {
		mArgs = args;
	}

	public Bundle getExtras() {
		return mArgs;
	}

	public void setTag(String tag) {
		mTag = tag;
	}

	public String getTag(String tag) {
		return mTag;
	}
}
