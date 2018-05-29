package com.tritonsfs.cac.cg.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tritonsfs.cac.cg.bank.security.BankDES3;
import com.tritonsfs.cac.cg.bank.security.BankRSA;
import com.tritonsfs.cac.cg.helper.DateHelper;
import com.tritonsfs.cac.cg.helper.LogHelper;
import com.tritonsfs.cac.cg.helper.XStreamHelper;
import com.tritonsfs.cac.cg.model.BankReq;
import com.tritonsfs.cac.cg.model.BankResp;
import com.tritonsfs.cac.cg.model.RequestBankBody;
import com.tritonsfs.cac.cg.model.RequestBankHeader;
import com.tritonsfs.cac.cg.util.XmlUtil;

@Service
public class MakeXmlService {
	
	private Logger logger = LoggerFactory.getLogger(MakeXmlService.class);
	
	private final String postUrl="http://172.16.21.240:6789/mockjsdata";
	
	public String getXml(BankResp resp){
		if("OGW00049".equals(resp.getBody().getTransCode())){
			return OGW00049(resp);
		}else if("OGW00048".equals(resp.getBody().getTransCode())){
			return OGW00048(resp);
		}else if("OGW00046".equals(resp.getBody().getTransCode())){
			return OGW00046(resp);
		}else if("OGW00043".equals(resp.getBody().getTransCode())){
			return OGW00043(resp);
		}else if("OGW00057".equals(resp.getBody().getTransCode())){
			return OGW00057(resp);
		}else if("OGW00064".equals(resp.getBody().getTransCode())){
			return OGW00064(resp);
		}else if("OGW00066".equals(resp.getBody().getTransCode())){
			return OGW00066(resp);
		}else if("OGW00068".equals(resp.getBody().getTransCode())){
			return OGW00068(resp);
		}else if("OGW00075".equals(resp.getBody().getTransCode())){
			return OGW00075(resp);
		}else if("OGW00077".equals(resp.getBody().getTransCode())){
			return OGW00077(resp);
		}else if("OGW00042".equals(resp.getBody().getTransCode())){
			//账户开立异步通知
			return OGWR0001(resp);
		}else if("OGWR0001".equals(resp.getBody().getTransCode())){
			return OGWR0001(resp);
		}else if("OGW00044".equals(resp.getBody().getTransCode())){
			//绑卡异步通知
			return OGWR0002(resp);
		}else if("OGWR0002".equals(resp.getBody().getTransCode())){
			return OGWR0002(resp);
		}else if("OGW00045".equals(resp.getBody().getTransCode())){
			//单笔专属账户充值异步通知
			return OGWR0003(resp);
		}else if("OGWR0003".equals(resp.getBody().getTransCode())){
			return OGWR0003(resp);
		}else if("OGW00041".equals(resp.getBody().getTransCode())){
			return OGW00041(resp);
		}else if("OGW00047".equals(resp.getBody().getTransCode())){
			//单笔提现异步通知
			return OGWR0004(resp);
		}else if("OGW00051".equals(resp.getBody().getTransCode())){
			return OGW00051(resp);
		}else if("OGW00056".equals(resp.getBody().getTransCode())){
			//如果是页面跳转，那么发起异步通知
			return OGWR0006(resp);
		}else if("OGW00059".equals(resp.getBody().getTransCode())){
			return OGW00059(resp);
		}else if("OGW00060".equals(resp.getBody().getTransCode())){
			return OGW00060(resp);
		}else if("OGW00063".equals(resp.getBody().getTransCode())){
			return OGW00063(resp);
		}else if("OGW00065".equals(resp.getBody().getTransCode())){
			return OGW00065(resp);
		}else if("OGWR0008".equals(resp.getBody().getTransCode())){
			//借款人单标还款异步通知
			return OGWR0008(resp);
		}else if("OGW00073".equals(resp.getBody().getTransCode())){
			return OGW00073(resp);
		}else if("OGW00074".equals(resp.getBody().getTransCode())){
			return OGW00074(resp);
		}else if("OGWR0006".equals(resp.getBody().getTransCode())){
			return OGWR0006(resp);
		}else if("OGW0015T".equals(resp.getBody().getTransCode())){
			return OGW0015T(resp);
		}else if("OGW0014T".equals(resp.getBody().getTransCode())){
			return OGW0014T(resp);
		}else if("OGW00042".equals(resp.getBody().getTransCode())){
			return OGW00042(resp);
		}else if("OGW00044".equals(resp.getBody().getTransCode())){
			return OGW00044(resp);
		}else if("OGW00045".equals(resp.getBody().getTransCode())){
			return OGW00045(resp);
		}else if("OGWR0004".equals(resp.getBody().getTransCode())){
			return OGWR0004(resp);
		}else if("OGW00047".equals(resp.getBody().getTransCode())){
			return OGW00047(resp);
		}else if("OGW00067".equals(resp.getBody().getTransCode())){
			return OGW00067(resp);
		}else if("OGW00056".equals(resp.getBody().getTransCode())){
			return OGW00056(resp);
		}else if("OGW00070".equals(resp.getBody().getTransCode())){
		    return OGW00070(resp);
		}else if("OGW00072".equals(resp.getBody().getTransCode())){
		    return OGW00072(resp);
		}else if("OGWR0011".equals(resp.getBody().getTransCode())){
		    return OGWR0011(resp);
		}else if("OGW00247".equals(resp.getBody().getTransCode())){
		    return OGW00247(resp);
		}else if("OGW00061".equals(resp.getBody().getTransCode())||"OGW00096".equals(resp.getBody().getTransCode())){
		    return OGW00061(resp);
		}else if("OGW00062".equals(resp.getBody().getTransCode())){
		    return OGW00062(resp);
        }else if("OGWR0007".equals(resp.getBody().getTransCode())){
            return OGWR0007(resp);
        }
		return "";
	}
	
