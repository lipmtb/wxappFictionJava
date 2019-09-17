package com.wxapp.service;

public interface ReaderLogin {
    public int hasUser(String openId);
    public String login(String openId,String sessionId);
	public String regist(String openId,String sessionId,String nickName,String avatarUrl,String readerGender);
	public int hasReaderByToken(String token);
}
