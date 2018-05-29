package com.tritonsfs.cac.cg.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tritonsfs.cac.cg.helper.LogHelper;
import com.tritonsfs.cac.cg.model.BankResp;
import com.tritonsfs.cac.cg.model.ResponseBankBody;
import com.tritonsfs.cac.cg.model.ResponseBankHeader;
import com.tritonsfs.cac.cg.service.MakeXmlService;
import com.tritonsfs.cac.cg.util.XmlUtil;


/**
 * 修改1
 * 存管系统测试专用（非对外公开）
 * @author njq
 */
@RequestMapping("bank")
@Controller
public class BankController {

	private Logger logger = LoggerFactory.getLogger(BankController.class);
	@Resource
	public MakeXmlService makeXmlService;
	
	/**
	 * 接收报文
	 * @param request
	 * @param response
	 */
	@RequestMapping("receiveXml")
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		BufferedReader reader;
		try {
			reader = request.getReader();
			String line = "";
			StringBuffer inputString = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				inputString.append(line);
			}
			/*
			 * 如果从流中获取不到数据说明是跳转页面
			 * 那么判断是否需要回调，如果需要回调那么进行异步回调 
			 */
			if(StringUtils.isEmpty(inputString.toString())){
				inputString.append(request.getParameter("RequestData"));
				BankResp cg=XmlUtil.tranXml(inputString.toString());
				String sendXml=makeXmlService.getXml(cg);
				if(XmlUtil.NO_BACK.equals(sendXml)){
					model.addAttribute("errorMsg","操作成功！不进行任何回调");
					return "error";
				}else{
					send(sendXml);
					return "index";
				}
			}else{
				BankResp cg=XmlUtil.tranXml(inputString.toString());
				String sendXml=makeXmlService.getXml(cg);
				// 设置发送报文的格式
				response.setContentType("text/xml");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println(sendXml);
				out.flush();
				out.close();				
			}
			return null;				
		} catch (Exception e) {
			LogHelper.error(logger,"接收报文接口出错！",e);
		}
		return null;
	}
	
	
	
	/**
	 * 主动发送报文
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendXml")
	public String sendXml(HttpServletRequest request,HttpServletResponse response, String transCode,Model model){
		if(transCode!=null){
			String str="";
			try {
				//发送的报文
				String sendXml="";
				BankResp resp =new BankResp();
				ResponseBankHeader header=new ResponseBankHeader();
				header.setChannelCode("GHB");
				header.setChannelFlow("P2P"+makeXmlService.getChannelFlow());
				ResponseBankBody body=new ResponseBankBody();
				body.setTransCode(transCode);
				resp.setHeader(header);
				resp.setBody(body);
				//生成发送的内容
				sendXml=makeXmlService.getXml(resp);
				LogHelper.debug(logger,"【mock银行主动发起】发送类型："+transCode+" 发送内容："+sendXml);
				//发送报文
				str=send(sendXml);
				LogHelper.info(logger,"【存管返回报文】报文值："+str);
				BankResp cg=XmlUtil.tranXml(str);
				LogHelper.info(logger,"【存管返回xmlpara】："+cg.getBody().getXmlPara());
				return "index";
			} catch (Exception e) {
				LogHelper.error(logger,"接收返回值："+str,e);
				model.addAttribute("errorMsg",e.getMessage());
				return "error";
			}
		}
		model.addAttribute("errorMsg","transCode填了么？");
		return "error";
	}
	
	
	/**
	 * 发送报文
	 * @param transCode
	 * @return
	 */
	private String send(String sendXml){
		try {
			URL uploadServlet = new URL("http://172.16.21.226:9030/bank/huaxing/callback");
			HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
			// 设置连接参数
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			servletConnection.setDoInput(true);
			servletConnection.setRequestProperty("content-Type", "application/json");
			servletConnection.setAllowUserInteraction(true);
			OutputStreamWriter out = new OutputStreamWriter(servletConnection.getOutputStream(), "UTF-8");
			out.write(sendXml);
			out.flush();
			out.close();
			InputStream inputStream = servletConnection.getInputStream();
			BufferedReader reader = null;
			StringBuffer buffer = new StringBuffer();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String strMessage = "";
			//获取返回的内容
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
			if (reader != null) {
				reader.close();
			}
			LogHelper.info(logger,"【mock银行接收返回报文】接收XML："+buffer.toString());
			return buffer.toString();
		} catch (Exception e) {
			LogHelper.error(logger,"报文发送失败",e);
		}
		return "";
	}
}
