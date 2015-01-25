package com.never.googlemarket.base;

import com.never.googlemarket.ui.weidget.LoadingPage;
import com.never.googlemarket.ui.weidget.LoadingPage.ResultState;
import com.never.googlemarket.utils.UIUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	private LoadingPage loadingPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		loadingPage = new LoadingPage(UIUtils.getContext()) {

			@Override
			public View oncreateSuccessed() {
				return BaseFragment.this.oncreateSuccessed();
			}

			// onLoad操作当前BaseFragment依然不知道如何实现具体业务逻辑操作
			@Override
			public ResultState onLoad() {
				return BaseFragment.this.onLoad();
			}

		};

		return loadingPage;
	}

	/**
	 * 请求网络的操作，返回请求状态
	 * 
	 * @return
	 */
	public abstract ResultState onLoad();

	/**
	 * 加载成功的界面怎么显示，由子页面调用实现
	 * 
	 * @return
	 */
	public abstract View oncreateSuccessed();

	/**
	 * 触发请求网络的操作，每一个子类去完成
	 */
	public void show() {
		if (loadingPage != null) {
			loadingPage.show();
		}
	}

}
