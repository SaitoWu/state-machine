package im.saito.statemachine;

import java.util.Map;
import java.util.Map.Entry;

import im.saito.statemachine.Process;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Machine {

	public Map<String, Object>	context;

	public Map<String, State>	states;

	public ScriptEngineManager	factory;

	public ScriptEngine			engine;

	public void regProcess(Process p) {
		this.states = p.states;
		this.context = p.context;
		factory = new ScriptEngineManager();
		engine = factory.getEngineByName("javascript");
	}

	public void run() {
		State s = states.get("start");
		// TODO fork join support
		while (s != null) {
			s.execute();
			s = states.get(next(s));
		}
	}

	private String next(State s) {
		String next = null;
		for (Transition t : s.nexts) {
			// has "exp" and "to" #=> eval exp then if true to = next else
			// continue
			// has "to" and "exp" == null #=> then to = next
			if (t.exp == null)
				next = t.to;
			else {
				try {
					for (Entry<String, Object> entry : context.entrySet()) {
						engine.put(entry.getKey(), entry.getValue());
					}
					Boolean result = (Boolean) engine.eval(t.exp);
					if (result)
						next = t.to;
					else
						continue;
				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}
		}
		return next;
	}
}
