package im.saito.example;

import im.saito.statemachine.Signal;
import im.saito.statemachine.State;

import java.util.HashMap;
import java.util.Map;


public class Process {
	
	public Callee callee;

	public Map<String, State>	states;

	public Process() {
		callee = new Callee();
		states = new HashMap<String, State>();
		this.init();
	}

	private void init() {
		// new start state
		State start = new State("start");
		start.enter = new Signal(String.class).add(callee, true, "beep", "<============= start enter helloWorld ===============>");
		start.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in start state ===============>");
		start.to("service").when("a > 5");
		start.to("task").when("a <= 5");
		// new service state
		State service = new State("service");
		service.to("end");
		// add enter and exit signal
		service.enter = new Signal(String.class).add(callee, true, "beep", "<============= service enter helloWorld ===============>");
		service.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in service state ===============>");
		service.exit = new Signal(String.class).add(callee, true, "beep", "<============= service exit byeWorld ===============>");
		// new task state
		State task = new State("task");
		task.enter = new Signal(String.class).add(callee, false, "beep", "<============= task enter helloWorld ===============>");
		task.to("end");
		// new end state
		State end = new State("end");
		end.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in end state ===============>");
		// put into states
		states.put("start", start);
		states.put("service", service);
		states.put("task", task);
		states.put("end", end);
	}

}
