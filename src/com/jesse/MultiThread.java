package com.jesse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiThread {
	/** 
	  * 使用种子初始化URL队列 
	  */  
	 private void initCrawlerWithSeeds(String[] seeds) {  
	  for (int i = 0; i < seeds.length; i++)  
	   RedisVisited.addUnvisitedUrl(seeds[i]);  
	 }  
	public void createThread(int i,String[] seeds,String pathName) throws InterruptedException{
		
		initCrawlerWithSeeds(seeds);
		List<Thread> lists = new ArrayList<>();
		for(int j=0;j<i;j++){
			Thread thread = new Thread(new CrawlerThread(seeds, pathName),"爬虫" + j + "号");
			lists.add(thread);
		}
		for(int k=0;k<i;k++){
			lists.get(k).start();
			lists.get(k).join(5000);
			System.out.println("线程"+k+"启动了");
		}
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		MultiThread multiThread = new MultiThread();
		multiThread.createThread(3, new String[] { "http://www.djtu.edu.cn/list/index.asp?m=31" },"f://交大教授");
	}
}
