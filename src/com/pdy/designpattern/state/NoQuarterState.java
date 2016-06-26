package com.pdy.designpattern.state;

public class NoQuarterState extends AbstractState {

	public NoQuarterState(GumBallMachine machine) {
		super(machine);
	}

	@Override
	public void insertQuarter() {
		machine.setState(new HashQuarterState(machine));
	}

	
}
