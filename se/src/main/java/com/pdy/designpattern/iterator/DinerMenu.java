package com.pdy.designpattern.iterator;

import java.util.Iterator;

/**
 * 对象村餐厅菜单
 * @author pdy
 *
 */
public class DinerMenu implements IMenu{

	private static final int MAX_ITEMS = 3 ;
	private int numberOfItems = 0 ;
	private MenuItem[] menuItems ;
	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
		addItem("鱼香肉丝", "肉丝+胡萝卜+鸡肉", false, 12.00D);
		addItem("地三鲜", "土豆+茄子+青椒", false, 13.00D);
		addItem("番茄炒鸡蛋", "番茄+鸡蛋", false, 13.00D);
	}

	public void addItem(String name ,String description,Boolean vegetarian,Double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if(numberOfItems >= MAX_ITEMS){
			System.err.println("装不下菜单了 ");
		}else{
			menuItems[numberOfItems] = menuItem;
			numberOfItems ++ ;
		}
	}

	@Override
	public Iterator<MenuItem> createIterator() {
		return new DinerMenuIterator(menuItems);
	}
	
	
}
class DinerMenuIterator implements Iterator<MenuItem>{

	private MenuItem[] menuItems ;
	private int index  ;
	
	public DinerMenuIterator(MenuItem[] menuItems) {
		super();
		this.menuItems = menuItems;
		index = 0 ;
	}

	@Override
	public boolean hasNext() {
		return index < menuItems.length ;
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem =  menuItems[index];
		 index ++ ;
		 return menuItem;
	}

	@Override
	public void remove() {
		
	}
	
}