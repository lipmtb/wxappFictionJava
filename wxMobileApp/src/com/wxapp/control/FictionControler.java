package com.wxapp.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxapp.service.FictionLoadService;
import com.wxapp.service.ReaderLogin;
import com.wxapp.entity.Fiction;
/**
 * 1.通过分类id加载相关小说列表
 * 2.加载小说详情
 * 3.加载小说评论
 * 4.加载小说章节内容
 * 5.加载精品小说
 * 6.读者搜索小说
 * 7.读者搜索提示 (与bindinput响应)
 * 8.加载小说类别名列表
 * @author li
 *
 */
@Controller
@RequestMapping("/loadingfiction")
public class FictionControler {
   @Autowired
   FictionLoadService fictionLoadService;
   
   @Autowired
   ReaderLogin readerLoginService;
   
   /**
    * 通过分类id加载相关小说
    * @param token
    * @param ftypeid
    * @return
    */
   @RequestMapping("/fictiontype")
   @ResponseBody
   public Map<String,Object> loadingFictionByType(@RequestParam(value="token") String token,
		   @RequestParam(value="ftypeid") int ftypeid){
	   System.out.println("ftypeid:"+ftypeid);
	   //判断用户登录状态
	   Set<Fiction> fictionSet=null;
	   Integer state=0;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   System.out.println("用户身份通过验证");
		fictionSet=fictionLoadService.getFictionByTypeId(ftypeid);
			if(fictionSet.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionSet);
	   resMap.put("state",state);
	  return resMap;
   }
   /**
    * 加载小说详情
    * @param token
    * @param fid
    * @return
    */
   @RequestMapping("/fiction")
   @ResponseBody
   public Map<String,Object> loadingFictionByFictionId(@RequestParam(value="token") String token,
		   @RequestParam(value="fid") int fid){
	   //判断用户登录状态
	   Integer state=0;
	   Object fictionjson=null;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionjson=fictionLoadService.getFictionDetail(fid);
		   if(fictionjson!=null) {
			   state=1;
		   }
	   }else {
		   
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fiction",fictionjson);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   
   /**
    * 加载小说评论
    * @param token
    * @param fid
    * @return
    */
   @RequestMapping("/fictioncommentlist")
   @ResponseBody
   public Map<String,Object> loadingFictionComment(@RequestParam(value="token") String token,
		   @RequestParam(value="fid") int fid){
	   
	   Integer state=0;
	   List<Object> commentList=null;
	 //判断用户登录状态
	   if(readerLoginService.hasReaderByToken(token)==1) {
		  commentList=fictionLoadService.getGetFictionComment(fid);
		  if(commentList!=null) {
			  System.out.println("获取评论成功");
			  state=1;
		  }
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("commentList",commentList);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   /**
    * 加载小说章节内容
    * @param token
    * @param fid
    * @param chapterNum
    * @return
    */
   @RequestMapping("/fictionchapter")
   @ResponseBody
   public Map<String,Object> loadingFictionChapter(@RequestParam(value="token") String token,
		   @RequestParam(value="fid") int fid,
		   @RequestParam(value="fchapterNum") int chapterNum){
	   //判断用户登录状态
	   Integer state=0;
	   String str=null;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		 str=fictionLoadService.getFictionChapter(fid, chapterNum);
		 if(str!=null&&str.length()>0) {
			 state=1;
		 }
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("chapterContent",str);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   
   /**
    * 加载精品小说
    * @param token
    * @return
    */
   @RequestMapping("/goodfiction")
   @ResponseBody
   public Map<String,Object> loadingGoodFiction(@RequestParam(value="token") String token){
	   //判断用户登录状态
	   List<Fiction> fictionlist=null;
	   Integer state=0;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionlist=fictionLoadService.getGoodFiction(2);
			if(fictionlist.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionlist);
	   resMap.put("state",state);
	  return resMap;
   }
   
   /**
    *  读者搜索小说
    * @param token
    * @param keystr
    * @return
    */
   @RequestMapping("/searchfiction")
   @ResponseBody
   public Map<String,Object> searchFictionFn(@RequestParam(value="token") String token,@RequestParam(value="keystr") String keystr){
	  
	   List<Fiction> fictionlist=null;
	   Integer state=0;
	   //判断用户登录状态
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionlist=fictionLoadService.searchFiction(keystr);
		   System.out.println(fictionlist);
			if(fictionlist.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionlist);
	   resMap.put("state",state);
	  return resMap;
   }
   
   /**
    * 读者搜索提示
    * @param keystr
    * @return 
    */
   @RequestMapping("/searchtip")
   @ResponseBody
   public Map<String,Object> searchFictionTextTip(@RequestParam(value="keystr") String keystr){
	  
	   List<String> textlist=null;
	   Integer state=0;
	  
	  textlist=fictionLoadService.searchTipServiceFn(keystr);
	  System.out.println(textlist);
			
	   
	   if(textlist!=null) {
			state=1;
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("textlist",textlist);
	   resMap.put("state",state);
	  return resMap;
   }
   
   
   /**
    * 加载小说类别名列表
    */ 
   @RequestMapping("/ftypeName")
   @ResponseBody
   public Map<String,Object> getFictionTypeName(@RequestParam(value="token") String token){
	  
	   List<String> typeNameList=null;
	   Integer state=0;
	   //判断用户登录状态
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   typeNameList=fictionLoadService.fictionTypeNameListService();
		   System.out.println(typeNameList);
			
	   }else {
			System.out.println("用户未登录，请登录或注册");
		}
	   if(typeNameList!=null&&typeNameList.size()>0) {
			state=1;
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("typeNameList",typeNameList);
	   resMap.put("state",state);
	  return resMap;
   }
   
   
}
