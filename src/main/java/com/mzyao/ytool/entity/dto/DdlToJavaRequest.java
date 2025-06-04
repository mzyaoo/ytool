package com.mzyao.ytool.entity.dto;

import lombok.Data;

@Data
public class DdlToJavaRequest {

    /**
     * ddl生成语句
     */
    private String ddlText;

    /**
     * 代码生成基础配置
     */
    private GenerateConfigRequest generateConfigRequest;

}
