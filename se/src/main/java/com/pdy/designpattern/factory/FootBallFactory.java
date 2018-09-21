package com.pdy.designpattern.factory;

public class FootBallFactory implements IBallFactory {

	@Override
	public IBall getBall() {
		return new FootBall();
	}

}
