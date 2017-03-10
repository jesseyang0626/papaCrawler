package com.jesse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProfessorInfo {
	public static void writeProInfo2File(String name,String content,String filePth){
		if (content!=null) {
			
		 File file = new File(filePth,name+".txt");
		 BufferedWriter bufferWritter = null;
		 try {
			 FileWriter fileWriter = new FileWriter(file);
			 bufferWritter = new BufferedWriter(fileWriter);
			 bufferWritter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bufferWritter!=null)
				bufferWritter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}
	
	public static Map<String, String> extra(String url){
		Document doc;
	    Map<String, String> info = new HashMap<>();
		try {
			doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
					.timeout(5000).get();
			Element divs = doc.select("div.rdtitle").first();
			if (divs!=null){
			info.put("title", divs.text());
			StringBuffer sb = new StringBuffer();
			Element divRcontent = doc.select("div.rcontent").first();
			Elements elem = divRcontent.getAllElements();
			 for(Element element : elem){
				 if (element.tagName().equals("div") && !element.className().equals("rcontent")) {
					 System.out.println(element.text());
				//	 sb.append(element.text().replaceAll("\\?", " ")+"\r\n");
					 sb.append(element.text()+"\r\n");
				}
			info.put("content", sb.toString());
			 }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	public static void main(String[] args) {
		Map<String, String> map = ProfessorInfo.extra("http://www.djtu.edu.cn/list/detail.asp?m=31&tp=2&id=527");
		ProfessorInfo.writeProInfo2File(map.get("title"),map.get("content"), "f:\\");
	}
}
