package com.never.googlemarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.ui.weidget.LoadingPage.ResultState;
import com.never.googlemarket.utils.UIUtils;

public class RecommendFragment extends BaseFragment {

	//��ȡUI�ķ���
	@Override
	public View oncreateSuccessed() {
		TextView textView = new TextView(UIUtils.getContext());
		textView.setText("RecommendFragment");
		return textView;
	}

	//��������ķ���ֵ���
	@Override
	public ResultState onLoad() {
		return ResultState.RESULTSTATE_SUCCESS;
	}



}
