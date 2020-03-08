package com.enterpriseWechat.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.enterpriseWechat.utils.WXBizMsgCrypt;

@Controller
public class EnterpriseWechatController {
	String sToken = "XpVqOD6HTFlIdBDQHwnE7FG2Oj6";
	String sEncodingAESKey = "XZN6NG7WRZTsiOdjxV1KdlfmT9JYmLAe2bNPKn8gYN3";
	String suiteid = "ww7a9f103f24745de4";
	String corpid = "wwe97a267d84e44d6d";

	// 此接口暂无作用
    @RequestMapping(value = "/testPage")
    public String testPage() throws Exception {
		return "index.html";
	}
	
	// 此接口暂无作用
	@ResponseBody
    @RequestMapping(value = "/businessCallback")
    public void businessCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------------------");
    	System.out.println("this is businessCallback");
    	
//    	InputStream ins = request.getInputStream();
//    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ins));
//    	StringBuilder postData = new StringBuilder();
//    	String line = null;
//    	while((line=bufferedReader.readLine()) != null) {
//    		postData.append(line);
//    	}
//    	
//		// 解析xml，若InfoType是suite_ticket的话，说明是发送ticket的响应方式
//    	Document doc = DocumentHelper.parseText(postData.toString());
//    	Element root = doc.getRootElement();
//    	String infoType = root.elementTextTrim("InfoType");
//    	if("suite_ticket".equals(infoType)) {
//    		String suiteTicket = root.elementTextTrim("SuiteTicket");
//    		System.out.println("拿到的ticket是: " + suiteTicket);
//    	}
//    	
//    	String echostr = request.getParameter("echostr");
//    	System.out.println("echostr:" + echostr);
//        System.out.println("Encrypt: "+ transInfo.getEncrypt());
//        System.out.println("AgentID: "+ transInfo.getAgentID());
//        System.out.println("ToUserName: "+ transInfo.getToUserName());
    	
