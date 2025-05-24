package com.mzyao.ytool.dbstrategy.table;

import com.mzyao.ytool.entity.vo.TableInfo;

import java.sql.Connection;
import java.util.List;

public interface DbTableDataStrategy {


    List<TableInfo> listTables(Connection connection, String schema);



}
