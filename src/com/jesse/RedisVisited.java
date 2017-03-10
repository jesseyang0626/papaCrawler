package com.jesse;

import redis.clients.jedis.Jedis;

public class RedisVisited {
//	/** 
//	  * 已访问的url集合，即Visited表 
//	  */  
//	private static Set visitedUrl=new HashSet();
//	//待访问的URL集合
//	private static Queue unVisitedUrl=new Queue();
	
	private static RedisQueue unVisited = new RedisQueue();
	private static Jedis jedis = new Jedis("localhost");
	static String todoList = "todojiaoshou";
	static String visited = "visitedjiaoshou";
	/** 
	  * 添加到访问过的 URL 队列中 
	  */  
	 public static void addVisitedUrl(String url) {  
	    jedis.sadd(visited, url);
	 }  
	 /** 
	  * 移除访问过的 URL 
	  */  
	 public static void removeVisitedUrl(String url) {  
		 jedis.srem(visited, url); 
	 }  
	 /** 
	  * 获得已经访问的 URL 数目 
	  */  
	 public static int getVisitedUrlNum() {  
	  return Integer.valueOf(String.valueOf(jedis.scard(visited)));  
	 }  
	 /** 
	  * 获得UnVisited队列 
	  */  
	 public static RedisQueue getUnVisitedUrl() {  
	  return unVisited;  
	 }  
	 /** 
	  * 未访问的unVisitedUrl出队列 
	  */  
	 public static String unVisitedUrlDeQueue() {  
	  return unVisited.outQueue();  
	 }  
	 /** 
	  * 保证添加url到unVisitedUrl的时候每个 URL只被访问一次 
	  */  
	 public static void addUnvisitedUrl(String url) {  
	  if (url != null && !url.trim().equals("") && !jedis.sismember(visited,url)
	    && !unVisited.contians(url)){  
	   unVisited.inQueue(url);  
	  }
	 }  
	 /** 
	  * 判断未访问的 URL队列中是否为空 
	  */  
	 public static boolean unVisitedUrlsEmpty() {  
	  return jedis.llen(todoList) == 0;  
	 }  
	 public static void main(String[] args) {
		System.out.println(jedis.scard(todoList) == 0);
	}
}
