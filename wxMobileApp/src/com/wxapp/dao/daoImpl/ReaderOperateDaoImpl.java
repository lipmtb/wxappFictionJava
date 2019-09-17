package com.wxapp.dao.daoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wxapp.dao.ReaderOperateDao;
import com.wxapp.entity.Reader;
import com.wxapp.entity.Fiction;
import com.wxapp.entity.FictionComment;

import java.util.List;
@Repository("readerOperateDao")
public class ReaderOperateDaoImpl implements ReaderOperateDao{
    @Autowired
   SessionFactory sessionFactory;
    
    /**
     * 读者点赞
     */
	@Override
	public int readerLike(String token, int fid) {
		int state=0;
	  
		try {
			 String queryStr="from Reader where token=:testtoken";
			Session sess=sessionFactory.getCurrentSession();
			Query qu=sess.createQuery(queryStr);
			qu.setString("testtoken", token);
			List<Reader> readerList=(List<Reader>)(qu.list());
			if(readerList.size()>0) {
				Reader reader=readerList.get(0);
				Fiction fiction=sess.load(Fiction.class,fid);
				reader.getLikeFictionSet().add(fiction);
				state=1;
			}
			
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}
 /**
  * 读者取消点赞
  */
	@Override
	public int readerUnlike(String token, int fid) {
		int state=0;
		try {
			 String queryStr="from Reader where token=:testtoken";
				Session sess=sessionFactory.getCurrentSession();
				Query qu=sess.createQuery(queryStr);
				qu.setString("testtoken", token);
				List<Reader> readerList=(List<Reader>)(qu.list());
				if(readerList.size()>0) {
					Reader reader=readerList.get(0);
					Fiction fiction=sess.load(Fiction.class,fid);
					reader.getLikeFictionSet().remove(fiction);
					state=1;
				}
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}

	/**
	 * 读者收藏
	 */
	@Override
	public int readerCollect(String token, int fid) {
		int state=0;
		try {
			 String queryStr="from Reader where token=:testtoken";
				Session sess=sessionFactory.getCurrentSession();
				Query qu=sess.createQuery(queryStr);
				qu.setString("testtoken", token);
				List<Reader> readerList=(List<Reader>)(qu.list());
				if(readerList.size()>0) {
					Reader reader=readerList.get(0);
					Fiction fiction=sess.load(Fiction.class,fid);
					reader.getCollectFictionSet().add(fiction);
					state=1;
				}
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}

	/**
	 * 读者取消收藏
	 */
	@Override
	public int readerUncollect(String token, int fid) {
		int state=0;
		try {
			 String queryStr="from Reader where token=:testtoken";
				Session sess=sessionFactory.getCurrentSession();
				Query qu=sess.createQuery(queryStr);
				qu.setString("testtoken", token);
				List<Reader> readerList=(List<Reader>)(qu.list());
				if(readerList.size()>0) {
					Reader reader=readerList.get(0);
					Fiction fiction=sess.load(Fiction.class,fid);
					reader.getCollectFictionSet().remove(fiction);
					state=1;
				}
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}

	/**
	 * 读者阅读
	 */
	@Override
	public int readerRead(String token, int fid) {
		int state=0;
		try {
			String queryStr="from Reader where token=:testtoken";
			Session sess=sessionFactory.getCurrentSession();
			Query qu=sess.createQuery(queryStr);
			qu.setString("testtoken", token);
			List<Reader> readerList=(List<Reader>)(qu.list());
			if(readerList.size()>0) {
				Reader reader=readerList.get(0);
				Fiction fiction=sess.load(Fiction.class,fid);
				reader.getReadFictionSet().add(fiction);
				state=1;
			}
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}

	

	/**
	 * 用户评论小说
	 */
	@Override
	public int readerComment(String token, int fid, String commentContent, String commentTime) {
		int state=0;
		try {
			String queryStr="from Reader where token=:testtoken";
			Session sess=sessionFactory.getCurrentSession();
			Query qu=sess.createQuery(queryStr);
			qu.setString("testtoken", token);
			List<Reader> readerList=(List<Reader>)(qu.list());
			if(readerList.size()>0) {
				Reader reader=readerList.get(0);
				Fiction fiction=sess.load(Fiction.class,fid);
			   FictionComment fcomment=new FictionComment(commentContent,commentTime);
			   fcomment.setReader(reader);
			   fcomment.setFiction(fiction);
			   reader.getUserCommentSet().add(fcomment);
			   fiction.getfCommentSet().add(fcomment);
			   sess.save(fcomment);
			   state=1;
			}
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}
	
	@Override
	public int readerClearReadFiction(String token, int fid) {
		int state=0;
		try {
			
		}catch(Exception e) {
			state=0;
			e.printStackTrace();
		}
		
		return state;
	}

}
