package com.pdy.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class SimpleObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("普通气象板" + (WeatherStation)o +" 状态改变"+arg);
	}

}
