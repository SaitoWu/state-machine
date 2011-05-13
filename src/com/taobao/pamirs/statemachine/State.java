package com.taobao.pamirs.statemachine;

public class State {

	public String	name;
	public String	next;
	//signal point
	public Signal	enter;
	public Signal	exit;

	public State(String name) {
		super();
		this.name = name;
	}

	public State next(String stateName) {
		this.next = stateName;
		return this;
	}

	public String execute() {
		if (enter != null)
			enter.dispatch();
		System.out.println("<===========i am in " + name + " state===============>");
		if (exit != null)
			exit.dispatch();
		return next;
	}
}
