package im.saito.statemachine;

import im.saito.statemachine.model.Process;
import im.saito.statemachine.model.State;
import im.saito.statemachine.model.Transition;

import java.util.Map;

import org.mvel2.MVEL;

public class Machine {

	public Process p;

	public void regProcess(Process p) {
		this.p = p;
	}

    public String run(String state, Map<String, Object> context){
        String next = null;
        State s = p.states.get(state);
        if(s != null){
            s.execute(context);
            for(Transition t : s.transitions){
                if(t.exp == null){
                    next = t.to;
                    break;
                }
                if(MVEL.evalToBoolean(t.exp, context)){
                    next = t.to;
                    break;
                }
            }
        }
        return next;
    }
}
