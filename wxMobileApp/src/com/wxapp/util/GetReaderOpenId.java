package com.wxapp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetReaderOpenId {
	private static final long serialVersionUID = 1L;
	private static final String URLBASE="https://api.weixin.qq.com/sns/jscode2session";
	//填写自己的appid和appsecret
//     private static final String APPID="your appid";
//     private static final String APPSECRET="your appsecret";
	  //拼接完整的url
    public static String getCompleteUrl(String jsCode) {
   	  String url=GetReaderOpenId.URLBASE;
   	  url=url+"?appid="+GetReaderOpenId.APPID+"&secret="+GetReaderOpenId.APPSECRET+"&js_code="
   	  +jsCode+"&grant_type=authorization_code";
   	  return url;
    }
    
  //利用code去微信服务器获取openId和sessionId
    public static String getUserSecretInfo(String code) {
   	 BufferedReader br=null;
   	 StringBuffer sb=new StringBuffer();
   	 HttpURLConnection conn=null;
   	String path=getCompleteUrl(code);
   	 try {
   		     
   		    URL url = new URL(path);    // 把字符串转换为URL请求地址
   		    conn = (HttpURLConnection) url.openConnection();// 打开连接
   		    conn.setRequestMethod("GET");
   		    conn.setConnectTimeout(5000);
   		    conn.setDoInput(true);
   		    conn.connect();// 连接会话

   		    // 获取输入流
   		    br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
   		    String line=null;
   		    while((line=br.readLine())!=null) {
   		    	sb.append(line);
   		    	
   		    }
   		} catch (Exception e) {
   		   e.printStackTrace();
   		}finally {
   		
   				try {
   					 if(br!=null) {
						    br.close();
   					 }
   					 if(conn!=null) {
   						 conn.disconnect();
   					 }
					} catch (Exception e) {
						
						e.printStackTrace();
					}
   	      }
   		return sb.toString();
    }
}
