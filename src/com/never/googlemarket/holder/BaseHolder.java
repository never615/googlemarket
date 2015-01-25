package com.never.googlemarket.holder;

import android.view.View;

public abstract class BaseHolder<T> {

	private View view;
	private T data;

	public BaseHolder() {
		super();
		// 初始化控件
		view = initView();

		// 给当前复用的view设置标记
		view.setTag(this);
	}
	public void setData(T data) {
		this.data = data;
		setViewdata();
	}
	public T getData(){
		return data;
	}
	/**
	 * 初始化控件，只有调用者知道
	 */
	public abstract View initView();

	/**
	 * 设置view的数据
	 */
	public abstract void setViewdata();
}
