package com.tritonsfs.cac.cg.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.tritonsfs.cac.cg.bank.security.BankDES3;
import com.tritonsfs.cac.cg.bank.security.BankRSA;
import com.tritonsfs.cac.cg.helper.XStreamHelper;
import com.tritonsfs.cac.cg.model.BankResp;
import com.tritonsfs.cac.cg.model.ResponseBankBody;

public class XmlUtil {

	//需要进行异步通知
	public static String NEED_BACK="1";
	//不需要通知
	public static String NO_BACK="0";

	/**
	 * 代码解析
	 * @param xmlpara
	 * @return
	 */
	public static Map<String, String> analysisParam(String xmlpara){
		Map<String, String> map=new HashMap<String, String>();
		String[] labels= xmlpara.split(">");
		for(String label:labels){
			String[] l=label.split("</");
			if(l.length>1){
				map.put(l[1], l[0]);
			}
		}
		return map;
	}
	
	public static String formatXmlpara(Map<String, String> map){
		String str="";
		for (String key: map.keySet()) {
			if("RSLIST".equals(key)){
				str+=map.get(key);
			}else{
				str+="<"+key+">"+map.get(key)+"</"+key+">";				
			}
		}
		return str;
	}
	
	@SuppressWarnings("rawtypes")
	public static String formatXmlRslist(JSONArray jsonArray){
		StringBuffer str=new StringBuffer();
		JSONObject json;
		Iterator iterator;
		String key="";
		String value="";
		for(int i=0;i<jsonArray.length();i++){
			str.append("<RSLIST>");
			json=jsonArray.getJSONObject(i);
			iterator = json.keys();
			while(iterator.hasNext()){
				key = (String) iterator.next();
			    value = json.getString(key);
			    str.append("<"+key+">"+value+"</"+key+">");
			}
			str.append("</RSLIST>");
		}
		return str.toString();
	}

	
	public static BankResp tranXml(String result){
		BankResp resp = null;
		int index = result.indexOf("<?xml");
		String xml = result.substring(index);
	    try{
	        resp = (BankResp) XStreamHelper.fromXml(getXmlContent(xml),BankResp.class);
	        ResponseBankBody body = new ResponseBankBody();
	        body.setTransCode(resp.getBody().getTransCode());
	        if(resp.getBody().getXmlPara()!=null){
	            body.setXmlPara(BankDES3.decode(resp.getBody().getXmlPara()));//xmlPara解密	            
	            resp.setBody(body);
	        }
	    }catch( Exception e ){
	        throw new RuntimeException("xml转化失败");
	    }
		return resp;
	}
	
	
    /**
     * 去除字符中间的所有空格和换行
     * @param str
     * @return
     */
    public static String removeBlankSpace( String str ){
        if( StringUtils.isEmpty(str) ){
            return "";
        }
        return str.replaceAll(" ", "").replaceAll("\n", "");
    }
	
	
    public static String getSecurityLen(String sign) {
        if( null == sign || "".equals(sign) ){
            return "00000000";
        }
        String len = String.valueOf( sign.length() );
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < 8-len.length(); i++ ){
            sb.append("0");
        }
        sb.append(len);
        return sb.toString();
    }
	
	
	public static BankResp getBankResp( String result ) throws Exception{
	    if( null == result || "".equals(result) ){
	        throw new IllegalArgumentException("参数不能为空");
	    }
	    //截取xml报文进行md5摘要
	    int index = result.indexOf("<?xml");
	    if( -1 == index ){
	        throw new IllegalArgumentException("参数不正确！无xml标签");
	    }
	    String xml = result.substring(index);
	    String md5Str = BankRSA.MD5(xml);
	    String xmlMd5Str = null;
	    //截取签名后数据并用银行公钥解�?
	    try{
	        String xmlSign = result.substring(24, index);
            xmlMd5Str = BankRSA.decode( xmlSign );
        }catch(StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException("参数不正确！无签名数据！");
        }
	    
	    if( ! md5Str.equals(xmlMd5Str) ){
	        throw new RuntimeException("验签失败！签名不正确");
	    }
	    
	    BankResp resp = null;
	    try{
	        resp = (BankResp) XStreamHelper.fromXml(getXmlContent(xml),BankResp.class);
	    }catch( Exception e ){
	        throw new RuntimeException("xml转化失败");
	    }
	    
	    try{
	        //xmlPara存在则进行解�?
	        if( null != resp.getBody() && (!StringUtils.isEmpty(resp.getBody().getXmlPara())) ){
	            ResponseBankBody body = new ResponseBankBody();
	            body.setXmlPara(BankDES3.decode(resp.getBody().getXmlPara()));//xmlPara解密
	            resp.setBody(body);
	        }
	    }catch( Exception e ){
            throw new RuntimeException("xml解密失败");
        }
	    return resp;
	}

	private static String getXmlContent( String xml ){
        int index = xml.indexOf("<Document>");
        if( -1 == index ){
            throw new IllegalArgumentException("xml内容有误！无Document标签！");
        }
        return xml.substring(index);
    }
}
