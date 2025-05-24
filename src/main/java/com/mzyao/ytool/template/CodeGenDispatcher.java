package com.mzyao.ytool.template;

import com.mzyao.ytool.template.impl.JapCodeGenerator;
import com.mzyao.ytool.template.impl.MyBatisCodeGenerator;
import com.mzyao.ytool.template.impl.MybatisPlusCodeGenerator;
import freemarker.template.Configuration;

import javax.annotation.Resource;

@org.springframework.context.annotation.Configuration
public class CodeGenDispatcher {

    @Resource
    private Configuration freemarkerConfiguration;

    public AbstractCodeGenerator getStrategy(String type) {
        switch (type) {
            case "mybatis":
                return new MyBatisCodeGenerator(freemarkerConfiguration);
            case "mybatis-plus":
                return new MybatisPlusCodeGenerator(freemarkerConfiguration);
            case "jpa":
                return new JapCodeGenerator(freemarkerConfiguration);
            default:
                throw new IllegalArgumentException("不支持的类型: " + type);
        }
    }
}
