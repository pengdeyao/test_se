package com.pdy.designpattern.observer;

import java.util.Observable;

public class WeatherStation extends Observable{

	private String status ;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		this.setChanged();
		this.notifyObservers(status);
	}
	
}
