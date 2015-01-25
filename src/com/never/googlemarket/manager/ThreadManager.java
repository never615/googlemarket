package com.never.googlemarket.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

	private static Object object = new Object();
	private static ThreadPoolProxy poolProxy;

	/**
	 * ��ȡ�̳߳صĴ������
	 * 
	 * @return
	 */
	public static ThreadPoolProxy getPoolProxy() {
		synchronized (object) {
			if (poolProxy == null) {
				poolProxy = new ThreadPoolProxy(5, 5, 5L);
			}
			return poolProxy;
		}
	}

	/**
	 * �̳߳صĴ��������
	 * 
	 * @author rong
	 * 
	 */
	public static class ThreadPoolProxy {

		private ThreadPoolExecutor threadPoolExecutor;

		private int corePoolSize;
		private int maximumPoolSize;
		private long keepAliveTime;

		public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,
				long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		public void execute(Runnable runnable) {
			if (runnable == null) {
				return;
			} else {
				if (threadPoolExecutor == null
						|| threadPoolExecutor.isShutdown()) {

					/*
					 * corePoolSize�� �̳߳�ά���̵߳��������� maximumPoolSize���̳߳�ά���̵߳��������
					 * keepAliveTime�� �̳߳�ά���߳�������Ŀ���ʱ�� unit�� �̳߳�ά���߳�������Ŀ���ʱ��ĵ�λ
					 * workQueue�� �̳߳���ʹ�õĻ������ handler�� �̳߳ضԾܾ�����Ĵ������
					 */

					threadPoolExecutor = new ThreadPoolExecutor(
					// �����߳���
							corePoolSize,
							// ����߳���
							maximumPoolSize,
							// �߳̿��д���ʱ��
							keepAliveTime, TimeUnit.MILLISECONDS,
							// ������ʱ���ŶӵĶ���
							new LinkedBlockingQueue<Runnable>(),
							// ��ȡ�̹߳����ķ���
							Executors.defaultThreadFactory(),
							// �쳣������
							new ThreadPoolExecutor.AbortPolicy());

				}
				// ִ������
				threadPoolExecutor.execute(runnable);
			}
		}
	}

}
