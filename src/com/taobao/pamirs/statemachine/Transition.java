package com.taobao.pamirs.statemachine;

public class Transition {

	public String exp;
	public String to;
	
	public void unless(String exp){
		this.exp = exp;
	}
}
