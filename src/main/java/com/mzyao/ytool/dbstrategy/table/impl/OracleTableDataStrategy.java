package com.mzyao.ytool.dbstrategy.table.impl;

import com.mzyao.ytool.dbstrategy.table.DbTableDataStrategy;
import com.mzyao.ytool.entity.vo.TableInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class OracleTableDataStrategy implements DbTableDataStrategy {


    @Override
    public List<TableInfo> listTables(Connection connection, String schema) {
        List<TableInfo> tables = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, schema.toUpperCase(), null, new String[]{"TABLE"});
            while (rs.next()) {
                TableInfo table = new TableInfo();
                table.setTableName(rs.getString("TABLE_NAME"));
                table.setTableComment(rs.getString("REMARKS"));
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tables;
    }
}
