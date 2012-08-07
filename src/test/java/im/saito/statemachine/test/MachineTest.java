package im.saito.statemachine.test;

import im.saito.statemachine.Machine;
import im.saito.statemachine.model.*;
import im.saito.statemachine.model.Process;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MachineTest {

    private Machine m;
    private Map     map;

    @Before
    public void setUp(){
        m = new Machine();
        Process p = new Process("Hello world");
        State start = p.createState("start");
        start.exec = "System.out.println(\"Hello World\")";
        State end = p.createState("end");
        end.exec = "import im.saito.statemachine.model.Transition; System.out.println(new Transition())";
        start.to(end).when("a > b");
        map = new HashMap<String, Object>();
        map.put("a", 5);
        map.put("b", 4);
        m.regProcess(p);
    }


    @Test
    public void testApp(){
        String next = m.run("start", map);
        Assert.assertEquals("end", next);
        next = m.run(next, map);
        Assert.assertEquals(null, next);
    }
}
