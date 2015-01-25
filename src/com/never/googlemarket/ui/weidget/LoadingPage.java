package com.never.googlemarket.ui.weidget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.never.googlemarket.R;
import com.never.googlemarket.manager.ThreadManager;
import com.never.googlemarket.utils.UIUtils;

public abstract class LoadingPage extends FrameLayout {

	/**
	 * ҳ��״̬��δ����
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
	 * ���سɹ��Ľ��棬�߼��ҵ�����ȥʵ��
	 */
	private View successedView;

	private LayoutParams layoutParams;

	public LoadingPage(Context context) {
		super(context);
		// ��ʼ���н�����ҳ�涼��ӵ���ǰ��FrameLayout�У����������ȡ��״̬��������ʾ
		init();
	}

	private void init() {
		// ��ʼ״̬��δ����
		CURRENT_STATE = STATE_UNLOAD;

		// ��ʼ��layoutParams
		layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		// 1.��Ӵ������
		errorView = onCreateErrorView();
		if (errorView != null) {
			addView(errorView);
		}

		// 2.��ӿս���
		emptyView = oncreateEmptyView();
		if (emptyView != null) {
			addView(emptyView);
		}
		// 3.������ڼ��ؽ���
		loadingView = oncreateLoadingView();
		if (loadingView != null) {
			addView(loadingView);
		}

		// ������ʾ�߼�
		showSafePage();

	}

	/**
	 * ͨ������UIUtils�еķ�����ȷ����ȫ����ʾui
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
	 * ���������ʾ���߼�
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
	 * ��������֮�󣬸���onLoad���صĽ�����������ҳ����ʾ
	 */
	public void show(){
		//ÿ����������֮ǰ����λ״̬λunload
		CURRENT_STATE=STATE_UNLOAD;
		//ͨ���̹߳��������߳�ִ������
		ThreadManager.getPoolProxy().execute(new RunnableTask());
	}
	/**
	 * ִ��ҳ����ʾ������
	 * @author rong
	 *
	 */
	class RunnableTask implements Runnable{

		@Override
		public void run() {
			//��������֮���õ���״̬
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
	 * ״̬��λ��������show����֮ǰҪ��λ��ǰ��״̬������� ���󣬿գ����سɹ�����λΪunload��Ҳ����show֮ǰ��״̬��Ϊδ���أ�
	 * @return
	 *//*
	private int resetState() {
		if(CURRENT_STATE == STATE_ERROR || CURRENT_STATE == STATE_EMPTY || CURRENT_STATE == STATE_SUCCESS){
			CURRENT_STATE = STATE_UNLOAD;
		}
		return CURRENT_STATE;
	}*/
	
	/**
	 * ���سɹ�ʱ�����ص�view���ɵ����ߴ���
	 * 
	 * @return
	 */
	public abstract View oncreateSuccessed();

	/**
	 * �������������ɺ���Ҫ����LoadingPageҪ��ʾ�Ǹ����棬��Ҫ���������
	 * @return
	 */
	public abstract ResultState onLoad();

	/**
	 * ��ͬ�����緵��״̬
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
	 * ��ʼ�����ؽ���
	 * 
	 * @return
	 */
	private View oncreateLoadingView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page, null);
	}

	/**
	 * ��ʼ���ս���
	 * 
	 * @return
	 */
	private View oncreateEmptyView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page_empty,
				null);
	}

	/**
	 * ��ʼ���������
	 * 
	 * @return
	 */
	private View onCreateErrorView() {
		return View.inflate(UIUtils.getContext(), R.layout.loading_page_error,
				null);
	}

}
