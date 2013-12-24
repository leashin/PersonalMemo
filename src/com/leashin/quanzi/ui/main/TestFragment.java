package com.leashin.quanzi.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.leashin.quanzi.R;
import com.leashin.quanzi.ui.base.AbsFragment;

public class TestFragment extends AbsFragment {

	private Button mStartOtherButton;
	private OnFragmentDetachListener l;

	public static TestFragment newInstance(String title) {
		TestFragment f = new TestFragment();
		Bundle args = new Bundle();

		args.putString("title", title);
		f.setArguments(args);

		return f;
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
				l.onFragmentDetach(1);
				Intent intent = new Intent();
				intent.setClass(getActivity().getBaseContext(),
						DetailActivity.class);
				getActivity().startActivity(intent);
			}
		});

		return v;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		l = (OnFragmentDetachListener) activity;
	}

	public interface OnFragmentDetachListener {
		public void onFragmentDetach(int tag);
	}
	
	
}
