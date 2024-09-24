package cn.javabook.chapter08.github.userauth.controller;

import cn.javabook.chapter08.github.userauth.service.InfoAuthService;
import cn.javabook.chapter08.github.userauth.entity.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;

/**
 * 页面控制器
 *
 */
@Controller
public class ThirdController {
    @Resource
    private InfoAuthService infoAuthService;

    /**
     * 进入系统时就跳转到注册页面
     *
     */
    @GetMapping("/index")
    public String register() {
        return "userauth/register";
    }

    /**
     * 提交注册信息
     *
     */
    @GetMapping("/register")
    public String register(String username, String password,
                           String mobile, String email, String realname) {
        boolean flag = infoAuthService.register(username, password, mobile, email, realname);
        if (flag) {
            return "userauth/success";
        }
        return "userauth/failure";
    }

    /**
     * 注册成功跳转到登录页
     *
     */
    @GetMapping("/signin")
    public String signin() {
        return "userauth/login";
    }

    /**
     * 提交登录信息
     *
     */
    @GetMapping("/login")
    public String login(String identifier, String credential) {
        UserVO userVO = infoAuthService.login(identifier, credential);
        if (null != userVO) {
            return "userauth/index";
        }
        return "userauth/failure";
    }

    /**
     * 登出
     *
     */
    @GetMapping("/logout")
    public String logout() {
        return "userauth/success";
    }
}
