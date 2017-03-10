package com.jesse;
/*
 * 此接口用来过滤链接，符合条件的才爬取
 */
public interface LinkFilter {
	
	public boolean accept(String url);
}
