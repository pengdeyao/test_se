package com.pdy.designpattern.state;

/**
 * 状态模式之---糖果|彩蛋机<BR>
 * 建议将状态变化放入 彩蛋机中控制，不然状态太多时看代码不知道怎么流转的
 * @author pdy 
 *
 */
public class GumBallMachine {

	private int count ;
	
	private IState state ;
	

	public GumBallMachine(int count) {
		super();
		this.count = count;
		state = new NoQuarterState(this);
	}
	
	public void insertQuarter(){
		state.insertQuarter();
	}
	
	public void ejectQuarter(){
		state.ejectQuarter();
	}
	
	public void turnCrank(){
		state.turnCrank();
	}
	
	public void dispense(){
		state.dispense();
	}
	
	 void releaseBall(){
		if(count != 0){ 
			count -- ;
			System.out.println("放出了糖果");
		}
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state.getClass().getName() +" 糖果"+count+"颗";
	}

	public int getCount() {
		return count;
	}
	
	
}