        System.out.println("----------------------------");
        
        
    	response.setCharacterEncoding("UTF-8");
    	response.getWriter().write("success");
    }
    
	@ResponseBody
    @RequestMapping(value = "/dataCallback")
    public void dataCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------------------");
    	System.out.println("this is dataCallback");
    	response.setCharacterEncoding("UTF-8");
    	
    	try {
//	    	String corpid = request.getParameter("corpid");
//	    	if(StringUtils.isEmpty(corpid)) {
//	    		corpid = this.corpid;
//	    	}
//	    	System.out.println("corpid:" + corpid);
//	    	// 企业微信加密签名
//	        String msgSignature = request.getParameter("msg_signature");
//	        // 时间戳 与nonce结合使用，用于防止请求重放攻击
//	        String timestamp = request.getParameter("timestamp");
//	        // 校验时字符串
//	        String echostr = request.getParameter("echostr");
//	        // 随机数 与timestamp结合使用，用于防止请求重放攻击
//	        String nonce = request.getParameter("nonce");
	        
    		String corpid = "wwe97a267d84e44d6d";
    		String msgSignature = "43559a47f0077705dea5330c6039e3d6846017e9";
    		String timestamp = "1583652654";
	        String echostr = "FGM5DG6Ton0eBPGxS15dvl89bKyPrxcWDaIMC2hiBw2U0jAEaHGcLyfUOScOPALTGdWj1pqSujzn7iZDk+KT/w==";
	        String nonce = "1584042257";
    		
	        System.out.println("msgSignature: "+ msgSignature);
	        System.out.println("timestamp: "+ timestamp);
	        System.out.println("echostr: "+ echostr);
	        System.out.println("nonce: "+ nonce);
	    	if("GET".equals(request.getMethod())) { // get请求表示是验证
		        String echostrDecrypt = null;
		        // 校验服务商公司id
		        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, corpid);
				echostrDecrypt = wxcpt.VerifyURL(msgSignature, timestamp, nonce, echostr);
				System.out.println("verifyurl echostr: " + echostrDecrypt);
		    	
		    	response.getWriter().write(echostrDecrypt);
	    	}else { // post请求表示是真实数据
	    		// 获取传过来的xml信息（密文）
	        	InputStream ins = request.getInputStream();
	        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ins));
	        	StringBuilder postData = new StringBuilder();
	        	String line = null;
	        	while((line=bufferedReader.readLine()) != null) {
	        		postData.append(line);
	        	}
	        	System.out.println("postData:"+ postData);
	    		
	    		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, suiteid);
	    		String info = wxcpt.DecryptMsg(msgSignature, timestamp, nonce, postData.toString());
	    		System.out.println("解析的明文是：" + info); // 此处明文是xml信息
	    		
	    		// 解析xml，若InfoType是suite_ticket的话，说明是发送ticket的响应方式
	        	Document doc = DocumentHelper.parseText(info);
	        	Element root = doc.getRootElement();
	        	String infoType = root.elementTextTrim("InfoType");
	        	if("suite_ticket".equals(infoType)) {
	        		String suiteTicket = root.elementTextTrim("SuiteTicket");
	        		System.out.println("拿到的ticket是: " + suiteTicket);
	        	}
	    		
	    		response.getWriter().write("success");
	    	}
	    	System.out.println("----------------------------");
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
    }
    
	@ResponseBody
    @RequestMapping(value = "/directCallback")
    public void directCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------------------");
    	System.out.println("this is directCallback");
    	response.setCharacterEncoding("UTF-8");
    	
    	try {
	    	String corpid = request.getParameter("corpid");
	    	// 企业微信加密签名
	        String msgSignature = request.getParameter("msg_signature");
	        // 时间戳 与nonce结合使用，用于防止请求重放攻击
	        String timestamp = request.getParameter("timestamp");
	        // 校验时字符串
	        String echostr = request.getParameter("echostr");
	        // 随机数 与timestamp结合使用，用于防止请求重放攻击
	        String nonce = request.getParameter("nonce");
	        
	        System.out.println("msgSignature: "+ msgSignature);
	        System.out.println("timestamp: "+ timestamp);
	        System.out.println("echostr: "+ echostr);
	        System.out.println("nonce: "+ nonce);
	    	if("GET".equals(request.getMethod())) { // get请求表示是验证
	    		System.out.println("corpid:" + corpid);
		        String echostrDecrypt = null;
		        // 校验服务商公司id
		        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, corpid);
				echostrDecrypt = wxcpt.VerifyURL(msgSignature, timestamp, nonce, echostr);
				System.out.println("verifyurl echostr: " + echostrDecrypt);
		    	
		    	response.setCharacterEncoding("UTF-8");
		    	response.getWriter().write(echostrDecrypt);
	    	}else { // post请求表示是真实数据
	    		// 获取传过来的xml信息（密文）
	        	InputStream ins = request.getInputStream();
	        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ins));
	        	StringBuilder postData = new StringBuilder();
	        	String line = null;
	        	while((line=bufferedReader.readLine()) != null) {
	        		postData.append(line);
	        	}
	        	System.out.println("postData:"+ postData);
	    		
	    		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, suiteid);
	    		String info = wxcpt.DecryptMsg(msgSignature, timestamp, nonce, postData.toString());
	    		System.out.println("解析的明文是：" + info); // 此处明文是xml信息
	    		
	    		// 解析xml，若InfoType是suite_ticket的话，说明是发送ticket的响应方式
	        	Document doc = DocumentHelper.parseText(info);
	        	Element root = doc.getRootElement();
	        	String infoType = root.elementTextTrim("InfoType");
	        	if("suite_ticket".equals(infoType)) {
	        		String suiteTicket = root.elementTextTrim("SuiteTicket");
	        		System.out.println("拿到的ticket是: " + suiteTicket);
	        	}
	    		
	    		response.getWriter().write("success");
	    	}
	    	System.out.println("----------------------------");
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
    }
	
	// 获取suie_access_token
	@ResponseBody
    @RequestMapping(value = "/getAccessToken")
    public String getAccessToken(@RequestBody TokenParamVo tokenParamInfo, HttpServletResponse response) throws Exception {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
		Map<String, String> param = new HashMap<>();
		param.put("suite_id", tokenParamInfo.getSuiteId());
		param.put("suite_secret", tokenParamInfo.getSuiteSecret());
		param.put("suite_ticket", tokenParamInfo.getSuiteTicket());
		Object res = HttpUtils.httpPostWithJson(url, JSON.toJSONString(param));
		return res.toString();
	}
	
	// 获取suie_access_token
	@ResponseBody
    @RequestMapping(value = "/getTmpAuthCode")
    public void getTmpAuthCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------------------");
    	System.out.println("getTmpAuthCode");
        // 临时授权码
        String auth_code = request.getParameter("auth_code");
        // 过期时间
        String expires_in = request.getParameter("echostr");
        // 三方校验参数
        String state = request.getParameter("state");
        
		System.out.println("auth_code:" + auth_code);
		System.out.println("expires_in:" + expires_in);
		System.out.println("state:" + state);
		System.out.println("----------------------------");
	}
}
