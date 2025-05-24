package com.mzyao.ytool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class YtoolApplicationTests {

    @Resource
    private Configuration configuration;

    @Test
    void contextLoads() throws IOException, TemplateException {
        Template template = configuration.getTemplate("/freemarker/controller/Controller.java.ftl");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("package", "com.example.demo");
        dataModel.put("className", "User");
        dataModel.put("idType", "Long");

        try (Writer out = new FileWriter("/Users/imzyao/Downloads/genJava/UserController.java")) {
            template.process(dataModel, out);
        }

        System.out.println("UserController.java 生成成功");
    }

}
