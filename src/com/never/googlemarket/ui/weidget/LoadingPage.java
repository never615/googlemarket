package com.never.googlemarket.ui.weidget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.never.googlemarket.R;
import com.never.googlemarket.manager.ThreadManager;
import com.never.googlemarket.utils.UIUtils;

public abstract class LoadingPage extends FrameLayout {

	/**
	 * 页面状态，未加载
	 */
	private int STATE_UNLOAD = 0;
	private int STATE_LOADING = 1;
	private int STATE_ERROR = 2;
	private int STATE_EMPTY = 3;
	private int STATE_SUCCESS = 4;

	private int CURRENT_STATE;

	private View errorView;
	private View emptyView;
	private View loadingView;
	/**
	 * 加载成功的界面，逻辑右调用者去实现
	 */
	private View successedView;

	private LayoutParams layoutParams;

	public LoadingPage(Context context) {
		super(context);
		// 初始化中将所有页面都添加到当前的FrameLayout中，根据网络获取的状态做具体显示
		init();
	}

	private void init() {
		// 初始状态是未加载
		CURRENT_STATE = STATE_UNLOAD;

		// 初始化layoutParams
		layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		// 1.添加错误界面
		errorView = onCreateErrorView();
		if (errorView != null) {
			addView(errorView);
		}

		// 2.添加空界面
		emptyView = oncreateEmptyView();
		if (emptyView != null) {
			addView(emptyView);
		}
		// 3.添加正在加载界面
		loadingView = oncreateLoadingView();
		if (loadingView != null) {
			addView(loadingView);
		}

		// 处理显示逻辑
		showSafePage();

	}

	/**
	 * 通过调用UIUtils中的方法，确保安全的显示ui
	 */
	private void showSafePage() {
		UIUtils.runInMainThread(new Runnable() {

			@Override
			public void run() {
				showPage();
			}

		});
	}

	/**
	 * 处理界面显示的逻辑
	 */
	private void showPage() {
		if (errorView != null) {
			errorView.setVisibility(CURRENT_STATE == STATE_ERROR ? View.VISIBLE
					: View.GONE);
		}
		if (emptyView != null) {
			emptyView.setVisibility(CURRENT_STATE == STATE_EMPTY ? View.VISIBLE
					: View.GONE);
		}
		if (loadingView != null) {
			loadingView
					.setVisibility((CURRENT_STATE == STATE_LOADING || CURRENT_STATE == STATE_UNLOAD) ? View.VISIBLE
							: View.GONE);
		}
		if (successedView == null && CURRENT_STATE == STATE_SUCCESS) {
			successedView = oncreateSuccessed();
			addView(successedView, layoutParams);
		}

		if (CURRENT_STATE == STATE_SUCCESS) {
			successedView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 请求网络之后，根据onLoad返回的结果，做具体的页面显示
	 */
	public void show(){
		//每次请求网络之前，复位状态位unload
		CURRENT_STATE=STATE_UNLOAD;
		//通过线程管理器拿线程执行任务
		ThreadManager.getPoolProxy().execute(new RunnableTask());
	}
	/**
	 * 执行页面显示的任务
	 * @author rong
	 *
	 */
	class RunnableTask implements Runnable{

		@Override
		public void run() {
			//请求网络之后拿到的状态
			final ResultState resultState=onLoad();
			UIUtils.runInMainThread(new Runnable() {
				
				@Override
				public void run() {
					CURRENT_STATE=resultState.getValue();
					showPage();
				}
			});
		}
		
	}
/*	*//**
	 * 状态复位操作，在show（）之前要复位当前的状态，如果是 错误，空，加载成功，复位为unload，也就是show之前把状态变为未加载；
	 * @return
	 *//*
	private int resetState() {
		if(CURRENT_STATE == STATE_ERROR || CURRENT_STATE == STATE_EMPTY || CURRENT_STATE == STATE_SUCCESS){
			CURRENT_STATE = STATE_UNLOAD;
		}
		return CURRENT_STATE;
	}*/
	
	/**
	 * 加载成功时，返回的view，由调用者处理
	 * 
	 * @return
	 */
	public abstract View oncreateSuccessed();

	/**
	 * 请求网络操作完成后，需要告诉LoadingPage要显示那个界面，需要调用者完成
	 * @return
	 */
	public abstract ResultState onLoad();

	/**
	 * 不同的网络返回状态
	 */
	public enum ResultState {
		RESULTSTATE_ERROR(2), RESULTSTATE_EMPTY(3), RESULTSTATE_SUCCESS(4);

		private int value;

		ResultState(int value) {
			this.value = value;
		}
		public int getValue(){
			return value;
		}

	}

	/**
	 * 初始化加载界面
	 * 
	 * @return
	 */
	private View oncreateLoadingView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page, null);
	}

	/**
	 * 初始化空界面
	 * 
	 * @return
	 */
	private View oncreateEmptyView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page_empty,
				null);
	}

	/**
	 * 初始化错误界面
	 * 
	 * @return
	 */
	private View onCreateErrorView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page_error,
				null);
	}

}
