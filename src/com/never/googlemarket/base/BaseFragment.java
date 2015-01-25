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

			// onLoad������ǰBaseFragment��Ȼ��֪�����ʵ�־���ҵ���߼�����
			@Override
			public ResultState onLoad() {
				return BaseFragment.this.onLoad();
			}

		};

		return loadingPage;
	}

	/**
	 * ��������Ĳ�������������״̬
	 * 
	 * @return
	 */
	public abstract ResultState onLoad();

	/**
	 * ���سɹ��Ľ�����ô��ʾ������ҳ�����ʵ��
	 * 
	 * @return
	 */
	public abstract View oncreateSuccessed();

	/**
	 * ������������Ĳ�����ÿһ������ȥ���
	 */
	public void show() {
		if (loadingPage != null) {
			loadingPage.show();
		}
	}

}
