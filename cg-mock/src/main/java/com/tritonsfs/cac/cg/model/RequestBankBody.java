package com.tritonsfs.cac.cg.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class RequestBankBody {

    @XStreamAlias("TRANSCODE")
    private String transCode;
    
    @XStreamAlias("BANKID")
    private String bankId;
    
    @XStreamAlias("XMLPARA")
    private String xmlPara;
    
	@XStreamAlias("MERCHANTID")
	private String merchantId;

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

    
    public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

}
