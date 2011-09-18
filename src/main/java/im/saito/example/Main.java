package im.saito.example;

import im.saito.statemachine.Machine;
import im.saito.statemachine.Process;


public class Main {

	public static void main(String[] args) {
		Process p = new Procces();
		Machine m = new Machine();
		m.regProcess(p);
		m.run();
		//shutdown if all of these are executed.
		//Executor.shutdown();
	}
}
