package com.leashin.quanzi.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsFragment;
import com.leashin.quanzi.ui.main.TestFragment.OnFragmentDetachListener;

public class OtherFragment extends AbsFragment {

	private Button mBackBtn;
	private OnFragmentDetachListener l;
	
	public static OtherFragment newInstance(String title) {
		OtherFragment f = new OtherFragment();
		Bundle args = new Bundle();

		args.putString("title", title);
		f.setArguments(args);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_other, container, false);

		mBackBtn = (Button) v.findViewById(R.id.btn_back);

		mBackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "·µ»Ø", Toast.LENGTH_SHORT).show();
				l.onFragmentDetach(2);
			}
		});

		return v;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		l = (OnFragmentDetachListener) activity;
	}
	
}
