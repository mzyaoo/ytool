package com.mzyao.ytool.entity.dto;

import com.mzyao.ytool.entity.vo.ColumnInfo;
import lombok.Data;

import java.util.List;

@Data
public class CodeGenerateRequest {

    private DbConfigRequest dbConfig;

    private GenerateConfigRequest generateConfigRequest;

    // 名称
    private String tableName;

    // 用户最终保留的字段
    private List<ColumnInfo> columns;

}
