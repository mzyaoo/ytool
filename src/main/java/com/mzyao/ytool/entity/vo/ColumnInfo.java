package com.mzyao.ytool.entity.vo;

import lombok.Data;

@Data
public class ColumnInfo {

    private String javaName;

    private String columnName;       // 列名

    private String dataType;         // 数据类型

    private String columnComment;    // 注释（可能为空）

    private String javaType;    // java类型

    private boolean primaryKey;      // 是否主键

    private boolean nullable;        // 是否可为空
}
