package com.pdy.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
public static void main(String[] args) {
	 
	
	PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
	DinerMenu dinerMenu = new DinerMenu();
	
	Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
	waitress.printMenu();
	System.err.println("========================================");
	List<IMenu> menus = new ArrayList<IMenu>();
	menus.add(dinerMenu);
	menus.add(pancakeHouseMenu);
	Waitress waitress2 = new Waitress(menus);
	waitress2.printMenu2();
	
}
}
