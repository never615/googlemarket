package com.never.googlemarket.holder;

import android.view.View;

public abstract class BaseHolder<T> {

	private View view;
	private T data;

	public BaseHolder() {
		super();
		// ��ʼ���ؼ�
		view = initView();

		// ����ǰ���õ�view���ñ��
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
	 * ��ʼ���ؼ���ֻ�е�����֪��
	 */
	public abstract View initView();

	/**
	 * ����view������
	 */
	public abstract void setViewdata();
}
