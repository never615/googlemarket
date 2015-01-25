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
			//获取holder，复用convertView
			baseHolder=(BaseHolder) convertView.getTag();
		}else{
			//获取新创建的holder，创建view对象，查找控件findViewById
			baseHolder=getHolder();
		}
		
		//给holder填充数据
		baseHolder.setData(list.get(position));
		
		return baseHolder.initView();
	}

	/**
	 * 每个子控件的holder都不一样，所以由调用者构建
	 * @return
	 */
	public abstract BaseHolder getHolder() ;
}
