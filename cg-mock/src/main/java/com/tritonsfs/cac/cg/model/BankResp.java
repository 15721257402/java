package com.tritonsfs.cac.cg.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 银行返回
 * @author njq
 */
@XStreamAlias("Document")
public class BankResp{
    
    @XStreamAlias("header")
	private ResponseBankHeader header;
	
	@XStreamAlias("body")
	private ResponseBankBody body;

    public ResponseBankHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseBankHeader header) {
        this.header = header;
    }

    public ResponseBankBody getBody() {
        return body;
    }

    public void setBody(ResponseBankBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "BankResp [header=" + header + ", body=" + body + "]";
    }

}

