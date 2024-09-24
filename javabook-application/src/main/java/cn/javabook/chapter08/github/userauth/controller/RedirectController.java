package cn.javabook.chapter08.github.userauth.controller;

import cn.javabook.chapter08.github.userauth.entity.UserVO;
import cn.javabook.chapter08.github.userauth.service.InfoAuthService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
public class RedirectController {
    @Resource
    private InfoAuthService infoAuthService;

    /**
     * 回调接口
     *
     */
    @GetMapping("oauth/redirect")
    public void redirect(final String code) {
        // 获得github返回的授权码
        System.out.println(code);

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "[申请的client_id]");
        map.put("client_secret", "[申请的client_secret]");
        map.put("code", code);
        // 向Github发送POST请求
        String result = sendPost("https://github.com/login/oauth/access_token", map);
        // 解析返回结果
        String[] values = result.split("&");

        String token = values[0].split("=")[1];
        // 请求数据
        result = sendGet("https://api.github.com/user", token);
        // 解析
        JSONObject content = JSONObject.parseObject(result);
        System.out.println(result);
        String identifier = content.getString("login");
        String avatar = content.getString("avatar_url");
        infoAuthService.saveThirdLoginInfo(identifier, token, identifier, avatar);
    }

    @GetMapping("/token")
    public void token(final String identifier) {
        UserVO userVO = infoAuthService.queryIdentifier(identifier);
        String result = sendGet("https://api.github.com/user", userVO.getCredential());
        System.out.println(result);
    }

    /**
     * 请求令牌的POST方法
     *
     */
    public static String sendPost(final String url, final Map<String, String> map) {
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

    /**
     * 请求数据的GET方法
     *
     */
    public static String sendGet(final String url, final String token) {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 创建get方式请求对象
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("accept", "application/json");
            httpGet.addHeader("Authorization", "token " + token);
            CloseableHttpResponse response = httpClient.execute(httpGet);
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

