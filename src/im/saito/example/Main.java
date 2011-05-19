package im.saito.example;

import im.saito.statemachine.Executor;
import im.saito.statemachine.Machine;

import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		Vehicle v = new Vehicle();
		Machine m = new Machine();
		m.context = new HashMap<String, Object>();
		m.context.put("a", 8);
		m.regProcess(v.states);
		m.run();
		Executor.shutdown();
	}
}
