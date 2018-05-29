package com.tritonsfs.cac.cg.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 返回结果响应
 *
 */
public class ResponseBankHeader {

    @XStreamAlias("channelCode")
	private String channelCode = "";
	
    @XStreamAlias("transCode")
	private String transCode = "";
	
    @XStreamAlias("channelFlow")
	private String channelFlow = "";
	
    @XStreamAlias("serverFlow")
	private String serverFlow = "";
	
    @XStreamAlias("serverDate")
	private String serverDate = "";
	
    @XStreamAlias("serverTime")
	private String serverTime = "";
	
    @XStreamAlias("encryptData")
	private String encryptData = "";
	
    @XStreamAlias("status")
	private String status = "";
	
    @XStreamAlias("errorCode")
	private String errorCode = "";
	
    @XStreamAlias("errorMsg")
	private String errorMsg = "";

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getChannelFlow() {
        return channelFlow;
    }

    public void setChannelFlow(String channelFlow) {
        this.channelFlow = channelFlow;
    }

    public String getServerFlow() {
        return serverFlow;
    }

    public void setServerFlow(String serverFlow) {
        this.serverFlow = serverFlow;
    }

    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getEncryptData() {
        return encryptData;
    }

    public void setEncryptData(String encryptData) {
        this.encryptData = encryptData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseHeader [channelCode=" + channelCode + ", transCode=" + transCode + ", channelFlow=" + channelFlow + ", serverFlow="
                + serverFlow + ", serverDate=" + serverDate + ", serverTime=" + serverTime + ", encryptData=" + encryptData + ", status=" + status
                + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }
	
}
