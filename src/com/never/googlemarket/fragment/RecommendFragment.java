package com.never.googlemarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.ui.weidget.LoadingPage.ResultState;
import com.never.googlemarket.utils.UIUtils;

public class RecommendFragment extends BaseFragment {

	//获取UI的方法
	@Override
	public View oncreateSuccessed() {
		TextView textView = new TextView(UIUtils.getContext());
		textView.setText("RecommendFragment");
		return textView;
	}

	//请求网络的返回值结果
	@Override
	public ResultState onLoad() {
		return ResultState.RESULTSTATE_SUCCESS;
	}



}
