package com.pdy.designpattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 新餐厅服务员
 * @author pdy
 *
 */
public class Waitress {

	private IMenu pancakeHouseMenu ;
	
	private IMenu dinerMenu ;
	
	private List<IMenu> menus ;

	public Waitress(IMenu pancakeHouseMenu, IMenu dinerMenu) {
		super();
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	//开闭原则 修改方案
	public Waitress(List<IMenu> menus) {
		super();
		this.menus = menus;
	}




	public void printMenu(){
		printMenu(pancakeHouseMenu.createIterator());
		System.out.println("-------------------分割线----------------");
		printMenu(dinerMenu.createIterator());
	}
	/**
	 * 开闭原则 修改方案
	 */
	public void printMenu2(){
		Iterator<IMenu> menuIterator = menus.iterator();
		while(menuIterator.hasNext()){
			printMenu(menuIterator.next().createIterator());
			System.out.println("-------------------分割线----------------");
		}
	}
	
	private void printMenu(Iterator<MenuItem> iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = iterator.next();
			System.out.println(menuItem.getName());
			System.out.println(menuItem.getDescription());
			System.out.println(menuItem.getPrice());
			System.out.println(menuItem.getVegetarian());
		}
		
	}
}
