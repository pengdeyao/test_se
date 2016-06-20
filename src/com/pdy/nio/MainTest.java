package com.pdy.nio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

	public static void main(String[] args) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Map<Object,Object> map2 = new LinkedHashMap<>();
		map2.put("1", "1");
		map.put(null, null);
		System.out.println(map.get(null));
		System.out.println(map2.entrySet());
		List<String>   list = Arrays.asList("a");
		System.out.println(list.getClass());
		// TODO Auto-generated method stub
		System.out.println("11111111111");
		System.out.println("测试提交到分");
	}

}
