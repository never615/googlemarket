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

	// 从资源文件中获取对象
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
		// dp和px的转换关系
		float density = getResources().getDisplayMetrics().density;
		// 2*1.5+0.5 2*0.75 = 1.5+0.5
		return (int) (dp * density + 0.5);
	}

	// px To dip
	public static int px2dip(int px) {
		float density = getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5);
	}

	// 判断当前的线程是否在主线程中
	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}

	// UI展示，保证处理UI的操作都在主线程中
	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			// 直接跑在主线程中，直接调用任务的run方法，处理UI
			runnable.run();
		} else {
			// 获取handler，调用post方法，将任务传递进去执行
			getHandler().post(runnable);
		}
	}

	/**
	 * 针对不同的按钮状态显示不同的颜色，颜色选择器
	 * 
	 * @param mTabTextColorResId
	 * @return
	 */
	public static ColorStateList getColorStateList(int mTabTextColorResId) {
		return getResources().getColorStateList(mTabTextColorResId);
	}
}
