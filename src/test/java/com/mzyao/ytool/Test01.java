package com.mzyao.ytool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) throws IOException, TemplateException {
        // Step 1: 创建 FreeMarker 配置实例
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");

        // Step 2: 加载模板
        Template template = cfg.getTemplate("controller.ftl");

        // Step 3: 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("package", "com.example.demo");
        dataModel.put("className", "User"); // 实体类名
        dataModel.put("idType", "Long");    // ID 类型，如 Long、Integer、String

        // Step 4: 合并模板与数据模型
        Writer out = new FileWriter("output/UserController.java");
        template.process(dataModel, out);
        out.close();

        System.out.println("Controller 生成成功！");
    }
}
