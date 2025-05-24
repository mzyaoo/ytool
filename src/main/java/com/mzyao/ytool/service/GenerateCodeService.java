package com.mzyao.ytool.service;

import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.entity.vo.TableInfo;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface GenerateCodeService {

    /**
     * 获取表数据
     *
     * @param config
     * @return
     */
    List<TableInfo> getTables(DbConfigRequest config) throws Exception;

    /**
     * 获取表结构
     *
     * @param config
     * @param tableName
     * @return
     */
    List<ColumnInfo> tableColumnInfos(DbConfigRequest config, String tableName) throws Exception;

    /**
     * 代码生成
     *
     * @param request
     * @return
     * @throws Exception
     */
    Map<String, ByteArrayOutputStream> generateCodeFiles(CodeGenerateRequest request) throws Exception;
}
