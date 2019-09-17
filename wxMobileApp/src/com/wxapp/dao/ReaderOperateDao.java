package com.wxapp.dao;

public interface ReaderOperateDao {
   public int readerLike(String token,int fid);//读者点赞
   public int readerUnlike(String token,int fid);//读者取消点赞

   public int readerCollect(String token,int fid);//读者收藏
   public int readerUncollect(String token,int fid);//读者取消收藏
   

   public int readerRead(String token,int fid);//读者阅读
   
   /**
    * 读者评论
    * @param token
    * @param fid
    * @param commentContent
    * @param commentTime
    * @return
    */
   public int readerComment(String token,int fid,String commentContent,String commentTime);
   
   /**
    * 读者清除阅读记录
    * @param token
    * @param fid
    * @return
    */
   public int readerClearReadFiction(String token,int fid);
   
}
