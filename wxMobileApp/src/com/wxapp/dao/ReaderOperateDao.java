package com.wxapp.dao;

public interface ReaderOperateDao {
   public int readerLike(String token,int fid);//���ߵ���
   public int readerUnlike(String token,int fid);//����ȡ������

   public int readerCollect(String token,int fid);//�����ղ�
   public int readerUncollect(String token,int fid);//����ȡ���ղ�
   

   public int readerRead(String token,int fid);//�����Ķ�
   
   /**
    * ��������
    * @param token
    * @param fid
    * @param commentContent
    * @param commentTime
    * @return
    */
   public int readerComment(String token,int fid,String commentContent,String commentTime);
   
   /**
    * ��������Ķ���¼
    * @param token
    * @param fid
    * @return
    */
   public int readerClearReadFiction(String token,int fid);
   
}
