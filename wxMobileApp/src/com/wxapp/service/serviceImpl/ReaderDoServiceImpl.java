package com.wxapp.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxapp.dao.ReaderInfoDao;
import com.wxapp.dao.ReaderOperateDao;
import com.wxapp.service.ReaderDoService;
@Service("readerDoService")
@Transactional
public class ReaderDoServiceImpl implements ReaderDoService{
    @Autowired
    ReaderOperateDao readerOperateDao;
    
    @Autowired
    ReaderInfoDao readerInfoDao;
    /**
     * �����Ķ������ղ�
     */
	@Override
	public int readerReadLikeCollect(String token, int fid, String etype) {
		int state=0;
		
		switch(etype) {
			case "read":{
				if(readerInfoDao.readerHasReadFiction(token, fid)==0){
					state=readerOperateDao.readerRead(token, fid);
					System.out.println("�����Ķ�");
				}
				
				break;
			}
			case "like":{
				if(readerInfoDao.readerHasLike(token, fid)==0) {
					state=readerOperateDao.readerLike(token, fid);
					System.out.println("���ߵ���");
				}
				break;
			}
			case "unlike":{
				if(readerInfoDao.readerHasLike(token, fid)==1) {
					state=readerOperateDao.readerUnlike(token, fid);
					System.out.println("����ȡ������");
				}
				break;
			}
			case "collect":{
				if(readerInfoDao.readerHasCollect(token, fid)==0) {
					state=readerOperateDao.readerCollect(token, fid);
					System.out.println("�����ղ�");
				}
				
				break;
			}
			case "uncollect":{
				if(readerInfoDao.readerHasCollect(token, fid)==1) {
					state=readerOperateDao.readerUncollect(token, fid);
					System.out.println("����ȡ���ղ�");
				}
				break;
			}
		}
		return state;
	}
	
	/**
	 * ��������С˵
	 */
	@Override
	public int readerCommentFiction(String token, int fid, String commentContent, String commentTime) {
		
		return readerOperateDao.readerComment(token, fid, commentContent, commentTime);
	}

}
