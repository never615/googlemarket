package com.never.googlemarket.application;

import android.R.anim;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application{
	//上下文环境
	private static Context context;
	//主线程ID
	private static int mainThreadId;
	//主线程对象
	private static Thread mainThread;
	//轮寻器
	private static Looper mainThreadLooper;
	//Handler
	private static Handler mainThreadHandler;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		this.context=getApplicationContext();
		//获取主线程id
		this.mainThreadId=android.os.Process.myTid();
		//获取主线程对象
		this.mainThread=Thread.currentThread();
		//获取主线程轮寻器
		this.mainThreadLooper=getMainLooper();
		//获取handler对象
		this.mainThreadHandler=new Handler();
	}

	public static Context getContext() {
		return context;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

	public static Thread getMainThread() {
		return mainThread;
	}

	public static Looper getMainThreadLooper() {
		return mainThreadLooper;
	}

	public static Handler getMainThreadHandler() {
		return mainThreadHandler;
	}
	
}
