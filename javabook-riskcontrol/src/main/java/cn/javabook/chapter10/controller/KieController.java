package cn.javabook.chapter10.controller;

import cn.javabook.chapter10.entity.Coupon;
import cn.javabook.chapter10.service.StatelessKieService;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * KieController
 *
 */
@RestController
public class KieController {
    @Resource
    private StatelessKieService statelessKieService;

    /**
     * 执行规则
     *
     */
    @GetMapping("/execute")
    public String calcPoint(final int buyMoney) {
        Coupon coupon = new Coupon(0, buyMoney);
        String result = statelessKieService.execute(coupon);
        if ("OK".equalsIgnoreCase(result)) {
            return "订单积分增加了 " + coupon.getPoint() + " 分";
        }
        return result;
    }

    /**
     * 动态添加指定目录下的全部规则集
     * filePath为文件路径，不包括文件名，例如：/user/local
     *
     */
    @GetMapping("/addAll")
    public void addAll(final String filePath) {
        statelessKieService.addPackage(filePath);
    }

    /**
     * 动态添加单一规则
     * filePathName为全路径名称，例如：/user/local/buy_money_rules.drl
     *
     */
    @GetMapping("/add")
    public void add(final String filePathName) {
        statelessKieService.addRule(filePathName);
    }

    /**
     * 动态删除规则
     *
     */
    @GetMapping("/remove")
    public void remove(final String packageName, final String ruleName) {
        statelessKieService.removeRule(packageName, ruleName);
    }

    /**
     * 动态添加决策表
     * filePathName为全路径名称，例如：/user/local/decision_table.xlsx
     *
     */
    @GetMapping("/load")
    public String load(final String filePathName) {
        File file = new File(filePathName);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(is, InputType.XLS);
        System.out.println(drl);

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        StatelessKieSession kieSession = kieHelper.build().newStatelessKieSession();
        Coupon coupon = new Coupon(0, 900);
        kieSession.execute(coupon);
        return "订单积分增加了 " + coupon.getPoint() + " 分";
    }
}
