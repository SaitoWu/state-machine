package com.taobao.pamirs.example;

import com.taobao.pamirs.statemachine.Machine;

public class Main {

	public static void main(String[] args) {
		Vehicle v = new Vehicle();
		Machine m = new Machine();
		m.regProcess(v.states);
		m.run();
	}
}
