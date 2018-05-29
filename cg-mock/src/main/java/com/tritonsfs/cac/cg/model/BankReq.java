package com.tritonsfs.cac.cg.model;



import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 银行返回报文（统一）
 * @author njq
 */
@XStreamAlias("Document")
public class BankReq {

	@XStreamAlias("header")
	private RequestBankHeader header;
	
	@XStreamAlias("body")
	private RequestBankBody body;
	
	
	public RequestBankHeader getHeader() {
		return header;
	}


	public void setHeader(RequestBankHeader header) {
		this.header = header;
	}


	public RequestBankBody getBody() {
		return body;
	}


	public void setBody(RequestBankBody body) {
		this.body = body;
	}


	@Override
	public String toString() {
		return "BankReq [header=" + header + ", body=" + body + "]";
	}

}
