package com.mzyao.ytool.entity.dto;

import lombok.Data;

/**
 * 数据库连接参数信息
 */
@Data
public class DbConfigRequest {

    // 用户名
    private String username;

    // 密码
    private String password;

    /**
     * 连接地址
     * oracle : jdbc:oracle:thin:@//localhost:1521/ORCL
     * mysql : jdbc:mysql://127.0.0.1:3306/my-db
     */
    private String url;

    /**
     * 连接驱动
     * oracle : oracle.jdbc.OracleDriver
     * mysql8.x : com.mysql.cj.jdbc.Driver
     */
    private String driverClassName;

    // 数据库类型
    private String dbType;

}
