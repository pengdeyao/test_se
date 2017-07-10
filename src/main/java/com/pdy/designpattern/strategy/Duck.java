package com.pdy.designpattern.strategy;

/**
 * 鸭子类
 * @author pdy
 *
 */
public abstract class Duck {
	
	protected IFlyBehavior flyBehavior ;
	
	protected IQuackBehavior quackBehavior;
	
	/**
	 * 叫
	 */
	public void performQuack(){
		quackBehavior.quark();
	}
	public void performFly(){
		
		flyBehavior.fly();
	}
	
	
	/**
	 * 外观
	 */
	public abstract void display();
	/**
	 * 游泳
	 */
	public void swim(){
		System.out.println("duck is  swimming");
	}
	public IFlyBehavior getFlyBehavior() {
		return flyBehavior;
	}
	public void setFlyBehavior(IFlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public IQuackBehavior getQuackBehavior() {
		return quackBehavior;
	}
	public void setQuackBehavior(IQuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
}
