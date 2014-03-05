package com.leashin.personalmemo.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leashin.personalmemo.R;
import com.leashin.personalmemo.ui.base.BaseFragment;

public class TestFragment extends BaseFragment {
	private TextView mArgsTv;

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_test, container, false);

		mArgsTv = (TextView) v.findViewById(R.id.tv_title);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mArgsTv.setText(getArguments().getString("title"));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

}
