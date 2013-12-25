package com.leashin.quanzi.ui.base;

public interface FragmentCallback {
	public void startFragment(FragmentIntent fi);

	public void startFragment(FragmentIntent fi, boolean backToStack);
}
