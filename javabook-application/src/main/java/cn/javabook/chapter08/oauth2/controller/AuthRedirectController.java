package cn.javabook.chapter08.oauth2.controller;

import cn.javabook.chapter08.oauth2.service.Auth2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 重定向控制器
 *
 */
@RestController
public class AuthRedirectController {
    @Resource
    private Auth2Service auth2Service;

    /*
     * 这里注释回调接口是为了配合OTP，如果不需要OTP就取消注释
     */
//    // 回调接口
//    @GetMapping("oauth2/redirect")
//    public void redirect(final String code) throws ParseException {
//        // 获得返回的授权码
//        System.out.println("授权码code：" + code);
//        // 带上授权码，请求token
//        Map<String, String> map = new HashMap<>();
//        map.put("appid", "8e5f7cc5cb85427cbcd0c632548afaf4");
//        map.put("appsecret", "c5023ccf1f2a4d33876a66d7af8c469c");
//        map.put("code", code);
//        // 发送POST请求
//        String token = sendPost("http://localhost:9528/token", map);
//        // 获得token然后保存起来（走之前oauth的那一套流程），留待以后使用
//        System.out.println("令牌token：" + token);
//        // 请求用户数据的接口（略）
//    }

    /*
     * 这里增加接口也是为了配合验证OTP
     *
     */
    @GetMapping("oauth2/token")
    public void token(final String token) throws ParseException {
        Map<String, String> map = new HashMap<>();
        map.put("appid", "8e5f7cc5cb85427cbcd0c632548afaf4");
        map.put("appsecret", "javabook");
        map.put("code", token);
        // 发送POST请求
        String result = sendPost("http://localhost:9528/token", map);
        System.out.println("验证结果：" + result);
    }

    /**
     * 请求令牌的POST方法
     *
     */
    public static String sendPost(final String url, Map<String, String> map) {
    	String result = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if (map != null) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Entry<String, String> entry : map.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
                httpPost.setEntity(formEntity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                System.out.println("请求异常");
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

