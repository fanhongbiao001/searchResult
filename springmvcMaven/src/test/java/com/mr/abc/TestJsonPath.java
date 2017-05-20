package com.mr.abc;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class TestJsonPath {
	
	@Test
	public void jsonPathTest(){
		DefaultHttpClient client = new DefaultHttpClient();
		try {
            //发送get请求
            String url = "http://59.151.102.96/SuggestNo20160419.php?callback=yicheIndexSug&en=utf-8&d=1495249937472&k=上海";
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                System.out.println(strResult);
                Pattern p=Pattern.compile("yicheIndexSug\\((.*)\\)");
                Matcher m=p.matcher(strResult);  
                if(m.find()){
                	String resultJson = m.group(1);
                	Object str = JSONPath.read(resultJson, "$.s");
                	DocumentContext ctx = JsonPath.parse(str);
                	List<Map<String, Object>> lst = JsonPath
                             .using(Configuration.defaultConfiguration())
                             .parse(resultJson)
                             .read("$.s", List.class);
                	System.out.println("size:"+lst.size());
                    //http://www.cnblogs.com/digod/p/5762921.html
                }
                
                
               
            } else {
                System.out.println("error");
            }
        } catch (IOException e) {
          
        }finally{

        }
	}

}
