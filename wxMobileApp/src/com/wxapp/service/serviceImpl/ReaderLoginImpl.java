package com.wxapp.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxapp.dao.ReaderInfoDao;
import com.wxapp.service.ReaderLogin;
@Service("readerLoginService")
@Transactional
public class ReaderLoginImpl implements ReaderLogin {

    @Autowired
    ReaderInfoDao readerInfoDao;
	@Override
	public int hasUser(String openId) {
	   
		return readerInfoDao.hasReader(openId);
	}

	@Override
	public String login(String openId, String sessionId) {
	
		return readerInfoDao.updateToken(openId,sessionId);
	}

	@Override
	public String regist(String openId, String sessionId, String nickName, String avatarUrl, String readerGender) {
	   
		return readerInfoDao.addNewReader(openId, sessionId, nickName, avatarUrl, readerGender);
	}

	@Override
	public int hasReaderByToken(String token) {
		
	  return readerInfoDao.hasReaderByToken(token);
	}

}
