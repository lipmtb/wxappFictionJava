package com.wxapp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetReaderOpenId {
	private static final long serialVersionUID = 1L;
	private static final String URLBASE="https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID="wx8ba275ed8b28009c";
    private static final String APPSECRET="7db5321d779843db4e8c601bc160413d";
	  //ƴ��������url
    public static String getCompleteUrl(String jsCode) {
   	  String url=GetReaderOpenId.URLBASE;
   	  url=url+"?appid="+GetReaderOpenId.APPID+"&secret="+GetReaderOpenId.APPSECRET+"&js_code="
   	  +jsCode+"&grant_type=authorization_code";
   	  return url;
    }
    
  //����codeȥ΢�ŷ�������ȡopenId��sessionId
    public static String getUserSecretInfo(String code) {
   	 BufferedReader br=null;
   	 StringBuffer sb=new StringBuffer();
   	 HttpURLConnection conn=null;
   	String path=getCompleteUrl(code);
   	 try {
   		     
   		    URL url = new URL(path);    // ���ַ���ת��ΪURL�����ַ
   		    conn = (HttpURLConnection) url.openConnection();// ������
   		    conn.setRequestMethod("GET");
   		    conn.setConnectTimeout(5000);
   		    conn.setDoInput(true);
   		    conn.connect();// ���ӻỰ

   		    // ��ȡ������
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
