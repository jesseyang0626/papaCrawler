package com.jesse;

import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisQueue {
		//使用redis
		Jedis queue = new Jedis("localhost");
		String todoList = "todojiaoshou";
		/** 
		  * 将t加入到队列中 
		  */  
		 public void inQueue(String t) {  
		  queue.lpush(todoList, t);  
		 }  
		 /** 
		  * 移除队列中的第一项并将其返回 
		  */  
		 public String outQueue() {  
		  return queue.rpop(todoList);  
		 }  
		 /** 
		  * 返回队列是否为空 
		  */  
		 public boolean isQueueEmpty() {  
		  return (queue.llen(todoList) == 0);  
		 }  
		 /** 
		  * 判断并返回队列是否包含t 
		  */  
		 public synchronized boolean contians(String t) { 
			 boolean flag = false;
			List<String> lists = queue.lrange(todoList,0,500000);
	
			 for(String s:lists){
				 if(s.equals(t)){
					 flag = true;
				 }else{
					 flag =  false;
				 }
			 }
			 return flag;
		 }  
		 /** 
		  * 判断并返回队列是否为空 
		  */  
		 public boolean empty() {  
		  return (queue.llen(todoList) == 0);  
		 }  
		 
		 

}
