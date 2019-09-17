package com.wxapp.dao;
import java.util.List;
import java.util.Set;
import com.wxapp.entity.Fiction;
import com.wxapp.entity.FictionComment;
public interface FictionLoadingDao {
   public Set<Fiction> loadingFictionByTypeId(int ftypeId);
   public Object loadingFictionByFictionId(int fId);
   
   public String loadingFictionChapter(int fId,int chapterNum);
   
   public List<Object> loadingFictionComment(int fId);
   
   public Set<Fiction> loadingReaderHasRead(String token);
   public Set<Fiction> loadingReaderHasCollect(String token);
   
   public List<Fiction> loadingGoodFiction(int rank);
   
   public List<Fiction> searchFictionByKey(String keystr);
   
   public List<String> searchTextTip(String keystr);
   
   public List<String> getFictionTypeName();
   
}
