package com.mzyao.ytool.dbstrategy.column;

import com.mzyao.ytool.dbstrategy.column.impl.MySQLTableColumnStrategy;
import com.mzyao.ytool.dbstrategy.column.impl.OracleTableColumnStrategy;
import com.mzyao.ytool.dbstrategy.table.DbTableDataStrategy;
import com.mzyao.ytool.dbstrategy.table.impl.MySQLTableDataStrategy;
import com.mzyao.ytool.dbstrategy.table.impl.OracleTableDataStrategy;

public class DbColumnStrategyFactory {

    public static DbColumnDataStrategy getStrategy(String dbType) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MySQLTableColumnStrategy();
            case "oracle":
                return new OracleTableColumnStrategy();
            default:
                throw new IllegalArgumentException("不支持的数据库类型: " + dbType);
        }
    }

}
