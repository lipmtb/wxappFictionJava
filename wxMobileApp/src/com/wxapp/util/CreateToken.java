package com.wxapp.util;

public class CreateToken {
   public static String createTokenStr(String openId,String sessionId) {
	   
	   String token=null;
	   token=openId+sessionId;
	   return token;
	   
   }
}
