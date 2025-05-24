package com.mzyao.ytool.utils;

import java.util.HashMap;
import java.util.Map;

public class MysqlToJavaType {

    private static final Map<String, String> TYPE_MAP = new HashMap<>();

    static {
        // 数值类型
        TYPE_MAP.put("tinyint", "Integer");
        TYPE_MAP.put("smallint", "Integer");
        TYPE_MAP.put("mediumint", "Integer");
        TYPE_MAP.put("int", "Integer");
        TYPE_MAP.put("integer", "Integer");
        TYPE_MAP.put("bigint", "Long");
        TYPE_MAP.put("float", "Float");
        TYPE_MAP.put("double", "Double");
        TYPE_MAP.put("decimal", "BigDecimal");
        TYPE_MAP.put("numeric", "BigDecimal");

        // 字符类型
        TYPE_MAP.put("char", "String");
        TYPE_MAP.put("varchar", "String");
        TYPE_MAP.put("text", "String");
        TYPE_MAP.put("tinytext", "String");
        TYPE_MAP.put("mediumtext", "String");
        TYPE_MAP.put("longtext", "String");

        // 日期时间类型
        TYPE_MAP.put("date", "LocalDate");
        TYPE_MAP.put("datetime", "LocalDateTime");
        TYPE_MAP.put("timestamp", "LocalDateTime");
        TYPE_MAP.put("time", "LocalTime");
        TYPE_MAP.put("year", "Integer");

        // 二进制类型
        TYPE_MAP.put("blob", "byte[]");
        TYPE_MAP.put("binary", "byte[]");
        TYPE_MAP.put("varbinary", "byte[]");
    }

    public static String convert(String mysqlType) {
        if (mysqlType == null) {
            return "Object";
        }
        mysqlType = mysqlType.toLowerCase();
        // 去除括号，例如 varchar(255)
        if (mysqlType.contains("(")) {
            mysqlType = mysqlType.substring(0, mysqlType.indexOf("("));
        }
        return TYPE_MAP.getOrDefault(mysqlType, "Object");
    }
}
