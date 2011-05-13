package com.taobao.pamirs.statemachine;

import java.util.Map;

public class Machine {

	public Map<String, State>	states;

	public void regProcess(Map<String, State> states) {
		this.states = states;
	}

	public void run() {
		State s = states.get("start");
		while (s != null) {
			s.execute();
			s = states.get(s.next);
		}
	}

}
