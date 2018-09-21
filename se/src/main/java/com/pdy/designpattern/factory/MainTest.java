package com.pdy.designpattern.factory;

public class MainTest {

	public static void main(String[] args) {
		//普通工厂
		BallFactory factory  = new BallFactory();
		factory.getBall("football").getBallName();
		factory.getBall("basketball").getBallName();
		
		//静态方法工厂
		BallFactory.getBasketBall().getBallName();
		BallFactory.getFootBall().getBallName();
		
		//抽象方法工厂
		FootBallFactory footBallFactory = new FootBallFactory() ;
		footBallFactory.getBall().getBallName();
		BasketBallFactory basketBallFactory = new BasketBallFactory();
		basketBallFactory.getBall().getBallName();
		
		
	}

}
