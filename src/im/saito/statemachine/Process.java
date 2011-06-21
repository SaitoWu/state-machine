package im.saito.statemachine;

import java.util.HashMap;
import java.util.Map;

public abstract class Process {

	public Map<String, State> states;
	
	public Map<String, Object> context;
	
	public Process() {
		states = new HashMap<String, State>();
		context = new HashMap<String, Object>();
	}
	
	public State createState(String name){
		State state = new State(name);
		states.put(name, state);
		return state;
	}
}
