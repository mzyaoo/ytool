package com.mzyao.ytool.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DdlToJavaVo {

    // 包路径（绝对路径）
    private String packagePath;

    // 对应 java 文件代码
    private String javaCode;

}
