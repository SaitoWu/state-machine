package com.taobao.pamirs.statemachine;

import java.util.ArrayList;
import java.util.List;

public class State {

	public String			name;
	public List<Transition>	nexts;

	// signal point
	public Signal			enter;
	public Signal			exec;
	public Signal			exit;

	public State(String name) {
		this.name = name;
		this.nexts = new ArrayList<Transition>();
	}

	public Transition to(String stateName) {
		Transition t = new Transition();
		t.to = stateName;
		nexts.add(t);
		return t;
	}

	public void execute() {
		if (enter != null)
			enter.dispatch();
		if(exec != null)
			exec.dispatch();
		if (exit != null)
			exit.dispatch();
	}
}
