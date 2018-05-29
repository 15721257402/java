package com.tritonsfs.cac.cg.model;

import java.io.Serializable;

public class ShReq implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String serviceName;
    
    private String platformNo;
    
    private String reqData;
    
    private String keySerial;
    
    private String sign;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(String platformNo) {
        this.platformNo = platformNo;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public String getKeySerial() {
        return keySerial;
    }

    public void setKeySerial(String keySerial) {
        this.keySerial = keySerial;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    
    
    
}
