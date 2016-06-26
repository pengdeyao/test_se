package com.pdy.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 煎饼屋菜单
 * @author pdy
 *
 */
public class PancakeHouseMenu implements IMenu{

	private List<MenuItem> menuItems ;

	public PancakeHouseMenu() {
		this.menuItems =  new ArrayList<MenuItem>();
		addItem("煎饼", "煎饼果子", false, 5.00D);
		addItem("鸡蛋煎饼", "鸡蛋煎饼", false, 5.50D);
		addItem("鸡蛋灌饼", "鸡蛋灌饼", false, 5.00D);
	}
	
	public void addItem(String name ,String description,Boolean vegetarian,Double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}

	@Override
	public Iterator<MenuItem> createIterator() {
		return menuItems.iterator();
	}
	
}
