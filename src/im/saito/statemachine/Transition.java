package im.saito.statemachine;

public class Transition {

	public String exp;
	public String to;
	
	public void when(String exp){
		this.exp = exp;
	}
}
