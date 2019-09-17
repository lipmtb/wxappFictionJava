package com.wxapp.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wxapp.service.ReaderInfoService;
import com.wxapp.dao.ReaderInfoDao;
@Service("readerInfoService")
@Transactional
public class ReaderInfoServiceImpl implements ReaderInfoService{
  
	 @Autowired
	 ReaderInfoDao readerInfoDao;
	@Override
	public int readerHasLikeService(String token, int fid) {
		
		return readerInfoDao.readerHasLike(token, fid);
	}

	@Override
	public int readerHasCollectService(String token, int fid) {
		
		return readerInfoDao.readerHasCollect(token, fid);
	}

	@Override
	public int readerHasReadFictionService(String token, int fid) {
	
		return readerInfoDao.readerHasReadFiction(token, fid);
	}
  
}
