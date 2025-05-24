package com.mzyao.ytool.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableInfo {

    private String tableName;

    private String tableComment;

    private List<ColumnInfo> columns = new ArrayList<>();

}
