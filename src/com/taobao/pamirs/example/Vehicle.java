package com.taobao.pamirs.example;

import java.util.HashMap;
import java.util.Map;

import com.taobao.pamirs.statemachine.Signal;
import com.taobao.pamirs.statemachine.State;

public class Vehicle {

	public Map<String, State>	states;

	public Vehicle() {
		states = new HashMap<String, State>();
		this.init();
	}

	private void init() {
		Car car = new Car();
		// new start state
		State start = new State("start").next("service");
		start.enter = new Signal(String.class);
		//TODO maybe .addListner().addSync().addMethod().add()
		start.enter.add(car, false, "beep", "<============= start enter helloWorld ===============>");
		// new service state
		State service = new State("service").next("end");
		// add enter and exit signal
		service.enter = new Signal(String.class);
		service.enter.add(car, true, "beep", "<============= service enter helloWorld ===============>");
		service.exit = new Signal(String.class);
		service.exit.add(car, true, "beep", "<============= service exit byeWorld ===============>");
		// new end state
		State end = new State("end");
		// put into states
		states.put("start", start);
		states.put("service", service);
		states.put("end", end);
	}

}
