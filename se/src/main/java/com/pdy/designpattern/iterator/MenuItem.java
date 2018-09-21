package com.pdy.designpattern.iterator;
/**
 * 对象村餐厅和对象村煎饼屋合并了, 他们的菜单也需要合并
 * 他们都实现了一个菜单项
 * @author pdy
 *
 */
public class MenuItem {

	private String name ;
	
	private String description;
	/** 素食者 */
	private Boolean vegetarian ;
	
	private Double price ;
	
	public MenuItem(String name, String description, Boolean vegetarian, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
