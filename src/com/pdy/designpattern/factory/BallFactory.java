package com.pdy.designpattern.factory;

public class BallFactory {

	/**
	 * 普通工厂方法
	 * @param ballType
	 * @return
	 */
	public IBall getBall(String ballType){
		if("football".equals(ballType)){
			return new FootBall();
		}else if("basketball".equals(ballType)){
			return new BasketBall();
		}else {
			return null ;
		}
	}
	
	/**
	 * 静态方法工厂
	 * @return
	 */
	public static IBall getBasketBall(){
		return new BasketBall();
	}
	
	/**
	 * 静态方法工厂
	 * @return
	 */
	public static IBall getFootBall(){
		return new FootBall();
	}
}
