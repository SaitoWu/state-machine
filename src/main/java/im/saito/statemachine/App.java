package im.saito.statemachine;

import im.saito.statemachine.model.Process;
import im.saito.statemachine.model.State;
import im.saito.statemachine.model.Transition;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String args[]){
        Process p = new Process("Hello world");
        State start = p.createState("start");
        start.exec = "System.out.println(\"Hello World\")";
        State end = p.createState("end");
        end.exec = "import im.saito.statemachine.model.Transition; System.out.println(new Transition())";
        start.to(end).when("a > b");

        //System.out.println(Transition.class);
        Machine m = new Machine();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 5);
        map.put("b", 4);
        m.regProcess(p);
        String next = m.run("start", map);
        System.out.println("next state is: " + next);
        next = m.run(next, map);
        System.out.println("next state is: " + next);
    }
}
