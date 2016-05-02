package com.pdy.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {

	public static void main(String[] args) {
		
	}

}

/**
 * 文件名过滤器
 * @author pdy
 *
 */
class DirFilter implements FilenameFilter{
	private Pattern pattern ;
	
	public  DirFilter(String regex) {
		pattern  = Pattern.compile(regex);
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return false;
	}
	
}