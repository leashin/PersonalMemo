package com.leashin.personalmemo.ui.main;

import com.leashin.personalmemo.R;
import com.leashin.personalmemo.ui.base.BaseFragmentActivity;
import com.leashin.personalmemo.utils.Logs;
import com.leashin.personalmemo.widget.OptionView;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OptionListFragment extends ListFragment {
	private static final String TAG = "OptionListFragment";
	private static final boolean DEBUG = true;

	private ListView mOptionsLv;
	private Button mTestBtn;

	private int[] mOptionsIconId = { R.drawable.abs_main_home_bg,
			R.drawable.abs_main_right_bg };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_options, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	private void initViews() {
		mTestBtn = (Button) getView().findViewById(R.id.btn_test);
		mTestBtn.setText("sliding menu");

		mOptionsLv = getListView();
		mOptionsLv.setAdapter(new OptionListAdapter());
		mOptionsLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mOptionsLv.setItemChecked(0, true);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mOptionsLv.setItemChecked(position, true);
		showContent(position);
	}

	private void showContent(int position) {
		BaseFragmentActivity bfa = (BaseFragmentActivity) getActivity();
		bfa.replaceFragment(R.id.fragment_container,
				TestFragment.newInstance("content " + position));
	}

	private class OptionListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 50;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();
				convertView = new OptionView(getActivity());

				holder.mOptionIconIv = ((OptionView) convertView)
						.getOptionIcon();
				holder.mOptionTv = ((OptionView) convertView).getOptionText();

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.mOptionTv.setText("test " + position);
			holder.mOptionIconIv.setImageResource(mOptionsIconId[position
					% mOptionsIconId.length]);

			Logs.d(TAG, "lv getView() position = " + position, DEBUG);

			return convertView;
		}

		class ViewHolder {
			ImageView mOptionIconIv;
			TextView mOptionTv;
		}
	}
}
