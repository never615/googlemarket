package com.never.googlemarket.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.never.googlemarket.adapter.MyBaseAdapter;
import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.holder.BaseHolder;
import com.never.googlemarket.holder.HomeHolder;
import com.never.googlemarket.ui.weidget.LoadingPage.ResultState;
import com.never.googlemarket.utils.UIUtils;

public class HomeFragment extends BaseFragment {

	private List<String> stringList=new ArrayList<String>();
	

	/**
	 * ���سɹ�view�ķ���
	 * @return
	 */
	@Override
	public View oncreateSuccessed() {
		ListView listView=new ListView(UIUtils.getContext());
		listView.setAdapter(new MyAdapter(stringList));
		return listView;
	}
	/**
	 * ��������Ĳ�������������״̬
	 * @return
	 */
	@Override
	public ResultState onLoad() {
		initData();
		return ResultState.RESULTSTATE_SUCCESS;
	}

	
	private void initData() {
		stringList.clear();
		for(int i=1;i<21;i++){
			stringList.add("string"+i);
		}
	}


	class MyAdapter extends MyBaseAdapter{

		public MyAdapter(List list) {
			super(list);
		}

		@Override
		public BaseHolder getHolder() {
			return new HomeHolder();
		}
	}
}
