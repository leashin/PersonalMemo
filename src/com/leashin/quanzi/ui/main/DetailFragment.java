package com.leashin.quanzi.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsFragment;

public class DetailFragment extends AbsFragment {
	private TextView mTestTv;

	public static DetailFragment newInstance(int id) {
		DetailFragment f = new DetailFragment();
		Bundle args = new Bundle();

		args.putInt("testid", id);
		f.setArguments(args);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.item_checklist, container, false);

		mTestTv = (TextView) v.findViewById(R.id.tv_testid);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();

		mTestTv.setText(this + ": " + args.getInt("testid"));
	}

}
