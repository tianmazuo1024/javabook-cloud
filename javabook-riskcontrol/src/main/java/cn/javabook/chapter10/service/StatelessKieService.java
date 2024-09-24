package cn.javabook.chapter10.service;

import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.drools.kiesession.rulebase.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;
import java.io.File;

/**
 * 无状态的kieSession
 *
 */
@Service
public class StatelessKieService {
    // 无状态会话StatelessKieSession
    private StatelessKieSession statelessKieSession;

    // 规则库
    private final InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

    // 知识库构建器
    private final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

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
                // 由规则库创建无状态会话
                statelessKieSession = kbase.newStatelessKieSession();
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
        // 由规则库创建无状态会话
        statelessKieSession = kbase.newStatelessKieSession();
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
    public String execute(Object object) {
        if (null == statelessKieSession) {
            return "Please add a rule or rule packages first.";
        }
        statelessKieSession.execute(object);
        return "OK";
    }
}
