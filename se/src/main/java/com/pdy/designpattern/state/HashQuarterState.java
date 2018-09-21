package com.pdy.designpattern.state;

import java.util.Random;

public class HashQuarterState extends AbstractState {

	private Random radom = new Random();
	public HashQuarterState(GumBallMachine machine) {
		super(machine);
	}

	@Override
	public void ejectQuarter() {
		machine.setState(new NoQuarterState(machine));
	}

	@Override
	public void turnCrank() {
		
		int nextInt = radom.nextInt(10);
		if(nextInt == 0){
			machine.setState(new WinnerState(machine));
		}else{
			machine.setState(new SoldState(machine));
		}
	}


	
}
