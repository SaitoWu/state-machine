package com.taobao.pamirs.statemachine;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Signal {

	protected Class<?>[]		params;

	protected List<Slot>		slots;

	private ThreadPoolExecutor	executor;
	private int					coreThreadCount;
	private int					maxThreadCount;

	public Signal(Class<?>... params) {
		this.params = params;
		this.slots = new ArrayList<Slot>();
		coreThreadCount = maxThreadCount = 2;
		executor = newThreadPoolExecutor(coreThreadCount, maxThreadCount);
	}

	private static ThreadPoolExecutor newThreadPoolExecutor(int coreThreadCount, int maxThreadCount) {
		return new ThreadPoolExecutor(coreThreadCount, maxThreadCount, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	public void add(Object listener, Boolean sync, String callback, Object... args) {
		Slot slot = new Slot(listener, sync, args);
		try {
			slot.delegate = listener.getClass().getMethod(callback, params);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		slots.add(slot);
	}

	public void dispatch() {
		for (Slot slot : slots) {
			if (slot.sync) {
				invokeSlot(slot);
			} else {
				PendingTask task = new PendingTask(slot);
				executor.execute(task);
			}

		}
	}

	private void invokeSlot(Slot slot) {
		try {
			slot.delegate.invoke(slot.listener, slot.args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	class PendingTask implements Runnable {

		private Slot	slot;

		public PendingTask(Slot slot) {
			super();
			this.slot = slot;
		}

		@Override
		public void run() {
			invokeSlot(slot);
		}
	}
}
