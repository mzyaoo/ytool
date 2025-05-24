package com.mzyao.ytool.template.impl;

import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.template.AbstractCodeGenerator;
import freemarker.template.Configuration;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Map;

public class MyBatisCodeGenerator extends AbstractCodeGenerator {

    public MyBatisCodeGenerator(Configuration freemarker) {
        super(freemarker);
    }

    @Override
    protected Map<String, ByteArrayOutputStream> generateServiceAndDao(CodeGenerateRequest request) throws Exception {
        return Collections.emptyMap();
    }

}
