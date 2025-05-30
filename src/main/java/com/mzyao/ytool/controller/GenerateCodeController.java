package com.mzyao.ytool.controller;

import com.mzyao.ytool.aop.annotations.Log;
import com.mzyao.ytool.entity.dto.CodeGenerateRequest;
import com.mzyao.ytool.entity.dto.DbConfigRequest;
import com.mzyao.ytool.entity.vo.ColumnInfo;
import com.mzyao.ytool.entity.vo.TableInfo;
import com.mzyao.ytool.enums.JavaType;
import com.mzyao.ytool.service.GenerateCodeService;
import com.mzyao.ytool.utils.ZipUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("generate")
public class GenerateCodeController {

    @Resource
    private GenerateCodeService generateCodeService;

    /**
     * 获取所有的表结构
     *
     * @param config
     * @return
     */
    @Log
    @GetMapping("table/list")
    public List<TableInfo> getTables(DbConfigRequest config) throws Exception {
        return generateCodeService.getTables(config);
    }


    /**
     * 获取表信息
     *
     * @param config
     * @param tableName
     * @return
     */
    @Log
    @GetMapping("table/columns")
    public List<ColumnInfo> listColumns(DbConfigRequest config,
                                        String tableName) throws Exception {
        return generateCodeService.tableColumnInfos(config, tableName);
    }

    /**
     * 代码生成
     *
     * @return
     */
    @Log
    @PostMapping("code")
    public void generateCode(@RequestBody CodeGenerateRequest codeGenerateRequest,
                             HttpServletResponse response,
                             HttpServletRequest request) throws Exception {
        Map<String, ByteArrayOutputStream> outputStreamMap = generateCodeService.generateCodeFiles(codeGenerateRequest);
        ZipUtil.downloadFileForZip(response, outputStreamMap, codeGenerateRequest.getTableName() + ".zip");
    }

    /**
     * 获取Java类型列表
     *
     * @return
     */
    @Log
    @GetMapping("tool/java/type")
    public List<String> getJavaTypeList() {
        return JavaType.getTypeList();
    }

}
