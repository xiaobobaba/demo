package com.example.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Link Xue 
 * @version 2017年8月20日 上午11:03:13
 * 模拟HTTP请求获取返回值
 */
@Slf4j
public class HttpURLConectionGET {

    //处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"
   	public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
   		StringBuffer buffer=null;
   		try{
   		URL url=new URL(requestUrl);
   		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
   		conn.setDoOutput(true);
   		conn.setDoInput(true);
   		conn.setRequestMethod(requestMethod);
   		conn.connect();
   		//往服务器端写内容 也就是发起http请求需要带的参数
   		if(null!=outputStr){
   			OutputStream os=conn.getOutputStream();
   			os.write(outputStr.getBytes("utf-8"));
   			os.close();
   		}
   		
   		//读取服务器端返回的内容
   		InputStream is=conn.getInputStream();
   		InputStreamReader isr=new InputStreamReader(is,"utf-8");
   		BufferedReader br=new BufferedReader(isr);
   		buffer=new StringBuffer();
   		String line=null;
   		while((line=br.readLine())!=null){
   			buffer.append(line);
   		}
   		log.info("[weixin]: do get request({}), and get response({}).", url, buffer.toString());
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return buffer.toString();
   	}

}