package com.pdy.designpattern.state;

public abstract class AbstractState implements IState {

	protected GumBallMachine machine ;

	public AbstractState(GumBallMachine machine) {
		super();
		this.machine = machine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("无效操作");
		
	}

	@Override
	public void ejectQuarter() {
		System.out.println("无效操作");		
	}

	@Override
	public void turnCrank() {
		System.out.println("无效操作");		
	}

	@Override
	public void dispense() {
		System.out.println("无效操作");
		
	}
	
	

}
