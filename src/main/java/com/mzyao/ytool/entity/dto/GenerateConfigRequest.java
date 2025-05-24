package com.mzyao.ytool.entity.dto;

import lombok.Data;

@Data
public class GenerateConfigRequest {

    private String packagePath;

    private String openLombok;

    private String openSwagger;

    private String dal;

    private String entityName;

    private String mapperName;

    private String serviceName;

    private String controllerName;

}
