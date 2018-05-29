package com.tritonsfs.cac.cg.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tritonsfs.cac.cg.helper.LogHelper;


@Service
public class ShService {

    private Logger logger = LoggerFactory.getLogger(ShService.class);
    private final String postUrl="http://172.16.21.240:6789/mockjsdata/19/api/";
    
    public String getStr(Map<String, Object> map1,Map<String, Object> map2){
        String serviceName=map1.get("serviceName").toString();
        String url="";
        if("QUERY_TRANSACTION".equals(serviceName)){
            String transactionType=map2.get("transactionType").toString();
            url=serviceName+"_"+transactionType;;
        }else{
            url=serviceName;
        }
        return getString(url);
    }
    
    
    private String getString(String method){
        return getParam(postUrl+method);
    }
    
    
    public String getParam(String url){
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
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
            LogHelper.info(logger,"【mock银行获取】【RAP参数】："+buffer);
            if (reader != null) {
                reader.close();
            }
            return str.toString();
        } catch (Exception e) {
            LogHelper.error(logger,"mock银行Rap获取参数出错！",e);
        }
        return null;
    }
    
}
