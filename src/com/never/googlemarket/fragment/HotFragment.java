package com.never.googlemarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.ui.weidget.LoadingPage.ResultState;
import com.never.googlemarket.utils.UIUtils;

public class HotFragment extends BaseFragment {

		@Override
		public View oncreateSuccessed() {
			TextView textView = new TextView(UIUtils.getContext());
			textView.setText("HotFragment");
			return textView;
		}

		@Override
		public ResultState onLoad() {
			return ResultState.RESULTSTATE_SUCCESS;
		}

}
