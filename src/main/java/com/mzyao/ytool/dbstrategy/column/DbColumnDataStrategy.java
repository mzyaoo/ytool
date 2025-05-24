package com.mzyao.ytool.dbstrategy.column;

import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;

import java.util.List;

public interface DbColumnDataStrategy {

    /**
     * 是否支持当前数据库类型
     */
    boolean supports(String dbType);

    /**
     * 获取字段信息
     */
    List<ColumnInfo> listTables(DbConfigRequest config, String tableName) throws Exception;

}