	/**
	 * 获取短信验证码
	 * @param resp
	 * @return
	 */
	private String OGW00041(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00041"));
	}
	
	/**
	 * 账户开立
	 * @param resp
	 * @return
	 */
	private String OGW00042(BankResp resp){
		return OGWR0001(resp);
	}
	
	/**
	 * 账户开立异步通知
	 * @param resp
	 * @return
	 */
	private String OGWR0001(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0001");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0001");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	/**
	 * 绑卡
	 * @param resp
	 * @return
	 */
	private String OGW00044(BankResp resp){
		return OGWR0002(resp);
	}
	
	/**
	 * 绑卡通知
	 * @param resp
	 * @return
	 */
	private String OGWR0002(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0002");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0001");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 账户开立结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00043(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00043"));
	}
	
	
	/**
	 * 单笔专属账户充值
	 * @param resp
	 * @return
	 */
	private String OGW00045(BankResp resp){
		return OGWR0003(resp);
	}
	
	/**
	 * 充值异步通知
	 * @param resp
	 * @return
	 */
	private String OGWR0003(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0003");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0003");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 提笔充值结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00046(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00046"));
	}
	
	/**
	 * 单笔提现
	 * @param resp
	 * @return
	 */
	private String OGW00047(BankResp resp){
		return OGWR0004(resp);
	}
	
	/**
	 * 提现异步通知
	 * @param resp
	 * @return
	 */
	private String OGWR0004(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0004");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0004");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 单笔提现结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00048(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00048"));
	}
	
	/**
	 * 余额查询
	 * @param resp
	 * @return
	 */
	private String OGW00049(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00049"));
	}
	
	/**
	 * 单笔发标信息通知
	 * @param resp
	 * @return
	 */
	private String OGW00051(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00051"));
	}
	
