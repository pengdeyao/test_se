package com.pdy.designpattern.factory.observer;

public class MainTest {
	public static void main(String[] args) {
		SimpleObserver o = new SimpleObserver();
		CommonObserver o2 = new CommonObserver();
		
		WeatherStation ws = new WeatherStation();
		ws.addObserver(o);
		ws.addObserver(o2);
		ws.setStatus("阴天");
		ws.setStatus("晴天");
		ws.setStatus("下雨");
	}
}
