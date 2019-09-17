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
 * 读者各项操作:阅读，点赞和收藏，评论
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
	 * 处理读者阅读点赞 和收藏
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
			   System.out.println(etype+"成功");
		   }else {
			   System.out.println(etype+"重复");
		   }
		}else {
			System.out.println("用户未登录，请登录或注册");
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
			   System.out.println("评论成功");
		   }
		}else {
			System.out.println("用户未登录，请登录或注册");
		}
		resMap.put("state",state);
		return resMap;
	}
	
}
