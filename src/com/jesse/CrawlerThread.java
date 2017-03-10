package com.jesse;

import java.util.Map;
import java.util.Set;

public class CrawlerThread implements Runnable{
	
	private String[] seeds;
	private String pathName;
	
	public CrawlerThread(String[] seeds,String pathName) {
		this.seeds = seeds;
		this.pathName = pathName;
	}
	
	@Override
	public void run() {
		  // 初始化 URL 队列  
		  // 循环条件：待抓取的链接不空且抓取的网页不多于 1000  
		  while (!RedisVisited.unVisitedUrlsEmpty()){
			  	String visitUrl = (String) RedisVisited.unVisitedUrlDeQueue();  
			    if (visitUrl == null)  
				    continue;  
				System.out.println(Thread.currentThread().getName() + "正在爬取:"+visitUrl);
				Map<String , String> info = ProfessorInfo.extra(visitUrl);
				ProfessorInfo.writeProInfo2File(info.get("title"), info.get("content"), pathName);
				// 该 URL 放入已访问的 URL 中  
			    RedisVisited.addVisitedUrl(visitUrl);  
			    // 提取出下载网页中的 URL  
			    //Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);  
			    Set<String> links =JsoupTool.extracLinks(visitUrl);
			    // 新的未访问的 URL 入队  
			    for (String link : links) {  
			    RedisVisited.addUnvisitedUrl(link);  
				   }  
		  }
	}

}
