package cn.javabook.cloud.core.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.javabook.cloud.core.parent.BaseUtil;

/**
 * 统一返回结果工具类
 *
 */
public class ResultUtil extends BaseUtil {
    @JsonIgnore
    private static final long serialVersionUID = 7162247162650428940L;

    /**
     * 成功返回无结果
     *
     * @return JSONObject
     */
    public static JSONObject success() {
        JSONObject result = new JSONObject();
        result.put("errcode", 0);
        result.put("errmsg", "");
        // 业务JSON数据
        result.put("data", new JSONObject());
        return result;
    }
    
    public static JSONObject success(final int errcode) {
        JSONObject result = new JSONObject();
        result.put("errcode", errcode);
        result.put("errmsg", "");
        result.put("data", new JSONObject());
        return result;
    }

    public static JSONObject failure(final int errcode, final String errmsg) {
        JSONObject result = new JSONObject();
        result.put("errcode", errcode);
        result.put("errmsg", errmsg);
        result.put("data", new JSONObject());
        return result;
    }

    public static JSONObject data(final JSONArray data) {
        JSONObject result = new JSONObject();
        result.put("errcode", 0);
        result.put("errmsg", "");
        result.put("data", data);
        return result;
    }
}
