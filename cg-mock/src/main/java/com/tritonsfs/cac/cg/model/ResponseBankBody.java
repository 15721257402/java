package com.tritonsfs.cac.cg.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 返回结果
 * @author njq
 *
 */
public class ResponseBankBody {

    @XStreamAlias("MERCHANTID")
    private String merchanId;
    
    @XStreamAlias("BANKID")
    private String bankId;
    
    @XStreamAlias("TRANSCODE")
    private String transCode;
    
	@XStreamAlias("XMLPARA")
	private String xmlPara;

	public String getMerchanId() {
        return merchanId;
    }

    public void setMerchanId(String merchanId) {
        this.merchanId = merchanId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getXmlPara() {
		return xmlPara;
	}

	public void setXmlPara(String xmlPara) {
		this.xmlPara = xmlPara;
	}

    @Override
    public String toString() {
        return "ResponseBody [merchanId=" + merchanId + ", bankId=" + bankId + ", transCode=" + transCode + ", xmlPara=" + xmlPara + "]";
    }

}
