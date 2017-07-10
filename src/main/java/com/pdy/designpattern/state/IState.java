package com.pdy.designpattern.state;
/**
 * 糖果机 状态<BR>
 * 映射糖果机可能发生的动作
 * @author pdy
 *
 */
public interface IState {

	/**
	 * 投入硬币
	 */
	void insertQuarter();
	/**
	 * 退出硬币
	 */
	void ejectQuarter();
	/**
	 * 转动曲柄
	 */
	void turnCrank();
	/**
	 * 发放糖果
	 */
	void dispense();
	
}
