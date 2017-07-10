package com.pdy.designpattern.iterator;

import java.util.Iterator;

/**
 * 所有菜单接口
 * @author pdy
 *
 */
public interface IMenu {

	public Iterator<MenuItem> createIterator();
}
