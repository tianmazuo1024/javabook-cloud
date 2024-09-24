package cn.javabook.chapter10.service;

import cn.javabook.chapter10.entity.LoginEvent;
import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.drools.kiesession.rulebase.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * 有状态的kieSession
 *
 */
@Service
public class StatefulKieService {
    @Resource
    private BlackListService blackListService;

    @Resource
    private EventService eventService;

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    // 有状态会话StatelessKieSession
    private KieSession kieSession;

    private Globals globals;

    // 规则库
    private final InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

    // 知识库构建器
    private final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    // 设为全局变量，方便drl文件使用
    private void setGlobal() {
        globals = kieSession.getGlobals();
        globals.set("blackListService", blackListService);
        globals.set("eventService", eventService);
        globals.set("orderService", orderService);
        globals.set("userService", userService);
    }

    // 动态添加指定目录下的全部规则
    public void addPackage(String path) {
        try {
            // 也可以使用CLASSPATH的方式加载.drl文件
            // 加载文件路径，不要文件名
            File filePath = new File(path);
            // 是否是目录
            if (filePath.isDirectory()) {
                File[] files = filePath.listFiles();
                // 逐个文件加载
                assert files != null;
                for (File file : files) {
                    // 加载drl规则文件
                    if (file.getName().endsWith(".drl")) {
                        builder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
                        if (builder.hasErrors()) {
                            KnowledgeBuilderErrors errors = builder.getErrors();
                            errors.forEach(error -> System.out.println(error.getMessage()));
                            return;
                        }
                    }
                }
                // 加载package
                kbase.addPackages(builder.getKnowledgePackages());
                // 由规则库创建有状态会话
                kieSession = kbase.newKieSession();
                // 设为全局变量，方便drl文件使用
                setGlobal();
                // 打印出已加载的规则
                printRules();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // 打印出已加载的规则
    public void printRules() {
        System.out.println();
        System.out.println("added rule: -----------------------");
        kbase.getKiePackages().forEach(knowledgePackage -> knowledgePackage.getRules()
                              .forEach(rule -> System.out.println("rule: " + knowledgePackage.getName() + "." + rule.getName())));
        System.out.println("-----------------------------------");
        System.out.println();
    }

    // 动态添加单一规则
    public void addRule(String filePathName) {
        // 也可以使用CLASSPATH的方式加载.drl文件
        // ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
        File file = new File(filePathName);
        builder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
        if (builder.hasErrors()) {
            System.out.println("Unable to compile " + file.getName());
            return;
        }
        // 加载package
        kbase.addPackages(builder.getKnowledgePackages());
        // 由规则库创建有状态会话
        kieSession = kbase.newKieSession();
        // 设为全局变量，方便drl文件使用
        setGlobal();
        // 打印出已加载的规则
        printRules();
    }

    // 动态删除单一规则
    public void removeRule(String packageName, String ruleName) {
        if (kbase.getRule(packageName, ruleName) != null) {
            kbase.removeRule(packageName, ruleName);
            System.out.println("remove rule: " + packageName + "." + ruleName);
        } else {
            System.out.println("no rule: " + packageName + "." + ruleName);
        }
    }

    // 执行登录规则
    public String execute(final String username, final int orders, final int buyMoney) {
        if (null == kieSession) {
            return "Please add a rule or rule packages first.";
        }
        LoginEvent event = new LoginEvent(UUID.randomUUID().toString().replace("-", ""),
                                          username,"", 0, orders, buyMoney,
                                          System.currentTimeMillis(),// System.currentTimeMillis() - 86400 * 1000, 这里可以插入上次登出时间，满足上网时长大于24小时的条件
                                          System.currentTimeMillis());
        // 执行登录规则
        kieSession.insert(event);
        kieSession.fireAllRules();
        return "OK";
    }
}
