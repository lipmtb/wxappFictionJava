package com.wxapp.dao.daoImpl;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wxapp.dao.ReaderInfoDao;
import com.wxapp.entity.Fiction;
import com.wxapp.entity.Reader;
import com.wxapp.util.CreateToken;
@Repository("readerInfoDao")
public class ReaderInfoDaoImpl implements ReaderInfoDao{
	 @Autowired
	SessionFactory sessionFactory;
	 
	@Override
	public int hasReader(String openId) {
		Session sess=sessionFactory.getCurrentSession();
		String sql="from Reader where openId=:id";
		Query query=sess.createQuery(sql);
		query.setString("id",openId);
		@SuppressWarnings("unchecked")
		List<Reader> readerList=query.list();
		if(readerList.size()>0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 更新用户token
	 */
	@Override
	public String updateToken(String openId, String sessionId) {
		String token=CreateToken.createTokenStr(openId, sessionId);
		Session sess=sessionFactory.getCurrentSession();
		String sql="from Reader where openId=:id";
		Query query=sess.createQuery(sql);
		query.setString("id",openId);
		@SuppressWarnings("unchecked")
		List<Reader> readerList=query.list();
		Reader reader=readerList.get(0);
		reader.setToken(token);
		sess.update(reader);
		return token;
	}
 /**
  * 注册新用户
  */
	@Override
	public String addNewReader(String openId, String sessionId, String nickName, String avatarUrl,
			String readerGender) {
		String token=CreateToken.createTokenStr(openId, sessionId);
		 Reader reader=new Reader(nickName, avatarUrl,readerGender);
		 reader.setOpenId(openId);
		 reader.setToken(token);
		 Session sess=sessionFactory.getCurrentSession();
		 sess.save(reader);
		 
		 return token;
	}
/**
 * 判断用户的登录 状态
 */
	@Override
	public int hasReaderByToken(String token) {
		Session sess=sessionFactory.getCurrentSession();
		String sql="from Reader where token=:tokenid";
		Query query=sess.createQuery(sql);
		query.setString("tokenid",token);
		@SuppressWarnings("unchecked")
		List<Reader> readerList=query.list();
		if(readerList.size()>0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 读者是否点赞过小说
	 * @param token
	 * @param fid
	 * @return
	 */
@Override
public int readerHasLike(String token, int fid) {
    int state=0;
    Session sess=sessionFactory.getCurrentSession();
    String sql="from Reader where token=:tokenid";
	Query query=sess.createQuery(sql);
	query.setString("tokenid",token);
	@SuppressWarnings("unchecked")
	List<Reader> readerList=query.list();
	Reader reader=readerList.get(0);
	Set<Fiction> readerLikeSet=reader.getLikeFictionSet();
	Iterator<Fiction> it=readerLikeSet.iterator();
	while(it.hasNext()) {
		Fiction f=it.next();
		if(f.getfId()==fid) {
			return 1;
		}
	}
	return state;
}

/**
 * 用户是否收藏过小说
 */
@Override
public int readerHasCollect(String token, int fid) {
	int state=0;
	 Session sess=sessionFactory.getCurrentSession();
	    String sql="from Reader where token=:tokenid";
		Query query=sess.createQuery(sql);
		query.setString("tokenid",token);
		@SuppressWarnings("unchecked")
		List<Reader> readerList=query.list();
		if(readerList.size()>0) {
			Reader reader=readerList.get(0);
			
			Set<Fiction> readerCollectSet=reader.getCollectFictionSet();
			Iterator<Fiction> it=readerCollectSet.iterator();
			while(it.hasNext()) {
				Fiction f=it.next();
				if(f.getfId()==fid) {
					return 1;
				}
			}
		}
	  return state;
    }

/**
 * 读者是否阅读过
 */
@Override
public int readerHasReadFiction(String token, int fid) {
	
	int state=0;
	 Session sess=sessionFactory.getCurrentSession();
	    String sql="from Reader where token=:tokenid";
		Query query=sess.createQuery(sql);
		query.setString("tokenid",token);
		@SuppressWarnings("unchecked")
		List<Reader> readerList=query.list();
		if(readerList.size()>0) {
			Reader reader=readerList.get(0);
			
			Set<Fiction> readerHasReadFictionSet=reader.getReadFictionSet();
			Iterator<Fiction> it=readerHasReadFictionSet.iterator();
			while(it.hasNext()) {
				Fiction f=it.next();
				if(f.getfId()==fid) {
					return 1;
				}
			}
		}
	  return state;
}

}
