package com.mzyao.ytool.service.impl;

import com.mzyao.ytool.dbstrategy.column.DbColumnDataStrategy;
import com.mzyao.ytool.dbstrategy.column.DbColumnStrategyFactory;
import com.mzyao.ytool.dbstrategy.table.DbTableDataStrategy;
import com.mzyao.ytool.dbstrategy.table.DbTableStrategyFactory;
import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.entity.vo.TableInfo;
import com.mzyao.ytool.service.GenerateCodeService;
import com.mzyao.ytool.template.AbstractCodeGenerator;
import com.mzyao.ytool.template.CodeGenDispatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    @Resource
    private CodeGenDispatcher codeGenDispatcher;

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
        AbstractCodeGenerator abstractCodeGenerator = codeGenDispatcher.getStrategy("mybatis");
        return abstractCodeGenerator.generate(request);
    }
}
