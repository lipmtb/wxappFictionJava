package com.wxapp.service;

public interface ReaderInfoService {
	public int readerHasLikeService(String token,int fid);
	 public int readerHasCollectService(String token,int fid);
	 public int readerHasReadFictionService(String token,int fid);
}
