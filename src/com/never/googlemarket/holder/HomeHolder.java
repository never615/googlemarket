package com.never.googlemarket.holder;

import com.never.googlemarket.R;
import com.never.googlemarket.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class HomeHolder extends BaseHolder<String> {

	private TextView home_item_text;

	/**
	 * 初始化控件，由调用者实现
	 */
	@Override
	public View initView() {
		View view = View.inflate(UIUtils.getContext(),
				R.layout.layout_home_item, null);
		home_item_text = (TextView) view.findViewById(R.id.home_item_text);
		return view;
	}

	/**
	 * 设置控件的数据，由调用者实现
	 */
	@Override
	public void setViewdata() {
		// 拿到传递进来的数据，设置给控件
		String string = getData();
		home_item_text.setText(string);
	}
}
