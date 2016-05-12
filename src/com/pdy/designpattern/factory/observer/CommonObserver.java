package com.pdy.designpattern.factory.observer;

import java.util.Observable;
import java.util.Observer;

public class CommonObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("简单气象板" + (WeatherStation)o +" 状态改变"+arg);
	}

}
