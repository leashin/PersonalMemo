package com.leashin.quanzi.ui.adapter;

import com.leashin.quanzi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChecklistAdapter extends BaseAdapter {
	private static final int TEST_COUNT = 20;

	private Context mContext;

	public ChecklistAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return TEST_COUNT;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_checklist, null);

			holder.mTestTv = (TextView) convertView
					.findViewById(R.id.tv_testid);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mTestTv.setText("µÚ" + position + "¸ö");

		return convertView;
	}

	class ViewHolder {
		TextView mTestTv;
	}
}
