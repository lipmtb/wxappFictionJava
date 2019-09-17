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
 * 1.ͨ������id�������С˵�б�
 * 2.����С˵����
 * 3.����С˵����
 * 4.����С˵�½�����
 * 5.���ؾ�ƷС˵
 * 6.��������С˵
 * 7.����������ʾ (��bindinput��Ӧ)
 * 8.����С˵������б�
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
    * ͨ������id�������С˵
    * @param token
    * @param ftypeid
    * @return
    */
   @RequestMapping("/fictiontype")
   @ResponseBody
   public Map<String,Object> loadingFictionByType(@RequestParam(value="token") String token,
		   @RequestParam(value="ftypeid") int ftypeid){
	   System.out.println("ftypeid:"+ftypeid);
	   //�ж��û���¼״̬
	   Set<Fiction> fictionSet=null;
	   Integer state=0;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   System.out.println("�û����ͨ����֤");
		fictionSet=fictionLoadService.getFictionByTypeId(ftypeid);
			if(fictionSet.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionSet);
	   resMap.put("state",state);
	  return resMap;
   }
   /**
    * ����С˵����
    * @param token
    * @param fid
    * @return
    */
   @RequestMapping("/fiction")
   @ResponseBody
   public Map<String,Object> loadingFictionByFictionId(@RequestParam(value="token") String token,
		   @RequestParam(value="fid") int fid){
	   //�ж��û���¼״̬
	   Integer state=0;
	   Object fictionjson=null;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionjson=fictionLoadService.getFictionDetail(fid);
		   if(fictionjson!=null) {
			   state=1;
		   }
	   }else {
		   
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fiction",fictionjson);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   
   /**
    * ����С˵����
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
	 //�ж��û���¼״̬
	   if(readerLoginService.hasReaderByToken(token)==1) {
		  commentList=fictionLoadService.getGetFictionComment(fid);
		  if(commentList!=null) {
			  System.out.println("��ȡ���۳ɹ�");
			  state=1;
		  }
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("commentList",commentList);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   /**
    * ����С˵�½�����
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
	   //�ж��û���¼״̬
	   Integer state=0;
	   String str=null;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		 str=fictionLoadService.getFictionChapter(fid, chapterNum);
		 if(str!=null&&str.length()>0) {
			 state=1;
		 }
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("chapterContent",str);
	   resMap.put("state",state);
	  return resMap;
	   
   }
   
   /**
    * ���ؾ�ƷС˵
    * @param token
    * @return
    */
   @RequestMapping("/goodfiction")
   @ResponseBody
   public Map<String,Object> loadingGoodFiction(@RequestParam(value="token") String token){
	   //�ж��û���¼״̬
	   List<Fiction> fictionlist=null;
	   Integer state=0;
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionlist=fictionLoadService.getGoodFiction(2);
			if(fictionlist.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionlist);
	   resMap.put("state",state);
	  return resMap;
   }
   
   /**
    *  ��������С˵
    * @param token
    * @param keystr
    * @return
    */
   @RequestMapping("/searchfiction")
   @ResponseBody
   public Map<String,Object> searchFictionFn(@RequestParam(value="token") String token,@RequestParam(value="keystr") String keystr){
	  
	   List<Fiction> fictionlist=null;
	   Integer state=0;
	   //�ж��û���¼״̬
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   fictionlist=fictionLoadService.searchFiction(keystr);
		   System.out.println(fictionlist);
			if(fictionlist.size()>0) {
				state=1;
			}	
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
	   Map<String,Object> resMap=new HashMap<String,Object>();
	   resMap.put("fictionlist",fictionlist);
	   resMap.put("state",state);
	  return resMap;
   }
   
   /**
    * ����������ʾ
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
    * ����С˵������б�
    */ 
   @RequestMapping("/ftypeName")
   @ResponseBody
   public Map<String,Object> getFictionTypeName(@RequestParam(value="token") String token){
	  
	   List<String> typeNameList=null;
	   Integer state=0;
	   //�ж��û���¼״̬
	   if(readerLoginService.hasReaderByToken(token)==1) {
		   typeNameList=fictionLoadService.fictionTypeNameListService();
		   System.out.println(typeNameList);
			
	   }else {
			System.out.println("�û�δ��¼�����¼��ע��");
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
