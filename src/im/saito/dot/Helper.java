package im.saito.dot;


import im.saito.statemachine.State;
import im.saito.statemachine.Transition;
import im.saito.example.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class Helper {

	static final String	template	= "digraph finite_state_machine {\n" + "\trankdir=LR;\n" + "\tsize=\"8,5\"\n"
											+ "\tnode [shape = doublecircle];\n\n";
	static final String	close		= "}";

	public static void main(String args[]) {
		// file name
		File dot = new File("src/im/saito/dot/fsm.dot");
		// dot context
		String content = ctx(new Process().states);

		try {
			if (!dot.exists())
				dot.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(dot));
			output.write(template);
			output.write(content);
			output.write(close);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String ctx(Map<String, State> map) {

		StringBuilder appender = new StringBuilder();

		for (State state : map.values()) {
			for (Transition transition : state.nexts) {
				appender.append("\t");
				appender.append(state.name + " -> " + transition.to);
				if (transition.exp != null)
					appender.append(" [ label = \"" + transition.exp + "\" ]");
				appender.append(";\n");
			}
		}

		return appender.toString();
	}

}
