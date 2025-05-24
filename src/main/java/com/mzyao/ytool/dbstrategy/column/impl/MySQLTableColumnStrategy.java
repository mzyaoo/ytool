package com.mzyao.ytool.dbstrategy.column.impl;

import cn.hutool.core.util.StrUtil;
import com.mzyao.ytool.dbstrategy.column.DbColumnDataStrategy;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.utils.MysqlToJavaType;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

@Service
public class MySQLTableColumnStrategy implements DbColumnDataStrategy {


    @Override
    public boolean supports(String dbType) {
        return "mysql".equalsIgnoreCase(dbType);
    }

    /**
     * @param config
     * @param tableName
     * @return
     * @throws Exception
     */
    @Override
    public List<ColumnInfo> listTables(DbConfigRequest config, String tableName) throws Exception {
        List<ColumnInfo> columnList = new ArrayList<>();

        Class.forName(config.getDriverClassName());
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            DatabaseMetaData metaData = conn.getMetaData();
            String catalog = conn.getCatalog();

            // 获取主键信息
            Set<String> primaryKeys = new HashSet<>();
            try (ResultSet pkRs = metaData.getPrimaryKeys(catalog, null, tableName)) {
                while (pkRs.next()) {
                    primaryKeys.add(pkRs.getString("COLUMN_NAME"));
                }
            }

            // 获取列信息
            try (ResultSet rs = metaData.getColumns(catalog, null, tableName, "%")) {
                while (rs.next()) {
                    ColumnInfo col = new ColumnInfo();
                    String columnName = rs.getString("COLUMN_NAME");
                    col.setColumnName(columnName);
                    String dataType = rs.getString("TYPE_NAME");
                    col.setDataType(dataType);
                    col.setJavaName(StrUtil.toCamelCase(columnName));
                    col.setJavaType(MysqlToJavaType.convert(dataType));
                    col.setNullable("YES".equalsIgnoreCase(rs.getString("IS_NULLABLE")));
                    col.setPrimaryKey(primaryKeys.contains(rs.getString("COLUMN_NAME")));
                    col.setColumnComment(rs.getString("REMARKS"));
                    columnList.add(col);
                }
            }
        }

        return columnList;
    }

}
