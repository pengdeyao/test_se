package com.pdy.designpattern.strategy;

public class MiniDuckSimulator {

	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		duck.setFlyBehavior(new FlyWithWings());
		duck.setQuackBehavior(new Quack());
		
		duck.performFly();
		duck.performQuack();
		
		duck.setFlyBehavior(new FlyNoWay());
		duck.setQuackBehavior(new MuteQuack());
		duck.performFly();
		duck.performQuack();
		
		duck = new ModelDuck();
		duck.display();
		duck.setFlyBehavior(new FlyNoWay());
		duck.performFly();
		duck.setFlyBehavior(new FlyRocketPowered());
		duck.performFly();

	}

}
