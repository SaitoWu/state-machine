package im.saito.statemachine.model;

public class Path {

	public String exp;
	public String to;
	
	public void when(String exp){
		this.exp = exp;
	}
}