	/**
	 * 自动投标授权
	 * @param resp
	 * @return
	 */
	private String OGW00056(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGW00056");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 投标授权异步通知
	 * @param resp
	 * @return
	 */
	private String OGWR0006(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0006");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0006");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 授权结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00057(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00057"));
	}
	
	/**
	 * 自动单笔投标
	 * @param resp
	 * @return
	 */
	private String OGW00059(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00059"));
	}
	
	/**
	 * 单笔撤标
	 * @param resp
	 * @return
	 */
	private String OGW00060(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00060"));
	}
	
	/**
	 * 流标
	 * @param resp
	 * @return
	 */
	private String OGW00063(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00063"));
	}
	
	/**
	 * 流标结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00064(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00064"));
	}
	
	/**
	 * 放款
	 * @param resp
	 * @return
	 */
	private String OGW00065(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00065"));
	}
	
	/**
	 * 放款结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00066(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00066"));
	}
	
	
	/**
	 * 借款人单标还款
	 * @param resp
	 * @return
	 */
	private String OGW00067(BankResp resp){
		return OGWR0008(resp);
	}
	
	/**
	 * 借款人单标还款异步通知
	 * @param resp
	 * @return
	 */
	private String OGWR0008(BankResp resp){
		Map<String, String> map=getParam(postUrl+"/14/api/OGWR0008");
		if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
			resp.getBody().setTransCode("OGWR0008");
			return common(resp,map);			
		}else{
			return XmlUtil.NO_BACK;
		}
	}
	
	/**
	 * 借款人单笔还款结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00068(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00068"));
	}
	
	/**
	 * 单笔公司垫付还款
	 * @param resp
	 * @return
	 */
	private String OGW00073(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00073"));
	}
	
	/**
	 * 还款收益明细提交
	 * @param resp
	 * @return
	 */
	private String OGW00074(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00074"));
	}
	
	/**
	 * 还款收益结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00075(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00075"));
	}
	
	/**
	 * 日终对账结果查询
	 * @param resp
	 * @return
	 */
	private String OGW00077(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW00077"));
	}
	
	/**
	 * 银行主动流标
	 * @param resp
	 * @return
	 */
	private String OGW0014T(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW0014T"));
	}
	
	/**
	 * 银行主动单笔撤标
	 * @param resp
	 * @return
	 */
	private String OGW0015T(BankResp resp){
		return common(resp, getParam(postUrl+"/14/api/OGW0015T"));
	}
	
	/**
	 * 自动还款授权异步通知 
	 * @param resp
	 * @return
	 * @auth njq
	 */
	private String OGWR0011(BankResp resp){
        Map<String, String> map=getParam(postUrl+"/14/api/OGWR0011");
        if(XmlUtil.NEED_BACK.equals(map.get("ISNEEDBACK"))){
            resp.getBody().setTransCode("OGWR0011");
            return common(resp,map);            
        }else{
            return XmlUtil.NO_BACK;
        }
    }
	
	/**
     * 自动还款结果查询
     * @param resp
     * @return
     * @auth njq
     */
    private String OGW00070(BankResp resp){
        return common(resp, getParam(postUrl+"/14/api/OGW00070"));
    }
    
    /**
     * 自动还款
     * @param resp
     * @return
     * @auth njq
     */
    private String OGW00072(BankResp resp){
        return common(resp, getParam(postUrl+"/14/api/OGW00072"));
    }
    
    /**
     * 交易查询
     * @param resp
     * @return
     * @auth njq
     */
    private String OGW00247(BankResp resp){
        return common(resp, getParam(postUrl+"/14/api/OGW00247"));
    }
    
    /**
     * 债转申请
     * @param resp
     * @return
     * @auth njq
     */
    private String OGW00061(BankResp resp){
        return OGWR0007(resp);
    }
    
    /**
     * 债转标结果查询
     * @param resp
     * @return
     * @auth njq
     */
    private String OGW00062(BankResp resp){
        return common(resp, getParam(postUrl+"/14/api/OGW00062"));
    }
    
    /**
     * 债转标转让申请异步回调
     * @param resp
     * @return
     * @auth njq
     */
    private String OGWR0007(BankResp resp){
        return common(resp, getParam(postUrl+"/14/api/OGWR0007"));
    }
    
