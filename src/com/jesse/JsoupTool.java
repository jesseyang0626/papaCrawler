package com.jesse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTool {
	
	//抽取适合条件的链接
	public static Set<String> extracLinks(String url){
	//新建一个链接集合
	Set<String> links = new HashSet<String>();  
   // 确保在当前网站内，过滤链接
   LinkFilter linkFilter = new LinkFilter() {
	@Override
	public boolean accept(String url) {
			if(url.contains("http://www.djtu.edu.cn/list/detail.asp?m=31&tp=2&id") && url.contains("http")){
				return true;
			}else
			return false;
		}
	  };
	  
	try {
		Document doc = Jsoup.connect(url)
						.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
						.timeout(5000).get();
		Elements hrefs = doc.select("a[href]");
		for(Element elem:hrefs){
//			System.out.println(elem.attr("abs:href"));
			if (linkFilter.accept(elem.attr("abs:href"))) {
				links.add(elem.attr("abs:href"));
			}
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e){
		e.printStackTrace();
	}
	return links;
	}

}
