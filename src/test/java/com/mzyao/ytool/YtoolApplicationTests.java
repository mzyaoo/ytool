package com.mzyao.ytool;

import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.template.AbstractCodeGenerator;
import com.mzyao.ytool.template.CodeGenDispatcher;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class YtoolApplicationTests {

    @Resource
    private Configuration configuration;

    @Resource
    private CodeGenDispatcher codeGenDispatcher;

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

    @Test
    void test01() throws Exception {
        AbstractCodeGenerator mybatis = codeGenDispatcher.getStrategy("mybatis");
        Map<String, ByteArrayOutputStream> generate = mybatis.generate(new CodeGenerateRequest());
        System.out.println(generate);
    }

}
