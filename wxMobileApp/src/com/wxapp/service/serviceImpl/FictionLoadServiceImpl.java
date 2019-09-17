package com.wxapp.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxapp.dao.FictionLoadingDao;
import com.wxapp.entity.Fiction;
import com.wxapp.service.FictionLoadService;
@Service("fictionLoadService")
@Transactional
public class FictionLoadServiceImpl implements FictionLoadService {
   @Autowired
   FictionLoadingDao fictionLoadingDao;
   
	@Override
	public Set<Fiction> getFictionByTypeId(int ftypeId) {
		
		return fictionLoadingDao.loadingFictionByTypeId(ftypeId);
	}

	@Override
	public Object getFictionDetail(int fid) {
		
		return fictionLoadingDao.loadingFictionByFictionId(fid);
	}

	@Override
	public List<Object> getGetFictionComment(int fid) {
		
		return fictionLoadingDao.loadingFictionComment(fid);
	}

	@Override
	public String getFictionChapter(int fid, int chapterNum) {
		
		return fictionLoadingDao.loadingFictionChapter(fid, chapterNum);
	}

	@Override
	public Set<Fiction> getReaderHasReadFiction(String token) {
		
		return fictionLoadingDao.loadingReaderHasRead(token);
	}

	@Override
	public Set<Fiction> getReaderHasCollectFiction(String token) {
		
		return fictionLoadingDao.loadingReaderHasCollect(token);
	}

	@Override
	public List<Fiction> getGoodFiction(int rank) {
		
		return fictionLoadingDao.loadingGoodFiction(rank);
	}

	@Override
	public List<Fiction> searchFiction(String keystr) {
		
		return fictionLoadingDao.searchFictionByKey(keystr);
	}

	@Override
	public List<String> searchTipServiceFn(String keystr) {
		
		return fictionLoadingDao.searchTextTip(keystr);
	}

	@Override
	public List<String> fictionTypeNameListService() {
		
		return fictionLoadingDao.getFictionTypeName();
	}

}
