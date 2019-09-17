package com.wxapp.service;

public interface ReaderDoService {
   public int readerReadLikeCollect(String token,int fid,String etype);
   public int readerCommentFiction(String token,int fid,String commentContent,String commentTime);
}
