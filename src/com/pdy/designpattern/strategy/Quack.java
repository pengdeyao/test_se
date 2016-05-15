package com.pdy.designpattern.strategy;

public class Quack implements IQuackBehavior {

	@Override
	public void quark() {
		System.out.println("Quack");
	}

}
