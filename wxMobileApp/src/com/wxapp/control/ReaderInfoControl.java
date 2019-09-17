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
 * 1.���ض����Ķ�����С˵
 * 2.���ض����ղع���С˵
 * 3.�����Ƿ���޹�ĳС˵
 * 4.�����Ƿ��ղع�ĳС˵
 * 5. �����Ƿ��Ķ���ĳС˵(��)
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
	  * ���ض����Ķ�����С˵
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
	    	System.out.println("���µ�¼��ע��");
	    }
	    
	    resMap.put("state",state);
	    resMap.put("readfictionlist",fictionset);
	    return resMap;
  }
  
  
  /**
   * ���ض����ղع���С˵
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
	    	System.out.println("���µ�¼��ע��");
	    }
	    
	    resMap.put("state",state);
	    resMap.put("collectfictionlist",fictionset);
	    return resMap;
  }
  
  
  /***
   * �����Ƿ���޹�
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
	    	System.out.println("���µ�¼��ע��");
	    }
	    if(haslike!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("haslike",haslike);
	    return resMap;
  }
  
  /**
   * �����Ƿ��ղع�
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
	    	System.out.println("���µ�¼��ע��");
	    }
	    if(hascollect!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("hascollect",hascollect);
	    return resMap;
  }
  
  /**
   * �����Ƿ��Ķ���
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
	    	System.out.println("���µ�¼��ע��");
	    }
	    if(hasread!=null) {
    		state=1;
    	}
	    resMap.put("state",state);
	    resMap.put("hasread",hasread);
	    return resMap;
  }
  
}
