package com.leashin.quanzi.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsFragment;
import com.leashin.quanzi.ui.base.FragmentCallback;
import com.leashin.quanzi.ui.base.FragmentIntent;
import com.leashin.quanzi.utils.Logs;

public class TestFragment extends AbsFragment {

	private Button mStartOtherButton;
	private FragmentCallback mFragmentCallback;

	public static TestFragment newInstance(String title) {
		TestFragment f = new TestFragment();
		Bundle args = new Bundle();

		args.putString("title", title);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			FragmentTransaction ft = getChildFragmentManager()
					.beginTransaction();

			Fragment newFragment = OtherFragment.newInstance("sdfsf");

			ft.add(R.id.fragment_container, newFragment);

			ft.commit();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_test, container, false);

		mStartOtherButton = (Button) v.findViewById(R.id.btn_start_other);

		mStartOtherButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "¿ªÊ¼", Toast.LENGTH_SHORT).show();

				FragmentIntent fi = new FragmentIntent(OtherFragment.class);
				mFragmentCallback.startFragment(fi, true);
			}
		});

		Logs.d("SherlockFragmentLifecycle", this + ": onCreateView");

		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mFragmentCallback = (FragmentCallback) activity;
	}

}
