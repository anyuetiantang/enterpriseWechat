package com.enterpriseWechat.test;

import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class HttpUtils {
	
	public static HttpResponse httpPostWithJson(String url, String data){
	    boolean isSuccess = false;
	    
	    HttpPost post = null;
	    try {
	        HttpClient httpClient = new DefaultHttpClient();

	        // 设置超时时间
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
	            
	        post = new HttpPost(url);
	        // 构造消息头
	        post.setHeader("Content-type", "application/json; charset=utf-8");
//	        post.setHeader("Connection", "Close");
//	        String sessionId = getSessionId();
//	        post.setHeader("SessionId", sessionId);
//	        post.setHeader("appid", appId);
	                    
	        // 构建消息实体
	        StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
	        entity.setContentType("application/json");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);
	            
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode != HttpStatus.SC_OK){
	            System.out.println("请求出错: "+statusCode);
	            throw new Exception("请求出错");
	        }else{
	           return response;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        isSuccess = false;
	    }
		return null;
	}
	
//	// 构建唯一会话Id
//	public static String getSessionId(){
//	    UUID uuid = UUID.randomUUID();
//	    String str = uuid.toString();
//	    return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
//	}
}
