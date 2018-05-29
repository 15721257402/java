package com.tritonsfs.cac.cg.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tritonsfs.cac.cg.helper.LogHelper;
import com.tritonsfs.cac.cg.service.ShService;

@RequestMapping("sh")
@Controller
public class ShController {
    private Logger logger = LoggerFactory.getLogger(ShController.class);
    
    @Autowired
    private ShService shService;
    
    
    /**
     * 获取报文
     * @author njq
     * @param @param request
     * @param @param response
     * @param @param model
     * @param @return
     * @return String
     */
    @RequestMapping("receiveXml")
    public String test(@RequestBody String data,HttpServletRequest request,HttpServletResponse response,Model model){
        try {
            Map<String, Object> map1=getUrlParams(data);
            String reqData=map1.get("reqData").toString();
            reqData=reqData.replaceAll("%7B", "");
            reqData=reqData.replaceAll("%3A", "=");
            reqData=reqData.replaceAll("%2C", "&");
            reqData=reqData.replaceAll("%22", "");
            reqData=reqData.replaceAll("%7D", "");
            Map<String, Object> map2=getUrlParams(reqData);
            String sendStr=shService.getStr(map1,map2);
            logger.info("mock返回值："+sendStr);
            // 设置发送报文的格式
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(sendStr);
            out.flush();
            out.close();               
            return null;                
        } catch (Exception e) {
            LogHelper.error(logger,"接收报文接口出错！",e);
        }
        return null;
    }
    
    
    /**
     * 主动跳转页面
     * @author njq
     * @param @param request
     * @param @param response
     * @param @param model
     * @param @return
     * @return String
     */
    @RequestMapping("sendXml")
    public String jump(HttpServletRequest request,HttpServletResponse response,Model model){
        return "index";
    }
    
    
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }
    
}
