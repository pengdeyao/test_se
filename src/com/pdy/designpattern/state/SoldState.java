package com.pdy.designpattern.state;

/**
 * 可售状态
 * @author pdy
 *
 */
public class SoldState extends AbstractState{

	public SoldState(GumBallMachine machine) {
		super(machine);
	}

	@Override
	public void insertQuarter() {
		System.out.println("可售状态，投币无效");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("可售状态，退币无效");
	}

	@Override
	public void turnCrank() {
		System.out.println("可售状态，投币无效");
	}

	@Override
	public void dispense() {
		this.machine.releaseBall();
		if(machine.getCount() == 0){
			this.machine.setState(new SoldOutState(machine));
		}
	}

}
