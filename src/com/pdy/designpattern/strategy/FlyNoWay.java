package com.pdy.designpattern.strategy;

public class FlyNoWay implements IFlyBehavior {

	@Override
	public void fly() {
		System.out.println("I can't fly");
	}

}