	/**
	 * 通用部分
	 * @param resp
	 * @param paramMap
	 * @return
	 */
	public String common(BankResp resp,Map<String, String> paramMap){
		Date nowTime=new Date(); 
		String str="001X11          ";
		RequestBankHeader header = new RequestBankHeader();
		header.setChannelCode(resp.getHeader().getChannelCode());
		header.setServerFlow("OGW"+getChannelFlow());
		header.setChannelFlow(resp.getHeader().getChannelFlow());
		header.setErrorMsg(paramMap.get("ERRORMSG"));
		header.setErrorCode(paramMap.get("ERRORCODE"));
		header.setStatus("0");
		header.setServerDate(DateHelper.formatYmd(nowTime));
		header.setServerTime(DateHelper.formatHsm(nowTime));
		header.setTransCode(resp.getBody().getTransCode());
		String xmlPara= XmlUtil.formatXmlpara(paramMap);
		LogHelper.info(logger,"【mock银行发送】【remotePara】报文xml为："+xmlPara);
		try {
			RequestBankBody body = new RequestBankBody();
			body.setXmlPara(BankDES3.encode(xmlPara));
			body.setBankId("GHB");
			body.setMerchantId("HJXD");
			body.setTransCode(resp.getBody().getTransCode());
			BankReq req = new BankReq();
			req.setHeader(header);
			req.setBody(body);
			String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+ XmlUtil.removeBlankSpace(XStreamHelper.toXml(req));
			String sign = BankRSA.encode(xml);
			String lenStr = XmlUtil.getSecurityLen(sign);
			str+=lenStr+sign+xml;
			LogHelper.info(logger,"【mock银行发送】【remoteXML】报文xml为："+str);
			return str;
		} catch (Exception e) {
			LogHelper.error(logger,"mock银行转换出错",e);
		}
		return "";
	}
	
	/**
	 * 获取自定义参数
	 * @param url
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> getParam(String url){
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		Map<String, String> paramMap=new HashMap<String, String>();
		try {
			String strMessage = "";
			URL uploadServlet = new URL(url);
			HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
			// 设置连接参数
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			servletConnection.setDoInput(true);
			servletConnection.setAllowUserInteraction(true);
			// 获取返回的数据
			InputStream inputStream = servletConnection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
			String str=buffer.toString();
			JSONObject xmlStr = new JSONObject(str.replaceAll("\"", "\'"));
			String rslistStr=null;
			if(xmlStr.has("RSLIST")){
                String rslist = xmlStr.get("RSLIST").toString();
				if((!StringUtils.isEmpty(rslist))&&rslist.indexOf("[")==-1){
					rslist="["+rslist+"]";
				}
				JSONArray myJsonArray = new JSONArray(rslist);
				rslistStr=XmlUtil.formatXmlRslist(myJsonArray);
				xmlStr.remove("RSLIST");
			}
			Iterator iterator = xmlStr.keys();
			String key="";
			String value="";
			while(iterator.hasNext()){
				key = (String) iterator.next();
			    value = xmlStr.getString(key);
			    paramMap.put(key, value);
			}
			if(rslistStr!=null){
				paramMap.put("RSLIST", rslistStr);
			}
			LogHelper.info(logger,"【mock银行获取】【RAP参数】："+buffer);
			if (reader != null) {
				reader.close();
			}
		} catch (Exception e) {
			LogHelper.error(logger,"mock银行Rap获取参数出错！",e);
		}
		
	    try {
	        if(paramMap.get("outTime")!=null){
	            int m=Integer.parseInt(paramMap.get("outTime").toString());
	            Thread.sleep(m*1000);
	        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return paramMap;
	}
	
	/**
	 * 获取返回流水号
	 * @return
	 */
	public String getChannelFlow(){
		String id=UUID.randomUUID().toString().replaceAll("-", "");
		return id.substring(id.length()-25);
	}
}
