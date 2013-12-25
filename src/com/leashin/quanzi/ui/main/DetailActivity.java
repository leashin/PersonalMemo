package com.leashin.quanzi.ui.main;

import android.os.Bundle;

import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsFragmentActivity;
import com.leashin.quanzi.ui.base.FragmentIntent;

public class DetailActivity extends AbsFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actionbar_main);
	}

	@Override
	protected void initViews() {
	}

	@Override
	protected void setListeners() {
	}

	@Override
	public void startFragment(FragmentIntent fi) {
	}

	@Override
	public void startFragment(FragmentIntent fi, boolean backToStack) {
	}

}
