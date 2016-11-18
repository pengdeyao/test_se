package com.pdy.collection.map;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityMapTest {

    public static void main(String[] args) {
        Map<String, Object> identityHashMap = new IdentityHashMap<String, Object>();
        String key1 = "aaa";
        String key2 = "aaa";
        String key3 = new String("bbb");
        String key4 = new String("bbb");
        identityHashMap.put(key1, null);
        identityHashMap.put(key3, null);
        System.out.println(identityHashMap.containsKey(key1));
        System.out.println(identityHashMap.containsKey(key2));
        System.out.println(identityHashMap.containsKey(key3));
        System.out.println(identityHashMap.containsKey(key4));
    }

}
