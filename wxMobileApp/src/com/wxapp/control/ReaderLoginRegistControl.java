package com.wxapp.control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wxapp.service.ReaderLogin;
import com.wxapp.util.*;
import net.sf.json.JSONObject;
/**
 * ÓÃ»§µÇÂ¼×¢²á¿ØÖÆ
 * @author li
 *
 */
@Controller
@RequestMapping("/readerLogin")
public class ReaderLoginRegistControl {
	@Autowired
	ReaderLogin readerLoginService;
    
    @RequestMapping("/login")
    @ResponseBody
	public Map<String,Object> loginOrRegist(@RequestParam(value="code") String code,
	@RequestParam(value="nickName") String nickName,
	@RequestParam(value="avatarUrl") String avatarUrl,
	@RequestParam(value="readerGender") String readerGender,
	HttpSession session,HttpServletRequest request,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin","http://192.168.30.12:8080");//¿çÓòÇëÇó
        Map<String,Object> res=new HashMap<String,Object>();
    	String token=null;
        Integer state=0;
		System.out.println("code:"+code);
		String secretStr=GetReaderOpenId.getUserSecretInfo(code);
		System.out.println(secretStr);
		JSONObject jobj=JSONObject.fromObject(secretStr);
		
		String openId=(String)jobj.getString("openid");
		if(openId!=null&&openId.length()>0) {
			String sessionId=session.getId();
			System.out.println("openId:"+openId);
			System.out.println("sessionId:"+sessionId);
			if(readerLoginService.hasUser(openId)==1) {
				System.out.println("µÇÂ¼");
				token=readerLoginService.login(openId,sessionId);
			}else {
				System.out.println("×¢²á");
				token=readerLoginService.regist(openId,sessionId,nickName,avatarUrl,readerGender);
			}
			state=1;
		}else {
			System.out.println("»ñÈ¡openIdÊ§°Ü");
		}
		
		
		res.put("token",token);
		res.put("state",state);
		return res;
		
	}
}
