package com.mzyao.ytool.dbstrategy.column.impl;

import com.mzyao.ytool.dbstrategy.column.DbColumnDataStrategy;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OracleTableColumnStrategy implements DbColumnDataStrategy {


    @Override
    public boolean supports(String dbType) {
        return false;
    }

    @Override
    public List<ColumnInfo> listTables(DbConfigRequest config, String tableName) {
        return Collections.emptyList();
    }
}
