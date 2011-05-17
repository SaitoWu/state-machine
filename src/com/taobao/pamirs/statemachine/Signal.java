package com.taobao.pamirs.statemachine;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Signal {

	protected Class<?>[]		params;

	protected List<Slot>		slots;

	public Signal(Class<?>... params) {
		this.params = params;
		this.slots = new ArrayList<Slot>();
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
				Executor.execute(task);
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
