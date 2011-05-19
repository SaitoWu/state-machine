package im.saito.statemachine;

import java.lang.reflect.Method;

public class Slot {

	public Object	listener;
	public Boolean	sync = true;
	public Method	delegate;
	public Object[]	args;
	
	public Slot(Object listener, Boolean sync, Object[] args) {
		super();
		this.listener = listener;
		this.sync = sync;
		this.args = args;
	}
	
}
