package im.saito.statemachine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;

public class State {

	public String			    name;
	public List<Transition>		transitions;

	// execute point
	public String			    enter;
	public String			    exec;
	public String			    exit;

	public State(String name) {
		this.name = name;
		this.transitions = new ArrayList<Transition>();
	}

	public Transition to(State state) {
		Transition transition = new Transition();
		transition.to = state.name;
        transitions.add(transition);
		return transition;
	}

	public void execute(Map<String, Object> context) {
		if (enter != null)
			MVEL.eval(enter, context);
		if (exec != null)
			MVEL.eval(exec, context);
		if (exit != null)
			MVEL.eval(exit, context);
	}
}
