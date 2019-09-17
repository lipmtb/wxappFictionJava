package com.wxapp.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxapp.entity.Fiction;
import com.wxapp.service.FictionLoadService;
import com.wxapp.service.ReaderInfoService;
import com.wxapp.service.ReaderLogin;
/**
 * 1.加载读者阅读过的小说
 * 2.加载读者收藏过的小说
 * 3.读者是否点赞过某小说
 * 4.读者是否收藏过某小说
 * 5. 读者是否阅读过某小说(暂)
 * @author li
 *
 */
@Controller
@RequestMapping("/readerStorage")
public class ReaderInfoControl {
	 @Autowired
	 ReaderLogin readerLoginService;
	 
	 @Autowired
	 FictionLoadService fictionLoadService;
	
	 @Autowired 
	 ReaderInfoService readerInfoService;
	 /**
	  * 加载读者阅读过的小说
	  * @param token
	  * @return
	  */
  @RequestMapping("/readerread")
  @ResponseBody
  public Map<String,Object> loadingReaderHasRead(@RequestParam(value="token") String token){
	    Map<String,Object> resMap=new HashMap<String,Object>();
	    Integer state=0;
	    
	    Set<Fiction> fictionset=null;
	    if(readerLoginService.hasReaderByToken(token)==1) {
	    	fictionset=fictionLoadService.getReaderHasReadFiction(token);
	    	if(fictionset!=null) {
	    		state=1;
	    	}
	    }else {
	    	System.out.println("重新登录或注册");
	    }
	    
	    resMap.put("state",state);
	    resMap.put("readfictionlist",fictionset);
	    return resMap;
  }
  
  
  /**
   * 加载读者收藏过的小说
   * @param token
   * @return
   */
  @RequestMapping("/readercollect")
  @ResponseBody
  public Map<String,Object> loadingReaderHasCollect(@RequestParam(value="token") String token){
	    Map<String,Object> resMap=new HashMap<String,Object>();
	    Integer state=0;
	    
	    Set<Fiction> fictionset=null;
	    if(readerLoginService.hasReaderByToken(token)==1) {
	    	fictionset=fictionLoadService.getReaderHasCollectFiction(token);
	    	if(fictionset!=null) {
	    		state=1;
	    	}
	    }else {
	    	System.out.println("重新登录或注册");
	    }
	    
	    resMap.put("state",state);
	    resMap.put("collectfictionlist",fictionset);
	    return resMap;
  }
  
  
  /***
   * 读者是否点赞过
   * @param token
   * @param fid
   * @return
   */
  @RequestMapping("/haslike")
  @ResponseBody
  public Map<String,Object> ReaderHasLike(@RequestParam(value="token") String token,
		  @RequestParam(value="fid") int fid){
	    Map<String,Object> resMap=new HashMap<String,Object>();
	    Integer state=0;
	    Integer haslike=null;
	    if(readerLoginService.hasReaderByToken(token)==1) {
	    	
	    	haslike=readerInfoService.readerHasLikeService(token, fid);
	    	
	    }else {
	    	System.out.println("重新登录或注册");
	    }
	    if(haslike!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("haslike",haslike);
	    return resMap;
  }
  
  /**
   * 读者是否收藏过
   * @param token
   * @param fid
   * @return
   */
  @RequestMapping("/hascollect")
  @ResponseBody
  public Map<String,Object> ReaderHasCollect(@RequestParam(value="token") String token,
		  @RequestParam(value="fid") int fid){
	    Map<String,Object> resMap=new HashMap<String,Object>();
	    Integer state=0;
	    Integer hascollect=null;
	    if(readerLoginService.hasReaderByToken(token)==1) {
	    	
	    	hascollect=readerInfoService.readerHasCollectService(token, fid);
	    	
	    }else {
	    	System.out.println("重新登录或注册");
	    }
	    if(hascollect!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("hascollect",hascollect);
	    return resMap;
  }
  
  /**
   * 读者是否阅读过
   * @param token
   * @param fid
   * @return
   */
  @RequestMapping("/hasreadfiction")
  @ResponseBody
  public Map<String,Object> ReaderHasReadFn(@RequestParam(value="token") String token,
		  @RequestParam(value="fid") int fid){
	    Map<String,Object> resMap=new HashMap<String,Object>();
	    Integer state=0;
	    Integer hasread=null;
	    if(readerLoginService.hasReaderByToken(token)==1) {
	    	
	    	hasread=readerInfoService.readerHasReadFictionService(token, fid);
	    	
	    }else {
	    	System.out.println("重新登录或注册");
	    }
	    if(hasread!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("hasread",hasread);
	    return resMap;
  }
  
}
