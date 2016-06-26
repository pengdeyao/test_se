package com.pdy.designpattern.state;

/**
 * 双倍状态
 * @author pdy
 *
 */
public class WinnerState extends AbstractState {

	public WinnerState(GumBallMachine machine) {
		super(machine);
	}

	@Override
	public void dispense() {
		machine.releaseBall();
		if(machine.getCount() > 0){
		machine.releaseBall();
		}
		if(machine.getCount() == 0){
			machine.setState(new SoldOutState(machine));
		}
	}

	
	
}
