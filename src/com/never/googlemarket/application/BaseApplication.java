package com.never.googlemarket.application;

import android.R.anim;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application{
	//�����Ļ���
	private static Context context;
	//���߳�ID
	private static int mainThreadId;
	//���̶߳���
	private static Thread mainThread;
	//��Ѱ��
	private static Looper mainThreadLooper;
	//Handler
	private static Handler mainThreadHandler;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		this.context=getApplicationContext();
		//��ȡ���߳�id
		this.mainThreadId=android.os.Process.myTid();
		//��ȡ���̶߳���
		this.mainThread=Thread.currentThread();
		//��ȡ���߳���Ѱ��
		this.mainThreadLooper=getMainLooper();
		//��ȡhandler����
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
