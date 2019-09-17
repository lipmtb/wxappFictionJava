package com.wxapp.dao;

public interface ReaderInfoDao {
   public int hasReader(String openId);
   public String updateToken(String openId,String sessionId);
   public String addNewReader(String openId, String sessionId, String nickName, String avatarUrl, String readerGender);
   public int hasReaderByToken(String token);
   
   public int readerHasLike(String token,int fid);
   public int readerHasCollect(String token,int fid);
   public int readerHasReadFiction(String token,int fid);
}
