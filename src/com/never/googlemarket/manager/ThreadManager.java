package com.never.googlemarket.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

	private static Object object = new Object();
	private static ThreadPoolProxy poolProxy;

	/**
	 * 获取线程池的代理对象
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
	 * 线程池的代理对象类
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
					 * corePoolSize： 线程池维护线程的最少数量 maximumPoolSize：线程池维护线程的最大数量
					 * keepAliveTime： 线程池维护线程所允许的空闲时间 unit： 线程池维护线程所允许的空闲时间的单位
					 * workQueue： 线程池所使用的缓冲队列 handler： 线程池对拒绝任务的处理策略
					 */

					threadPoolExecutor = new ThreadPoolExecutor(
					// 核心线程数
							corePoolSize,
							// 最大线程数
							maximumPoolSize,
							// 线程空闲存活的时间
							keepAliveTime, TimeUnit.MILLISECONDS,
							// 任务多的时候排队的队列
							new LinkedBlockingQueue<Runnable>(),
							// 获取线程工厂的方法
							Executors.defaultThreadFactory(),
							// 异常处理类
							new ThreadPoolExecutor.AbortPolicy());

				}
				// 执行任务
				threadPoolExecutor.execute(runnable);
			}
		}
	}

}
