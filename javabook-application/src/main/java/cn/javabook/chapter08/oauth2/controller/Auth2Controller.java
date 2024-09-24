package cn.javabook.chapter08.oauth2.controller;

import cn.javabook.chapter08.oauth2.entity.UserVO;
import cn.javabook.chapter08.oauth2.service.Auth2Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;

/**
 * 页面控制器
 *
 */
@Controller
public class Auth2Controller {
    @Resource
    private Auth2Service auth2Service;

//    // 进入系统时就跳转到注册页面
//    @GetMapping("/oauth2")
//    public String register() {
//        return "oauth2/register";
//    }
    // 进入系统时就跳转到token页面：配合OTP演示
    @GetMapping("/oauth2")
    public String token() {
        return "oauth2/token";
    }

    // 提交注册信息
    @GetMapping("/oauth2/register")
    public String register(String username, String password,
                           String mobile, String email, String realname) {
        boolean flag = auth2Service.register(username, password, mobile, email, realname);
        if (flag) {
            return "oauth2/success";
        }
        return "oauth2/failure";
    }

    // 注册成功，跳转到登录页
    @GetMapping("/oauth2/signin")
    public String signin() {
        return "oauth2/login";
    }

    // 提交登录信息
    @GetMapping("/oauth2/login")
    public String login(String identifier, String credential) {
        UserVO userVO = auth2Service.login(identifier, credential);
        if (null != userVO) {
            return "oauth2/index";
        }
        return "oauth2/failure";
    }

    // 登出
    @GetMapping("/oauth2/logout")
    public String logout() {
        return "oauth2/success";
    }
}
