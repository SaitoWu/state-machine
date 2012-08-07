package im.saito.statemachine.helper;


import im.saito.statemachine.model.Transition;
import im.saito.statemachine.model.State;
import im.saito.statemachine.model.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class DotHelper {

	static final String	template	= "digraph finite_state_machine {\n" + "\trankdir=LR;\n" + "\tsize=\"8,5\"\n"
											+ "\tnode [shape = doublecircle];\n\n";
	static final String	close		= "}";

	public static void main(String args[]) {
		// file name
		File f = new File("src/im/saito/dot/fsm.dot");
		// dot context
		String content = ctx(new Process("caller").states);

		try {
			if (!f.exists())
				f.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(template);
			output.write(content);
			output.write(close);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		dot(f);

	}

	private static String ctx(Map<String, State> map) {

		StringBuilder appender = new StringBuilder();

		for (State state : map.values()) {
			for (Transition transition : state.transitions) {
				appender.append("\t");
				appender.append(state.name + " -> " + transition.to);
				if (transition.exp != null)
					appender.append(" [ label = \"" + transition.exp + "\" ]");
				appender.append(";\n");
			}
		}

		return appender.toString();
	}

	private static void dot(File f){
		String abpath = f.getAbsolutePath();
		String cmd = "dot -Tpng "+abpath+" -o"+f.getAbsolutePath()+".png";
		ProcessBuilder pb = null;
		if (System.getProperty("os.name").startsWith("Windows"))
			pb = new ProcessBuilder("cmd", "/C", cmd);
		else
			pb = new ProcessBuilder("bash", "-c", cmd);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
