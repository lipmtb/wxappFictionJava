package com.wxapp.service;
import java.util.List;
import java.util.Set;
import com.wxapp.entity.Fiction;
public interface FictionLoadService {
   public Set<Fiction> getFictionByTypeId(int ftypeId);
   public Object getFictionDetail(int fid);
   
   public List<Object> getGetFictionComment(int fid);
   
   public String getFictionChapter(int fid,int chapterNum);
   
   public Set<Fiction> getReaderHasReadFiction(String token);
   public Set<Fiction> getReaderHasCollectFiction(String token);
   
   public List<Fiction> getGoodFiction(int rank);
   
   public List<Fiction> searchFiction(String keystr);
   
   public List<String> searchTipServiceFn(String keystr);
   
   public List<String> fictionTypeNameListService();
   
   
}
