package com.mzyao.ytool.dbstrategy.table;

import com.mzyao.ytool.dbstrategy.table.impl.MySQLTableDataStrategy;
import com.mzyao.ytool.dbstrategy.table.impl.OracleTableDataStrategy;

public class DbTableStrategyFactory {

    public static DbTableDataStrategy getStrategy(String dbType) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MySQLTableDataStrategy();
            case "oracle":
                return new OracleTableDataStrategy();
            default:
                throw new IllegalArgumentException("不支持的数据库类型: " + dbType);
        }
    }

}
