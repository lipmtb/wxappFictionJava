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
     * 读者阅读点赞收藏
     */
	@Override
	public int readerReadLikeCollect(String token, int fid, String etype) {
		int state=0;
		
		switch(etype) {
			case "read":{
				if(readerInfoDao.readerHasReadFiction(token, fid)==0){
					state=readerOperateDao.readerRead(token, fid);
					System.out.println("读者阅读");
				}
				
				break;
			}
			case "like":{
				if(readerInfoDao.readerHasLike(token, fid)==0) {
					state=readerOperateDao.readerLike(token, fid);
					System.out.println("读者点赞");
				}
				break;
			}
			case "unlike":{
				if(readerInfoDao.readerHasLike(token, fid)==1) {
					state=readerOperateDao.readerUnlike(token, fid);
					System.out.println("读者取消点赞");
				}
				break;
			}
			case "collect":{
				if(readerInfoDao.readerHasCollect(token, fid)==0) {
					state=readerOperateDao.readerCollect(token, fid);
					System.out.println("读者收藏");
				}
				
				break;
			}
			case "uncollect":{
				if(readerInfoDao.readerHasCollect(token, fid)==1) {
					state=readerOperateDao.readerUncollect(token, fid);
					System.out.println("读者取消收藏");
				}
				break;
			}
		}
		return state;
	}
	
	/**
	 * 读者评论小说
	 */
	@Override
	public int readerCommentFiction(String token, int fid, String commentContent, String commentTime) {
		
		return readerOperateDao.readerComment(token, fid, commentContent, commentTime);
	}

}
