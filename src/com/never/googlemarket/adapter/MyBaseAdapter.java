package com.never.googlemarket.adapter;

import java.util.List;

import com.never.googlemarket.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	private List<T> list;
	private BaseHolder baseHolder;
	
	public MyBaseAdapter(List<T> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView!=null&&baseHolder instanceof BaseHolder){
			//��ȡholder������convertView
			baseHolder=(BaseHolder) convertView.getTag();
		}else{
			//��ȡ�´�����holder������view���󣬲��ҿؼ�findViewById
			baseHolder=getHolder();
		}
		
		//��holder�������
		baseHolder.setData(list.get(position));
		
		return baseHolder.initView();
	}

	/**
	 * ÿ���ӿؼ���holder����һ���������ɵ����߹���
	 * @return
	 */
	public abstract BaseHolder getHolder() ;
}
