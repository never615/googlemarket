package com.never.googlemarket.holder;

import com.never.googlemarket.R;
import com.never.googlemarket.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

public class HomeHolder extends BaseHolder<String> {

	private TextView home_item_text;

	/**
	 * ��ʼ���ؼ����ɵ�����ʵ��
	 */
	@Override
	public View initView() {
		View view = View.inflate(UIUtils.getContext(),
				R.layout.layout_home_item, null);
		home_item_text = (TextView) view.findViewById(R.id.home_item_text);
		return view;
	}

	/**
	 * ���ÿؼ������ݣ��ɵ�����ʵ��
	 */
	@Override
	public void setViewdata() {
		// �õ����ݽ��������ݣ����ø��ؼ�
		String string = getData();
		home_item_text.setText(string);
	}
}
