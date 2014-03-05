package com.leashin.quanzi.widget;

import com.leashin.quanzi.R;
import com.leashin.quanzi.utils.Logs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OptionView extends LinearLayout implements Checkable {
	private static final String TAG = "OptionView";
	private static final boolean DEBUG = true;

	private boolean mChecked;

	private ImageView mOptionIconIv;
	private TextView mOptionTv;

	public OptionView(Context context) {
		this(context, null);
	}

	public OptionView(Context context, AttributeSet attrs) {
		super(context, attrs);

		inflate(context, R.layout.item_menu_options, this);
		initViews();
	}

	private void initViews() {
		mOptionIconIv = (ImageView) findViewById(R.id.iv_option_icon);
		mOptionTv = (TextView) findViewById(R.id.tv_option);
	}

	public ImageView getOptionIcon() {
		return mOptionIconIv;
	}

	public TextView getOptionText() {
		return mOptionTv;
	}

	@Override
	public void setChecked(boolean checked) {
		Logs.d(TAG, "setChecked = " + checked, DEBUG);
		if (checked) {
			mOptionTv.setTextColor(getResources().getColor(
					R.color.option_checked_text_color));
		} else {
			mOptionTv.setTextColor(getResources().getColor(
					R.color.primary_text_color));
		}
	}

	@Override
	public boolean isChecked() {
		Logs.d(TAG, "isChecked = " + mChecked, DEBUG);
		return mChecked;
	}

	@Override
	public void toggle() {
		Logs.d(TAG, "toggle = " + mChecked);
		setChecked(!mChecked);
	}

}
