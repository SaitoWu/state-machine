package im.saito.example;

import im.saito.statemachine.Machine;

import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		Process v = new Process();
		Machine m = new Machine();
		m.context = new HashMap<String, Object>();
		m.context.put("a", 4);
		m.regProcess(v.states);
		m.run();
		//shutdown if all of these are executed.
		//Executor.shutdown();
	}
}
