package com.pdy.designpattern.strategy;

public class MuteQuack implements IQuackBehavior {

	@Override
	public void quark() {
		System.out.println("<<Silence>>");
	}

}
