package com.mzyao.ytool.template.impl;

import cn.hutool.core.util.StrUtil;
import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.GenerateConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.enums.SwitchInfo;
import com.mzyao.ytool.template.AbstractCodeGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MyBatisCodeGenerator extends AbstractCodeGenerator {

    public MyBatisCodeGenerator(Configuration freemarker) {
        super(freemarker);
    }

    /**
     * 具体的mapper层和service层代码
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    protected Map<String, ByteArrayOutputStream> generateServiceAndDao(CodeGenerateRequest request) throws Exception {

        Map<String, ByteArrayOutputStream> fileMap = new HashMap<>();


        fileMap.put("", mapper(request));
        fileMap.put("", service(request));

        return fileMap;
    }

    private ByteArrayOutputStream mapper(CodeGenerateRequest request) throws IOException, TemplateException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Template mapperJavaTemplate = freemarker.getTemplate("/mybatis/Mapper.java.ftl");

        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName()));

        Map<String, Object> model = new HashMap<>();
        model.put("enableLombok", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenLombok()));
        model.put("enableSwagger", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenSwagger()));
        String packageName = generateConfigRequest.getPackagePath() + "/" + generateConfigRequest.getMapperName();
        model.put("className", className);
        model.put("fields", request.getColumns());
        model.put("classComment", "");

        // 所有的字段信息
        List<ColumnInfo> columns = request.getColumns();

        Optional<ColumnInfo> columnInfo = columns.stream().filter(ColumnInfo::isPrimaryKey).findFirst();
        columnInfo.ifPresent(info -> model.put("idType", info.getJavaType()));

        model.put("package", packageName.replaceAll("/", "."));

        try (Writer writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            mapperJavaTemplate.process(model, writer);
        }
        return byteArrayOutputStream;
    }


    private ByteArrayOutputStream service(CodeGenerateRequest request) throws IOException, TemplateException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Template template = freemarker.getTemplate("/mybatis/service/Service.java.ftl");

        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName()));

        Map<String, Object> model = new HashMap<>();
        model.put("enableLombok", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenLombok()));
        model.put("enableSwagger", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenSwagger()));
        String packageName = generateConfigRequest.getPackagePath() + "/" + generateConfigRequest.getMapperName();
        model.put("className", className);
        model.put("fields", request.getColumns());
        model.put("classComment", "");

        // 所有的字段信息
        List<ColumnInfo> columns = request.getColumns();

        Optional<ColumnInfo> columnInfo = columns.stream().filter(ColumnInfo::isPrimaryKey).findFirst();
        columnInfo.ifPresent(info -> model.put("idType", info.getJavaType()));

        model.put("package", packageName.replaceAll("/", "."));

        try (Writer writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            template.process(model, writer);
        }
        return byteArrayOutputStream;
    }

}
