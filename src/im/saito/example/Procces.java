package im.saito.example;

import im.saito.statemachine.Process;
import im.saito.statemachine.Signal;
import im.saito.statemachine.State;


public class Procces extends Process{
	
	public Callee callee;

	public Procces() {
		context.put("a", 6);
		callee = new Callee();
		init();
	}
	
	public void init() {
		// new start state
		State start = createState("start");
		start.enter = new Signal(String.class).add(callee, true, "beep", "<============= start enter helloWorld ===============>");
		start.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in start state ===============>");
		// new service state
		State service = createState("service");
		// add enter and exit signal
		service.enter = new Signal(String.class).add(callee, true, "beep", "<============= service enter helloWorld ===============>");
		service.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in service state ===============>");
		service.exit = new Signal(String.class).add(callee, false, "beep", "<============= service exit byeWorld ===============>");
		// new task state
		State task = createState("task");
		task.enter = new Signal(String.class).add(callee, true, "beep", "<============= task enter helloWorld ===============>");
		// new end state
		State end = createState("end");
		end.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in end state ===============>");
		// process transition
		start.to(service).when("a > 5");
		start.to(task).when("a <= 5");
		service.to(end);
		task.to(end);
	}
}
