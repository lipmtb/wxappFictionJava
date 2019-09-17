package com.wxapp.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxapp.service.ReaderDoService;
import com.wxapp.service.ReaderLogin;

import java.util.Map;
import java.util.HashMap;
/**
 * ���߸������:�Ķ������޺��ղأ�����
 * @author li
 *
 */
@Controller
@RequestMapping("/readerdo")
public class ReaderOperateControl {
     
	@Autowired
	ReaderDoService readerDoService;
	
	@Autowired
	ReaderLogin readerLoginService;
	
	/**
	 * ��������Ķ����� ���ղ�
	 * @author li
	 */
	@RequestMapping("/readcollectlike")
	@ResponseBody
	public Map<String,Integer> handlerReaderReadLikeOrCollect(@RequestParam(value="token") String token,
			   @RequestParam(value="etype") String etype,
			   @RequestParam(value="fid") int fid) {
		
		Map<String,Integer> resMap=new HashMap<String,Integer>();
		Integer state=0;
		if(readerLoginService.hasReaderByToken(token)==1) {
		   state=readerDoService.readerReadLikeCollect(token, fid, etype);
		   if(state==1) {
			   System.out.println(etype+"�ɹ�");
		   }else {
			   System.out.println(etype+"�ظ�");
		   }
		}else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
		resMap.put("state",state);
		return resMap;
	}
	
	@RequestMapping("/readercomment")
	@ResponseBody
	public Map<String,Integer> handleReaderComment(
			@RequestParam(value="token") String token,
			   @RequestParam(value="fid") int fid,
			   @RequestParam(value="commentContent") String commentContent,
			   @RequestParam(value="commentTime") String commentTime) {
		Map<String,Integer> resMap=new HashMap<String,Integer>();
		
		Integer state=0;
		if(readerLoginService.hasReaderByToken(token)==1) {
		   state=readerDoService.readerCommentFiction(token, fid, commentContent, commentTime);
		   if(state==1) {
			   System.out.println("���۳ɹ�");
		   }
		}else {
			System.out.println("�û�δ��¼�����¼��ע��");
		}
		resMap.put("state",state);
		return resMap;
	}
	
}
