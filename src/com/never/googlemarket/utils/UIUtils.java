package com.never.googlemarket.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

import com.never.googlemarket.application.BaseApplication;

public class UIUtils {

	public static Context getContext() {
		return BaseApplication.getContext();
	}

	public static Handler getHandler() {
		return BaseApplication.getMainThreadHandler();
	}

	public static Looper getMainThreadLooper() {
		return BaseApplication.getMainThreadLooper();
	}

	public static int getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}

	public static Thread getMainThread() {
		return BaseApplication.getMainThread();
	}

	// ����Դ�ļ��л�ȡ����
	public static Resources getResources() {
		return getContext().getResources();
	}

	public static String getString(int id) {
		return getResources().getString(id);
	}

	public static String[] getStringArray(int id) {
		return getResources().getStringArray(id);
	}

	public static Drawable getDrawable(int id) {
		return getResources().getDrawable(id);
	}

	// dip To px
	public static int dip2px(int dp) {
		// dp��px��ת����ϵ
		float density = getResources().getDisplayMetrics().density;
		// 2*1.5+0.5 2*0.75 = 1.5+0.5
		return (int) (dp * density + 0.5);
	}

	// px To dip
	public static int px2dip(int px) {
		float density = getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5);
	}

	// �жϵ�ǰ���߳��Ƿ������߳���
	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}

	// UIչʾ����֤����UI�Ĳ����������߳���
	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			// ֱ���������߳��У�ֱ�ӵ��������run����������UI
			runnable.run();
		} else {
			// ��ȡhandler������post�����������񴫵ݽ�ȥִ��
			getHandler().post(runnable);
		}
	}

	/**
	 * ��Բ�ͬ�İ�ť״̬��ʾ��ͬ����ɫ����ɫѡ����
	 * 
	 * @param mTabTextColorResId
	 * @return
	 */
	public static ColorStateList getColorStateList(int mTabTextColorResId) {
		return getResources().getColorStateList(mTabTextColorResId);
	}
}
