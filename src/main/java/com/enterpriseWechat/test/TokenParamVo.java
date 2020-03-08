package com.enterpriseWechat.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TokenParamVo implements Serializable {

	private static final long serialVersionUID = 5297455253259090599L;
	
	private String suiteId;
	private String suiteSecret;
	private String suiteTicket;
	
	public String getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}
	public String getSuiteSecret() {
		return suiteSecret;
	}
	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}
	public String getSuiteTicket() {
		return suiteTicket;
	}
	public void setSuiteTicket(String suiteTicket) {
		this.suiteTicket = suiteTicket;
	}
}
