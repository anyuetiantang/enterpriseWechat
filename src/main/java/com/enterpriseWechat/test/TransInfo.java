package com.enterpriseWechat.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransInfo implements Serializable {

	private static final long serialVersionUID = 5297455253259090599L;
	
	/**
	 * 企业微信的CorpID，当为第三方应用回调事件时，CorpID的内容为suiteid
	 */
	@XmlElement
	private String ToUserName;
	/**
	 * 接收的应用id，可在应用的设置页面获取，仅应用相关的回调会带该字段
	 */
	@XmlElement
	private String AgentID;
	/**
	 * 消息结构体加密后的字符串
	 */
	@XmlElement
	private String Encrypt;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getAgentID() {
		return AgentID;
	}
	public void setAgentID(String agentID) {
		AgentID = agentID;
	}
	public String getEncrypt() {
		return Encrypt;
	}
	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}
}
