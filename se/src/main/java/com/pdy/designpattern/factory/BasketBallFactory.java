package com.pdy.designpattern.factory;

public class BasketBallFactory implements IBallFactory {

	@Override
	public IBall getBall() {
		return new BasketBall();
	}

}
