package search.searchResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class TestSearch {
	/**
	 * 测试检索页面是否正常显示
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testSearch() throws ClientProtocolException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		 String url = "http://www.cheyisou.com/qiche/北京80/";
		 HttpGet request = new HttpGet(url);
         HttpResponse response = client.execute(request);
         try{
	         /**请求发送成功，并得到响应**/
	         if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	             /**读取服务器返回过来的json字符串数据**/
	             String strResult = EntityUtils.toString(response.getEntity());
	             /**把json字符串转换成json对象**/
	             //System.out.println(strResult);
	             Document dc = Jsoup.parse(strResult);
	             Elements container = dc.getElementsByClass("c-container");
	                 //http://www.cnblogs.com/digod/p/5762921.html
	             Assert.assertFalse(container.isEmpty());
	            
	         } else {
	             System.out.println("error");
	         }
         }finally{
        	 client.getConnectionManager().shutdown();
         }
	}

}
