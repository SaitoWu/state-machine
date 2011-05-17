package com.taobao.pamirs.example;

import java.util.HashMap;

import com.taobao.pamirs.statemachine.Executor;
import com.taobao.pamirs.statemachine.Machine;

public class Main {

	public static void main(String[] args) {
		Vehicle v = new Vehicle();
		Machine m = new Machine();
		m.context = new HashMap<String, Object>();
		m.context.put("a", 5);
		m.regProcess(v.states);
		m.run();
		Executor.shutdown();
	}
}
