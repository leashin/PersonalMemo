package com.leashin.quanzi.ui.main;

import android.os.Bundle;

import com.leashin.quanzi.ui.base.AbsFragment;

public class SecondaryMenuFragment extends AbsFragment {

	public static SecondaryMenuFragment newInstance(Bundle args) {
		SecondaryMenuFragment f = new SecondaryMenuFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
}
