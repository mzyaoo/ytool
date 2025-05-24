package com.mzyao.ytool.template;

import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.GenerateConfigRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCodeGenerator {

    protected final Configuration freemarker;

    public AbstractCodeGenerator(Configuration freemarker) {
        this.freemarker = freemarker;
    }

    public Map<String, ByteArrayOutputStream> generate(CodeGenerateRequest request) throws Exception {
        Map<String, ByteArrayOutputStream> fileMap = new HashMap<>();

        // 通用部分
        fileMap.put(buildPath(request, "entity"), render("entity.java.ftl", request));
        fileMap.put(buildPath(request, "controller"), render("controller.java.ftl", request));

        // 不同部分（交给子类实现）
        fileMap.putAll(generateServiceAndDao(request));

        return fileMap;
    }

    protected abstract Map<String, ByteArrayOutputStream> generateServiceAndDao(CodeGenerateRequest request) throws Exception;

    protected ByteArrayOutputStream render(String templatePath, CodeGenerateRequest request) throws Exception {
        Template template = freemarker.getTemplate(templatePath);
        Map<String, Object> model = new HashMap<>();
        model.put("request", request);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (Writer writer = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
            template.process(model, writer);
        }
        return baos;
    }

    protected String buildPath(CodeGenerateRequest request, String type) {
        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String base = generateConfigRequest.getPackagePath();
        switch (type) {
            case "entity":
                return base + "/" + generateConfigRequest.getEntityName() + "/" + request.getTableName() + ".java";
            case "controller":
                return base + "/" + generateConfigRequest.getControllerName() + "/" + request.getTableName() + "Controller.java";
            default:
                return base + "/" + type + "/" + request.getTableName() + ".java";
        }
    }
}

