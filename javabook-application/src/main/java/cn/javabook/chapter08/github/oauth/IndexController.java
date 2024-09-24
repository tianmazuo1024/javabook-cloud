package cn.javabook.chapter08.github.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 跳转到第三方登录页面
 *
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "oauth/index";
    }
}
