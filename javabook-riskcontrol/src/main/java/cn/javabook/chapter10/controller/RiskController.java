package cn.javabook.chapter10.controller;

import cn.javabook.chapter10.service.StatefulKieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 多个规则都被触发时执行其中一个
 *
 */
@RestController
public class RiskController {
    @Resource
    private StatefulKieService statefulKieService;

    @GetMapping("/risk/test")
    public void hello() {
        System.out.println("这是一个MockMvc测试方法");
    }

    @GetMapping("/risk/addAll")
    public void addAll(final String filePath) {
        statefulKieService.addPackage(filePath);
    }

    @GetMapping("/risk/add")
    public void add(final String filePathName) {
        statefulKieService.addRule(filePathName);
    }

    @GetMapping(value = "/risk/execute")
    public String risk(final String username, final int orders, final int buyMoney) {
        try {
            if (StringUtils.isBlank(username)) {
                return null;
            }
            // 执行规则
            return this.statefulKieService.execute(username, orders, buyMoney);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "OK";
    }

    @GetMapping("/risk/remove")
    public void remove(final String packageName, final String ruleName) {
        statefulKieService.removeRule(packageName, ruleName);
    }
}
