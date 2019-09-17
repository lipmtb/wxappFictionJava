package com.wxapp.dao.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wxapp.dao.FictionLoadingDao;
import com.wxapp.entity.Fiction;
import com.wxapp.entity.FictionChapter;
import com.wxapp.entity.FictionComment;
import com.wxapp.entity.FictionType;
import com.wxapp.entity.Reader;

import net.sf.json.JSONObject;
import com.wxapp.util.GoodFictionAlgorithm;
@Repository("fictionLoadingDao")
public class FictionLoadingDaoImpl implements FictionLoadingDao {
    @Autowired
  SessionFactory sessionFactory;
    
	@Override
	public Set<Fiction> loadingFictionByTypeId(int ftypeId) {
		Session sess=sessionFactory.getCurrentSession();
		FictionType ftype=sess.get(FictionType.class,ftypeId);
		
		return ftype.getFictionSet();
	}

	/**
	 * 小说详情加载小说的信息:小说封面fCoverUrl，小说名fName，小说作者fAuthor，小说简介 fBrief,小说类别名ftypeName
	 * 小说阅读量，小说点赞量，小说收藏量，小说章节数
	 */
	@Override
	public Object loadingFictionByFictionId(int fId) {
		Session sess=sessionFactory.getCurrentSession();
		JSONObject jobj=new JSONObject();
		Fiction fiction=sess.get(Fiction.class,fId);
		jobj.put("fCoverUrl",fiction.getfCoverUrl());
		jobj.put("fName",fiction.getfName());
		jobj.put("fAuthor",fiction.getfAuthor());
		jobj.put("fBrief",fiction.getfBrief());
		jobj.put("ftypeName",fiction.getFtype().getfTypeName());
		int readNum=fiction.getfReadReaderSet().size();
		int likeNum=fiction.getfLikeReaderSet().size();
		int collectNum=fiction.getfCollectReaderSet().size();
		jobj.put("readNum",readNum);
		jobj.put("likeNum",likeNum);
		jobj.put("collectNum",collectNum);
		
		int chapterNum=fiction.getFchapterSet().size();
		jobj.put("chapterNum",chapterNum);
		return jobj;
	}

	/**
	 * 加载小说章节内容
	 */
	@Override
	public String loadingFictionChapter(int fId, int chapterNum) { 
		Session sess=sessionFactory.getCurrentSession();
		Fiction fiction=sess.get(Fiction.class,fId);
	    Set<FictionChapter> fchapterset=fiction.getFchapterSet();
	    Iterator<FictionChapter> it=fchapterset.iterator();
	    String str=null;
	    while(it.hasNext()) {
	    	FictionChapter fchapter=it.next();
	    	if(fchapter.getChapterNum()==chapterNum) {
	    		str=fchapter.getChapterContent();
	    		break;
	    	}
	    }
	    return str;
	}
/**
 * 加载小说评论列表
 */
	@Override
	public List<Object> loadingFictionComment(int fId) {
		Session sess=sessionFactory.getCurrentSession();
		Fiction fiction=sess.get(Fiction.class,fId);
		List<Object> commentList=new ArrayList<Object>();
		Set<FictionComment> fcommentSet=fiction.getfCommentSet();
		
		Iterator<FictionComment> it=fcommentSet.iterator();
		JSONObject jobj=null;
		while(it.hasNext()) {
			
			FictionComment commentItem=it.next();
			jobj=new JSONObject();
		   jobj.put("commentContent",commentItem.getCommentContent());
		   jobj.put("commentTime",commentItem.getCommentTime());
		   jobj.put("nickName",commentItem.getReader().getNickName());
		   jobj.put("avatarUrl",commentItem.getReader().getAvatarUrl());
		   commentList.add(jobj);
		}
		return commentList;
	}

	/**
	 * 加载用户阅读过的小说
	 */
@Override
public Set<Fiction> loadingReaderHasRead(String token) {
	Session sess=sessionFactory.getCurrentSession();
    String sql="from Reader where token=:histoken";
    Query qu=sess.createQuery(sql);
    qu.setString("histoken",token);
    Reader reader=((List<Reader>)qu.list()).get(0);
    Set<Fiction> fictionset=null;
    if(reader!=null) {
    	fictionset=reader.getReadFictionSet();
    }
	return fictionset;
}


/**
 * 加载用户收藏过的小说
 */
	@Override
	public Set<Fiction> loadingReaderHasCollect(String token) {
		Session sess=sessionFactory.getCurrentSession();
	    String sql="from Reader where token=:histoken";
	    Query qu=sess.createQuery(sql);
	    qu.setString("histoken",token);
	    Reader reader=((List<Reader>)qu.list()).get(0);
	    Set<Fiction> fictionset=null;
	    if(reader!=null) {
	    	fictionset=reader.getCollectFictionSet();
	    }
		return fictionset;
	}

	/**
	 * 加载精品小说，按收藏量前rank
	 */
@Override
public List<Fiction> loadingGoodFiction(int rank) {
	Session sess=sessionFactory.getCurrentSession();
	//String sql="from Fiction as f order by f.fCollectReaderSet.size() desc limit 0,3";
	String getAllFictionsql="from Fiction";
	Query qu=sess.createQuery(getAllFictionsql);
	List<Fiction> fictionList=qu.list();
	List<Fiction> res=GoodFictionAlgorithm.sortList(fictionList);
	
	return res.subList(0, rank);
}
/**
 * 用户搜索小说
 */
	@Override
	public List<Fiction> searchFictionByKey(String keystr) {
		Session sess=sessionFactory.getCurrentSession();
		List<Fiction> resList=null;
		/*Criteria c=sess.createCriteria(Fiction.class);
		c.add(Restrictions.like("fBrief",keystr,MatchMode.ANYWHERE));
		resList=c.list();*/
		
		String searchSql="from Fiction as f where f.fBrief like :keystr";
		Query qu=sess.createQuery(searchSql);
		qu.setString("keystr","%"+keystr+"%");
		resList=qu.list();
		return resList;
	}

@Override
public List<String> searchTextTip(String keystr) {
	Session sess=sessionFactory.getCurrentSession();
	List<String> resStrList=new ArrayList<String>();
	String sql="select f.fName,f.fId from Fiction as f where f.fName like :keystr";
	Query query=sess.createQuery(sql);
	query.setString("keystr","%"+keystr+"%");
	List<Object[]> strList=query.list();
    Iterator<Object[]> it=strList.iterator();
    while(it.hasNext()) {
    	Object[] objArr=it.next();
    	resStrList.add((String)objArr[0]);
    }
	return resStrList;
}

/**
 * 获取小说类型名列表
 */
@Override
public List<String> getFictionTypeName() {
	Session sess=sessionFactory.getCurrentSession();
    List<String> typeNameList=new ArrayList<String>();
    String sql="select ft.fTypeName,ft.fTypeId from FictionType as ft";
    Query qu=sess.createQuery(sql);
    List<Object[]> objArrList=qu.list();
   Iterator<Object[]> it=objArrList.iterator();
   while(it.hasNext()) {
	    String typeName=(String) it.next()[0];
	    typeNameList.add(typeName);
   }
	return typeNameList;
}

}
