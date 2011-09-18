package im.saito.statemachine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;

public class State {

	public String			name;
	public List<Path>		nexts;

	// execute point
	public String			enter;
	public String			exec;
	public String			exit;

	public State(String name) {
		this.name = name;
		this.nexts = new ArrayList<Path>();
	}

	public Path to(State state) {
		Path path = new Path();
		path.to = state.name;
		nexts.add(path);
		return path;
	}

	public void execute(Map<String, Object> context) {
		if (enter != null)
			MVEL.eval(enter, context);
		if (exec != null)
			MVEL.eval(enter, context);
		if (exit != null)
			MVEL.eval(enter, context);
	}
}
