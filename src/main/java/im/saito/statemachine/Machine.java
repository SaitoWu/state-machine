package im.saito.statemachine;

import im.saito.statemachine.model.Process;
import im.saito.statemachine.model.State;
import im.saito.statemachine.model.Path;

import java.util.Map;

import org.mvel2.MVEL;

public class Machine {

	public Process p;

	public void regProcess(Process p) {
		this.p = p;
	}

	public void run(Map<String, Object> context) {
		Map<String, State> states = p.states;
		State s = states.get("start");
		while (s != null) {
			s.execute(context);
			s = states.get(next(s, context));
		}
	}

	private String next(State s, Map<String, Object> context) {
		String next = null;
		for (Path path : s.nexts) {
			// has "exp" and "to" #=> eval exp then if true to = next else
			// continue
			// has "to" and "exp" == null #=> then to = next
			if (path.exp == null)
				next = path.to;
			else {
				if (MVEL.evalToBoolean(path.exp, context)){
					next = path.to;
					break;
				}else{
					continue;
				}
			}
		}
		return next;
	}
}
