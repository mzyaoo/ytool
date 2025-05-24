package com.mzyao.ytool.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mzyao.ytool.dbstrategy.column.DbColumnDataStrategy;
import com.mzyao.ytool.dbstrategy.column.DbColumnStrategyFactory;
import com.mzyao.ytool.dbstrategy.table.DbTableDataStrategy;
import com.mzyao.ytool.dbstrategy.table.DbTableStrategyFactory;
import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.dto.GenerateConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.entity.vo.TableInfo;
import com.mzyao.ytool.service.GenerateCodeService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    @Resource
    private Configuration freemarkerConfiguration;

    @Override
    public List<TableInfo> getTables(DbConfigRequest config) throws Exception {
        // 加载驱动
        Class.forName(config.getDriverClassName());
        Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());

        DbTableDataStrategy dbTableDataStrategy = DbTableStrategyFactory.getStrategy(config.getDbType());
        return dbTableDataStrategy.listTables(connection, connection.getSchema());

    }


    @Override
    public List<ColumnInfo> tableColumnInfos(DbConfigRequest config, String tableName) throws Exception {
        DbColumnDataStrategy columnDataStrategy = DbColumnStrategyFactory.getStrategy(config.getDbType());
        return columnDataStrategy.listTables(config, tableName);
    }

    @Override
    public Map<String, ByteArrayOutputStream> generateCodeFiles(CodeGenerateRequest request) throws Exception {
        Map<String, ByteArrayOutputStream> fileMap = new HashMap<>();
        GenerateConfigRequest generateConfigRequest = request.getGenerateConfigRequest();
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(request.getTableName()));
        // Java 实体类\
        String packagePath = generateConfigRequest.getPackagePath()
                + "/" + generateConfigRequest.getEntityName() + "/" + className + ".java";
        Map<String, Object> model = new HashMap<>();
        model.put("enableLombok", generateConfigRequest.getOpenLombok());
        model.put("enableSwagger", generateConfigRequest.getOpenSwagger());
        String packageName = generateConfigRequest.getPackagePath()
                + "/" + generateConfigRequest.getEntityName();
        model.put("className", className);
        model.put("fields", request.getColumns());
        model.put("package", packageName.replaceAll("/", "."));
        ByteArrayOutputStream entityStream = renderTemplate("/entity/Entity.java.ftl", model);
        fileMap.put(packagePath, entityStream);

        return fileMap;
    }

    private ByteArrayOutputStream renderTemplate(String templateName, Map<String, Object> model) throws Exception {
        Template template = freemarkerConfiguration.getTemplate(templateName);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); OutputStreamWriter writer = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
            template.process(model, writer);
            writer.flush();
            return baos;
        }
    }


}
