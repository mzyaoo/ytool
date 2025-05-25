package com.mzyao.ytool.template;

import cn.hutool.core.util.StrUtil;
import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.GenerateConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.enums.SwitchInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractCodeGenerator {

    protected final Configuration freemarker;

    public AbstractCodeGenerator(Configuration freemarker) {
        this.freemarker = freemarker;
    }

    public Map<String, ByteArrayOutputStream> generate(CodeGenerateRequest request) throws Exception {
        Map<String, ByteArrayOutputStream> fileMap = new HashMap<>();
        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        // 通用部分
        fileMap.put(buildPath(request, "entity"), render(generateConfigRequest.getEntityName(), "/entity/Entity.java.ftl", request));
        fileMap.put(buildPath(request, "controller"), render(generateConfigRequest.getControllerName(), "/controller/Controller.java.ftl", request));

        // 不同部分（交给子类实现）
        fileMap.putAll(generateServiceAndDao(request));

        return fileMap;
    }

    /**
     * mapper/service 模版代码逻辑
     *
     * @param request
     * @return
     * @throws Exception
     */
    protected abstract Map<String, ByteArrayOutputStream> generateServiceAndDao(CodeGenerateRequest request) throws Exception;

    protected ByteArrayOutputStream render(String defaultPackageName, String templatePath, CodeGenerateRequest request) throws Exception {
        Template template = freemarker.getTemplate(templatePath);

        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName()));

        Map<String, Object> model = new HashMap<>();
        model.put("enableLombok", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenLombok()));
        model.put("enableSwagger", StrUtil.equals(SwitchInfo.YES.getLabel(), generateConfigRequest.getOpenSwagger()));
        String packageName = generateConfigRequest.getPackagePath() + "/" + defaultPackageName;
        model.put("className", className);
        model.put("fields", request.getColumns());
        model.put("classComment", "");

        // 所有的字段信息
        List<ColumnInfo> columns = request.getColumns();

        Optional<ColumnInfo> columnInfo = columns.stream().filter(ColumnInfo::isPrimaryKey).findFirst();
        columnInfo.ifPresent(info -> model.put("idType", info.getJavaType()));

        model.put("package", packageName.replaceAll("/", "."));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (Writer writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            template.process(model, writer);
        }
        return byteArrayOutputStream;
    }

    protected String buildPath(CodeGenerateRequest request, String type) {
        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String base = generateConfigRequest.getPackagePath();
        switch (type) {
            case "entity":
                return base + "/" + generateConfigRequest.getEntityName() + "/" + StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName())) + ".java";
            case "controller":
                return base + "/" + generateConfigRequest.getControllerName() + "/" + StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName())) + "Controller.java";
            default:
                return base + "/" + type + "/" + StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName())) + ".java";
        }
    }
}

